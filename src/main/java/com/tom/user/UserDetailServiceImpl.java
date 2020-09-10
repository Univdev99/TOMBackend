package com.tom.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tom.common.dao.GenericDao;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.common.security.AuthenticationService;
import com.tom.common.security.UserContext;


/**
 * Implements Spring Security {@link UserDetailServiceImpl} that is injected into authentication provider in configuration XML. It interacts with domain, loads user details and wraps it into
 * {@link UserContext} which implements Spring Security {@link UserDetails}.
 * 
 * @author CCJoshi
 *
 */
@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailService {
	static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Autowired
	GenericDao<User> genericDao;

	@Autowired
	GenericDao<Object[]> objectDao;

	/**
	 * This will be called from
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
	 * when {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)} calls {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}. Easy.
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug(" *** UseDetailService.loadUserByUsername");
		User user = getByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		return new UserContext(user);
	}

	/**
	 * To check if user exist
	 * 
	 * @param UserName
	 * @return user
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public User getByUserName(String username) {
		try {
			if (username == null || "".equals(username)) {
				return null;
			}

			Map<String, Object> map = new HashMap<>();
			map.put("workEmail", username);

			List<User> userList = genericDao.getListByRistrictions(User.class, map, null, null);
			if (!userList.isEmpty()) {
				User user = genericDao.get(User.class, userList.get(0).getUserId());
				List<Map<String, Object>> passwordDetails = null;
				StringBuilder query = new StringBuilder("Exec OmniOneSP_CheckIsUserPasswordGoingToExpire :userId");
				Map<String, Object> column = new HashMap<>();
				column.put("userId", user.getUserId());
//				passwordDetails = objectDao.executeSqlSelectMap(query, column);
				
				column.clear();
				return user;
			}
		} catch (Exception e) {
			throw new TOMRuntimeException("Exception in getByUserName", e);
		}
		return null;
	}
}
