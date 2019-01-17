package com.sunny.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author JiaChang
 * @date 2019/1/16
 */
public class BaseRedisUtil {

    private static int MAX_TOTAL = 50;
    private static int MIN_IDLE = 50;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT_MILLIS = 3000;
    private static int CONNET_TIME_OUT = 3000;
    private static String REDIS_HOST = "172.16.150.169";
    private static int REDIS_PORT = 7501;
    private static String REDIS_PASSWORD = "317hu@2016";
    private static JedisPool JEDIS_POOL = null;
    /**
     * 当前Jedis池里面活着的数量
     */
    private static int ALIVE_JEDIS_NUM = MAX_TOTAL;
    static {
        init();
    }

    private static void init(){
        // jedis池优化 https://www.cnblogs.com/benwu/articles/8616141.html

        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例
        config.setMaxTotal(MAX_TOTAL);
        config.setMinIdle(MIN_IDLE);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
        config.setMaxIdle(MAX_IDLE);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
        //小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis((long)MAX_WAIT_MILLIS);
        //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        config.setTestOnBorrow(true);
        //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
        config.setTestOnReturn(true);
        JEDIS_POOL = new JedisPool(config, REDIS_HOST, REDIS_PORT, CONNET_TIME_OUT, REDIS_PASSWORD);
    }

    public synchronized static Jedis getJedis(){
        while (true){
            if(ALIVE_JEDIS_NUM <=0){
                try {
                    Thread.sleep(30);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ALIVE_JEDIS_NUM --;
            return JEDIS_POOL.getResource();
        }
    }

    public synchronized static boolean recycleJedis(Jedis jedis){
        jedis.close();
        ALIVE_JEDIS_NUM ++;
        return true;
    }

}
