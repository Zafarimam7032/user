package com.grwts.security.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grwts.security.user.vo.UserAuthorityVo;

@Repository
public interface UserAuthorityRepo extends JpaRepository<UserAuthorityVo, Integer> {

}
