package com.group4.shared.plugin;

import com.group4.shared.Model.User;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/14/2017.
 */

public interface IUserDao {

    public void saveUsers(List<User> users);
    public List<User> getUsers();
    public void clear();
    public void create();

}
