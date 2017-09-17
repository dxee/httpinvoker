package org.blling.httpinvoker.server.bean;

import java.io.Serializable;

/**
 * Created by Janita on 2017-03-24 16:34
 */
public class Animal implements Serializable {

    private Long animalId;
    private String name;

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", name='" + name + '\'' +
                '}';
    }
}
