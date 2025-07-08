package com.rodrigoramos.tech_challenge.services;

import com.rodrigoramos.tech_challenge.dto.AddressDTO;
import com.rodrigoramos.tech_challenge.entities.Address;
import com.rodrigoramos.tech_challenge.entities.User;
import com.rodrigoramos.tech_challenge.repositories.AddressRepository;
import com.rodrigoramos.tech_challenge.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public AddressDTO findById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado"));
        return convertToDTO(address);
    }
    public List<AddressDTO> getAllByUserId(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(this::convertToDTO).toList();
    }

    public AddressDTO addAddressToUser(Long userId, AddressDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Address address = convertToEntity(dto, user);
        return convertToDTO(addressRepository.save(address));
    }

    public AddressDTO update(AddressDTO dto, Long id) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        existingAddress.setStreet(dto.street());
        existingAddress.setNumber(dto.number());
        existingAddress.setCity(dto.city());
        existingAddress.setNeighborhood(dto.neighborhood());
        existingAddress.setState(dto.state());
        existingAddress.setPostalCode(dto.postalCode());

        return convertToDTO(addressRepository.save(existingAddress));
    }

    public void delete(Long id) {
        if(!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
        try {
            addressRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private AddressDTO convertToDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getNeighborhood(),
                address.getState(),
                address.getPostalCode()
        );
    }

    private Address convertToEntity(AddressDTO dto, User user) {
        return new Address(
                dto.id(),
                dto.street(),
                dto.number(),
                dto.city(),
                dto.neighborhood(),
                dto.state(),
                dto.postalCode(),
                user
        );
    }
}
