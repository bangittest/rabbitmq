package com.bn.rabbitmq.messaging;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(List<User> user) {
        userRepository.saveAll(user);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}
