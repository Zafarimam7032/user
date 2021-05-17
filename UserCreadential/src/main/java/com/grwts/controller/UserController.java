package com.grwts.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grwts.security.user.service.UserService;
import com.grwts.security.user.vo.UserVo;

@RestController
@RequestMapping(path = "/home/user/")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping(path = "/get/all")
	public ResponseEntity<List<UserVo>> getAllUserDetails() {
		try {
			List<UserVo> listAllUser = userService.getAllUser();
			return ResponseEntity.of(Optional.of(listAllUser));
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@GetMapping(path = "get/{username}")
	public ResponseEntity<UserVo> getUserDetails(@PathVariable("username") String username) {
		try {
			UserVo userVo = userService.getUser(username);
			return ResponseEntity.of(Optional.of(userVo));
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}

	}

	@PostMapping(path = "add/user")
	public ResponseEntity<UserVo> addUserDeatils(@RequestBody(required = true) UserVo userVo) {
		try {
			boolean userCheck = userService.addUser(userVo);
			if (userCheck) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (NullPointerException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "update/oldusername/{oldusername}/newUsername/{newUsername}")
	public ResponseEntity<UserVo> updateUsername(@PathVariable("oldusername") String oldusername,
			@PathVariable("newUsername") String newUsername) {
		try {
			boolean check = userService.updateUsername(oldusername, newUsername);
			if (check) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "update//{username}/password/{password}")
	public ResponseEntity<UserVo> updatePassword(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		try {
			boolean check = userService.updateUsername(username, password);
			if (check) {
				return ResponseEntity.accepted().build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "update/username/{username}")
	public ResponseEntity<UserVo> deleteUser(@PathVariable("username") String username) {
		try {
			boolean check = userService.deleteUser(username);
			if (check) {
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
