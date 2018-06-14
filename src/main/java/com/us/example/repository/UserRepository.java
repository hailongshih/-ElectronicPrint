package com.us.example.repository;

import com.us.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Override
	User saveAndFlush(User user);

	User findByEmail(String email);

	User findByUsername(String username);
}