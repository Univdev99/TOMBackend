package com.tom.firm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.project.ProjectComplete;

@Service
public class FirmServiceImpl implements FirmService, Constant {

	@Autowired
	GenericDao<FirmProfile> firmProfileDao;

	@Autowired
	GenericDao<ProjectComplete> projectCompleteDao;

	@Autowired
	GenericDao<Serializable> genericDao;

	@Override
	public FirmProfile createFirmProfile(FirmProfile firmProfile) throws Exception {
		return firmProfileDao.saveOrUpdate(firmProfile);
	}

	@Override
	public FirmProfile getFirmProfile(Long firmProfileId) throws Exception {
		return firmProfileDao.get(FirmProfile.class, firmProfileId);
	}

	@Override
	public FirmProfile getFirmByUserId(Long userId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("from FirmProfile where userId = :userId ");
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		List<FirmProfile> firmProfileList = firmProfileDao.executeHqlSelect(queryString, map);

		if (!firmProfileList.isEmpty()) {
			return firmProfileList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAccpetedFirmProject(Long userId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		queryString.append("EXEC TOM_SearchAcceptedProjectForFirm @argUserId = :userId");
		List<Map<String, Object>> projectList = genericDao.executeSqlSelectMap(queryString, map);
		if (!projectList.isEmpty()) {
			return projectList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getFirmProjectHistory(Long userId)  throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		queryString.append("EXEC TOM_SearchCompletedProjectForFirm @argUserId = :userId");
		List<Map<String, Object>> projectList = genericDao.executeSqlSelectMap(queryString, map);
		if (!projectList.isEmpty()) {
			return projectList;
		} else {
			return null;
		}
	}

	@Override
	public Integer projectCompleted(Long projectCompleteId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectCompleteTable SET isCompletedByFirm = 1 WHERE projectCompleteId = :projectCompleteId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_COMPLETE_ID, projectCompleteId);
		return projectCompleteDao.executeSqlUpdate(query, map);
	}
}
