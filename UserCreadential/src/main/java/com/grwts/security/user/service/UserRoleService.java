package com.grwts.security.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grwts.security.user.repo.UserRepository;
import com.grwts.security.user.repo.UserRoleRepo;
import com.grwts.security.user.vo.UserRole;
import com.grwts.security.user.vo.UserVo;

@Service
public class UserRoleService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepo userRoleRepo;

	public UserRole getUserRole(String username) {
		List<UserRole> allUserRole = userRoleRepo.findAll();
		if (allUserRole.size() > 0) {
			return allUserRole.stream()
					.filter(userrole -> userrole.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElseThrow();
		}
		return null;
	}

	public boolean addUserRole(String username, UserRole userRole) {
		boolean check = false;
		UserVo userVo = userRepository.findByUsername(username);

		List<UserRole> allUserRole = userRoleRepo.findAll();
		if (allUserRole.size() > 0) {
			UserRole userRole2 = allUserRole.stream()
					.filter(userRoleDb -> userRoleDb.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElse(null);
			if (userRole2 == null) {
				if (userVo != null) {
					String role = userRole.getRole();
					String upperCase = role.toUpperCase();
					userRole.setRole(upperCase);
					userRole.setUserVo(userVo);
					userRoleRepo.save(userRole);
					check = true;
				}
			}
		}

		return check;
	}

	public boolean updateUserRole(int roleId, String userRole) {
		boolean check = false;
		UserRole dbUserRole = userRoleRepo.findById(roleId).orElse(null);
		if (dbUserRole != null) {

			dbUserRole.setRole(userRole.toUpperCase());
			userRoleRepo.save(dbUserRole);
			check = true;

		}
		return check;
	}

	public boolean deleteUserRole(int roleId) {
		userRoleRepo.deleteById(roleId);
		return true;
	}
}
