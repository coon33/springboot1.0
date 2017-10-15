package com.coonchen.core.cache;

import com.coonchen.core.entity.User;
import com.coonchen.framework.cache.CacheConfigurationBean;
import com.coonchen.framework.cache.CacheManagerFactory;
import org.ehcache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    public static CacheManager cacheManager;

    private static Map<String, String> frontvariablecache = new ConcurrentHashMap<String, String>();// 前段变量
    private static Map<String, String> backstagecache = new ConcurrentHashMap<String, String>();// 后端变量

    static
    {
        CacheManagerFactory.getInstance().init("/home/coonchen/frame/springboot/ehcache","com.coonchen.core.cache.dir");
    }

    public synchronized static CacheManager getInstance(){
        if(cacheManager==null)
            cacheManager = new CacheManager();
        return cacheManager;
    }

    public String getBackVariable(String key){
        if(!backstagecache.isEmpty()) return backstagecache.get(key);
        return "";
    }

    public void setVariable(String key,String value,boolean isFront){
        if(isFront) frontvariablecache.put(key,value);
        else backstagecache.put(key,value);
    }
}
