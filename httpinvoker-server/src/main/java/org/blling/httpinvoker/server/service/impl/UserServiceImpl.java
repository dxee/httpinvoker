package org.blling.httpinvoker.server.service.impl;

import org.blling.httpinvoker.server.bean.User;
import org.blling.httpinvoker.server.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by Janita on 2017-03-24 14:34
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(Long userId) {
        User user = new User();
        user.setUserId(userId);
        if (userId == 1){
            user.setName("Janita");
            user.setAge(10);
        }else {
            user.setName("JJ");
            user.setAge(80);
        }
        return user;
    }
}
