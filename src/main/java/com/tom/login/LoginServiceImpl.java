package com.tom.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.common.encryption.SHA1Encryptor;
import com.tom.user.User;

@Service
public class LoginServiceImpl implements LoginService, Constant {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	GenericDao<Object[]> loginDao;

	@Autowired
	GenericDao<User> userDao;

	public Object invalidLoginAttempt(String username, Boolean increaseInvalidIpAttempt) throws Exception {
		LOGGER.debug("Execute method : invalidLoginAttempt()");
		StringBuilder query = new StringBuilder(
				"Exec OmnioneSP_InvalidLoginAttempt :username, :increaseInvalidIpAttempt");
		Map<String, Object> column = new HashMap<>();
		column.put("username", username);
		if (increaseInvalidIpAttempt) {
			column.put("increaseInvalidIpAttempt", 1);
		} else {
			column.put("increaseInvalidIpAttempt", 0);
		}
		return loginDao.executeSqlSelect(query, column);
	}

	@Override
	public Map<String, User> getUserDataBasedOnEmail(String email) throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		Map<String, User> userMap = new HashMap<>();
		queryString.append("FROM User WHERE  workEmail = :email");
		map.put(EMAIL, email);
		List<User> userList = userDao.executeHqlSelect(queryString, map);
		if (!userList.isEmpty()) {
			userMap.put(USER, userList.get(0));
			return userMap;
		}
		return null;
	}

	@Override
	public Map<String, Object> getUserByToken(String token) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		queryString.append("SELECT  * FROM UserTable WHERE forgotToken = :token");
		map.put(TOKEN, token);
		List<Map<String, Object>> userList = userDao.executeSqlSelectMap(queryString, map);
		if (!userList.isEmpty()) {
			resultMap.put(USER, userList.get(0));
			return resultMap;
		}
		return null;
	}

	@Override
	public Integer changePassWord(Long userId, String newPassWord) throws Exception {
		String encrptedPassWord = SHA1Encryptor.sHA1(newPassWord);
		StringBuilder query = new StringBuilder();
		query.append(" update UserTable SET password = :password,forgotToken = null,forgotTokenTime = GETUTCDATE(), modifiedDate = GETUTCDATE() WHERE userId = :userId ");
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		map.put(PASSWORD, encrptedPassWord);
		return userDao.executeSqlUpdate(query, map);
	}
}
