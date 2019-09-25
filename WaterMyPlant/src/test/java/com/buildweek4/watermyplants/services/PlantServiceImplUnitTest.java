//package com.buildweek4.watermyplants.services;
//
//import com.buildweek4.watermyplants.WatermyplantsApplication;
//import com.buildweek4.watermyplants.exceptions.ResourceNotFoundException;
//import com.buildweek4.watermyplants.models.Plant;
//import com.buildweek4.watermyplants.models.User;
//import org.h2.table.Plan;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = WatermyplantsApplication.class)
//public class PlantServiceImplUnitTest
//    {
//        @Autowired
//    private PlantService plantService;
//
//        @Autowired
//        private UserService userService;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @After
//    public void tearDown() throws Exception
//    {
//
//    }
//
//    @Test
//    public void findAll()
//    {
//        assertEquals(8, plantService.findAll().size());
//    }
//
//    @Test
//    public void findPlantById() throws ResourceNotFoundException
//    {
//        assertEquals("TEST Sunny", plantService.findPlantById(5).getName());
//    }
//
//
//    @Test
//    public void delete()
//    {
//        plantService.delete(5);
//        assertEquals(7, plantService.findAll().size());
//    }
//
//    @Test(expected = ResourceNotFoundException.class)
//    public void deleteNotFound()
//    {
//        plantService.delete(5000);
//        assertEquals(8, plantService.findAll().size());
//
//    }
//
//
//    @Test
//    public void save()
//    {
//        String plantName = "A";
//        User user1 = userService.findUserById(4);
//        Plant plant = new Plant("Abby", plantName, "LivingRoom", user1, 3 );
//
//        Plant addPlant = plantService.save(plant);
//        assertNotNull(addPlant);
//
//        Plant foundPlant = plantService.findPlantById(addPlant.getPlantid());
//        assertEquals(addPlant.getName(), foundPlant.getName());
//    }
//    }