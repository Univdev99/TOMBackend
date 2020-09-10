package com.tom.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tom.common.security.UserContext;
import com.tom.user.User;

public class UserContext implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;

	public UserContext(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleId().toString()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getWorkEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
		// return user.getIsCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
//		Byte status = 1;
//		boolean isEnabled = false;
//		if (user.getStatus() != null && user.getStatus().equals(status)) {
//			isEnabled = true;
//		}
//		return isEnabled;
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return this == o || o != null && o instanceof UserContext && Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" + "user=" + user + '}';
	}

	public User getUser() {
		return this.user;
	}
}
