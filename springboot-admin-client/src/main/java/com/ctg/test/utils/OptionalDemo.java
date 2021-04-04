package com.ctg.test.utils;

import com.ctg.test.po.Address;
import com.ctg.test.po.User;

import java.util.Optional;


/**
 * @author liuyue
 * @date 2021/2/9 9:54
 * @Description:
 */
public class OptionalDemo {

    public static void main(String[] args) throws Exception {


        //当user值不为null时，orElse函数依然会执行createUser()方法，而orElseGet函数并不会执行createUser()方法
        User user = null;
        user = Optional.ofNullable(user).orElse(createUser("orElse"));
        user = Optional.ofNullable(user).orElseGet(() -> createUser("orElseGet"));
        user = Optional.ofNullable(user).orElseThrow(() -> new Exception("用户不存在"));

        //唯一区别的就是入参，map函数所接受的入参类型为Function<? super T, ? extends U>，而flapMap的入参类型为Function<? super T, Optional<U>>。
        //public class User {
        //    private String name;
        //    public String getName() {
        //        return name;
        //    }
        //}
        String city = Optional.ofNullable(user).map(u -> u.getName()).get();
        System.out.println(city);

        //public class User {
        //    private String name;
        //    public Optional<String> getName() {
        //        return Optional.ofNullable(name);
        //    }
        //}
        //String city = Optional.ofNullable(user).flatMap(u -> u.getName()).get();


        //filter
        Optional<User> user1 = Optional.ofNullable(user).filter(u -> u.getName().length() < 7);
        System.out.println("filter: " + user1.get());


        //取值模板
        String city1 = Optional.ofNullable(user)
                .map(u -> u.getAddress())
                .map(a -> a.getCity())
                .orElseThrow(() -> new Exception("取指错误"));
        System.out.println("取值模板:" + city1);

        //isPresent即判断value值是否为空，而ifPresent就是在value值不为空时，做一些操作。
        //例二
        //    if(user!=null){
        //        dosomething(user);
        //    }
        Optional.ofNullable(user).ifPresent(u -> {
            // TODO: do something
            System.out.println("user:" + u);
            u.setName("ifPresent");
            System.out.println("user:" + u);
        });


        //例一
        //以前得写法
        //public User getUser(User user) throws Exception{
        //    if(user!=null){
        //        String name = user.getName();
        //        if("zhangsan".equals(name)){
        //            return user;
        //        }
        //    }else{
        //        user = new User();
        //        user.setName("zhangsan");
        //        return user;
        //    }
        //}
        User user3 = Optional.ofNullable(user)
                .filter(u -> "zhangsan".equals(u.getName()))
                .orElseGet(() -> {
                    User user2 = new User();
                    user2.setName("zhangsan");
                    return user2;
                });

    }

    public static User createUser(String userName) {
        System.out.println("create User: " + userName);
        User user = new User();
        user.setName("刘粤");
        user.setAge("18");
        Address address = new Address();
        address.setProvince("74");
        address.setCity("7401");
        address.setArea("0010");
        user.setAddress(address);
        return user;
    }
}
