package com.buildweek4.watermyplants.services;

import com.buildweek4.watermyplants.exceptions.ResourceNotFoundException;
import com.buildweek4.watermyplants.models.Plant;
import com.buildweek4.watermyplants.models.User;
import com.buildweek4.watermyplants.repository.PlantRepository;
import com.buildweek4.watermyplants.repository.RoleRepository;
import com.buildweek4.watermyplants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "plantService")
public class PlantServiceImpl implements  PlantService
    {

        @Autowired
        private PlantRepository plantrepo;

        @Autowired
        private UserRepository userrepo;

        @Autowired
        private RoleRepository rolerepo;

    @Override
    public List<Plant> findAll()
        {
            List<Plant> list = new ArrayList<>();
            plantrepo.findAll()
                    .iterator()
                    .forEachRemaining(list:: add);
            return list;
        }
    @Override
    public Plant findPlantById(long id)
        {
            return plantrepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Plant id" + id + "Not Found"));
        }
    @Override
    public void delete(long id) throws ResourceNotFoundException
        {
            if (plantrepo.findById(id).isPresent())
            {
                plantrepo.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(Long.toString(id));
            }
        }
    @Override
    public List<Plant> findPlantByUserName(String username)
        {
            List<Plant> plantList = new ArrayList<>();
            plantrepo.findAll()
                    .iterator()
                    .forEachRemaining(plantList::add);
            plantList.removeIf(plant -> !plant.getUser()
                                .getUsername()
                                .equalsIgnoreCase(username));
            return plantList;
        }


    @Override
    public Plant save(Plant plant)
        {
//            Plant newPlant = new Plant();
//              newPlant.setSpecies(plant.getSpecies());
//              newPlant.setName(plant.getName());
//              newPlant.setLocation(plant.getLocation());
//              ArrayList<User> newUsers = new ArrayList<>();
//            for (User u : plant.getUser()) {
//                newUsers.add(new User(u.getUsername()));
//            }
//            newPlant.setUser(newUsers);
//            return plantrepo.save(newPlant);


//            Plant newPlant = new Plant();
//              newPlant.setSpecies(plant.getSpecies());
//              newPlant.setName(plant.getName());
//              newPlant.setLocation(plant.getLocation());
//              ArrayList<User> newUsers = new ArrayList<>();
//            for (User u : plant.getUser()) {
//                newUsers.add(new User(u.getUsername()));
//            }
//            newPlant.setUser(newUsers);
//            return plantrepo.save(newPlant);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userrepo.findByUsername(authentication.getName());
            Plant savePlant =  plantrepo.save(plant);
            return savePlant;
        }
    }
