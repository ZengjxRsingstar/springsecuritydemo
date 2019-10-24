package com.zengjx.security;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/22  22:17
 * @Version V1.0
 */
public class TestMd5 {

    public static void main(String[] args) {
        String hello = MD5Utils.md5("hello");
        System.out.println("hello"+hello);
    }
}
