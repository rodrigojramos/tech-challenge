package com.rodrigoramos.tech_challenge.repositories;

import com.rodrigoramos.tech_challenge.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
