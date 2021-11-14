package com.ituwei.polls.Repository;

import java.util.List;
import java.util.Optional;

import com.ituwei.polls.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    List<User> findByIdIn(List<Long> userIds);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
