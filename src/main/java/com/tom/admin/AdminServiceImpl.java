package com.tom.admin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.common.encryption.SHA1Encryptor;
import com.tom.project.ProjectComplete;
import com.tom.user.User;

@Service
public class AdminServiceImpl implements AdminService, Constant {

	@Autowired
	GenericDao<Serializable> genericDao;

	@Autowired
	GenericDao<User> userDao;

	@Autowired
	GenericDao<ProjectComplete> projectCompleteDao;

	@Autowired
	GenericDao<AdminProfit> adminProfitDao;

	@Override
	public List<Map<String, Object>> getAllPro() throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("EXEC TOM_SearchFirmJobForAdmin");
		List<Map<String, Object>> firmList = genericDao.executeSqlSelectMap(queryString, null);
		if (!firmList.isEmpty()) {
			return firmList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAllFirms() throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("EXEC TOM_SearchFirmJobForAdmin");
		List<Map<String, Object>> firmList = genericDao.executeSqlSelectMap(queryString, null);
		if (!firmList.isEmpty()) {
			return firmList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAllFirmProfileList() throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("EXEC TOM_SearchFirmProfileForAdmin");
		List<Map<String, Object>> firmProfileList = genericDao.executeSqlSelectMap(queryString, null);
		if (!firmProfileList.isEmpty()) {
			return firmProfileList;
		} else {
			return null;
		}
	}

	@Override
	public User createAdmin(User user) throws Exception {
		user.setPassword(SHA1Encryptor.sHA1(user.getPassword()));
		return userDao.saveOrUpdate(user);
	}

	@Override
	public Boolean isAdminExist(String workEmail) throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("select COUNT(1) as dataCount FROM UserTable WHERE workEmail = :workEmail AND roleId = 1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WORK_EMAIL, workEmail);
		List<Map<String, Object>> adminList = userDao.executeSqlSelectMap(queryString, map);
		if (!adminList.isEmpty() && !adminList.get(0).get("dataCount").equals(0)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public List<User> getAdminList() throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("FROM User WHERE roleId = :userRoleType");
		Map<String, Object> column = new HashMap<String, Object>();
		column.put(USER_ROLE_TYPE, Long.valueOf(ADMIN_USER_TYPE));
		return userDao.executeHqlSelect(query, column);
	}

	@Override
	public Integer userActiveByAdmin(Long userId, String userStatus) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(" update UserTable SET userStatus = :userStatus WHERE userId = :userId ");
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		map.put(USER_STATUS, userStatus);
		return userDao.executeSqlUpdate(query, map);
	}

	@Override
	public List<Map<String, Object>> getAllProProfileList() throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("EXEC TOM_SearchProfessionalForAdmin");
		List<Map<String, Object>> proProfileList = genericDao.executeSqlSelectMap(queryString, null);
		if (!proProfileList.isEmpty()) {
			return proProfileList;
		} else {
			return null;
		}
	}

	@Override
	public Integer projectCompletedByAdmin(Long projectCompleteId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectCompleteTable SET isCompletedByAdmin = 1 WHERE projectCompleteId = :projectCompleteId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_COMPLETE_ID, projectCompleteId);
		return projectCompleteDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer paymentConfirmedByAdmin(Long projectCompleteId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectCompleteTable SET isPaymentReceived = 1 WHERE projectCompleteId = :projectCompleteId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_COMPLETE_ID, projectCompleteId);
		return projectCompleteDao.executeSqlUpdate(query, map);
	}

	@Override
	public List<Map<String, Object>> getAllProJobStatus() throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("EXEC TOM_SearchAllProJobStatus");
		List<Map<String, Object>> jobList = genericDao.executeSqlSelectMap(queryString, null);
		if (!jobList.isEmpty()) {
			return jobList;
		} else {
			return null;
		}
	}

	@Override
	public AdminProfit saveAdminProfit(AdminProfit adminProfit) throws Exception {
		StringBuilder query = new StringBuilder(" update AdminProfitTable SET status = 0");
		projectCompleteDao.executeSqlUpdate(query, null);
		return adminProfitDao.saveOrUpdate(adminProfit);
	}

	@Override
	public Map<String, Object> getAdminProfit() throws Exception {
		StringBuilder sb = new StringBuilder("SELECT profit from adminProfitTable WHERE status = 1");
		List<Map<String, Object>> list = adminProfitDao.executeSqlSelectMap(sb, null);
		Map<String, Object> profitMap = new HashMap<>();
		if (!list.isEmpty()) {
			profitMap.put("profit", list.get(0).get("profit"));
		} else {
			profitMap.put("profit", null);
		}
		return profitMap;
	}

}
