package com.tom.user;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.common.encryption.SHA1Encryptor;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.common.project.ProjectProperties;
import com.tom.common.security.TokenManager;
import com.tom.common.util.MailSenderUtil;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;
import com.tom.util.StringUtil;

@Service
public class UserServiceImpl implements UserService, Constant {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String OPERATION = "Operation";
	private static final String GET_DASHBOARD = "GetDashboard";
	private static final String GET_KYP = "GetKYP";
	private static final String SAVE = "Save";
	private static final String USER_ID = "UserId";
	private static final String IS_DISPLAY = "IsDisplay";
	private static final String DEFAULT_FREQUENCY = "DefaultFrequency";
	private static final String REPORT_WIDGET_ID = "ReportWidgetId";
	private static final String SORT_ORDER = "SortOrder";
	private static final String USER_WIDGET_SETTING_ID = "UserWidgetSettingId";

	@Autowired
	GenericDao<User> userDao;

	@Autowired
	GenericDao<Object[]> genericDao;

	@Autowired
	TokenManager tokenManager;

	private Map<String, Object> newUsersList;

	@Override
	public User addUser(User user) throws Exception {
		LOGGER.debug("Execute method : addUser()");
		return userDao.saveOrUpdate(user);
	}

	@Override
	public User getUserById(long id) throws Exception {
		LOGGER.debug("Execute method : getUserById()");
		return userDao.get(User.class, id);
	}

	@Override
	public List<User> getUserList() throws Exception {
		LOGGER.debug("Execute method : getUserList()");
		return userDao.getAll(User.class);
	}

	@Override
	public void deleteUser(long id) throws Exception {
		LOGGER.debug("Execute method : deleteUser()");
		userDao.delete(userDao.get(User.class, id));
	}

	@Override
	public boolean isAuthorized(User user, String actionName) throws Exception {

		return true;
	}

	@Override
	@Transactional
	public User saveUser(User user) throws Exception {
		if (null == user.getUserId()) {
			user.setPassword(SHA1Encryptor.sHA1(user.getPassword()));
		}
		return this.userDao.saveOrUpdate(user);
	}

	@Override
	public User getUser(ObjectNode json) throws Exception {
		Long userId = json.get(USER_ID).asLong();

		return this.userDao.get(User.class, userId);
	}

	@Override
	public List<Map<String, Object>> getUserListByEntity(Long entityId) throws Exception {
		LOGGER.debug("execute getUserListByEntity()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(
				"SELECT  RUT.UserId AS UserId,RUT.lastName+', '+RUT.firstName AS UserName, RUT.status AS Status, RUT.isUserLocked AS IsUserLocked")
				.append(" FROM UserEBPMapTable RUEMT INNER JOIN EntityBusinessTable REBT ON RUEMT.EntityBusinessId=REBT.EntityBusinessId")
				.append(" INNER JOIN UserTable RUT ON RUEMT.UserId=RUT.UserId WHERE REBT.IsEntity=0 and REBT.ParentEntityId=:entityId")
				.append(" GROUP BY RUT.UserId,RUT.lastName,RUT.firstName,RUT.createdDate,RUT.status,RUT.isUserLocked ORDER BY RUT.createdDate DESC");

		Map<String, Object> column = new HashMap<>();
		column.put(ENTITY_ID, entityId);

		return userDao.executeSqlSelectMap(hqlQuery, column);
	}

	@Override
	public List<Map<String, Object>> getActionList(Long roleId) throws Exception {
		LOGGER.debug("execute getActionList()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(
				"select RAT.ActionId as ActionId,a.ActionDisplayName as title,a.IsReadOnlyAction as status from RoleAccessTable RAT ")
				.append("inner join ActionTable a on RAT.ActionId=a.ActionId where RAT.roleId=:roleId");

		Map<String, Object> map = new HashMap<>();
		map.put(ROLEID, roleId);

		return userDao.executeSqlSelectMap(hqlQuery, map);
	}

	@Override
	public List<Map<String, Object>> getDefaultPLP(Long userId) throws Exception {
		LOGGER.debug("execute getDefaultPLP()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(
				"select RUDPL.UserId as UserId, RUDPL.ClinicID as ClinicId, RUDPL.LocationId as LocationId, RUDPL.ProviderId as ProviderId, RUDPL.UserAsProvider as UserAsProvider, CT.EntityBusinessid AS BusinessId from UserDefaultPracticeLocationMappingTable RUDPL inner join UserTable RUT on RUDPL.UserId = RUT.UserId INNER JOIN ClinicTable CT ON RUDPL.ClinicID = CT.ClinicId where RUT.UserId=:userId");
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);

		return userDao.executeSqlSelectMap(hqlQuery, map);
	}

	@Override
	public void deleteAllUserDocument(Long userId) throws Exception {
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("delete from UserDocument where userId=:userId");

		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);

		userDao.executeUpdate(hqlQuery, map);
	}

	@Override
	public void deleteUserDocument(Long userDocumentId) throws Exception {
		LOGGER.debug("execute deleteUserDocument()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("delete from UserDocument where userDocumentId=:userDocumentId");

		Map<String, Object> map = new HashMap<>();
		map.put(USERDOCUMENTID, userDocumentId);

		userDao.executeUpdate(hqlQuery, map);
	}

	@Override
	public void deleteDefaultPLP(Long userId) throws Exception {
		LOGGER.debug("execute deleteDefaultPLP()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("delete from UserDefaultPracticeLocationMap where userId=:userId");

		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);

		userDao.executeUpdate(hqlQuery, map);
	}

	@Override
	public List<Map<String, Object>> getRoleByRoleId(Long roleId) throws Exception {
		LOGGER.debug("execute getRoleByRoleId()");
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("select case when AccessPermission = 'CUSTOM' then 'Custom'")
				.append(" when AccessPermission = 'FULL_CONTROLL' then 'Full Control'")
				.append(" else 'Read Only' end as AccessPermission ,RoleName as RoleName,IsActive as IsActive,RoleDescription as RoleDescription,RoleType as RoleType")
				.append(",RoleId as RoleId,EntityBusinessId as EntityBusinessId,IsSystemRole as IsSystemRole,isTempRole as isTempRole")
				.append(" from RoleTable where roleId=:roleId");

		Map<String, Object> column = new HashMap<>();
		column.put(ROLEID, roleId);

		return userDao.executeSqlSelectMap(hqlQuery, column);
	}

	@Override
	public List<Object[]> getUserFromEmail(String email) throws Exception {
		StringBuilder query = new StringBuilder(
				"select userId, InvalidSecurityQuestionAttempt, SecurityQuestionAttemptTime from userTable where email = :email");
		Map<String, Object> column = new HashMap<>();
		column.put("email", email);
		return genericDao.executeSqlSelect(query, column);
	}

	@Override
	public List<Map<String, Object>> getUserFromEmailMobile(String email) throws Exception {
		StringBuilder query = new StringBuilder(
				"select userId, InvalidSecurityQuestionAttempt, SecurityQuestionAttemptTime from userTable where email = :email");
		Map<String, Object> column = new HashMap<>();
		column.put("email", email);
		return genericDao.executeSqlSelectMap(query, column);
	}

	@Override
	public List<Object[]> getUserFromUsername(String username) throws Exception {
		StringBuilder query = new StringBuilder(
				"select UT.userId, UT.status, UT.IsUserLocked, UT.InvalidLoginCount, UT.email, RT.RoleName, UT.EntityBusinessId, UDPLMT.ClinicId, UT.Phone  from userTable UT inner join UserDefaultPracticeLocationMappingTable UDPLMT ON UT.UserId = UDPLMT.UserId INNER JOIN RoleTable RT ON UT.RoleId = RT.RoleId where username = :username");
		Map<String, Object> column = new HashMap<>();
		column.put("username", username);
		return genericDao.executeSqlSelect(query, column);
	}

	@Override
	public List<Object[]> getUserNameFromUserId(Long userId) throws Exception {
		StringBuilder query = new StringBuilder("select userName, IsUserLocked from UserTable where userId = :userId ");
		Map<String, Object> column = new HashMap<>();
		column.put(USER_ID, userId);
		return genericDao.executeSqlSelect(query, column);
	}

	@Override
	public void activateUser(Long userId) throws Exception {
		StringBuilder query = new StringBuilder(
				"update usertable set InvalidLoginCount = 0, IsUserLocked = 0, InvalidLoginCountDueToWrongIP = 0 where userId = :userId");
		Map<String, Object> column = new HashMap<>();
		column.put(USER_ID, userId);
		genericDao.executeSqlUpdate(query, column);

	}

	@Override
	public List<Map<String, Object>> getDashboardSetupData(Long userId) throws Exception {
		StringBuilder queryString = new StringBuilder("EXEC OmniOneSP_UserDashBoardSetup :Operation, :UserId");
		Map<String, Object> column = new HashMap<>();
		column.put(OPERATION, GET_DASHBOARD);
		column.put(USER_ID, userId);
		return userDao.executeSqlSelectMap(queryString, column);
	}

	@Override
	@Transactional
	public void saveWidgetDashboardSetupData(JsonNode widgetObjectsListJson, String operation) throws Exception {

		Map<String, Object> columnDelete = new HashMap<>();
		StringBuilder queryStringDelete = new StringBuilder("EXEC OmniOneSP_UserDashBoardSetup :Operation, :UserId");
		columnDelete.put(OPERATION, operation);
		columnDelete.put(USER_ID, CommonUtil.getContextUser().getUserId());
		userDao.executeSqlUpdate(queryStringDelete, columnDelete);

		int i = 1;
		for (JsonNode widgetObject : widgetObjectsListJson) {
			StringBuilder queryString = new StringBuilder(
					"EXEC OmniOneSP_UserDashBoardSetup :Operation, :UserId, :IsDisplay, :DefaultFrequency, :ReportWidgetId, :SortOrder");
			Map<String, Object> column = new HashMap<>();
			column.put(OPERATION, SAVE);
			column.put(USER_ID, CommonUtil.getContextUser().getUserId());
			column.put(IS_DISPLAY, widgetObject.get(IS_DISPLAY).asBoolean());
			column.put(DEFAULT_FREQUENCY,
					!StringUtil.isNullorEmpty(widgetObject.get(DEFAULT_FREQUENCY).asText())
							? widgetObject.get(DEFAULT_FREQUENCY).asText()
							: null);
			column.put(REPORT_WIDGET_ID, widgetObject.get(REPORT_WIDGET_ID).asLong());
			column.put(SORT_ORDER, i);
			userDao.executeSqlUpdate(queryString, column);
			i++;
		}
	}

	@Override
	public void saveOrUpdate(User user) throws Exception {
		userDao.saveOrUpdate(user);
	}

	@Override
	public List<Map<String, Object>> checkIsPasswordGonnaBeExpired(Long userId) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder("Exec OmniOneSP_CheckIsUserPasswordGoingToExpire :userId");
		Map<String, Object> column = new HashMap<>();
		column.put("userId", userId);
		return genericDao.executeSqlSelectMap(query, column);
	}

	@Override
	public List<Map<String, Object>> getLogedInUserDetails() throws Exception {
		Map<String, Object> queryMap = new HashMap<>();
		StringBuilder query = new StringBuilder(
				"SELECT TOP 1 UT.FirstName, UT.LastName, CT.ClinicName, LT.LocationName, LT.LocationCode, CT.ClinicCode, RT.RoleName, CT.ClinicId, DT.DocPath AS UserImgPath, DT.Docid FROM UserTable UT ")
						.append("LEFT JOIN UserDefaultPracticeLocationMappingTable UDPLMT ON UT.UserId = UDPLMT.UserId ")
						.append("LEFT JOIN ClinicTable CT ON CT.ClinicId = UDPLMT.ClinicId ")
						.append("LEFT JOIN RoleTable RT ON Rt.RoleId = UT.RoleId ")
						.append("LEFT JOIN LocationTable LT ON LT.LocationId = UDPLMT.LocationId ")
						.append(" LEFT JOIN DocumentMappingTable DMT ON UT.UserId = DMT.ObjectId and DMT.status = 1 and ModuleId = 98 and SubModuleId = 189")
						.append(" LEFT JOIN DocumentTable DT ON DT.Docid = DMT.DocumentId and DT.IsActive = 0")
						.append(" WHERE UT.UserId = :UserId ORDER BY DT.Docid DESC");
		queryMap.put("UserId", CommonUtil.getContextUser().getUserId());
		return userDao.executeSqlSelectMap(query, queryMap);
	}

	@Override
	public Set<String> checkDuplicate(User user) throws Exception {
		List<User> users = getDuplicateUsers(user);
		Set<String> validate = new HashSet<>();
		if (!users.isEmpty()) {
			for (User userFromDB : users) {
				if (user.getWorkEmail() != null && !userFromDB.getUserId().equals(user.getUserId())
						&& !user.getWorkEmail().equals("") && user.getWorkEmail().equals(userFromDB.getWorkEmail())) {
					validate.add(WORK_EMAIL);
				}
			}
		}
		return validate;
	}

	public List<User> getDuplicateUsers(User user) throws Exception {
		StringBuilder query = new StringBuilder("from User where workEmail = :workEmail and roleId = :roleId");
		Map<String, Object> column = new HashMap<>();
		column.put(WORK_EMAIL, user.getWorkEmail());
		column.put(ROLE_ID, user.getRoleId());
		return userDao.executeHqlSelect(query, column);
	}

	@Override
	public List<User> checkUserExists(Long entityId, String firstName, String lastName, String email,
			String contactNumber) throws Exception {
		StringBuilder query = new StringBuilder(
				"from User where (entityBusinessId = :entityBusinessId and (phone = :phone or email = :email))");
		Map<String, Object> column = new HashMap<>();
		column.put("entityBusinessId", entityId);
		column.put("phone", contactNumber);
		column.put("email", email);
		return userDao.executeHqlSelect(query, column);
	}

	@Override
	@Transactional
	public void updateSortOrder(JsonNode widgetObjectsListJson, String operation) throws Exception {
		Map<String, Object> columnDelete = new HashMap<>();
		StringBuilder queryStringDelete = new StringBuilder(
				"EXEC OmniOneSP_UserDashBoardSetup @argOperation = :Operation, @argUserId = :UserId, @argLayer = :Layer");
		columnDelete.put(OPERATION, operation);
		columnDelete.put(USER_ID, CommonUtil.getContextUser().getUserId());
		columnDelete.put("Layer", widgetObjectsListJson.get(0).get("Layer").asText());

		userDao.executeSqlUpdate(queryStringDelete, columnDelete);

		int i = 1;
		for (JsonNode widgetObject : widgetObjectsListJson) {
			StringBuilder queryString = new StringBuilder(
					"EXEC OmniOneSP_UserDashBoardSetup @argOperation = :Operation, @argUserId = :UserId, @argLayer = :Layer, @argSortOrder = :SortOrder, @argUserWidgetSettingId = :UserWidgetSettingId, @argReportWidgetId = :ReportWidgetId, @argFrequency = :DefaultFrequency");
			Map<String, Object> column = new HashMap<>();
			column.put(OPERATION, "UpdateSortOrder");
			column.put(USER_ID, CommonUtil.getContextUser().getUserId());
			column.put("Layer", widgetObject.get("Layer").asText());
			column.put(SORT_ORDER, i);
			column.put(USER_WIDGET_SETTING_ID,
					!StringUtil.isNullorEmpty(widgetObject.get(USER_WIDGET_SETTING_ID).asText())
							? widgetObject.get(USER_WIDGET_SETTING_ID).asText()
							: null);
			column.put(REPORT_WIDGET_ID, widgetObject.get(REPORT_WIDGET_ID).asLong());
			column.put(DEFAULT_FREQUENCY,
					widgetObject.has(DEFAULT_FREQUENCY) && !widgetObject.get(DEFAULT_FREQUENCY).isNull()
							? widgetObject.get(DEFAULT_FREQUENCY).asText()
							: null);
			userDao.executeSqlUpdate(queryString, column);
			i++;
		}
	}

	@Override
	public void generateAndSendEmailToUser(User user) throws Exception {
		String template = getTemplate();
		if (template != null && !template.equals("")) {
			template = template.replaceAll(":firstName", user.getFirstName());
			template = template.replaceAll(":lastName", user.getLastName());
			template = template.replaceAll(":workEmail", user.getWorkEmail());
			String random = generateRandomKey();
			template = template.replaceAll(":randomToken", random);
			setUserRandomKey(random, user);
			sendEmail(user.getWorkEmail(), "Change Password", new StringBuilder(template));
		}
	}

	private String generateRandomKey() {
		String possibleText = "ABpqrstEFGTUVWXOPQRSefghijklmnuvwxyz012345YZabCDocdHIJKLMN6789";
		StringBuilder randomKey = new StringBuilder();
		for (int i = 0; i <= 36; i++) {
			int index = (int) (possibleText.length() * Math.random());
			randomKey.append(possibleText.charAt(index));
		}
		return randomKey.toString();
	}

	@Override
	public void sendEmail(String emailAddress, String emailSubject, StringBuilder message) throws Exception {
		new Thread() {
			@Override
			public void run() {
				try {
					MailSenderUtil mailSenderUtil = new MailSenderUtil();
					mailSenderUtil.omniOneMailSender(emailAddress, "", "", "", "", emailSubject, message);

				} catch (Exception e) {
					LOGGER.error("Error in sendEmail()", e);
					throw new TOMRuntimeException("Error from sendEmail()", e);
				}
			}
		}.start();
	}

	public String getTemplate() throws Exception {
		StringBuffer content = new StringBuffer();
		StringBuilder fileName = new StringBuilder(ProjectProperties.get("masterTemplates"))
				.append("resetPassEmailTemplate.html");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName.toString()));
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					content.append(line);
					content.append(System.getProperty("line.separator"));
				}
			} finally {
				reader.close();
			}
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			throw new Exception("error in getting file");
		}

		return content.toString();
	}

	@Override
	public void setUserRandomKey(String randomKey, User user) throws Exception {
//		removeInvalidUsersFromList();
//		if (newUsersList == null) {
//			newUsersList = new ConcurrentHashMap<>();
//		}
//		newUsersList.put(randomKey, user);
		updateUserUrlKey(randomKey, user.getUserId());
	}

	private void updateUserUrlKey(String random, Long userId) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder("Update userTable set forgotToken = :random where userId = :userId");
		Map<String, Object> column = new HashMap<>();
		column.put("random", random);
		column.put("userId", userId);
		genericDao.executeSqlUpdate(query, column);
	}

	@Override
	public User getUserFromRandomKey(String randomKey) throws Exception {
		removeInvalidUsersFromList();
		if (newUsersList == null) {
			newUsersList = new ConcurrentHashMap<>();
		}
		newUsersList.clear();
		User user = (User) newUsersList.get(randomKey);
		if (user != null) {
			newUsersList.remove(randomKey);
		} else {
			StringBuilder query = new StringBuilder(" from User where urlKey = :random");
			Map<String, Object> column = new HashMap<>();
			column.put("random", randomKey);
			List<User> userList = userDao.executeHqlSelect(query, column);
			if (userList != null && !userList.isEmpty()) {
				Timestamp currentTime = DateUtil.getCurrentTimestamp();
				if (!currentTime.after(new Timestamp(userList.get(0).getModifiedDate().getTime() + (30 * 60 * 1000)))) {
					user = userList.get(0);
				}
				updateUserUrlKey(null, userList.get(0).getUserId());
			}
		}
		return user;
	}

	public void removeInvalidUsersFromList() {
		if (newUsersList != null) {
			Set<String> keys = newUsersList.keySet();
			Timestamp currentTime = DateUtil.getCurrentTimestamp();
			for (String key : keys) {
				User user = (User) newUsersList.get(key);
				if (currentTime.after(new Timestamp(user.getModifiedDate().getTime() + (30 * 60 * 1000)))) {
					newUsersList.remove(key);
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> getKYPSetupData(Long userId) throws Exception {
		StringBuilder queryString = new StringBuilder("EXEC OmniOneSP_UserDashBoardSetup :Operation, :UserId");
		Map<String, Object> column = new HashMap<>();
		column.put(OPERATION, GET_KYP);
		column.put(USER_ID, userId);
		return userDao.executeSqlSelectMap(queryString, column);
	}

	@Override
	public void updateActionCenterFlagOnLogout() throws Exception {
		StringBuilder query = new StringBuilder(
				"Update UserTable set DonotShowActionCenterModelOnLogout = 1 where userId = :UserId");
		Map<String, Object> column = new HashMap<>();
		column.put(USER_ID, CommonUtil.getContextUser().getUserId());
		userDao.executeSqlUpdate(query, column);

	}

	@Override
	public Map<String, Object> getUserFromUsernameForMobile(String username) throws Exception {
		StringBuilder query = new StringBuilder(
				"select UT.userId as userId, UT.status as status, UT.IsUserLocked as isUserLocked, UT.InvalidLoginCount as invalidLoginCount, IIF(CharIndex('@', UT.email) > 5, (select stuff(UT.email, 2, CharIndex('@', UT.email) - 3, REPLICATE('*', CharIndex('@', UT.email) - 3))), null) as email, RT.RoleName as roleName, UT.EntityBusinessId as entityBusinessId, UDPLMT.ClinicId as clinicId  from userTable UT inner join UserDefaultPracticeLocationMappingTable UDPLMT ON UT.UserId = UDPLMT.UserId INNER JOIN RoleTable RT ON UT.RoleId = RT.RoleId where username = :userName");
		Map<String, Object> column = new HashMap<>();
		column.put("userName", username);
		List<Map<String, Object>> userDetail = userDao.executeSqlSelectMap(query, column);
		if (!userDetail.isEmpty() && userDetail != null) {
			return userDetail.get(0);
		}
		return null;
	}

	@Override
	public Object getUserMap(Long userId) throws Exception {
		StringBuilder query = new StringBuilder("select * from usertable where userId = :userId");
		Map<String, Object> column = new HashMap<>();
		column.put(USER_ID, userId);
		return userDao.executeSqlSelectMap(query, column).get(0);
	}

	@Override
	public void addUpdateUserMenuSortOrder(JsonNode menuJson) throws Exception {
		StringBuilder queryString = new StringBuilder("EXEC OmniOneSP_AddUpdateUserMenuSortOrder :menuJson, :userId");
		Map<String, Object> column = new HashMap<>();
		column.put("menuJson", null != menuJson ? menuJson.toString() : "[]");
		column.put(USER_ID, CommonUtil.getContextUser().getUserId());
		userDao.executeSqlUpdate(queryString, column);
	}

	@Override
	public List<Map<String, Object>> getListOfClinicAndBusinessByUserIds(String userIds) throws Exception {

		StringBuilder query = new StringBuilder("");
		Map<String, Object> params = new HashMap<String, Object>();

		query.append("SELECT ");
		query.append("UEBPMT.UserId, ");
		query.append("CT.ClinicId, CT.ClinicName, CT.ClinicCode, ");
		query.append("EBT.EntityBusinessId, EBT.EntityName AS BusinessName, EBT.EntityCode ");
		query.append("FROM UserEBPMapTable UEBPMT ");
		query.append("INNER JOIN ClinicTable CT ON UEBPMT.ClinicID = CT.ClinicId ");
		query.append("INNER JOIN EntityBusinessTable EBT ON CT.EntityBusinessid = EBT.EntityBusinessId ");
		query.append("WHERE UserId IN (SELECT Item FROM dbo.fn_SplitRows(:userIds,',')) ");
		query.append("ORDER BY CT.ClinicName ");

		params.put("userIds", StringUtil.isNullorEmptyWithNullString(userIds) ? CommonUtil.getContextUser() : userIds);

		return userDao.executeSqlSelectMap(query, params);
	}

}
