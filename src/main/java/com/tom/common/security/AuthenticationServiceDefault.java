package com.tom.common.security;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.tom.common.dao.GenericDao;
import com.tom.user.User;
import com.tom.user.UserService;

/**
 * @author Chandani Joshi This class is used for authentication and logout tasks
 */

@Transactional
public class AuthenticationServiceDefault implements AuthenticationService {
	static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceDefault.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	GenericDao<Object[]> objectDao;

	@Autowired
	UserService userService;

	private final AuthenticationManager authenticationManager;
	private final TokenManager tokenManager;

	public AuthenticationServiceDefault(AuthenticationManager authenticationManager, TokenManager tokenManager) {
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}

	@PostConstruct
	public void init() {
		LOGGER.debug("Execute Method : init() with: ", applicationContext);
	}

	@Override
	public TokenInfo authenticate(String login, String password, String role) throws Exception {
		LOGGER.debug("Execute Method : authenticate()");

		Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
		authentication = authenticationManager.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (authentication.getPrincipal() != null) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			TokenInfo newToken = tokenManager.createNewToken(userDetail);
			if (newToken == null) {
				return null;
			}
			return newToken;
		}
		return null;
	}

	@Override
	public boolean checkToken(String token) {
		LOGGER.debug("Execute Method : checkToken()");
		UserDetails userDetails = tokenManager.getUserDetails(token);
		if (userDetails == null) {
			User user = tokenManager.checkDBToken(token);
			if (user != null) {
				userDetails = new UserContext(user);
				tokenManager.addToken(userDetails, token);
			} else {
				return false;
			}
		}
		Authentication securityToken = new PreAuthenticatedAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(securityToken);

		return true;
	}

	@Override
	public void logout(String token) {
		UserDetails logoutUser = tokenManager.removeToken(token);
		LOGGER.debug("Execute Method : logout() : ", logoutUser);
		SecurityContextHolder.clearContext();
	}

	@Override
	public UserDetails currentUser() {
		LOGGER.debug("Execute Method : currentUser()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (UserDetails) authentication.getPrincipal();
	}

	@Override
	public void logoutUser(User user) throws Exception {
		// tokenManager.logoutUser(user);
	}

}
