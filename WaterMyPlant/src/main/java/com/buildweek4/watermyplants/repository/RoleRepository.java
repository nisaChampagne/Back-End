package com.buildweek4.watermyplants.repository;

import com.buildweek4.watermyplants.models.Role;
import com.buildweek4.watermyplants.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long>
    {
    @Query(value = "SELECT COUNT(*) as count FROM userroles WHERE userid = :userid AND roleid = :roleid",
            nativeQuery = true)
    JustTheCount checkUserRolesCombo(long userid, long roleid);


    @Transactional
    @Modifying
    @Query(value = "DELETE from UserRoles where userid = :userid")
    void deleteUserRoles(long userid);

    @Transactional
    @Modifying
    @Query(value = "INSERT into UserRoles(userid, roleid) values (:userid, :roleid)")

    default void insertUserRoles(long userid, long roleid) {
    }

    Role findByNameIgnoreCase(String name);
    }
