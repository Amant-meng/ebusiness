package com.ccut.ebusiness.java8.test;

public interface UserService {


    void addUser();

    default String getUser(){
        return "test";
    }

    static  void delUser(){
        System.out.println("删除学生");
    }
}
