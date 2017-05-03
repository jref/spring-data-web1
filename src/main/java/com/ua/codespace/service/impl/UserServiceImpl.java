package com.ua.codespace.service.impl;


import com.ua.codespace.exception.UserNotFoundException;
import com.ua.codespace.model.User;
import com.ua.codespace.repository.UserRepository;
import com.ua.codespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUserFriends(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            return user.getFriends().stream()
                    .sorted(comparing(User::getUsername))
                    .collect(toList());
        }
    }
}
