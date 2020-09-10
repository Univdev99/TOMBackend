package com.tom.admin;

import java.util.List;
import java.util.Map;

import com.tom.common.exception.ExceptionWrapper;
import com.tom.user.User;

public interface AdminService {

	List<Map<String, Object>> getAllPro() throws Exception;

	List<Map<String, Object>> getAllFirms() throws Exception;

	User createAdmin(User user) throws Exception;

	Boolean isAdminExist(String workEmail) throws Exception;

	List<User> getAdminList() throws Exception;

	Integer userActiveByAdmin(Long userId, String userStatus) throws Exception;

	List<Map<String, Object>> getAllFirmProfileList() throws Exception;

	List<Map<String, Object>> getAllProProfileList() throws Exception;

	Integer projectCompletedByAdmin(Long projectCompleteId) throws Exception;

	Integer paymentConfirmedByAdmin(Long projectCompleteId) throws Exception;

	List<Map<String, Object>> getAllProJobStatus() throws Exception;

	AdminProfit saveAdminProfit(AdminProfit adminProfit) throws Exception;

	Map<String, Object> getAdminProfit() throws Exception;

}
