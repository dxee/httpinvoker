package org.blling.httpinvoker.server.service;

import org.blling.httpinvoker.server.bean.User;

/**
 * Created by Janita on 2017-03-24 14:33
 */
public interface IUserService {

    User getUserById(Long userId);
}
