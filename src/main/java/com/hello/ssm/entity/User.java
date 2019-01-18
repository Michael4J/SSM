package com.hello.ssm.entity;

import java.math.BigDecimal;

/**
 * @Title: User
 * @Auther: Michael
 * @Date: 2018/11/15
 */
public class User {

    private int userid;
    private String username;
    private String password;
    private char sex;
    private BigDecimal salary;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
