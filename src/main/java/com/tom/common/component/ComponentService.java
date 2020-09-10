
package com.tom.common.component;

import java.util.List;
import java.util.Map;

import com.tom.common.Notification;
import com.tom.common.beans.Calender;
import com.tom.common.beans.Help;
import com.tom.common.beans.Review;
import com.tom.common.exception.ExceptionWrapper;

public interface ComponentService {

	List<Map<String, Object>> getSearchData(String... params) throws Exception;

	Help saveAndSendHelp(Help help) throws Exception;

	Review saveReview(Review review) throws Exception;

	List<Map<String, Object>> getLatestAverageReview(Long proProfileId, Long firmProfileId) throws Exception;

	List<Notification> notificationSave(List<Notification> notificationList) throws Exception;

	Object getNotification(Long userId, String argFrom) throws Exception;

	List<Map<String, Object>> getNotificationData(Long toUserId, String notificationFor) throws Exception;

	Integer saveMarkAsRead(Long notificationId) throws Exception;

	Calender calenderSave(Calender calender) throws Exception;

	List<Map<String, Object>> calenderGet(Long userId, String argFrom) throws Exception;

}
