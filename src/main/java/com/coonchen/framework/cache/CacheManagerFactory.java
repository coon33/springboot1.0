package com.coonchen.framework.cache;

import com.coonchen.framework.utils.ScannerUtil;
import org.ehcache.Cache;

import java.util.List;

public class CacheManagerFactory {
    private static CacheManagerFactory cacheFactory;
    public EhcacheProvider ehcacheProvider;

    public static synchronized CacheManagerFactory getInstance(){
        if(cacheFactory==null)
            cacheFactory = new CacheManagerFactory();
        return cacheFactory;
    }

    public void init(String location_path,String cache_path){
        ehcacheProvider = new EhcacheProvider(location_path);

        List<Class> classes = ScannerUtil.getClasses(cache_path);
        if(classes!=null && !classes.isEmpty()) {
            for (Class c : classes) {
                try {
                    if(((c.getModifiers()&1024)!=1024)&&((c.getModifiers()&16)!=16)&&((c.getModifiers()&512)!=512)&&BaseCache.class.isAssignableFrom(c)) {
                        c.newInstance();
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <K, V> Cache<K,V> createCacheManager(CacheConfigurationBean<K,V> cacheConfigurationBean){
        Cache<K,V> cache = ehcacheProvider.createCacheManager(cacheConfigurationBean);
        return cache;
    }


    public void closeCacheManager(){
        ehcacheProvider.closeCacheManager();
    }
}
