package com.coonchen.core.entity;

public class User {
    /**用户ID**/
    private Integer userid;

    /**用户组ID**/
    private Integer sugid;

    /**昵称**/
    private String nickname;

    /**手机号**/
    private String mobile;

    /**密码**/
    private String password;

    /**密钥**/
    private String securekey;

    /**头像**/
    private String userheadpath;

    /**添加时间**/
    private Integer addtime;

    /**最后登录时间**/
    private Integer lastlogintime;

    /**登录次数**/
    private Integer logintime;

    /**ip地址**/
    private String ipaddress;

    /**积分**/
    private Double gold;

    /**设备名称**/
    private String device;

    /**设备码**/
    private String devicecode;

    /**APP当前版本**/
    private String currentversion;

    /**手机系统版本**/
    private String systemversion;

    /**身份码，最长期限30天**/
    private String authentication;

    /**微信 qq等第三方登录**/
    private String openid;

    /**通道号(小米推送)**/
    private String channelid;

    /**来源 1为QQ,2为微信**/
    private Short loginsource;

    /**虚拟用户**/
    private Short vu;

    /**应用来源**/
    private String apksource;

    /**联系电话**/
    private String contactmobile;

    /**真实姓名**/
    private String realname;

    /**身份证号**/
    private String idcard;

    /**是否有效**/
    private Short visable;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSugid() {
        return sugid;
    }

    public void setSugid(Integer sugid) {
        this.sugid = sugid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSecurekey() {
        return securekey;
    }

    public void setSecurekey(String securekey) {
        this.securekey = securekey == null ? null : securekey.trim();
    }

    public String getUserheadpath() {
        return userheadpath;
    }

    public void setUserheadpath(String userheadpath) {
        this.userheadpath = userheadpath == null ? null : userheadpath.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Integer lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Integer getLogintime() {
        return logintime;
    }

    public void setLogintime(Integer logintime) {
        this.logintime = logintime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public String getCurrentversion() {
        return currentversion;
    }

    public void setCurrentversion(String currentversion) {
        this.currentversion = currentversion == null ? null : currentversion.trim();
    }

    public String getSystemversion() {
        return systemversion;
    }

    public void setSystemversion(String systemversion) {
        this.systemversion = systemversion == null ? null : systemversion.trim();
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication == null ? null : authentication.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid == null ? null : channelid.trim();
    }

    public Short getLoginsource() {
        return loginsource;
    }

    public void setLoginsource(Short loginsource) {
        this.loginsource = loginsource;
    }

    public Short getVu() {
        return vu;
    }

    public void setVu(Short vu) {
        this.vu = vu;
    }

    public String getApksource() {
        return apksource;
    }

    public void setApksource(String apksource) {
        this.apksource = apksource == null ? null : apksource.trim();
    }

    public String getContactmobile() {
        return contactmobile;
    }

    public void setContactmobile(String contactmobile) {
        this.contactmobile = contactmobile == null ? null : contactmobile.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Short getVisable() {
        return visable;
    }

    public void setVisable(Short visable) {
        this.visable = visable;
    }
}