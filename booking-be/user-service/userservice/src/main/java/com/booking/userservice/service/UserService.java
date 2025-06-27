package com.booking.userservice.service;

import com.booking.userservice.dto.request.CreateUserRequest;
import com.booking.userservice.dto.request.LoginRequest;
import com.booking.userservice.dto.request.UpdateUserRequest;
import com.booking.userservice.dto.response.UserResponse;
import com.booking.userservice.exception.UserAlreadyExistsException;
import com.booking.userservice.exception.UserNotFoundException;
import com.booking.userservice.mapper.UserMapper;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

  BCryptPasswordEncoder passwordEncoder;

  UserRepository userRepository;
  UserMapper userMapper;


  public void createUser(CreateUserRequest req) throws UserAlreadyExistsException {
    boolean exists = userRepository.findByUsername(req.getUsername()).isPresent();

    if (exists) {
      throw new UserAlreadyExistsException("User already exists with username: " + req.getUsername());
    }
    if (!req.getPassword().equals(req.getPassword())) {
      throw new UserAlreadyExistsException("Password does not match");
    }
    User newUser = userMapper.toUser(req);
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    userRepository.saveAndFlush(newUser);
  }


  public UserResponse getUserById(Long id) throws UserNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    return userMapper.toUserReponse(user);
  }

  public UserResponse getUserByUsername(String username) throws UserNotFoundException {

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

    return userMapper.toUserReponse(user);
   }


  public List<UserResponse> getAllUsers(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);
    return users.stream()
        .map(userMapper::toUserReponse)
        .toList();
  }


  public void updateUser(Long id, UpdateUserRequest req) throws UserNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    userMapper.updateUserFromRequest(user, req);

    userRepository.save(user);
  }

  public void softDeleteUser(Long id) throws UserNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    user.setDeletedAt(LocalDateTime.now());
    user.setDeleted(true);
    userRepository.save(user);
  }

  public void deleteUser(Long id) throws UserNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    userRepository.delete(user);
  }

  public UserResponse validateCredentials(LoginRequest req) throws UserNotFoundException {
    User user = userRepository.findByUsername(req.getUsername())
        .orElseThrow(() -> new UserNotFoundException("User not found with username: " + req.getUsername()));

    boolean checkPassword = passwordEncoder.matches(req.getPassword(), user.getPassword());

    if(!checkPassword) {
      throw new UserNotFoundException("Password is not correct");
    }

    return userMapper.toUserReponse(user);
  }


}
