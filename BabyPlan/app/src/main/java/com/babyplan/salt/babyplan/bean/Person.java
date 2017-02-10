package com.babyplan.salt.babyplan.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Salt on 2017/2/9.
 */
public class Person extends BmobUser {
    public Person(){}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    private Integer age;
    private String sex;
}
