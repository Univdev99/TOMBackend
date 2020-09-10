package com.tom.user;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;

@Component
public class UserValidator implements Validator,Constant {

	static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {

		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.debug("Error in Validate()");
		try {
			User user = (User) target;
			Set<String> validation = userService.checkDuplicate(user);
			if (!validation.isEmpty()) {
				if (validation.contains(WORK_EMAIL)) {
					errors.rejectValue(WORK_EMAIL, "workEmail.exists");
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Validate()", ex);
			throw new TOMRuntimeException("Error in validate()", ex);
		}

	}

}
