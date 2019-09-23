package com.buildweek4.watermyplants;

import com.buildweek4.watermyplants.models.Plant;
import com.buildweek4.watermyplants.models.Role;
import com.buildweek4.watermyplants.models.User;
import com.buildweek4.watermyplants.models.UserRoles;
import com.buildweek4.watermyplants.repository.PlantRepository;
import com.buildweek4.watermyplants.repository.RoleRepository;
import com.buildweek4.watermyplants.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{

    UserRepository userRepository;
    RoleRepository roleRepository;
    PlantRepository plantRepository;


    public SeedData(UserRepository userRepository, RoleRepository roleRepository, PlantRepository plantRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.plantRepository = plantRepository;
    }


    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleRepository.save(r1);
        roleRepository.save(r2);
        roleRepository.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", "222-333-4444", admins);
        u1.getPlants().add(new Plant("Sunflower", "Sunny", "Front Porch", u1, 3));
        u1.getPlants().add(new Plant("Spider Plant", "Spidey", "Family Room", u1, 2));
        userRepository.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("data", "password", "919-333-4444", datas);
        userRepository.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("uno", "password", "415-555-1212", users);
        u3.getPlants().add(new Plant("Jade Plant", "Jade", "Front Porch", u3, 4));
        u3.getPlants().add(new Plant("Peacock Plant", "PeeWee", "Bedroom", u3, 1));
        u3.getPlants().add(new Plant("Climbing Fig", "Figgy", "Back Deck", u3, 2));
        u3.getPlants().add(new Plant("Rubber Fig", "Ber", "Family Room", u3, 3));
        userRepository.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("dos", "password", "310-333-1234", users);
        u4.getPlants().add(new Plant("Devil's Ivy", "Lucifer", "Family Room", u4, 4));
        u4.getPlants().add(new Plant("Lucky Bamboo", "Bambi", "Kitchen", u4, 4));
        userRepository.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("tres", "password", "123-456-7890", users);
        userRepository.save(u5);
    }
}