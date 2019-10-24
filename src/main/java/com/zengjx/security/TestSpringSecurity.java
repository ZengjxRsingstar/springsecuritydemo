package com.zengjx.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author zengjx
 * @Company zengjx
 * @Date 2019/10/22  22:20
 * @Version V1.0
 */
//==spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密==。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
//
//==（1）加密(encode)==：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
//
//==（2）密码匹配(matches)==：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确。
//
//这正是为什么处理密码时要用hash算法，而不用加密算法。因为这样处理即使数据库泄漏，黑客也很难破解密码。
public class TestSpringSecurity {

    public static void main(String[] args) {
        // $2a$10$dyIf5fOjCRZs/pYXiBYy8uOiTa1z7I.mpqWlK5B/0icpAKijKCgxe
        // $2a$10$OphM.agzJ55McriN2BzCFeoLZh/z8uL.8dcGx.VCnjLq01vav7qEm

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String s = encoder.encode("abc");
//        System.out.println(s);
//        String s1 = encoder.encode("abc");
//        System.out.println(s1);
//
//        // 进行判断
//        boolean b = encoder.matches("abc", "$2a$10$qHoD1BoD.aJGucSaV.nFFuAtksBDUnadHkpZgs0v/eRGN47rKGzKm");
//        System.out.println(b);
            test();
    }


   public   static    void test(){//$2a$10$rzPh1hg05pxdCW7pOltu4ucncMNBLQv9XMo5TfcR2Av9ae09iCTF2

       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       String s = encoder.encode("admin");
       System.out.println(s);
//       String s1 = encoder.encode("abc");
//       System.out.println(s1);
       String  str ="$2a$10$svcmxJMa7Ie98jebVUNzQuzpoyG9BsKz1ftVkSen11nRpUrB9QHTu";
       // 进行判断
       boolean b = encoder.matches("admin", str);
       System.out.println(b);


   }

}
