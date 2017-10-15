package com.coonchen.core.cache.dir;

import com.coonchen.core.entity.User;
import com.coonchen.framework.cache.BaseCache;
import com.coonchen.framework.cache.CacheConfigurationBean;
import org.ehcache.Cache;

public class UserSessionCache extends BaseCache {
    public static Cache<String,User> userSession;
    public UserSessionCache(){
        CacheConfigurationBean<String,User> cacheConfigurationBean = new CacheConfigurationBean<String,User>();
        cacheConfigurationBean.setCacheName("usersession");
        cacheConfigurationBean.setClassKey(String.class);
        cacheConfigurationBean.setClassVal(User.class);
        cacheConfigurationBean.setDiskSzie(0);//磁盘存储
        cacheConfigurationBean.setHeapSize(10*1000);//内存存储
        cacheConfigurationBean.setTimeToLiveExpirySecond(90*24*60*60);//失效时间
        userSession = super.createCache(cacheConfigurationBean);
    }

    public static Cache<String,User> setAttribute(String key,User value) {
        userSession.put(key,value);
        return userSession;
    }

    public static User getAttribute(String key){
        return userSession.get(key);
    }

    public static void removeAttribute(String key){
        userSession.remove(key);
    }
}
