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
public class UserService  implements UserDetailsService {
    public   static Map<String ,com.zengjx.security.User> map  =new HashMap<>();
    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    static {
        com.zengjx.security.User user1 =new com.zengjx.security.User("zhangsan",passwordEncoder.encode("123456"));
        com.zengjx.security.User  user2 =new com.zengjx.security.User("admin",passwordEncoder.encode("admin"));//$2a$10$rzPh1hg05pxdCW7pOltu4ucncMNBLQv9XMo5TfcR2Av9ae09iCTF2
        // com.zengjx.security.User user1 =new com.zengjx.security.User("zhangsan","123456");
        // com.zengjx.security.User  user2 =new com.zengjx.security.User("admin","admin");//$2a$10$rzPh1hg05pxdCW7pOltu4ucncMNBLQv9XMo5TfcR2Av9ae09iCTF2

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
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //模拟数据库中的数据
//        System.out.println("用户名"+username);
//        //根据用户 查询数据库
//        com.zengjx.security.User user = map.get(username);
//        if(user==null){
//            System.out.println("没有这个用户");
//            return   null;
//        }
//       // String   passwordinDb="{noop}"+user.getPassword();
//        String   passwordinDb=user.getPassword();
//        System.out.println("ps:"+passwordinDb);
//
//        List<GrantedAuthority>  list  =new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("add"));
//        list.add(new SimpleGrantedAuthority("delete"));
//        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//        //返回User，参数一：存放登录名，参数二：存放数据库查询的密码（数据库获取的密码，默认会和页面获取的密码进行比对，成功跳转到成功页面，
//        // 失败回到登录页面，并抛出异常表示失败），存放当前用户具有的角色
//        UserDetails   userDetails =new org.springframework.security.core.userdetails.User(username,passwordinDb,list);
//        return userDetails;
//
//    }

    // 从数据库查询完成认证和授权，传递的参数就是s，s表示登录名
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 使用登录名，查询数据库，获取User对象
        User user = map.get(s);
        // 当前页面输入的用户名在数据库中不存在，此时表示登录不成功（用户名输入有误）:return null:表示用户名输入有误，后台会抛出一个异常
        // org.springframework.security.authentication.InternalAuthenticationServiceException
        if(user==null){
            return null;
        }
        // 如果用户名输入正确，比对密码   注意  不能用"{noop}"  因为已经用加密了
        //String password = "{noop}"+user.getPassword();
        String password = user.getPassword(); // 通过BCryptPasswordEncoder加密后的密码
        // 权限的集合（添加角色、权限）（后续从数据库查询）
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 角色
        list.add(new SimpleGrantedAuthority("add")); // 权限
        list.add(new SimpleGrantedAuthority("delete")); // 权限
        /**
         * public LoginUser(String username, String password, Collection<? extends GrantedAuthority>
         *     参数一：登录名
         *     参数二：比对的密码（默认会使用从数据库查询的密码和页面输入的密码进行比对，如果一致，表示登录成功，如果不一致，抛出异常，表示密码输入有误）
         *     参数三：权限的集合
         */
        // org.springframework.security.authentication.BadCredentialsException: Bad credentials
        return new org.springframework.security.core.userdetails.User(user.getUsername(),password,list);
    }



}
