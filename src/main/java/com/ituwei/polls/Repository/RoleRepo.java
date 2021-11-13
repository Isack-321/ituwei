package com.ituwei.polls.Repository;

import java.util.Optional;

import com.ituwei.polls.Model.Role;
import com.ituwei.polls.Model.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByroleName(RoleName roleName);
}
