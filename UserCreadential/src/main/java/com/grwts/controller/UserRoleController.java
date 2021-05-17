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

import com.grwts.security.user.service.UserRoleService;
import com.grwts.security.user.vo.UserRole;

@RestController
@RequestMapping(path = "/home/userrole/")
public class UserRoleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRoleService userRoleService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "get/all/{username}")
	public ResponseEntity<UserRole> getAllUserRole(@PathVariable("username") String username) {
		try {
			UserRole UserRole = userRoleService.getUserRole(username);
			return ResponseEntity.of(Optional.of(UserRole));
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PreAuthorize("hasAuthority('admin:write')")
	@PostMapping(path = "add/userrole/{username}")
	public ResponseEntity<UserRole> addUserRole(@PathVariable("username") String username,
			@RequestBody(required = true) UserRole userRole) {
		try {
			boolean chekUserRole = userRoleService.addUserRole(username, userRole);
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
	@PutMapping(path = "/update/roleid/{roleid}/userrole/{userrole}")
	public ResponseEntity<UserRole> updateUserRole(@PathVariable("roleid") int roleid,
			@PathVariable("userrole") String userrole) {
		try {
			boolean chekUserRole = userRoleService.updateUserRole(roleid, userrole);
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
	@DeleteMapping(path = "/delete/{userroleId}")
	public ResponseEntity<UserRole> deleteUserRole(@PathVariable("userroleId") int userroleId) {
		try {
			boolean deleteUserRole = userRoleService.deleteUserRole(userroleId);
			if (deleteUserRole) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
