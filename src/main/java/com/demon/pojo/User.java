package com.demon.pojo;

public class User {
//1教师， 0学生
    private String username;
    private String password;
    private String email;
    private int identity;

    public User(String username, String password, String email, int identity) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.identity = identity;
    }
    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", identity=" + identity +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
