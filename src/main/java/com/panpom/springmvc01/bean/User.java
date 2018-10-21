package com.panpom.springmvc01.bean;

public class User {
    private int id;

    private String uName;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public User() {
        super();
    }

    public User(int id, String uname) {
        super();
        this.id = id;
        this.uName = uname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uName='" + uName + '\'' +
                ", age=" + age +
                '}';
    }
}
