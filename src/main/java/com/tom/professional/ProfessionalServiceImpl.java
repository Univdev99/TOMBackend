package com.tom.professional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tom.common.beans.TreeNode;
import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.document.DocumentService;
import com.tom.project.ProjectComplete;
import com.tom.project.ProjectSchedule;

@Service
public class ProfessionalServiceImpl implements ProfessionalService, Constant {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalServiceImpl.class);

	@Autowired
	GenericDao<ProfessionalProfile> professionalProfileDao;

	@Autowired
	GenericDao<ProWorkAvailability> proWorkAvailabilityDao;

	@Autowired
	GenericDao<Serializable> genericDao;

	@Autowired
	GenericDao<ProjectAdditionalTime> projectAdditionalTimeDao;

	@Autowired
	GenericDao<ProWorkExperience> proWorkExperienceDao;
	@Autowired
	GenericDao<ProjectSchedule> projectScheduleDao;

	@Autowired
	DocumentService documentService;

	@Autowired
	GenericDao<ProjectComplete> projectCompleteDao;

	@Override
	public ProfessionalProfile createProProfile(ProfessionalProfile professionalProfile, Long documentId)
			throws Exception {
		ProfessionalProfile savedProfile = professionalProfileDao.saveOrUpdate(professionalProfile);
		if (null != documentId) {
			this.documentService.updateObjectId(documentId, savedProfile.getProfessionalProfileId());
		}
		return professionalProfile;

	}

	@Override
	public ProWorkAvailability getProWorkAvailabilty(Long professionalProfileId) throws Exception {
		LOGGER.debug("Execute method : getProWorkAvailabilty()");
		Map<String, Object> criteriaMap = new HashMap<String, Object>();
		criteriaMap.put(PROFESSIONAL_PROFILE_ID, professionalProfileId);
		List<ProWorkAvailability> ProWorkAvailList = proWorkAvailabilityDao
				.getListByRistrictions(ProWorkAvailability.class, criteriaMap, null, null);
		if (!ProWorkAvailList.isEmpty()) {
			return ProWorkAvailList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ProWorkAvailability createProWorkAvailProfile(ProWorkAvailability proWorkAvailability) throws Exception {
		return proWorkAvailabilityDao.saveOrUpdate(proWorkAvailability);
	}

	@Override
	public ProfessionalProfile getProProfessionalProfile(Long professionalProfileId) throws Exception {
		return professionalProfileDao.get(ProfessionalProfile.class, professionalProfileId);
	}

	@Override
	public ProWorkExperience createProWorkExpProfile(ProWorkExperience proWorkExperience, Long[] documentIdsArray)
			throws Exception {
		ProWorkExperience savedProWorkExperience = proWorkExperienceDao.merge(proWorkExperience);
		if (null != savedProWorkExperience.getProWorkExperienceId() && documentIdsArray.length > 0) {
//			this.ProWorkSkills(savedProWorkExperience.getProfessionalProfileId(), proSkillJSON);
			setTransactionIdInDocument(documentIdsArray, savedProWorkExperience.getProWorkExperienceId());
		}

		return savedProWorkExperience;
	}

	private void setTransactionIdInDocument(Long[] documentIdsArray, Long transactionId) throws Exception {
		for (Long docId : documentIdsArray) {
			documentService.updateObjectId(docId, transactionId);
		}
	}

	private void ProWorkSkills(Long professionalProfileId, JsonNode proSkillJSON) throws Exception {
		deleteProWorkSkillByProId(professionalProfileId);
		for (int i = 0; i < proSkillJSON.size(); i++) {
			ProSkill proWorkSkill = new ProSkill();
			proWorkSkill.setProSkillDetailId(proSkillJSON.get(i).asLong());
//			proWorkSkill.setProfessionalProfileId(professionalProfileId);
//			genericDao.saveOrUpdate(proWorkSkill);
		}
	}

	public void deleteProWorkSkillByProId(Long professionalProfileId) throws Exception {
//		StringBuilder query = new StringBuilder();
//		query.append("delete from ProWorkSkill where professionalProfileId = :professionalProfileId");
//		Map<String, Object> criteriaMap = new HashMap<>();
//		criteriaMap.put("professionalProfileId", professionalProfileId);
//		genericDao.executeUpdate(query, criteriaMap);
	}

	@Override
	public ProfessionalProfile getProPersonalByUserId(Long userId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("from ProfessionalProfile where userId = :userId");
		Map<String, Object> map = new HashMap<>();
		map.put(USER_ID, userId);
		List<ProfessionalProfile> proPersonalList = professionalProfileDao.executeHqlSelect(queryString, map);
		if (!proPersonalList.isEmpty()) {
			return proPersonalList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ProWorkExperience getProWorkExperience(Long professionalProfileId) throws Exception {
		LOGGER.debug("Execute method : getProWorkExperience()");
		Map<String, Object> criteriaMap = new HashMap<String, Object>();
		criteriaMap.put(PROFESSIONAL_PROFILE_ID, professionalProfileId);
		List<ProWorkExperience> proWorkExperienceList = proWorkExperienceDao
				.getListByRistrictions(ProWorkExperience.class, criteriaMap, null, null);
		if (!proWorkExperienceList.isEmpty()) {
			return proWorkExperienceList.get(0);
		} else {
			return null;
		}
	}

//	@Override
//	public List<TreeNode> getProSkillTree(Long proProfileId) throws Exception {
//		String mode = "add";
//		StringBuilder stringBuilder = new StringBuilder();
//		Map<String, Object> criteriaMap = new HashMap<>();
//		stringBuilder.append(
//				"SELECT distinct SMT.masterSkillName,SMT.proSkillMasterId,SCT.categorySkillName,SCT.proSkillCategoryId, ")
//				.append(" SDT.detailSkillName,SDT.proSkillDetailId ");
//
//		if (null != proProfileId) {
//			stringBuilder.append(" ,PWST.professionalProfileId ");
//		}
//
//		stringBuilder.append(" from ProSkillMasterTable SMT ");
//		stringBuilder.append(" LEFT JOIN ProSkillCategoryTable SCT on SCT.ProSkillMasterId = SMT.ProSkillMasterId")
//				.append(" LEFT JOIN ProSkillDetailTable SDT on SDT.ProSkillCategoryId = SDT.ProSkillCategoryId  AND  SMT.ProSkillMasterId = SDT.ProSkillMasterId ");
//		if (null != proProfileId) {
//			mode = "edit";
//			stringBuilder.append(
//					" left join ProWorkSkillTable PWST on PWST.proSkillDetailId = SDT.proSkillDetailId and PWST.ProfessionalProfileId =:proProfileId ");
//			criteriaMap.put("proProfileId", proProfileId);
//		}
//		stringBuilder.append(" ORDER BY MasterSkillName ,CategorySkillName, DetailSkillName");
//		List<Map<String, Object>> resultSet = genericDao.executeSqlSelectMap(stringBuilder, criteriaMap);
//		return getTree(resultSet, mode);
//	}

	@Override
	public List<Map<String, Object>> getProSkillTree(Long proProfileId) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"SELECT distinct SMT.masterSkillName,SMT.proSkillMasterId,SCT.categorySkillName,SCT.proSkillCategoryId, ")
				.append(" SDT.detailSkillName,SDT.proSkillDetailId ");

		stringBuilder.append(" from ProSkillMasterTable SMT ");
		stringBuilder.append(" LEFT JOIN ProSkillCategoryTable SCT on SCT.ProSkillMasterId = SMT.ProSkillMasterId")
				.append(" LEFT JOIN ProSkillDetailTable SDT on SDT.ProSkillCategoryId = SCT.ProSkillCategoryId  AND  SMT.ProSkillMasterId = SDT.ProSkillMasterId ");

		stringBuilder.append(" ORDER BY MasterSkillName ,CategorySkillName, DetailSkillName");
		return genericDao.executeSqlSelectMap(stringBuilder, null);
	}

	private List<TreeNode> getTree(List<Map<String, Object>> list, String mode) {
		List<TreeNode> treeNodes = new ArrayList<>();
		boolean isAlreadyMenu;
		String currentMasterId;
		String currentCategoryId;
		String prevMasterId = "";
		String prevCategoryId = "";
		List<TreeNode> treeNodes1List = new ArrayList<>();
		List<TreeNode> treeNodes2List = new ArrayList<>();
		TreeNode treeNode = new TreeNode();
		TreeNode treeNode1 = new TreeNode();
		for (Map<String, Object> row : list) {
			isAlreadyMenu = false;
			currentMasterId = row.get(PRO_SKILL_MASTER_ID).toString();
			currentCategoryId = row.get(PRO_SKILL_CATEGORY_ID).toString();
			TreeNode treeNode2 = new TreeNode();
			if ("".equals(prevMasterId) && "".equals(prevCategoryId)) {
				treeNode.setTitle("" + row.get("masterSkillName"));
				treeNode.setKey("");
				isAlreadyMenu = true;
				prevMasterId = "" + row.get(PRO_SKILL_MASTER_ID);
				prevCategoryId = "" + row.get(PRO_SKILL_CATEGORY_ID);
			}
			if (!currentMasterId.equals(prevMasterId)) {
				treeNode1.setChildren(treeNodes2List);
				treeNodes1List.add(treeNode1);
				treeNode.setChildren(treeNodes1List);
				treeNodes.add(treeNode);

				treeNode = new TreeNode();
				treeNode1 = new TreeNode();
				treeNodes1List = new ArrayList<>();
				treeNodes2List = new ArrayList<>();

				treeNode.setTitle("" + row.get("masterSkillName"));
				treeNode.setKey("");
				isAlreadyMenu = true;
				prevMasterId = "" + row.get(PRO_SKILL_MASTER_ID);
				prevCategoryId = "" + row.get(PRO_SKILL_CATEGORY_ID);
			}
			if (!currentCategoryId.equals(prevCategoryId)) {
				treeNode1.setChildren(treeNodes2List);
				treeNodes1List.add(treeNode1);
				treeNode1 = new TreeNode();
				treeNodes2List = new ArrayList<>();
				treeNode1.setTitle("" + row.get("categorySkillName"));
				treeNode1.setKey("");
				prevMasterId = "" + row.get(PRO_SKILL_MASTER_ID);
				prevCategoryId = "" + row.get(PRO_SKILL_CATEGORY_ID);
			}
			if (currentMasterId.equals(prevMasterId) && isAlreadyMenu) {
				treeNode1.setTitle("" + row.get("categorySkillName"));
				treeNode1.setKey("");
				prevMasterId = "" + row.get(PRO_SKILL_MASTER_ID);
				prevCategoryId = "" + row.get(PRO_SKILL_CATEGORY_ID);
			}
			if (currentCategoryId.equals(prevCategoryId)) {
				treeNode2.setTitle("" + row.get("detailSkillName"));
				treeNode2.setKey("" + row.get(PRO_SKILL_DETAIL_ID));
				prevMasterId = "" + row.get(PRO_SKILL_MASTER_ID);
				prevCategoryId = "" + row.get(PRO_SKILL_CATEGORY_ID);
				treeNodes2List.add(treeNode2);
			}
			if (mode.equals(EDIT)) {
				if (null != row.get(PROFESSIONAL_PROFILE_ID)) {
					treeNode.setSelect(true);
					treeNode1.setSelect(true);
					treeNode2.setSelect(true);
				}
			} else {
				treeNode.setSelect(false);
				treeNode1.setSelect(false);
				treeNode2.setSelect(false);
			}
		}
		return treeNodes;
	}

	@Override
	public List<Map<String, Object>> searchProfessionals(String... searchParams) throws Exception {
		return genericDao.getDataFromSP("TOM_SearchProfessional", searchParams);
	}

	@Override
	public Map<String, Object> getWholeProfile(Long proProfileId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(PRO_WORK_AVAILABILITY, getProWorkAvailabilty(proProfileId));
		map.put(PRO_PERSONAL_PROFILE, getProProfessionalProfile(proProfileId));
		ProWorkExperience proWorkExperience = getProWorkExperience(proProfileId);
		map.put(PRO_WORK_EXPERIENCE, proWorkExperience);
		if (proWorkExperience != null) {
			map.put(PRO_SKILLS, getProSelectedSkills(proWorkExperience.getProWorkExperienceId()));
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getProJobs(Long proProfileId, String jobStatus) throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put(PRO_PROFILE_ID, proProfileId);
		map.put(JOB_STATUS, jobStatus);
		queryString.append("EXEC TOM_SearchProfessionalJOB @proProfileId = :proProfileId, @jobStatus = :jobStatus");
		List<Map<String, Object>> jobList = projectScheduleDao.executeSqlSelectMap(queryString, map);
		if (!jobList.isEmpty()) {
			return jobList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getProSelectedSkills(Long proWorkExperienceId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(PRO_WORK_EXPERIENCE_ID, proWorkExperienceId);
		StringBuilder queryString = new StringBuilder(" SELECT distinct  ");
		queryString.append(
				" SMT.masterSkillName,SMT.proSkillMasterId,SCT.categorySkillName,SCT.proSkillCategoryId,  SDT.detailSkillName,SDT.proSkillDetailId ");
		queryString.append(
				" from ProSkillMasterTable SMT LEFT JOIN ProSkillTable PST on PST.ProSkillMasterId = SMT.ProSkillMasterId  ");
		queryString.append(" LEFT JOIN ProSkillCategoryTable SCT on PST.ProSkillCategoryId = SCT.ProSkillCategoryId  ");
		queryString.append(" LEFT JOIN ProSkillDetailTable SDT on SDT.proSkillDetailId = PST.proSkillDetailId  ");
		queryString.append(" WHERE PST.proWorkExperienceId = :proWorkExperienceId  ");
		List<Map<String, Object>> selectedSkillByPro = projectScheduleDao.executeSqlSelectMap(queryString, map);
		if (!selectedSkillByPro.isEmpty()) {
			return selectedSkillByPro;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getAccpetedProProject(Long proProfileId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put(PRO_PROFILE_ID, proProfileId);
		queryString.append("EXEC TOM_SearchAcceptedProjectForProfessional @proProfileId = :proProfileId");
		List<Map<String, Object>> jobList = projectScheduleDao.executeSqlSelectMap(queryString, map);
		if (!jobList.isEmpty()) {
			return jobList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getProProjectHistory(Long proProfileId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put(PRO_PROFILE_ID, proProfileId);
		queryString.append("EXEC TOM_SearchCompletedProjectForProfessional @proProfileId = :proProfileId");
		List<Map<String, Object>> jobList = projectScheduleDao.executeSqlSelectMap(queryString, map);
		if (!jobList.isEmpty()) {
			return jobList;
		} else {
			return null;
		}
	}

	@Override
	public ProjectComplete projectCompleted(ProjectComplete projectComplete) throws Exception {
		// TODO Auto-generated method stub
		return projectCompleteDao.saveOrUpdate(projectComplete);
	}

}
