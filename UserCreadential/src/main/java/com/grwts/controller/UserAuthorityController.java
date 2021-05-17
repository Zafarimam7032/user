package com.grwts.controller;

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

import com.grwts.security.user.service.UserAuthorityService;
import com.grwts.security.user.vo.UserAuthorityVo;
import com.grwts.security.user.vo.UserRole;

@RestController
@RequestMapping(path = "/home/userauthority/")
public class UserAuthorityController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAuthorityService userAuthorityService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "get/all")
	public ResponseEntity<List<UserAuthorityVo>> getAllUserAuthority(String username) {
		try {
			List<UserAuthorityVo> allUserRole = userAuthorityService.getAllUserAuthority(username);
			return ResponseEntity.of(Optional.of(allUserRole));
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PreAuthorize("hasAuthority('admin:write')")
	@PostMapping(path = "add/userauthority/{username}/userrole/{userRole}")
	public ResponseEntity<UserAuthorityVo> addUserAuthEntity(@PathVariable("username") String username,
			@PathVariable("userRole") String userRole, @RequestBody(required = true) UserAuthorityVo userAuthorityVo) {
		try {
			boolean chekUserRole = userAuthorityService.addAuthority(username, userRole, userAuthorityVo);
			if (chekUserRole) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@PutMapping(path = "/update/userauthority/username/{username}/authorityId/{authorityId}/userrole/{userrole}/userauthority/{userauthority}")
	public ResponseEntity<UserAuthorityVo> updateAuthority(@PathVariable("username") String username,
			@PathVariable("authorityId") int authorityId, @PathVariable("userrole") String userrole,
			@PathVariable("userauthority") String userauthority) {
		try {
			boolean chekUserRole = userAuthorityService.updateAuthority(username, authorityId, userrole, userauthority);
			if (chekUserRole) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	@PreAuthorize("hasAuthority('admin:write')")
	@DeleteMapping(path = "/delete/{authorityId}")
	public ResponseEntity<UserAuthorityVo> deleteUserRole(@PathVariable("authorityId") int authorityId) {
		try {
			boolean deleteUserRole = userAuthorityService.deleteUserAuthority(authorityId);
			if (deleteUserRole) {
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
