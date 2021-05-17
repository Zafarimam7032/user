package com.grwts.security.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grwts.security.user.repo.UserAuthorityRepo;
import com.grwts.security.user.repo.UserCredentialRepo;
import com.grwts.security.user.repo.UserRepository;
import com.grwts.security.user.repo.UserRoleRepo;
import com.grwts.security.user.vo.UserAuthorityVo;
import com.grwts.security.user.vo.UserCredentialControllerVo;
import com.grwts.security.user.vo.UserRole;
import com.grwts.security.user.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserAuthorityService userAuthorityService;
	@Autowired
	private UserCredentialService userCredentialService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserVo> getAllUser() {
		List<UserVo> allUser = userRepository.findAll();
		if (allUser.size() > 0) {
			return allUser;
		} else {
			return null;
		}
	}

	public UserVo getUser(String username) {
		UserVo userInfo = userRepository.findByUsername(username);
		if (userInfo != null) {
			return userInfo;
		} else {
			return null;
		}
	}

	public boolean addUser(UserVo userVo) {
		boolean check = false;
		if (userVo.getUsername() != null && userVo.getPassword() != null) {
			UserVo dbUser = userRepository.findByUsername(userVo.getUsername());
			if (dbUser == null) {
				String password = userVo.getPassword();
				userVo.setPassword(passwordEncoder.encode(password));
				userRepository.save(userVo);
				check = true;
			}
		}
		return check;
	}

	public boolean updateUsername(String oldUsername, String newUsername) {

		boolean check = false;
		UserVo dbUser = userRepository.findByUsername(oldUsername);
		if (dbUser != null) {
			if (newUsername != null) {
				dbUser.setUsername(newUsername);
				userRepository.save(dbUser);
				check = true;
			}
		}
		return check;
	}

	public boolean updatePassword(String username, String password) {

		boolean check = false;
		UserVo dbUser = userRepository.findByUsername(username);
		if (dbUser != null) {
			if (password != null) {

				dbUser.setPassword(passwordEncoder.encode(password));
				userRepository.save(dbUser);
				check = true;
			}
		}
		return check;
	}

	public boolean deleteUser(String username) {
		boolean check = false;
		UserVo dbUser = userRepository.findByUsername(username);
		if (dbUser != null) {
			List<UserAuthorityVo> allUserAuthority = userAuthorityService.getAllUserAuthority(username);
			for (UserAuthorityVo authorityVo : allUserAuthority) {
				userAuthorityService.deleteUserAuthority(authorityVo.getId());
			}
			UserCredentialControllerVo userCredentialControllerVo = userCredentialService.getCredential(username);
			if (userCredentialControllerVo != null) {
				userCredentialService.deleteUserCredential(userCredentialControllerVo.getId());
			}
			UserRole userRole = userRoleService.getUserRole(username);
			
				userRoleService.deleteUserRole(userRole.getId());
			
			userRepository.deleteById(dbUser.getId());
			check = true;
		}
		return check;
	}

}
