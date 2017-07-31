package com.fanxl.july.dao;

import com.fanxl.july.entity.User;
import com.fanxl.july.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PVer on 2017/7/4.
 */
@Repository
public interface UserDao extends MyMapper<User> {

    List<User> getAll();

    int create(User user);

}
