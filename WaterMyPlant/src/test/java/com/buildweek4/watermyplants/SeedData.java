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
            u1.getPlants().add(new Plant("TEST Sunflower", "TEST Sunny", "Front Porch", u1));
            u1.getPlants().add(new Plant("TEST Spider Plant", "TEST Spidey", "Family Room", u1));
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
            u3.getPlants().add(new Plant("TEST Jade Plant", "TEST Jade", "Front Porch", u3));
            u3.getPlants().add(new Plant("TEST Peacock Plant", "TEST PeeWee", "Bedroom", u3));
            u3.getPlants().add(new Plant("TEST Climbing Fig", "TEST Figgy", "Back Deck", u3));
            u3.getPlants().add(new Plant("TEST Rubber Fig", "TEST Ber", "Family Room", u3));
            userRepository.save(u3);

            users = new ArrayList<>();
            users.add(new UserRoles(new User(), r2));
            User u4 = new User("dos", "password", "310-333-1234", users);
            u4.getPlants().add(new Plant("TEST Devil's Ivy", "TEST Lucifer", "Family Room", u4));
            u4.getPlants().add(new Plant("TEST Lucky Bamboo", "TEST Bambi", "Kitchen", u4));
            userRepository.save(u4);

            users = new ArrayList<>();
            users.add(new UserRoles(new User(), r2));
            User u5 = new User("tres", "password", "123-456-7890", users);
            userRepository.save(u5);

            System.out.println("*******SEED DATA*****");
            System.out.println(u1.getPlants() + "Admin Plants " );
            System.out.println(u2.getPlants() + "Datas Plants");
            System.out.println(u3.getPlants() + "Unos Plants");
            System.out.println(u4.getPlants() + "Dos Plants");
            System.out.println(u5.getPlants() + "Tres Plants");
            System.out.println("*******SEED DATA*****");
        }
    }