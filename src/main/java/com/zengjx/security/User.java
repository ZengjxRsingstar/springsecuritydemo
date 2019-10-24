package com.zengjx.security;

import java.io.Serializable;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/22  21:40
 * @Version V1.0
 */
public class User   implements Serializable {

    private  String username;
    private  String   password;

    public User() {
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
