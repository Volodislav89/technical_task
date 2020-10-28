package com.zvl.technical_task.service;

import com.zvl.technical_task.exception.UserException;
import com.zvl.technical_task.model.User;
import com.zvl.technical_task.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User saveNewUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException(String.format("User with id: %d not found.", id)));
    }
}
