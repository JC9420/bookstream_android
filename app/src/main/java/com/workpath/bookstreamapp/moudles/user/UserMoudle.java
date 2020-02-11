package com.workpath.bookstreamapp.moudles.user;

import com.workpath.bookstreamapp.moudles.Base;

/**
 * Created by JayChen on 2020/2/5.
 */

public class UserMoudle extends Base {

    private String phone_number;//主键-电话号码

    private String password;//密码

    private String name;//名字

    private int sex;//性别 1-男 0-女

    private int age;//年龄

    protected UserMoudle(){//无参构造函数，设置为私有类型防止直接使用

    }

    public UserMoudle(String phone_number, String password, String name, int sex, int age) {
        this.phone_number = phone_number;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserMoudle{" +
                "phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
