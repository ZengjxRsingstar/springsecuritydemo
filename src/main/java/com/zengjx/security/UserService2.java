package com.zengjx.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/22  21:36
 * @Version V1.0
 */
@Component
public class UserService2 implements UserDetailsService {
    public   static Map<String , User> map  =new HashMap<>();
    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    static {
        User user1 =new User("zhangsan",passwordEncoder.encode("123456"));
        User  user2 =new User("admin",passwordEncoder.encode("admin"));//$2a$10$rzPh1hg05pxdCW7pOltu4ucncMNBLQv9XMo5TfcR2Av9ae09iCTF2
     map.put(user1.getUsername(),user1);
     map.put(user2.getUsername(),user2);

    }
    //根据用户名加载用户信息
//    @Override
//    public UserDetails loadUserByUsername2(String username) throws UsernameNotFoundException {
//     //模拟数据库中的数据
//        System.out.println("用户名"+username);
//      //根据用户 查询数据库
//        com.zengjx.security.User user = map.get(username);
//        if(user==null){
//            System.out.println("没有这个用户");
//            return   null;
//        }
//        String   passwordinDb="{noop}"+user.getPassword();
//        List<GrantedAuthority>  list  =new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("add"));
//        list.add(new SimpleGrantedAuthority("delete"));
//        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//        //返回User，参数一：存放登录名，参数二：存放数据库查询的密码（数据库获取的密码，默认会和页面获取的密码进行比对，成功跳转到成功页面，
//        // 失败回到登录页面，并抛出异常表示失败），存放当前用户具有的角色
//        UserDetails   userDetails =new org.springframework.security.core.userdetails.User(username,passwordinDb,list);
//
//        return userDetails;
//
//    }


    //用户密码加密


    //根据用户名加载用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //模拟数据库中的数据
        System.out.println("用户名"+username);
        //根据用户 查询数据库
        User user = map.get(username);
        if(user==null){
            System.out.println("没有这个用户");
            return   null;
        }
        String   passwordinDb="{noop}"+user.getPassword();

        List<GrantedAuthority>  list  =new ArrayList<>();
        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("delete"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //返回User，参数一：存放登录名，参数二：存放数据库查询的密码（数据库获取的密码，默认会和页面获取的密码进行比对，成功跳转到成功页面，
        // 失败回到登录页面，并抛出异常表示失败），存放当前用户具有的角色
        UserDetails   userDetails =new org.springframework.security.core.userdetails.User(username,passwordinDb,list);

        return userDetails;



    }
}
