package spring.security.demo.dao;

import spring.security.demo.entity.User;

public interface UserDAO {

    public void dbSaveUser(User user);

    public User dbGetUserByName(String username);

    public long dbGetUserCount();
}
