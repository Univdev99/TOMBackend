package com.tom.document;

import java.io.Serializable;

public class DocumentUpload implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 5023014828655198449L;

	// single upload bean
	public DocumentUpload(String moduleName, String source, String name, String documentLevel) {
		super();
		this.moduleId = moduleId;
		this.submoduleId = submoduleId;
		this.source = source;
		this.clinicId = clinicId;
		this.patientId = patientId;
		this.caseId = caseId;
		this.appointmentId = appointmentId;
		this.documentTitle = documentTitle;
		this.name = name;
		this.documentLevel = documentLevel;
	}

// used for upload for multiple clinic
	public DocumentUpload(Long moduleId, Long submoduleId, String source, Long clinicId, Long patientId, Long caseId,
			Long appointmentId, String documentTitle, String name, String documentLevel, Long uploadTime) {
		super();
		this.moduleId = moduleId;
		this.submoduleId = submoduleId;
		this.source = source;
		this.clinicId = clinicId;
		this.patientId = patientId;
		this.caseId = caseId;
		this.appointmentId = appointmentId;
		this.documentTitle = documentTitle;
		this.name = name;
		this.documentLevel = documentLevel;
		this.uploadTime = uploadTime;
	}

	private Long moduleId;
	private Long submoduleId;
	private String source;
	private Long clinicId;
	private Long entityBusinessId;
	private Long patientId;
	private Long caseId;
	private Long appointmentId;
	private String documentTitle;
	private String name;
	private Long child1;
	private Long child2;
	private String documentLevel;
	private Long ObjectId;
	private Long uploadTime;
	private String moduleName;

	public DocumentUpload() {
		/* Default Constructor */
	}

	/**
	 * @return the moduleId
	 */
	public Long getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the submoduleId
	 */
	public Long getSubmoduleId() {
		return submoduleId;
	}

	/**
	 * @param submoduleId the submoduleId to set
	 */
	public void setSubmoduleId(Long submoduleId) {
		this.submoduleId = submoduleId;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the clinicId
	 */
	public Long getClinicId() {
		return clinicId;
	}

	/**
	 * @param clinicId the clinicId to set
	 */
	public void setClinicId(Long clinicId) {
		this.clinicId = clinicId;
	}

	/**
	 * @return the entityBusinessId
	 */
	public Long getEntityBusinessId() {
		return entityBusinessId;
	}

	/**
	 * @param entityBusinessId the entityBusinessId to set
	 */
	public void setEntityBusinessId(Long entityBusinessId) {
		this.entityBusinessId = entityBusinessId;
	}

	/**
	 * @return the patientId
	 */
	public Long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the appointmentId
	 */
	public Long getAppointmentId() {
		return appointmentId;
	}

	/**
	 * @param appointmentId the appointmentId to set
	 */
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * @return the documentTitle
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the child1
	 */
	public Long getChild1() {
		return child1;
	}

	/**
	 * @param child1 the child1 to set
	 */
	public void setChild1(Long child1) {
		this.child1 = child1;
	}

	/**
	 * @return the child2
	 */
	public Long getChild2() {
		return child2;
	}

	/**
	 * @param child2 the child2 to set
	 */
	public void setChild2(Long child2) {
		this.child2 = child2;
	}

	/**
	 * @return the objectId
	 */
	public Long getObjectId() {
		return ObjectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Long objectId) {
		ObjectId = objectId;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getDocumentLevel() {
		return documentLevel;
	}

	public void setDocumentLevel(String documentLevel) {
		this.documentLevel = documentLevel;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
