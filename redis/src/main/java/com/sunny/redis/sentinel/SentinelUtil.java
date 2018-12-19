package com.sunny.redis.sentinel;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JiaChang
 * @date 2018/12/18
 */
public class SentinelUtil {

    private static final Set SENTINELS = new HashSet();
    private static String IP = "";

    static {

        //服务IP
        IP = "172.16.150.169";
        // Sentine端口
        SENTINELS.add(new HostAndPort(IP, 7505).toString());
        SENTINELS.add(new HostAndPort(IP, 7506).toString());
        SENTINELS.add(new HostAndPort(IP, 7507).toString());

    }

    public static void main(String[] args) {
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", SENTINELS);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
        Jedis master = sentinelPool.getResource();
        master.auth("317hu@2016");
        master.set("username","张三");
        sentinelPool.returnResource(master);
        Jedis master2 = sentinelPool.getResource();
        master2.auth("317hu@2016");
        String value = master2.get("username");
        System.out.println("username: " + value);
        master2.close();
        sentinelPool.destroy();
    }
}
