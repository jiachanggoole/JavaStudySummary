package com.sunny.redis.lock;

import com.sunny.redis.BaseRedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JiaChang
 * @date 2019/1/15
 */
public class RedisLockUtil {

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public static boolean commonLock(String lockKey,int expire) {

        Jedis jedis = BaseRedisUtil.getJedis();

        long expires = System.currentTimeMillis() + expire * 1000;

        // 锁到期时间
        String expiresStr = String.valueOf(expires);

        // 成功拿到锁
        if (jedis.setnx(lockKey, expiresStr) > 0) {
            // lock acquired
            BaseRedisUtil.recycleJedis(jedis);
            return true;
        }

        // redis里的时间
        String redisCurrentValueStr = jedis.get(lockKey);

        // redis里的时间过期了
        if (redisCurrentValueStr != null && Long.parseLong(redisCurrentValueStr) < System.currentTimeMillis()) {
            // 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
            // lock is expired

            expires = System.currentTimeMillis() + expire * 1000;
            expiresStr = String.valueOf(expires);

            //获取上一个锁到期时间，并设置现在的锁到期时间，
            // 这里如果并发的话  只有一个线程能拿到过期的那个时间 即：redisCurrentValueStr 拿到过期时间的那个就拿到了锁
            String oldValueStr = jedis.getSet(lockKey, expiresStr);
            if (oldValueStr != null && oldValueStr.equals(redisCurrentValueStr)) {
                //防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
                //[分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                // lock acquired
                BaseRedisUtil.recycleJedis(jedis);
                return true;
            }
        }
        BaseRedisUtil.recycleJedis(jedis);
        return false;
    }


    public static boolean goodLock(String lockKey,int expire,Long lockValue) {
        // jedis set 方法  key value nxxx(NX/XX) KEY不存在的时候设置/KEY存在的时候设置
        // expx(EX|PX)过期时间的单位 seconds/milliseconds expire 过期时间值
        Jedis jedis = BaseRedisUtil.getJedis();
        String result = jedis.set(lockKey,String.valueOf(lockValue),"NX","EX",expire);
        BaseRedisUtil.recycleJedis(jedis);
        return Objects.equals("OK",result);
    }


    public static boolean unlock(String lockKey,Long lockValue) {
        Jedis jedis = BaseRedisUtil.getJedis();
        String redisValue = jedis.get(lockKey);
        if(Objects.equals(redisValue,String.valueOf(lockValue))){
            jedis.del(lockKey);
            BaseRedisUtil.recycleJedis(jedis);
            return true;
        }
        BaseRedisUtil.recycleJedis(jedis);
        return false;
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Thread(new Runnable() {
                @Override
                public void run() {

                    String lockKey = "MyRedisLockUtil_lockKey";
                    Long lockValue = System.currentTimeMillis();
//                    if (RedisLockUtil.lock(lockKey,1)) {
                    if (RedisLockUtil.goodLock(lockKey,1,lockValue)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到了锁");
                            Thread.sleep(500);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            boolean result = RedisLockUtil.unlock(lockKey,lockValue);

                            System.out.println(Thread.currentThread().getName() + "开始释放锁，结果:"+result);
                        }
                    }else{
                        System.err.println(Thread.currentThread().getName() + "重复提交.....");
                    }
                }
            }));
        }
    }

}
