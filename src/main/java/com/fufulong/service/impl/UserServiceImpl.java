package com.fufulong.service.impl;

import com.fufulong.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 测试服务
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void query() {
        System.out.println("UserServiceImpl 的query()方法");
    }
}
