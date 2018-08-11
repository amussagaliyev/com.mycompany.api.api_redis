package com.mycompany.api.jedis;

import redis.clients.jedis.JedisPool;

public class RedisQueuePublisher implements AutoCloseable
{
	private JedisPool jedisPool;
	
	public RedisQueuePublisher(IRedisConfig cfg)
	{
		this.jedisPool = new JedisPoolFactory(cfg).getJedisPool();
	}
	
	public void addToQueue(String queueName, String value)
	{
		jedisPool.getResource().rpush(queueName, value);
	}

	@Override
	public void close() throws Exception
	{
		jedisPool.close();
		
	}
	
}
