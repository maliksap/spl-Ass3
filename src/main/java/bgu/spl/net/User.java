package bgu.spl.net;

import java.util.LinkedList;

public class User {
    private String password;
    private boolean admin;
    private boolean loggedIn;
    private LinkedList<Integer> registeredCourses;

    public User(String password, boolean admin) {
        this.password = password;
        this.admin = admin;
        this.loggedIn = false;
        this.registeredCourses=new LinkedList<>();
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logIn() {
        this.loggedIn = true;
    }

    public void logOut() {
        this.loggedIn = false;
    }

    public LinkedList<Integer> getRegisteredCourses() {
        return registeredCourses;
    }

}
