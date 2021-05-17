package com.grwts.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grwts.security.user.service.UserCredentialService;
import com.grwts.security.user.vo.UserCredentialControllerVo;

@RestController
@RequestMapping(path = "/home/usercredential/")
public class UserCredentialController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserCredentialService userCredentialService;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "get/all/username/{username}")
	public ResponseEntity<UserCredentialControllerVo> getUserCredential(@PathVariable("username") String username) {
		try {
			UserCredentialControllerVo credential = userCredentialService.getCredential(username);
			return ResponseEntity.of(Optional.of(credential));
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PostMapping(path = "add/username/{username}")
	public ResponseEntity<UserCredentialControllerVo> addCredential(@PathVariable("username") String username,
			@RequestBody(required = true) UserCredentialControllerVo crdential) {
		try {
			boolean credentialcheck = userCredentialService.addCredential(username, crdential);
			if (credentialcheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PutMapping(path = "update/username/{username}/isAccountNonExpired/{isAccountNonExpired}")
	public ResponseEntity<UserCredentialControllerVo> updateisAccountNonExpired(
			@PathVariable("username") String username,
			@PathVariable("isAccountNonExpired") boolean isAccountNonExpired) {
		try {
			boolean credentialcheck = userCredentialService.updateisAccountNonExpired(username, isAccountNonExpired);
			if (credentialcheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PutMapping(path = "update/username/{username}/isAccountNonLocked/{isAccountNonLocked}")
	public ResponseEntity<UserCredentialControllerVo> updateIsAccountNonLocked(
			@PathVariable("username") String username, @PathVariable("isAccountNonLocked") boolean isAccountNonLocked) {
		try {
			boolean credentialcheck = userCredentialService.updateisAccountNonExpired(username, isAccountNonLocked);
			if (credentialcheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PutMapping(path = "update/username/{username}/isCredentialsNonExpired/{isCredentialsNonExpired}")
	public ResponseEntity<UserCredentialControllerVo> updateIsCredentialsNonExpired(
			@PathVariable("username") String username,
			@PathVariable("isCredentialsNonExpired") boolean isCredentialsNonExpired) {
		try {
			boolean credentialcheck = userCredentialService.updateisCredentialsNonExpired(username,
					isCredentialsNonExpired);
			if (credentialcheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PutMapping(path = "update/username/{username}/isEnabled/{isEnabled}")
	public ResponseEntity<UserCredentialControllerVo> updateIsEnabled(@PathVariable("username") String username,
			@PathVariable("isEnabled") boolean isEnabled) {
		try {
			boolean credentialcheck = userCredentialService.updateisEnabled(username, isEnabled);
			if (credentialcheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@DeleteMapping(path = "delete/crdendential/{crdendentialId}")
	public ResponseEntity<UserCredentialControllerVo> deleteCredential(
			@PathVariable("crdendentialId") int crdendentialId) {
		try {
			boolean deleteUserCredential = userCredentialService.deleteUserCredential(crdendentialId);
			if (deleteUserCredential) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
