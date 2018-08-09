package com.mycompany.api.jedis;

import java.time.Duration;

public class SimpleRedisConfig implements IRedisConfig
{

	@Override
	public int getMaxTotal()
	{
		return getIntProp("redis_max_total", 128);
	}

	@Override
	public int getMaxIdle()
	{
		return getIntProp("redis_max_idle", 128);
	}

	@Override
	public int getMinIdle()
	{
		return getIntProp("redis_min_idle", 16);
	}

	@Override
	public boolean isTestOnBorrow()
	{
		return getBoolProp("redis_test_on_borrow", true);
	}

	@Override
	public boolean isTestOnReturn()
	{
		return getBoolProp("redis_test_on_return", true);
	}

	@Override
	public boolean isTestWhileIdle()
	{
		return getBoolProp("redis_test_while_idle", true);
	}

	@Override
	public long getMinEvictableIdleTimeMillis()
	{
		return Duration.ofSeconds(getIntProp("redis_min_evictable_idle_time_sec", 60)).toMillis();
	}

	@Override
	public long getTimeBetweenEvictionRunsMillis()
	{
		return Duration.ofSeconds(getIntProp("redis_time_between_eviction_runs_sec", 30)).toMillis();
	}

	@Override
	public int getNumTestsPerEvictionRun()
	{
		return getIntProp("redis_num_tests_per_eviction_run", 3);
	}

	@Override
	public boolean isBlockWhenExhausted()
	{
		return getBoolProp("redis_block_when_exhausted", false);
	}

	@Override
	public String getHost()
	{
		return getProp("redis_host", "localhost");
	}

	@Override
	public int getPort()
	{
		return getIntProp("redis_port", 6379);
	}
	
	private String getProp(String propName, String defaultValue)
	{
		return System.getProperty(propName, defaultValue);
	}

	private int getIntProp(String propName, int defaultValue)
	{
		return Integer.parseInt(getProp(propName, String.valueOf(defaultValue)));
	}

	private boolean getBoolProp(String propName, boolean defaultValue)
	{
		return Boolean.parseBoolean(getProp(propName, String.valueOf(defaultValue)));
	}

}
