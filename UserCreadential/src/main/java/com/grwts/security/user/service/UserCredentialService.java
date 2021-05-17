package com.grwts.security.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grwts.security.user.repo.UserCredentialRepo;
import com.grwts.security.user.repo.UserRepository;
import com.grwts.security.user.vo.UserCredentialControllerVo;
import com.grwts.security.user.vo.UserVo;

@Service
public class UserCredentialService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCredentialRepo userCredentialRepo;

	public UserCredentialControllerVo getCredential(String username) {
		List<UserCredentialControllerVo> allCredential = userCredentialRepo.findAll();
		if (allCredential.size() > 0) {
			return allCredential.stream()
					.filter(usercredential -> usercredential.getUserVo().getUsername().equalsIgnoreCase(username))
					.findFirst().orElse(null);
		}

		return null;
	}

	public boolean addCredential(String username, UserCredentialControllerVo userCredentialControllerVo) {
		boolean check = false;
		UserVo userVo = userRepository.findByUsername(username);
		if (userVo != null) {
			userCredentialControllerVo.setUserVo(userVo);
			userCredentialRepo.save(userCredentialControllerVo);
			check = true;
		}
		return check;
	}

	public boolean updateisAccountNonExpired(String username, boolean isAccountNonExpired) {
		boolean check = false;
		List<UserCredentialControllerVo> allCredential = userCredentialRepo.findAll();
		if (allCredential.size() > 0) {
			UserCredentialControllerVo userCredentialControllerVo = allCredential.stream()
					.filter(credential -> credential.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElse(null);
			userCredentialControllerVo.setAccountNonExpired(isAccountNonExpired);
			userCredentialRepo.save(userCredentialControllerVo);
			check = true;
		}
		return check;

	}

	public boolean updateisAccountNonLocked(String username, boolean isAccountNonLocked) {
		boolean check = false;
		List<UserCredentialControllerVo> allCredential = userCredentialRepo.findAll();
		if (allCredential.size() > 0) {
			UserCredentialControllerVo userCredentialControllerVo = allCredential.stream()
					.filter(credential -> credential.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElse(null);
			userCredentialControllerVo.setAccountNonLocked(isAccountNonLocked);
			userCredentialRepo.save(userCredentialControllerVo);
			check = true;
		}
		return check;
	}

	public boolean updateisCredentialsNonExpired(String username, boolean isCredentialsNonExpired) {
		boolean check = false;
		List<UserCredentialControllerVo> allCredential = userCredentialRepo.findAll();
		if (allCredential.size() > 0) {
			UserCredentialControllerVo userCredentialControllerVo = allCredential.stream()
					.filter(credential -> credential.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElse(null);
			userCredentialControllerVo.setCredentialsNonExpired(isCredentialsNonExpired);
			userCredentialRepo.save(userCredentialControllerVo);
			check = true;
		}
		return check;
	}

	public boolean updateisEnabled(String username, boolean isEnabled) {
		boolean check = false;
		List<UserCredentialControllerVo> allCredential = userCredentialRepo.findAll();
		if (allCredential.size() > 0) {
			UserCredentialControllerVo userCredentialControllerVo = allCredential.stream()
					.filter(credential -> credential.getUserVo().getUsername().equalsIgnoreCase(username)).findFirst()
					.orElse(null);
			userCredentialControllerVo.setEnabled(isEnabled);
			userCredentialRepo.save(userCredentialControllerVo);
			check = true;
		}
		return check;
	}

	public boolean deleteUserCredential(int userCredentialId) {
		userCredentialRepo.deleteById(userCredentialId);
		return true;
	}

}
