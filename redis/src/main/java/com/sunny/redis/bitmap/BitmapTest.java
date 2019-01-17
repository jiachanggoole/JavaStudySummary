package com.sunny.redis.bitmap;

import com.sunny.redis.BaseRedisUtil;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaChang
 * @date 2019/1/16
 */
public class BitmapTest {

    private static String KEY = "key_bitmap";
    private static String VALUE = "hello";

    public static void getsetbit(){
        System.out.println("\n ********* getsetbit开始  *********");
        Jedis jedis = BaseRedisUtil.getJedis();
        jedis.del(KEY);
        jedis.setbit(KEY,1,true);
        jedis.setbit(KEY,3,true);
        jedis.setbit(KEY,4,true);
        System.out.println(jedis.getbit(KEY,4));
        System.out.println("key 的值："+jedis.get(KEY));

        jedis.del(KEY);
        jedis.set(KEY,"peur");
        System.out.println(jedis.getbit(KEY,4));

        System.out.println(" ********* getsetbit结束  *********\n");
    }

    public static void bitcount() {
        Jedis jedis = BaseRedisUtil.getJedis();
        jedis.set(KEY, VALUE);

        System.out.println("************  bitcount 练习开始  ************");
        System.out.println("bitcount key所有1的位数:" + jedis.bitcount(KEY));
        System.out.println("bitcount 第一个字符中 1 的位数:" + jedis.bitcount(KEY, 0, 0));
        System.out.println("bitcount 前两个字符中 1 的位数:" + jedis.bitcount(KEY, 0, 1));
        System.out.println("************  bitcount 练习结束  ************\n");
    }


    public static void bitpos() {
        Jedis jedis = BaseRedisUtil.getJedis();
        jedis.set(KEY, VALUE);

        System.out.println("************  bitpos  练习开始  ************");
        System.out.println("bitpos key 第一个0位:" + jedis.bitpos(KEY, false));
        System.out.println("bitpos key 第一个1位:" + jedis.bitpos(KEY, true));
        System.out.println("bitcount 第二个字符开始，第一个1位:" + jedis.bitpos(KEY, true, new BitPosParams(1, 1)));
        System.out.println("bitcount 第三个字符开始，第一个1位:" + jedis.bitpos(KEY, true, new BitPosParams(2, 2)));
        System.out.println("************  bitpos  练习结束  ************");
    }


    /**
     * 登录场景测试
     */
    public static void signInOperation(){
        Jedis jedis = BaseRedisUtil.getJedis();
        String cacheKeyPre = "user_sign_in_";

        // 设置2018-01-10的签到情况
        for (int i = 5; i < 15; i++) {
            jedis.setbit(cacheKeyPre+"2018-01-10",i,true);
        }

        // 设置2018-01-11的签到情况
        for (int i = 1; i < 9; i++) {
            jedis.setbit(cacheKeyPre+"2018-01-11",i,true);
        }

        // 设置2018-01-12的签到情况
        for (int i = 1; i < 7; i++) {
            jedis.setbit(cacheKeyPre+"2018-01-12",i,true);
        }

        // 统计这三天都签过到的用户
        // BITOP 命令支持 AND 、 OR 、 NOT(非) 、 XOR(异或)
        jedis.bitop(BitOP.AND,"bitop_AND",cacheKeyPre+"2018-01-10",cacheKeyPre+"2018-01-11",cacheKeyPre+"2018-01-12");
        jedis.bitop(BitOP.OR,"bitop_OR",cacheKeyPre+"2018-01-10",cacheKeyPre+"2018-01-11",cacheKeyPre+"2018-01-12");

        System.out.println("这三天都签过到的用户数："+jedis.bitcount("bitop_AND"));
        System.out.println("这三天累计签过到的用户数："+jedis.bitcount("bitop_OR"));

    }


    public static void main(String[] args) {

/*        bitcount();

        bitpos();

        getsetbit();*/

        signInOperation();
    }

}
