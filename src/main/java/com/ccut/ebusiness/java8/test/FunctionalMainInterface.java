package com.ccut.ebusiness.java8.test;

@FunctionalInterface
public interface FunctionalMainInterface {

    public void addUser();

    //public void delUser();

    static void staticUser(){
        System.out.println("staticUser");
    }

    default void defaultUser(){
        System.out.println("defaultUser");
    }
}
