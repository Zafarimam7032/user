package com.grwts.permission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.grwts.security.user.service.UserAuthorityService;
import com.grwts.security.user.service.UserRoleService;
import com.grwts.security.user.vo.UserAuthorityVo;
import com.grwts.security.user.vo.UserRole;

@Service
public class UserPermission {
	@Autowired
	private UserAuthorityService authorityService;
	@Autowired
	private UserRoleService roleService;

	public Set<SimpleGrantedAuthority> getGrantedAuthorities(String username) {
		List<UserAuthorityVo> getAuthority = authorityService.getAllUserAuthority(username);
		UserRole UserRole = roleService.getUserRole(username);
		Set<SimpleGrantedAuthority> authority = getAuthority.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getAuthrity())).collect(Collectors.toSet());
		authority.add(new SimpleGrantedAuthority("ROLE_" + UserRole.getRole()));

		return authority;
	}
}
