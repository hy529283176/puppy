package com.fading.puppy.service;

import com.fading.puppy.dao.IUserDao;
import com.fading.puppy.entity.Auth_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@Transactional
public class LoginService implements ILoginService {
    @Autowired
    private IUserDao userDao;

    public LoginService(){
    }

    @Override
    public List<Auth_user> getUsers() {
        return this.userDao.getUsers();
    }
}
