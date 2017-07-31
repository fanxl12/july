package com.fanxl.july.service.impl;

import com.fanxl.july.dao.UserDao;
import com.fanxl.july.entity.User;
import com.fanxl.july.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fanxl2 on 2017/1/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional //事务注解，表明该方法应用事务
    @Override
    public boolean create(User user) {
        return userDao.create(user)>0;
    }
}
