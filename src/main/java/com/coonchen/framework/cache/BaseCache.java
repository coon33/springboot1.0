package com.coonchen.framework.cache;

import com.coonchen.core.entity.User;
import com.coonchen.framework.cache.CacheConfigurationBean;
import com.coonchen.framework.cache.CacheManagerFactory;
import org.ehcache.Cache;

public abstract class BaseCache {
    private CacheManagerFactory cacheManagerFactory = CacheManagerFactory.getInstance();
    public <K,V> Cache<K,V> createCache(CacheConfigurationBean<K,V> cacheConfigurationBean){
        return cacheManagerFactory.createCacheManager(cacheConfigurationBean);
    }
}
