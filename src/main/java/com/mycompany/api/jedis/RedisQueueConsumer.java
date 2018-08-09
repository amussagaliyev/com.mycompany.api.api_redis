package com.mycompany.api.jedis;

import redis.clients.jedis.Jedis;

public class RedisQueueConsumer
{
	private static final Jedis jedis = new Jedis(System.getProperty("jedis_host"));
	
	public static String lpop(String queueName)
	{
		return jedis.lpop(queueName);
	}
}
