package com.fanxl.july.config.shiro;

import com.fanxl.july.entity.User;
import com.fanxl.july.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fanxl2 on 2017/1/3.
 */
public class MyShiroRealm extends AuthorizingRealm {

    public static final String HASH_ALGORITHM = "SHA-512";
    public static final int HASH_ITERATIONS = 128;

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User userInfo  = (User)principals.getPrimaryPrincipal();
        if (userInfo!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("进行密码验证");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //得到密码
//        String password = new String((char[])token.getCredentials());
        User userInfo = userService.getByName(username);
        if (userInfo!=null){
            return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()), getName());
        }else {
            throw new UnknownAccountException("用户名不存在");
        }
    }

}
