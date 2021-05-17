package com.grwts.security.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grwts.security.user.repo.UserAuthorityRepo;
import com.grwts.security.user.repo.UserRepository;
import com.grwts.security.user.repo.UserRoleRepo;
import com.grwts.security.user.vo.UserAuthorityVo;
import com.grwts.security.user.vo.UserRole;
import com.grwts.security.user.vo.UserVo;

@Service
public class UserAuthorityService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAuthorityRepo userAuthorityRepo;
	@Autowired
	private UserRoleRepo userRoleRepo;

	public List<UserAuthorityVo> getAllUserAuthority(String username) {
		List<UserAuthorityVo> allAuthority = userAuthorityRepo.findAll();
		if (allAuthority.size() > 0) {
			return allAuthority.stream()
					.filter(authority -> authority.getUserVo().getUsername().equalsIgnoreCase(username))
					.collect(Collectors.toList());
		}
		return null;
	}

	public boolean addAuthority(String username, String userRole, UserAuthorityVo userAthorityVo) {
		boolean check = false;
		UserVo userVo = userRepository.findByUsername(username);
		if (userVo != null) {
			userAthorityVo.setUserVo(userVo);
			List<UserRole> allUserrole = userRoleRepo.findAll().stream()
					.filter(userrole -> userrole.getUserVo().getUsername().equalsIgnoreCase(username))
					.collect(Collectors.toList());
			UserRole userRole2 = allUserrole.stream().filter(userrole -> userrole.getRole().equalsIgnoreCase(userRole))
					.findFirst().orElse(null);
			if (userRole2 != null) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(userRole.toLowerCase()).append(":")
						.append(userAthorityVo.getAuthrity().toLowerCase());
				userAthorityVo.setAuthrity(stringBuilder.toString());
				userAuthorityRepo.save(userAthorityVo);
				check = true;
			}
		}
		return check;
	}

	public boolean updateAuthority(String username, int authorityId,String userrole, String userAuthority) {
		boolean check = false;
		UserAuthorityVo userAuthorityVo = userAuthorityRepo.findById(authorityId).orElse(null);
		if (userAuthorityVo != null) {
			List<UserRole> dbuserRoleVo = userRoleRepo.findAll().stream()
					.filter(userRoleVo -> userRoleVo.getUserVo().getUsername().equalsIgnoreCase(username))
					.collect(Collectors.toList());
			UserRole userRole2 = dbuserRoleVo.stream().filter(userRole -> userRole.getRole().equalsIgnoreCase(userrole))
					.findFirst().orElse(null);
			if (userRole2 != null) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(userrole.toLowerCase()).append(":").append(userAuthority.toLowerCase());
				userAuthorityVo.setAuthrity(stringBuilder.toString());
				check = true;
			}
		}
		return check;
	}

	public boolean deleteUserAuthority(int authorityId) {
		userAuthorityRepo.deleteById(authorityId);
		return true;
	}
}
