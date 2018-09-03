package com.fading.puppy.service;

import com.fading.puppy.entity.Auth_user;

import java.util.List;

public interface ILoginService {

    List<Auth_user> getUsers();
}
