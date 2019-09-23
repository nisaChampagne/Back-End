package com.buildweek4.watermyplants.repository;

import com.buildweek4.watermyplants.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
    {
        User findByUsername(String username);
    }
