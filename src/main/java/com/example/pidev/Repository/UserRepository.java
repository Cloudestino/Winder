package com.example.pidev.Repository;

import com.example.pidev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
}
