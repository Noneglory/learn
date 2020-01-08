package com.skills.redis;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
    private EnhancedJedisPoolConfig enhancedJedisPoolConfig;
    private JedisCluster jedisCluster;
    private int connectionTimeout;
    private int soTimeout;
    private int maxRedirections;
    private String password;
    private Set<HostAndPort> jedisClusterNodes;
    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.jedisCluster!=null?this.jedisCluster.getClass():JedisCluster.class);
    }

    /**
     * 完成bean的初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if(jedisClusterNodes==null || jedisClusterNodes.size()==0){
            throw new NullPointerException("jedisClusterNodes is null");
        }
        if(password==null || " ".equals(password)){
            jedisCluster = new JedisCluster(jedisClusterNodes,connectionTimeout,soTimeout,maxRedirections,enhancedJedisPoolConfig);
        }
    }

    public EnhancedJedisPoolConfig getEnhancedJedisPoolConfig() {
        return enhancedJedisPoolConfig;
    }

    public void setEnhancedJedisPoolConfig(EnhancedJedisPoolConfig enhancedJedisPoolConfig) {
        this.enhancedJedisPoolConfig = enhancedJedisPoolConfig;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<HostAndPort> getJedisClusterNodes() {
        return jedisClusterNodes;
    }

    public void setJedisClusterNodes(Set<HostAndPort> jedisClusterNodes) {
        this.jedisClusterNodes = jedisClusterNodes;
    }
}
