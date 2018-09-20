package com.jf.exam.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordTypeToken extends UsernamePasswordToken {

    public static final int SUTDENT = 1;
    public static final int TEACHER = 2;
    public static final int ADMIN = 3;

    private int userType;

    public UsernamePasswordTypeToken(String username, String password, int userType) {
        super(username, password);
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
