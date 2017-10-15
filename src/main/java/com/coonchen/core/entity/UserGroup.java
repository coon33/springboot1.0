package com.coonchen.core.entity;

public class UserGroup {
    /**用户组ID**/
    private Integer sugid;

    /**用户组名称**/
    private String sugname;

    public Integer getSugid() {
        return sugid;
    }

    public void setSugid(Integer sugid) {
        this.sugid = sugid;
    }

    public String getSugname() {
        return sugname;
    }

    public void setSugname(String sugname) {
        this.sugname = sugname == null ? null : sugname.trim();
    }
}