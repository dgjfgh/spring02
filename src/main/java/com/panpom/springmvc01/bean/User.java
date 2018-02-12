package com.panpom.springmvc01.bean;

public class User {
    private int id;

    private String uname;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public User() {
        super();
    }

    public User(int id, String uname) {
        super();
        this.id = id;
        this.uname = uname;
    }

}
