package com.buildweek4.watermyplants.services;

import com.buildweek4.watermyplants.exceptions.ResourceNotFoundException;
import com.buildweek4.watermyplants.models.Role;
import com.buildweek4.watermyplants.models.User;
import com.buildweek4.watermyplants.models.UserRoles;
import com.buildweek4.watermyplants.repository.RoleRepository;
import com.buildweek4.watermyplants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements  RoleService
    {

        @Autowired
        private RoleRepository rolerepo;

        @Autowired
        private UserRepository userrepo;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        rolerepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return  list;
    }

    @Override
    public Role findRoleById(long id)
    {
        return rolerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        rolerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
        rolerepo.deleteById(id);
    }

    @Override
    public Role save(Role role)
    {
        Role newRole = new Role();
        newRole.setName(role.getName());

        ArrayList<UserRoles> newUsers = new ArrayList<>();
        for(UserRoles userR: role.getUserRoles())
        {
            long id = userR.getUser()
                            .getUserid();
            User user = userrepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
            newUsers.add(new UserRoles(userR.getUser(), newRole));
        }
        newRole.setUserRoles(newUsers);
        return rolerepo.save(role);
    }

    @Override
    public Role findByName(String name)
    {
        Role r = rolerepo.findByNameIgnoreCase(name);

        if(r != null)
        {
            return  r;
        }else
        {
            throw  new ResourceNotFoundException(name);
        }
    }
    }
