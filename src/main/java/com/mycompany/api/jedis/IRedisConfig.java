package com.mycompany.api.jedis;

public interface IRedisConfig
{
	public String getHost();
	public int getPort();
	public int getMaxTotal();
	public int getMaxIdle();
	public int getMinIdle();
	public boolean isTestOnBorrow();
	public boolean isTestOnReturn();
	public boolean isTestWhileIdle();
	public long getMinEvictableIdleTimeMillis();
	public long getTimeBetweenEvictionRunsMillis();
	public int getNumTestsPerEvictionRun();
	public boolean isBlockWhenExhausted();
}
