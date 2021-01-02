package bgu.spl.net;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
    private String password;
    private boolean admin;
    private Boolean loggedIn;
    private ConcurrentLinkedQueue<Integer> registeredCourses;

    public User(String password, boolean admin) {
        this.password = password;
        this.admin = admin;
        this.loggedIn = false;
        this.registeredCourses=new ConcurrentLinkedQueue<>();
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void logIn() {
        this.loggedIn = true;
    }

    public void logOut() {
        this.loggedIn = false;
    }

    public ConcurrentLinkedQueue<Integer> getRegisteredCourses() {
        return registeredCourses;
    }

}
