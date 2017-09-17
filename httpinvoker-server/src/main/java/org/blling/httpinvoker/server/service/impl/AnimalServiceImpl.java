package org.blling.httpinvoker.server.service.impl;

import org.blling.httpinvoker.server.bean.Animal;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.springframework.stereotype.Service;

/**
 * Created by Janita on 2017-03-24 16:37
 */
@Service
public class AnimalServiceImpl implements IAnimalService {
    @Override
    public Animal getAnimal(Long animalId) {
        Animal animal = new Animal();
        animal.setAnimalId(animalId);
        animal.setName("JAMES");
        return animal;
    }
}
