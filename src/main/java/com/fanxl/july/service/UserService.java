package com.fanxl.july.service;


import com.fanxl.july.entity.User;

import java.util.List;

/**
 * Created by fanxl2 on 2017/1/4.
 */
public interface UserService {

    List<User> getAll();

    boolean create(User user);

    User getByName(String name);

}
