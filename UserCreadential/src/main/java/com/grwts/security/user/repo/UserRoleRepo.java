package com.grwts.security.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grwts.security.user.vo.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
