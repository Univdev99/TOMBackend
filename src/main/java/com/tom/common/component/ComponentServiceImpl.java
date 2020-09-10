
package com.tom.common.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.common.Notification;
import com.tom.common.beans.Calender;
import com.tom.common.beans.Help;
import com.tom.common.beans.Review;
import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.common.project.ProjectProperties;
import com.tom.common.util.MailSenderUtil;
import com.tom.user.User;
import com.tom.util.CommonUtil;

@Service
public class ComponentServiceImpl implements ComponentService, Constant {

	@Autowired
	GenericDao genericDAO;

	@Autowired
	GenericDao<Calender> calenderDAO;

	@Autowired
	GenericDao<Help> helpDao;

	@Autowired
	GenericDao<Review> reviewDao;

	@Autowired
	GenericDao<Notification> notificationDao;

	@Override
	public List<Map<String, Object>> getSearchData(String... params) throws Exception {
		if (params[0].contains("omnionto_")) {
			return genericDAO.getDataFromSP("OmniOntoSP_CommonSoftSearch", params);
		} else {
			return genericDAO.getDataFromSP("TOM_CommonSoftSearch", params);
		}
	}

	@Override
	public Help saveAndSendHelp(Help help) throws Exception {
		Help savedHelp = helpDao.saveOrUpdate(help);
		MailSenderUtil m = new MailSenderUtil();
		StringBuilder message = new StringBuilder();
		message.append(savedHelp.getMessage());
		m.omniOneMailSender("savaniyaamit@gmail.com", "", "", "", "", savedHelp.getSubject(), message);
		return savedHelp;

	}

	@Override
	public Review saveReview(Review review) throws Exception {
		// TODO Auto-generated method stub
		return reviewDao.saveOrUpdate(review);
	}

	@Override
	public List<Map<String, Object>> getLatestAverageReview(Long proProfileId, Long firmProfileId) throws Exception {
		List<Map<String, Object>> rateList = new ArrayList();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder queryString = new StringBuilder();
		queryString.append("select AVG(rating) as averageRating, COUNT(1) as reviewCount from ReviewTable");
		if (proProfileId != null || firmProfileId != null) {
			if (proProfileId != null) {
				params.put(PRO_PROFILE_ID, proProfileId);
				queryString.append(" WHERE proProfileId = :proProfileId AND reviewFor = 'Professional'  ");
			} else if (firmProfileId != null) {
				params.put(FIRM_PROFILE_ID, firmProfileId);
				queryString.append(" WHERE firmProfileId = :firmProfileId  AND reviewFor = 'Firm' ");
			}
			rateList = reviewDao.executeSqlSelectMap(queryString, params);
			if (!rateList.isEmpty()) {
				return rateList;
			}
		}
		return null;
	}

	@Override
	public List<Notification> notificationSave(List<Notification> notificationList) throws Exception {
		List<Notification> savedNotificationList = new ArrayList<Notification>();
		for (Notification notification : notificationList) {
			savedNotificationList.add(notificationDao.saveOrUpdate(notification));
		}
		return savedNotificationList;
	}

	@Override
	public Object getNotification(Long userId, String argFrom) throws Exception {
		Map<String, Object> map = new HashMap<>();
		StringBuilder sb = new StringBuilder("EXEC TOM_GetNotificationCount @userId = :userId, @argFrom = :argFrom");
		map.put(USER_ID, userId);
		map.put(ARG_FROM, argFrom);

		List<Map<String, Object>> list = notificationDao.executeSqlSelectMap(sb, map);
		Map<String, Object> countMap = new HashMap<>();
		if (!list.isEmpty()) {
			countMap.put("notificationCount", list.get(0).get("notificationCount"));
		} else {
			countMap.put("notificationCount", 0);
		}
		return countMap;
	}

	@Override
	public List<Map<String, Object>> getNotificationData(Long toUserId, String notificationFor) throws Exception {
		Map<String, Object> map = new HashMap<>();
		StringBuilder sb = new StringBuilder(
				"EXEC TOM_GetNotificationData @toUserId = :toUserId, @notificationFor = :notificationFor");
		map.put("toUserId", toUserId);
		map.put("notificationFor", notificationFor);

		List<Map<String, Object>> list = notificationDao.executeSqlSelectMap(sb, map);
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public Integer saveMarkAsRead(Long notificationId) throws Exception {
		Long modifiedById = CommonUtil.getContextUser().getUserId();
		StringBuilder query = new StringBuilder();
		query.append(
				" update NotificationTable SET isRead = 1, modifiedDate = GETUTCDATE(), modifiedById = :modifiedById WHERE notificationId = :notificationId ");
		Map<String, Object> map = new HashMap<>();
		map.put(NOTIFICATION_ID, notificationId);
		map.put(MODIFIEDBY_ID, modifiedById);
		return notificationDao.executeSqlUpdate(query, map);
	}

	@Override
	public Calender calenderSave(Calender calender) throws Exception {
		// TODO Auto-generated method stub
		return calenderDAO.saveOrUpdate(calender);
	}

	@Override
	public List<Map<String, Object>> calenderGet(Long userId, String argFrom) throws Exception {
		Map<String, Object> map = new HashMap<>();
		StringBuilder sb = new StringBuilder("EXEC TOM_GetCalenderData @userId = :userId, @argFrom = :argFrom");
		map.put(USER_ID, userId);
		map.put(ARG_FROM, argFrom);
		List<Map<String, Object>> list = calenderDAO.executeSqlSelectMap(sb, map);
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

}
