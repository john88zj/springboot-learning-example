package com.cherrys.platform.core.service;


import com.cherrys.platform.core.dao.UserRepository;
import com.cherrys.platform.core.domain.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public TUser findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
