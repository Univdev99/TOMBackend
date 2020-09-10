package com.tom.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface UserService {

	/**
	 * To save or update user
	 * 
	 * @param user
	 * @return user
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public User addUser(User user) throws Exception;

	/**
	 * To get user object by userId
	 * 
	 * @param id
	 * @return user
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public User getUserById(long id) throws Exception;

	/**
	 * To get list of users
	 * 
	 * @return userList
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public List<User> getUserList() throws Exception;

	/**
	 * To delete user by userId
	 * 
	 * @param id
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public void deleteUser(long id) throws Exception;

	/**
	 * @param user
	 * @param actionName
	 * @return true/false
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 17, 2017
	 */
	public boolean isAuthorized(User user, String actionName) throws Exception;

	/**
	 * Get businessClinicsTable by RoleType
	 * 
	 * @param user
	 * @param entityRole
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 28, 2017
	 */

	/**
	 * Get businessClinicsTable
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 28, 2017
	 */

	/**
	 * To get User attached business list
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 * @author AnuShah
	 * @ModifiedBy AnuShah
	 * @ModifiedDate Mar 20, 2017
	 */

	/**
	 * To get User attached practise list
	 * 
	 * @param businessClinicHashtable
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Feb 28, 2017
	 */

	/**
	 * To get Entity of user
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 * @author AnuShah
	 * @ModifiedBy AnuShah
	 * @ModifiedDate Mar 17, 2017
	 */

	/**
	 * Convert UserMapping List to DTO
	 * 
	 * @param businessClinicsTable
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Mar 20, 2017
	 */

	public User saveUser(User user) throws Exception;

	public User getUser(ObjectNode json) throws Exception;

	/**
	 * Get User List based on entity
	 * 
	 * @param entityId
	 * @return
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 3, 2017
	 */
	public List<Map<String, Object>> getUserListByEntity(Long entityId) throws Exception;

	/**
	 * Save User Document Data
	 * 
	 * @param userDocument
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 5, 2017
	 */

	/**
	 * Get User DocumentData on userId
	 * 
	 * @param userId
	 * @return userDocument bean
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 5, 2017
	 */

	/**
	 * Get Action List on userId
	 * 
	 * @param userId
	 * @return list of map
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 5, 2017
	 */
	public List<Map<String, Object>> getActionList(Long userId) throws Exception;

	/**
	 * Get Action List on userId
	 * 
	 * @param userId
	 * @return list of map
	 * @throws Exception
	 * @author DTalati
	 * @ModifiedBy Dtalati
	 * @ModifiedDate May 8, 2017
	 */
	public List<Map<String, Object>> getDefaultPLP(Long userId) throws Exception;

	/**
	 * Delete All User Document
	 * 
	 * @param userId
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 8, 2017
	 */
	public void deleteAllUserDocument(Long userId) throws Exception;

	/**
	 * Delete User Document on UserDocumentId
	 * 
	 * @param userDocumentId
	 * @throws Exception
	 * @author VRajyaguru
	 * @ModifiedBy VRajyaguru
	 * @ModifiedDate May 9, 2017
	 */
	public void deleteUserDocument(Long userDocumentId) throws Exception;

	/**
	 * Get Default Provide Location Practice on userId
	 * 
	 * @param userId
	 * @return list of map
	 * @throws Exception
	 * @author DTalati
	 * @ModifiedBy Dtalati
	 * @ModifiedDate May 10, 2017
	 */
	public void deleteDefaultPLP(Long userId) throws Exception;

	/**
	 * Get Role on roleId
	 * 
	 * @param userId
	 * @return list of map
	 * @throws Exception
	 * @author DTalati
	 * @ModifiedBy Dtalati
	 * @ModifiedDate May 10, 2017
	 */
	public List<Map<String, Object>> getRoleByRoleId(Long roleId) throws Exception;



	public List<Object[]> getUserFromEmail(String email) throws Exception;

	public List<Map<String, Object>> getUserFromEmailMobile(String email) throws Exception;

	public List<Object[]> getUserFromUsername(String username) throws Exception;

	public List<Object[]> getUserNameFromUserId(Long userId) throws Exception;

	public void activateUser(Long userId) throws Exception;

	/**
	 * Get Dashboard Setup Data
	 * 
	 * @param userId
	 * @param clinicId
	 * @return list of widget data
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Oct 24, 2017
	 */
	public List<Map<String, Object>> getDashboardSetupData(Long userId) throws Exception;

	/**
	 * Save Widget Dashboard Setup Data
	 * 
	 * @param widgetObjectsListJson
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Oct 24, 2017
	 */
	public void saveWidgetDashboardSetupData(JsonNode widgetObjectsListJson, String operation) throws Exception;

	/**
	 * Save Widget Dashboard Setup Data
	 * 
	 * @param widgetObjectsListJson
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Dec 27, 2017
	 */
	public void saveOrUpdate(User user) throws Exception;

	/**
	 * Save User Menu Object
	 * 
	 * @param userMenu
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Jan 21, 2017
	 */

	/**
	 * Get User Menu Object
	 * 
	 * @param userMenuId
	 * @param menuId
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Jan 21, 2017
	 */

	/**
	 * Add/Update User Menu Object
	 * 
	 * @param userMenu
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Jan 21, 2018
	 */

	public List<Map<String, Object>> checkIsPasswordGonnaBeExpired(Long userId) throws Exception;

	/**
	 * @param null
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Jan 28, 2017
	 */
	public List<Map<String, Object>> getLogedInUserDetails() throws Exception;

	public Set<String> checkDuplicate(User user) throws Exception;

	public List<User> checkUserExists(Long entityId, String firstName, String lastName, String email, String contactNumber) throws Exception;

	/**
	 * Update Sort Order
	 * 
	 * @param widgetObjectsListJson
	 * @param operation
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Oct 24, 2017
	 */
	public void updateSortOrder(JsonNode widgetObjectsListJson, String operation) throws Exception;

	public void setUserRandomKey(String randomKey, User user) throws Exception;

	public User getUserFromRandomKey(String randomKey) throws Exception;

	/**
	 * Get KYP Setup Data
	 * 
	 * @param userId
	 * @return list of widget data
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate May 14, 2017
	 */
	public List<Map<String, Object>> getKYPSetupData(Long userId) throws Exception;

	public void updateActionCenterFlagOnLogout() throws Exception;

	/**
	 * @param user
	 * @return
	 * @author HTotlani
	 * @ModifiedBy HTotlani
	 * @ModifiedDate 26-Aug-2019
	 */

	/**
	 * @param username
	 * @return
	 * @author HTotlani
	 * @ModifiedBy HTotlani
	 * @ModifiedDate 28-Aug-2019
	 */
	public Map<String, Object> getUserFromUsernameForMobile(String username) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @author HTotlani
	 * @ModifiedBy HTotlani
	 * @ModifiedDate 28-Aug-2019
	 */
	public Object getUserMap(Long userId) throws Exception;

	/**
	 * @param status
	 * @return
	 * @author KBaria
	 * @ModifiedBy KBaria
	 * @ModifiedDate 10-September-2019
	 */
	

	/**
	 * Add/Update User Menu Object
	 * 
	 * @param userMenu
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Jan 21, 2018
	 */
	public void addUpdateUserMenuSortOrder(JsonNode menuJson) throws Exception;

	/**
	 * Delete User Menu
	 * 
	 * @param userId
	 * @param roleId
	 * @throws Exception
	 * @author Kaushal Baria
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate Sept 11, 2018
	 */

	// public void updateLandingPage(Long userId, JsonNode lendingpage) throws Exception;
	/**
	 * @param entityId
	 * @return
	 * @author Karan Soni
	 * @param userIds
	 * @ModifiedBy Karan Soni
	 * @ModifiedDate Dec 20, 2019
	 */

	public void generateAndSendEmailToUser(User user) throws Exception;

	public void sendEmail(String emailAddress, String emailSubject, StringBuilder message) throws Exception;

	/**
	 * @return
	 * @author List<Map<String, Object>>
	 * @ModifiedBy Kaushal Baria
	 * @ModifiedDate April 21, 2020
	 */
	public List<Map<String, Object>> getListOfClinicAndBusinessByUserIds(String userIds) throws Exception;
}
