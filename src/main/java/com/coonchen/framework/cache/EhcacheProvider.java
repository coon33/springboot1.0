package com.coonchen.framework.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.concurrent.TimeUnit;

public class EhcacheProvider {
    public CacheManager cacheManager;

    public EhcacheProvider(String location_path){
        if(cacheManager==null)
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .with(CacheManagerBuilder.persistence(location_path)).build(true);
    }

    public <K, V> Cache<K,V> createCacheManager(CacheConfigurationBean<K,V> cacheConfigurationBean){
        if(cacheManager.getStatus()== Status.UNINITIALIZED){
            cacheManager.init();
        }
        Cache<K,V> cache= cacheManager.getCache(cacheConfigurationBean.getCacheName(), cacheConfigurationBean.getClassKey(), cacheConfigurationBean.getClassVal());
        if(cache==null){
            ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.newResourcePoolsBuilder();
            resourcePoolsBuilder = resourcePoolsBuilder.heap(cacheConfigurationBean.getHeapSize(), MemoryUnit.KB);
            if(cacheConfigurationBean.getDiskSzie()>0L)
                resourcePoolsBuilder = resourcePoolsBuilder.disk(cacheConfigurationBean.getDiskSzie(),MemoryUnit.KB,true);

            CacheConfigurationBuilder<K,V> cacheConfigurationBuilder = CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(cacheConfigurationBean.getClassKey(), cacheConfigurationBean.getClassVal(),resourcePoolsBuilder);

            if(cacheConfigurationBean.getTimeToLiveExpirySecond()>0L)
                cacheConfigurationBuilder = cacheConfigurationBuilder.withExpiry(Expirations.timeToLiveExpiration(Duration.of(cacheConfigurationBean.getTimeToLiveExpirySecond(), TimeUnit.SECONDS)));

            cache = cacheManager.createCache(cacheConfigurationBean.getCacheName(), cacheConfigurationBuilder.build());
        }

        return cache;
    }

    public void closeCacheManager(){
        if(cacheManager!=null) {
            cacheManager.close();
            cacheManager=null;
        }
    }
}
