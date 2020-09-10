package com.tom.professional;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.tom.common.beans.TreeNode;
import com.tom.common.exception.ExceptionWrapper;
import com.tom.project.ProjectComplete;

public interface ProfessionalService {

	ProfessionalProfile createProProfile(ProfessionalProfile professionalProfile, Long documentId) throws Exception;

	ProfessionalProfile getProProfessionalProfile(Long professionalProfileId) throws Exception;

	ProWorkAvailability createProWorkAvailProfile(ProWorkAvailability proWorkAvailability) throws Exception;

	ProWorkAvailability getProWorkAvailabilty(Long professionalProfileId) throws Exception;

	ProWorkExperience createProWorkExpProfile(ProWorkExperience proWorkExperience, Long[] documentIdsArray)
			throws Exception;

	ProfessionalProfile getProPersonalByUserId(Long userId) throws Exception;

	List<Map<String, Object>> getProSelectedSkills(Long proWorkExperienceId) throws Exception;

	ProWorkExperience getProWorkExperience(Long professionalProfileId) throws Exception;

	List<Map<String, Object>> getProSkillTree(Long proProfileId) throws Exception;

	List<Map<String, Object>> searchProfessionals(String... paramMap) throws Exception;

	Map<String, Object> getWholeProfile(Long proProfileId) throws Exception;

	List<Map<String, Object>> getProJobs(Long proProfileId, String jobStatus) throws Exception;

	List<Map<String, Object>> getAccpetedProProject(Long proProfileId) throws Exception;

	List<Map<String, Object>> getProProjectHistory(Long proProfileId) throws Exception;

	ProjectComplete projectCompleted(ProjectComplete projectComplete) throws Exception;

}
