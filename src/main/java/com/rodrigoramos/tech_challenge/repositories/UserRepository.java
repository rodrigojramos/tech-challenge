package com.rodrigoramos.tech_challenge.repositories;

import com.rodrigoramos.tech_challenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
