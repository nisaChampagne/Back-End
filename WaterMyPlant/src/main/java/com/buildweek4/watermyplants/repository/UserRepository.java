package com.buildweek4.watermyplants.repository;

import com.buildweek4.watermyplants.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
    {
        User findByUsername(String username);

        @Query(value = "SELECT userid FROM users WHERE username = :username",
                nativeQuery = true)
        long getUserIdWithUsername(String username);
    }
