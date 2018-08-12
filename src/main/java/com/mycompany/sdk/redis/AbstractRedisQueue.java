package com.mycompany.sdk.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractRedisQueue implements RedisQueue
{

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Autowired
	private RedisTemplate<String, Integer> redisTemplate;

	abstract public ChannelTopic getChannelTopic();

	public RedisTemplate<String, Integer> getRedisTemplate()
	{
		if (redisTemplate == null)
		{
			redisTemplate = new RedisTemplate<String, Integer>();
			redisTemplate.setConnectionFactory(jedisConnectionFactory);
			redisTemplate.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
		}
		return redisTemplate;
	}

	public void publish(String message)
	{
		getRedisTemplate().convertAndSend(getChannelTopic().getTopic(), message);
	}

}