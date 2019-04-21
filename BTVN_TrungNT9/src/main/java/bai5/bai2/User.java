package bai5.bai2;

import java.util.List;

public class User {
    private String name;
    private int age;
    private List<String> girlFriends;

    public User(String name, int age, List<String> girlFriends) {
        this.name = name;
        this.age = age;
        this.girlFriends = girlFriends;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getGirlFriends() {
        return girlFriends;
    }

    public void setGirlFriends(List<String> girlFriends) {
        this.girlFriends = girlFriends;
    }

    @Override
    public String toString() {
        return "name : " + name + "\nage : " + age + "\nGirl Friend : " + getGirlFriends();
    }
}
