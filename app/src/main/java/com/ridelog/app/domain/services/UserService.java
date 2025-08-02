package com.ridelog.app.domain.services;


import com.ridelog.app.dao.model.user.User;
import com.ridelog.app.dao.repositories.UserRepository;
import com.ridelog.app.ui.models.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User(name, email, passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Transactional()
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional()
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertToDTO);
    }

    @Transactional()
    public Page<UserDTO> searchUsers(String name, Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::convertToDTO);
    }

    public UserDTO addFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        user.getFriendIds().add(friendId);
        friend.getFriendIds().add(userId);

        userRepository.save(user);
        userRepository.save(friend);

        return convertToDTO(user);
    }

    public UserDTO removeFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        user.getFriendIds().remove(friendId);
        friend.getFriendIds().remove(userId);

        userRepository.save(user);
        userRepository.save(friend);

        return convertToDTO(user);
    }

    public UserDTO addVisitedPark(Long userId, Long parkId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getVisitedParkIds().add(parkId);
        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    public UserDTO addToWishlist(Long userId, Long parkId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getWishlistParkIds().add(parkId);
        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    @Transactional()
    public List<UserDTO> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userRepository.findAllByIdIn(user.getFriendIds().stream().toList())
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail());
        dto.setFriendIds(user.getFriendIds());
        dto.setVisitedParkIds(user.getVisitedParkIds());
        dto.setWishlistParkIds(user.getWishlistParkIds());
        return dto;
    }
}
