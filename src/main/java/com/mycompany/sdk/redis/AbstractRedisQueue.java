package com.mycompany.sdk.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;

public abstract class AbstractRedisQueue implements RedisQueue
{

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	private RedisTemplate<String, Integer> redisTemplate;
	private ChannelTopic channelTopic;

	abstract protected String getTopicName();

	public RedisTemplate<String, Integer> getRedisTemplate()
	{
		if (redisTemplate == null)
		{
			redisTemplate = new RedisTemplate<String, Integer>();
			redisTemplate.setConnectionFactory(redisConnectionFactory);
			redisTemplate.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
			redisTemplate.afterPropertiesSet();
		}
		
		return redisTemplate;
	}

	public void publish(String message)
	{
		getRedisTemplate().convertAndSend(getChannelTopic().getTopic(), message);
		log.info("Message pushed to the queue " + getTopicName());
	}

	public ChannelTopic getChannelTopic()
	{
		if (channelTopic == null)
			channelTopic = new ChannelTopic(getTopicName());
		return channelTopic;
	}
}