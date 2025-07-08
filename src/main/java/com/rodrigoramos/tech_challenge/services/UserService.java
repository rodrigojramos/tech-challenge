package com.rodrigoramos.tech_challenge.services;

import com.rodrigoramos.tech_challenge.dto.UserDTO;
import com.rodrigoramos.tech_challenge.entities.Address;
import com.rodrigoramos.tech_challenge.entities.User;
import com.rodrigoramos.tech_challenge.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado"));
        return convertToDTO(user);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).toList();
    }

    public UserDTO insert(UserDTO dto) {
        User user = convertToEntity(dto);
        user.setLastModifiedAt(LocalDateTime.now());
        return convertToDTO(userRepository.save(user));
    }

    public UserDTO update(UserDTO dto, Long id) {
        try {
            User existingUser = userRepository.getReferenceById(id);
            existingUser.setName(dto.name());
            existingUser.setLogin(dto.login());
            existingUser.setPassword(dto.password());
            existingUser.setLastModifiedAt(LocalDateTime.now());
            return convertToDTO(userRepository.save(existingUser));
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
    }

    public void delete(Long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private UserDTO convertToDTO(User user) {
        List<Address> addresses = user.getAddresses();
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getLastModifiedAt(),
                Optional.ofNullable(user.getAddresses()).orElse(List.of())
        );
    }

    private User convertToEntity(UserDTO dto) {
        return new User(
                dto.id(),
                dto.name(),
                dto.login(),
                dto.password(),
                dto.addresses()
        );
    }
}
