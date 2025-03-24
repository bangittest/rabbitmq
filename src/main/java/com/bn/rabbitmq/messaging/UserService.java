package com.bn.rabbitmq.messaging;

import java.util.List;

public interface UserService {
    void updateUser(List<User> user);
    void createUser(User user);
}
