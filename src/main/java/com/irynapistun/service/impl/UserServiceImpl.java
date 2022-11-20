package com.irynapistun.service.impl;

import com.irynapistun.domain.User;
import com.irynapistun.service.UserService;
import org.springframework.stereotype.Service;
import com.irynapistun.exception.UserNotFoundException;
import com.irynapistun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void update(Integer id, User uUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setFirstName(uUser.getFirstName());
        user.setLastName(uUser.getLastName());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
