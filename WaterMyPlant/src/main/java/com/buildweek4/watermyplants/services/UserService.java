package com.buildweek4.watermyplants.services;

import com.buildweek4.watermyplants.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
    {
    UserDetails loadUserByUsername(String username);

    List<User> findAll();

    User findUserById(long id);

    long getUserId(String username);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(User user, long id);

    void deleteUserRole(long userid, long roleid);

    void addUserRole(long userid, long roleid);

    User findUserByUsername(String username);
    }
