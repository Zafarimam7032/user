package com.grwts.security.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grwts.security.user.vo.UserVo;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Integer> {

	public UserVo findByUsername(String username);

}
