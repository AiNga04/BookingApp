package com.booking.userservice.repository;

import com.booking.userservice.enums.RoleType;
import com.booking.userservice.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRoleType(RoleType roleType);
}
