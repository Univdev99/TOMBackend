package com.tom.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tom.common.dao.GenericDao;

//import com.omnione.common.dao.GenericDao;
//import com.omnione.util.CommonUtil;
//import com.omnione.util.DateUtil;
//import com.omnione.util.StringUtil;

/**
 * @author CCJoshi
 *
 */
@Service
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserToken.class);

	@Autowired
	GenericDao<UserToken> userTokenDao;

//	@Autowired
//	GenericDao<SessionDetail> sessionDetailDao;

	@Override
	public UserToken save(UserToken userToken) throws Exception {
		LOGGER.debug("Execute method : save()");
		return userTokenDao.saveOrUpdate(userToken);
//		return null;
	}

	@Override
	public UserToken getUserToken(String tokenId) throws Exception {
		LOGGER.debug("Execute method : getUserToken()");

		StringBuilder queryString = new StringBuilder("from UserToken where tokenId = :tokenId");
		Map<String, Object> map = new HashMap<>();
		map.put("tokenId", tokenId);

		List<UserToken> list = userTokenDao.executeHqlSelect(queryString, map);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteToken(String tokenId) throws Exception {
		LOGGER.debug("Execute method : getUserToken()");

		StringBuilder query = new StringBuilder(
				"update sessionDetailTable set logouttime = :logoutTime where usertokenId = (select userTokenId from UserTokenTable where tokenId = :tokenId)");
		Map<String, Object> map = new HashMap<>();
		map.put("tokenId", tokenId);
//		map.put("logoutTime", DateUtil.getCurrentTimestamp());
//		sessionDetailDao.executeSqlUpdate(query, map);
		map.clear();
		StringBuilder queryString = new StringBuilder("delete from UserToken where tokenId = :tokenId");
		map.put("tokenId", tokenId);
		userTokenDao.executeUpdate(queryString, map);
	}

	@Override
	public SessionDetail saveSessionDetail(HttpServletRequest httpRequest, Long tokenId) throws Exception {
		// TODO Auto-generated method stub
		SessionDetail sessionDetail = new SessionDetail();
//		sessionDetail.setUser(CommonUtil.getContextUser());
//		sessionDetail.setLoginTime(DateUtil.getCurrentTimestamp());
//		String clientIP = httpRequest.getHeader("X-FORWARDED-FOR");
//		if (StringUtil.isNullorEmptyWithNullString(clientIP)) {
//			clientIP = httpRequest.getRemoteAddr();
//		}
//		sessionDetail.setIpAddress(clientIP);
//		sessionDetail.setUserTokenId(tokenId);
//		return sessionDetailDao.saveOrUpdate(sessionDetail);
		return  sessionDetail;
	}

}
