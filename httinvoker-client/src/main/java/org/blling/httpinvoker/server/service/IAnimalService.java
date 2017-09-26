package org.blling.httpinvoker.server.service;

import org.blling.httpinvoker.server.bean.Animal;

/**
 * Created by Janita on 2017-03-24 16:37
 */
public interface IAnimalService {
    Animal getAnimal(Long animalId);
}
