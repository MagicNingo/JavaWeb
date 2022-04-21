package com.bus.entity;

public class User {
    private Integer id;
    private String name;
    private Double balance;
    private Integer age;
    private String gender;

    public User() {
    }

    public User(Integer id, String name, Double balance, Integer age,
                String gender) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.age = age;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
