package com.coonchen.framework.cache;


public class CacheConfigurationBean<K,V> {
	private String cacheName;
	private long heapSize;
	private long diskSzie;
	private long timeToLiveExpirySecond;
	private Class<K> classKey;
	private Class<V> classVal;

	public Class<K> getClassKey() {
		return classKey;
	}

	public void setClassKey(Class<K> classKey) {
		this.classKey = classKey;
	}

	public Class<V> getClassVal() {
		return classVal;
	}

	public void setClassVal(Class<V> classVal) {
		this.classVal = classVal;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public long getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(long heapSize) {
		this.heapSize = heapSize;
	}

	public long getDiskSzie() {
		return diskSzie;
	}

	public void setDiskSzie(long diskSzie) {
		this.diskSzie = diskSzie;
	}

	public long getTimeToLiveExpirySecond() {
		return timeToLiveExpirySecond;
	}

	public void setTimeToLiveExpirySecond(long timeToLiveExpirySecond) {
		this.timeToLiveExpirySecond = timeToLiveExpirySecond;
	}

}
