package com.buildweek4.watermyplants.services;

import com.buildweek4.watermyplants.models.Plant;

import java.util.List;

public interface PlantService
    {
        List<Plant> findAll();

        Plant findPlantById(long id);

        void delete(long id);

        List<Plant> findPlantByUserName(String username);

        List<Plant> findPlantByUserId(long userid);

        Plant save(Plant plant);
    }
