package com.ituwei.polls.Repository;

import java.util.List;
import java.util.Optional;

import com.ituwei.polls.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdin(List<Long> userIds);

    Boolean existByUsername(String username);

    Boolean existByEmail(String email);
}
