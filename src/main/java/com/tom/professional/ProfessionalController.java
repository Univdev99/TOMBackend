package com.tom.professional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Status;
import com.tom.common.beans.TreeNode;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.project.ProjectComplete;
import com.tom.project.ProjectFinalize;
import com.tom.util.JsonUtil;

@RestController
@RequestMapping("/tom/professional")
public class ProfessionalController implements Constant {
	public static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalController.class);

	@Autowired
	ProfessionalService professionalService;

	@RequestMapping(value = {
			"/personalProfile/save" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveProPersonalProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveProPersonalProfile()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProfessionalProfile professionalProfile = mapper.convertValue(json.get(PRO_PERSONAL_PROFILE),
					ProfessionalProfile.class);
			Long documentId = json.has(DOCUMENT_ID) ? mapper.convertValue(json.get(DOCUMENT_ID), Long.class) : null;
			return new Status(ConstantStatus.OK, "",
					professionalService.createProProfile(professionalProfile, documentId));
		} catch (Exception e) {
			LOGGER.error("Error in saveProPersonalProfile() method :: ", e);
			throw new TOMRuntimeException("Error in saveProPersonalProfile()", e);
		}
	}

	@RequestMapping(value = {
			"/personalProfile/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProPersonalProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProPersonalProfile()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getProProfessionalProfile(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getProPersonalProfile() method :: ", e);
			throw new TOMRuntimeException("Error in getProPersonalProfile()", e);
		}
	}

	@RequestMapping(value = {
			"/workAvail/get", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProWorkAvailibilty(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProWorkAvailibilty()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getProWorkAvailabilty(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getProWorkAvailibilty() method :: ", e);
			throw new TOMRuntimeException("Error in getProWorkAvailibilty()", e);
		}
	}

	@RequestMapping(value = {
			"/workAvail/save", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createProWorkAvailability(HttpServletRequest request) {
		LOGGER.debug("Execute method : createProWorkAvailability()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProWorkAvailability proWorkAvailability = mapper.convertValue(json.get(PRO_WORK_AVAILABILITY),
					ProWorkAvailability.class);

			return new Status(ConstantStatus.OK, "",
					professionalService.createProWorkAvailProfile(proWorkAvailability));
		} catch (Exception e) {
			LOGGER.error("Error in createProWorkAvailability() method :: ", e);
			throw new TOMRuntimeException("Error in createProWorkAvailability()", e);
		}
	}

	@RequestMapping(value = {
			"/workExp/get", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProWorkExperience(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProWorkExperience()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getProWorkExperience(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getProWorkExperience() method :: ", e);
			throw new TOMRuntimeException("Error in getProWorkExperience()", e);
		}
	}

	@RequestMapping(value = {
			"/workExp/save", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createProWorkworkExperience(HttpServletRequest request) {
		LOGGER.debug("Execute method : createProWorkworkExperience()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProWorkExperience proWorkExperience = mapper.convertValue(json.get(PRO_WORK_EXPERIENCE),
					ProWorkExperience.class);
			Long[] documentIdsArray = mapper.convertValue(json.get(DOCUMENT_IDS_ARRAY), new TypeReference<Long[]>() {
			});
			return new Status(ConstantStatus.OK, "",
					professionalService.createProWorkExpProfile(proWorkExperience, documentIdsArray));
		} catch (Exception e) {
			LOGGER.error("Error in createProWorkworkExperience() method :: ", e);
			throw new TOMRuntimeException("Error in createProWorkworkExperience()", e);
		}
	}

	@RequestMapping(value = { "/tree/get", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProSkillTree(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProSkillTree()");
		List<TreeNode> treeNodeList = null;
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Map<Object, Object> map = new HashMap<>();
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
//			Map<String, Object> map  = professionalService.getProSkillTree(proProfileId);
			map.put("proSkillTree", treeNodeList);
			return new Status(ConstantStatus.OK, "", professionalService.getProSkillTree(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getProSkillTree() method :: ", e);
			throw new TOMRuntimeException("Error in getProSkillTree()", e);
		}
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status searchProfessionals(HttpServletRequest request) {
		LOGGER.debug("Execute method : searchProfessionals()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			String searchParams = mapper.convertValue(json.get("proSearchParams").asText(),
					new TypeReference<String>() {
					});
			return new Status(ConstantStatus.OK, "", professionalService.searchProfessionals(searchParams));
		} catch (Exception e) {
			LOGGER.error("Error in searchProfessionals() method :: ", e);
			throw new TOMRuntimeException("Error in searchProfessionals()", e);
		}
	}

	@RequestMapping(value = {
			"/getWholeProfile" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getWholeProProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : getWholeProProfile()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getWholeProfile(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getWholeProProfile() method :: ", e);
			throw new TOMRuntimeException("Error in getWholeProProfile()", e);
		}
	}

	@RequestMapping(value = { "/job/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProJob(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProJob()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			String jobStatus = !JsonUtil.isRequiredParamNull(json, new String[] { JOB_STATUS })
					? json.get(JOB_STATUS).asText()
					: null;
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getProJobs(proProfileId, jobStatus));
		} catch (Exception e) {
			LOGGER.error("Error in getProJob() method :: ", e);
			throw new TOMRuntimeException("Error in getProJob()", e);
		}
	}

	@RequestMapping(value = {
			"/project/accpeted" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAccpetedProProject(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAccpetedProProject()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getAccpetedProProject(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getAccpetedProProject() method :: ", e);
			throw new TOMRuntimeException("Error in getAccpetedProProject()", e);
		}
	}

	@RequestMapping(value = {
			"/project/history" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProProjectHistory(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProProjectHistory()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", professionalService.getProProjectHistory(proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getProProjectHistory() method :: ", e);
			throw new TOMRuntimeException("Error in getProProjectHistory()", e);
		}
	}

	@RequestMapping(value = {
			"/project/complete", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectCompleted(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectCompleted()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProjectComplete projectComplete = mapper.convertValue(json.get(PROJECT_COMPLETE), ProjectComplete.class);
			return new Status(ConstantStatus.OK, "", professionalService.projectCompleted(projectComplete));
		} catch (Exception e) {
			LOGGER.error("Error in projectCompleted() method :: ", e);
			throw new TOMRuntimeException("Error in projectCompleted()", e);
		}
	}

	@RequestMapping(value = {
			"/workExp/expWithSkill", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProWorkExpWithSkill(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProWorkExperience()");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			ProWorkExperience proWorkExperience = professionalService.getProWorkExperience(proProfileId);
			map.put(PRO_WORK_EXPERIENCE, proWorkExperience);
			map.put(PRO_SKILLS, professionalService.getProSelectedSkills(proWorkExperience.getProWorkExperienceId()));
			return new Status(ConstantStatus.OK, "", map);
		} catch (Exception e) {
			LOGGER.error("Error in getProWorkExperience() method :: ", e);
			throw new TOMRuntimeException("Error in getProWorkExperience()", e);
		}
	}

}
