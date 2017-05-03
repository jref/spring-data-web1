package com.ua.codespace.service;


import com.ua.codespace.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserFriends(Long id);
}
