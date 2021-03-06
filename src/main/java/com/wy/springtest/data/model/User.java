package com.wy.springtest.data.model;

import java.util.List;

public class User {
    private Integer id;

    private String account;

    private String name;

    private String pass;

    private String enable;

    private String locked;

    private List<String> authorities;

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("{id=").append(id)
                .append(",account:'").append(account)
                .append("',name:'").append(name)
                .append("',pass:'").append(pass)
                .append("',enable:'").append(enable)
                .append("',locked:'").append(locked)
                .append(",authorities:[");
        for (String authority : authorities) {
            buffer.append('\'').append(authority).append("',");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("]}");
        return buffer.toString();
    }
}
