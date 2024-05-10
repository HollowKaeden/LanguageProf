package ru.HollowKaeden.service;

import lombok.AllArgsConstructor;
import ru.HollowKaeden.DTO.UserDTO;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public User addUser(UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .role(dto.getRole())
                .tel_num(dto.getTel_num())
                .build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<User> findByName(String name) {
        return repository.findByName(name);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return repository.findByName(principal.getName()).get();
    }
}