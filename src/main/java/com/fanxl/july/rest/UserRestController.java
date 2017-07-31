package com.fanxl.july.rest;

import com.fanxl.july.dto.ApiResponse;
import com.fanxl.july.entity.User;
import com.fanxl.july.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PVer on 2017/7/4.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ApiResponse getAll(){

        ApiResponse<List<User>> result = new ApiResponse<>();
        List<User> userList = userService.getAll();
        result.setResult(userList);
        return result;
    }

}
