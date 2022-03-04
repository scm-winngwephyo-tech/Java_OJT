package spring.security.demo.service;

import spring.security.demo.entity.User;

public interface UserService {

    public User doGetUserByName(String username);

    public long doGetUserCount();
}
