package com.fading.puppy.dao;

import com.fading.puppy.entity.Auth_user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao extends PagingAndSortingRepository<Auth_user,Integer>{

    @Query(value = "select au.* from auth_user au where au.visible=true ",nativeQuery = true)
    List<Auth_user> getUsers();
}
