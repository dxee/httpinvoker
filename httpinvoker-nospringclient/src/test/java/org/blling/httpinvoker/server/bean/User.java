package org.blling.httpinvoker.server.bean;

import java.io.Serializable;

/**
 * Created by Janita on 2017-03-24 14:33
 * 若不实现序列化则：java.io.NotSerializableException: com.blling.httpinvoker.server.bean.User
 */
public class User implements Serializable {

    private Long userId;

    private String name;

    private Integer age;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
