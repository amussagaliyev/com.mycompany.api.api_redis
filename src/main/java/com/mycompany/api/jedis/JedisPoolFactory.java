package com.mycompany.api.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolFactory
{
	
	private final JedisPool jedisPool;
	
	public JedisPoolFactory(IRedisConfig cfg)
	{
		JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(cfg.getMaxTotal());
	    poolConfig.setMaxIdle(cfg.getMaxIdle());
	    poolConfig.setMinIdle(cfg.getMinIdle());
	    poolConfig.setTestOnBorrow(cfg.isTestOnBorrow());
	    poolConfig.setTestOnReturn(cfg.isTestOnReturn());
	    poolConfig.setTestWhileIdle(cfg.isTestWhileIdle());
	    poolConfig.setMinEvictableIdleTimeMillis(cfg.getMinEvictableIdleTimeMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(cfg.getTimeBetweenEvictionRunsMillis());
	    poolConfig.setNumTestsPerEvictionRun(cfg.getNumTestsPerEvictionRun());
	    poolConfig.setBlockWhenExhausted(cfg.isBlockWhenExhausted());
		
	    this.jedisPool = new JedisPool(poolConfig, cfg.getHost(), cfg.getPort());
	}
	
	public JedisPool getJedisPool()
	{
		return jedisPool;
	}
	
}
