package org.blling.httpinvoker.server.controller;

import org.blling.httpinvoker.server.bean.Animal;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Janita on 2017-03-24 16:36
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @GetMapping
    public Animal getAnimal(Long animalId){
        return animalService.getAnimal(animalId);
    }
}
