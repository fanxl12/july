package com.fanxl.july.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fanxl2 on 2017/1/3.
 */
@Configuration
public class ShiroConfiguration {

    private Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        logger.info("SecurityManager注入");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器;
        securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
        //注入记住我管理器;
//        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    @Bean(name = "shiroRealm")
    public MyShiroRealm myShiroRealm(){
        logger.info("自定义-MyShiroRealm注入");
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        logger.info("ShiroFilterFactoryBean注入");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/index", "user");
//        filterChainDefinitionMap.put("/", "user");

        filterChainDefinitionMap.put("/info", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     *凭证匹配器
     *由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *所以我们需要修改下doGetAuthenticationInfo中的代码;
     *
     * 设定Password校验的Hash算法与迭代次数.
     *
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        logger.info("HashedCredentialsMatcher注入");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(MyShiroRealm.HASH_ALGORITHM);
        matcher.setHashIterations(MyShiroRealm.HASH_ITERATIONS);
        return matcher;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        logger.info("EhCacheManager注入");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * cookie对象;
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        logger.info("SimpleCookie对象注入");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        logger.info("CookieRememberMeManager注入");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    //自动来调用配置在Springn IOC 容器中Shiro bean的生命周期方法
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        logger.info("LifecycleBeanPostProcessor注入");
        return new LifecycleBeanPostProcessor();
    }
}
