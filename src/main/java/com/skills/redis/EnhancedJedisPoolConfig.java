package com.skills.redis;

import redis.clients.jedis.JedisPoolConfig;

public class EnhancedJedisPoolConfig extends JedisPoolConfig {
    private int timeout=1000;
    public EnhancedJedisPoolConfig(){super();}

    public int getTimeout(){return this.timeout;}
    public void setTimeout(int timeout){
        this.timeout=timeout;
    }
}
