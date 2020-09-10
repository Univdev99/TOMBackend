package com.tom.login;

import java.util.List;
import java.util.Map;

import com.tom.common.exception.ExceptionWrapper;
import com.tom.user.User;

public interface LoginService {

	public Object invalidLoginAttempt(String username, Boolean increaseInvalidIpAttempt) throws Exception;

	public Map<String, User> getUserDataBasedOnEmail(String email) throws Exception;

	public Map<String, Object> getUserByToken(String token) throws Exception;

	public Integer changePassWord(Long userId, String newPassWord) throws Exception;

}
