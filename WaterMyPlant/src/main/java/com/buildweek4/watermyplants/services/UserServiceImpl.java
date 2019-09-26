package com.buildweek4.watermyplants.services;

import com.buildweek4.watermyplants.exceptions.ResourceFoundException;
import com.buildweek4.watermyplants.exceptions.ResourceNotFoundException;
import com.buildweek4.watermyplants.models.Plant;
import com.buildweek4.watermyplants.models.Role;
import com.buildweek4.watermyplants.models.User;
import com.buildweek4.watermyplants.models.UserRoles;
import com.buildweek4.watermyplants.repository.RoleRepository;
import com.buildweek4.watermyplants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
    {
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

        @Override
        public User findUserByUsername(String username) {
            return userrepos.findByUsername(username);
        }

        @Override
    public long getUserId(String username)
    {
        return userrepos.getUserIdWithUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name);
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (userrepos.findByUsername(user.getUsername()) != null)
        {
            throw new ResourceFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPhonenumber(user.getPhonenumber());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            long id = ur.getRole()
                    .getRoleid();
            Role role = rolerepos.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        return userrepos.save(newUser);
    }


    @Transactional
    @Override
    public User update(User user, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser != null)
        {
            if (id == currentUser.getUserid())
            {
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());
                }

                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }

                if (user.getPhonenumber() != null)
                {
                    currentUser.setPhonenumber(user.getPhonenumber());
                }

                if (user.getUserRoles().size() > 0)
                {
                    // delete the old ones
                    rolerepos.deleteUserRoles(currentUser.getUserid());

                    // add the new ones
                    for (UserRoles ur : user.getUserRoles())
                    {
                        rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
                    }
                }

                if (user.getPlants().size() > 0)
                {
                    for (Plant p : user.getPlants())
                    {
                        currentUser.getPlants().add(new Plant(p.getPlantid(), currentUser));
                    }
                }

                return userrepos.save(currentUser);
            } else
            {
                throw new ResourceNotFoundException(id + " Not current user");
            }
        } else
        {
            throw new ResourceNotFoundException(authentication.getName());
        }

    }
    @Transactional
    @Override
    public void deleteUserRole(long userid, long roleid)
    {
        userrepos.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid, roleid)
                .getCount() > 0)
        {
            rolerepos.deleteUserRoles(userid);
        } else
        {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addUserRole(long userid, long roleid)
    {
        userrepos.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid, roleid)
                .getCount() <= 0)
        {
            rolerepos.insertUserRoles(userid, roleid);
        } else
        {
            throw new ResourceFoundException("Role and User Combination Already Exists");
        }
    }
    }
