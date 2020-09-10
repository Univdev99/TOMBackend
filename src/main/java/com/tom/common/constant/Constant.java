package com.tom.common.constant;

public interface Constant {

	String TOKEN = "token";
	String CHANGED_PASSWORD = "changedpassword";
	
	public static final String ARG_FROM = "argFrom";
	public static final String FIRM_PROFILE = "firmProfile";
	public static final String ADMIN_PROFIT = "adminProfit";
	public static final String REVIEW = "review";
	public static final String NOTIFICATION_LIST = "notificationList";
	public static final String NOTIFICATION_ID = "notificationId";
	public static final String CALENDER = "calender";
	public static final String MODIFIEDBY_ID = "modifiedById";
	
	
	public static final String PROFESSIONAL_PROFILE = "professionalProfile";
	public static final String PROFESSIONAL_PROFILE_ID = "professionalProfileId";
	
	public static final String PRO_WORK_AVAILABILITY = "proWorkAvailability";
	public static final String PRO_WORK_EXPERIENCE = "proWorkExperience";
	public static final String PRO_SKILLS = "proSkills";
	public static final String PRO_PERSONAL_PROFILE = "proPersonalProfile";
	
	public static final String FIRM_PROFILE_ID = "firmProfileId";
	public static final String PRO_PROFILE_ID = "proProfileId";
	public static final String USER = "user";
	public static final String USER_ID = "userId";
	public static final String USER_PASSWORD = "userPassword";
	public static final String USER_STATUS = "userStatus";
	public static final String APPROVE = "Approve";
	public static final String DECLINE = "Decline";
	public static final String COMPLETED = "Completed";
	
	
	public static final String PRO_SKILL_MASTER_ID = "proSkillMasterId";
	public static final String PRO_SKILL_CATEGORY_ID = "proSkillCategoryId";
	public static final String PRO_SKILL_DETAIL_ID = "proSkillDetailId";
	public static final String PROJECT_STATUS = "projectStatus";
	public static final String PROJECT_SCHEDULE_ID = "projectScheduleId";
	public static final String PROJECT_SCHEDULE_BY_PRO = "projectScheduleByPro";
	public static final String PROJECT_SCHEDULE_BY_PRO_ID = "projectScheduleByProId";
	public static final String PROJECT_FINALIZE = "projectFinalize";
	public static final String PROJECT_FINALIZE_ID = "projectFinalizeId";
	public static final String AMENDS = "amends";
	public static final String PROJECT_COMPLETE = "projectComplete";
	public static final String PROJECT_COMPLETE_ID = "projectCompleteId";
	public static final String HELP = "help";
	public static final String PROJECT_ADDITIONAL_TIME = "projectAddtionalTime";
	public static final String PROJECT_ADDITIONAL_TIME_ID = "projectAdditionalTimeId";
	
	
	
	
	
	
	
	
	
	
	public static final String DOCUMENT_ID = "documentId";
	public static final String OBJECT_ID = "objectId";
	
	
	public static final String DOC_PATH = "commonDocPath";
	
	String DOCUMENT_IDS_ARRAY = "documentIdsArray";
	
	
	public static final String DOC_DB_PATH = "docPath";
	
	public static final String  USER_ROLE_TYPE = "userRoleType";
	public static final String ADMIN_USER_TYPE = "1";
	public static final String FIRM_USER_TYPE = "2";
	public static final String PRO_USER_TYPE = "3";
	public static final String WORK_EMAIL = "workEmail";
	public static final String ROLE_ID = "roleId";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String REQUEST_ATTR_DO_NOT_CONTINUE = "MyAuthenticationFilter-doNotContinue";
	
	public static final String PROJECT= "project";
	public static final String PROJECT_ID= "projectId";
	
	public static final String PROJECT_SCHEDULE= "projectSchedule";
	public static final String PRO_WORK_EXPERIENCE_ID= "proWorkExperienceId";
	public static final String JOB_STATUS= "jobStatus";
	public static final String STATUS = "status"; // dont change
	public static final String ACCEPTED = "Accepted"; // dont change
	
	
	
	public static final String ADMIN = "Admin";
	public static final String SUPERADMIN = "SuperAdmin";
	public static final String SUCCESS = "success";
	public static final String SUCCESSCAMEL = "Success";
	public static final String FAIL = "fail";
	public static final String DOT = ".";
	String SPACE = " ";
	String COLON = ":";
	public static final String COMMA = ",";
	public static final String TILD = "~";
	public static final String XAUTHTOKEN = "X-Auth-Token";
	public static final String AUTHTOKEN = "Authorization token";
	public static final String TABLETAPIVERSION = "api-version";
	public static final String HEADER = "header";
	public static final String STRING = "string";
	public static final String BODY = "body";
	public static final String PATIENT = "patient";
	public static final String COPAY = "Copay";
	public static final String PATIENTID = "patientId"; // do not change it it
														// is also dependent in
														// document controller
	public static final String PATIENTNAME = "patientName";
	public static final String BLOCKALPHA = "ABCDEFIJKLMNOPQRS;sTUVWXYZ";
	public static final String BLOCKNUMERIC = "1234567890";
	public static final String ENTITY_ID1 = "ENTITY_ID";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_ROLE = "USER_ROLE";
	public static final long DEFAULT_VALUE = 0L;
	public static final String REMOTE_ADDR = "REMOTE_ADDR";
	public static final String CLIENT_IP = "CLIENT_IP";
	public static final String BROWSER_NAME = "BROWSER_NAME";
	public static final String USER_AGENT = "USER_AGENT";
	
	public static final String SERVER_NAME = "SERVER_NAME";
	public static final String SERVER_IP = "SERVER_IP";
	public static final String URL_STRING = "URL_STRING";
	public static final String REQUEST_PARAMS = "REQUEST_PARAMS";
	public static final String REQUEST_START_TIME = "REQUEST_START_TIME";
	public static final String ACTION_ID = "ACTION_ID";
	public static final String REQUEST_END_TIME = "REQUEST_END_TIME";
	public static final String REQUEST_BODY = "REQUEST_BODY";
	public static final String LINEEND = "LINEEND";
	public static final String LINEEND_VAL = "*LINEEND*";
	public static final String IS_SYETEM_ACTION = "IS_SYETEM_ACTION";
	public static final String IS_AUDIT_ACTION = "IS_AUDIT_ACTION";
	public static final String ALL = "ALL";
	public static final String UPDATEMSG = "Changes Saved Successfully";
	public static final String USER_TYPE = "userType";
	public static final String STARTDATE = "startDate";
	public static final String ENDDATE = "endDate";

	// Tablet Connection and Card Scanning
	public static final String DEVICE_ID = "deviceId";
	public static final String REQUEST_ID = "requestId";

	// Payment, Refund
	public static final String DOUBLE_ZERO = "0.00";
	public static final String PAYMENT_ID = "paymentId";
	public static final String PAYMENT = "Payment";
	public static final String REFUND = "Refund";
	public static final String REMARK = "Remark";
	public static final String REMITTANCE_CODE = "remittanceCode";
	public static final String ADJUSTMENT = "Adjustment";
	public static final String ADJUSTMENT_CODE = "adjustmentCode";
	public static final String ADJUSTMENT_CODES = "adjustmentCodes";
	public static final String ADJUSTMENT_CODE_ID = "adjustmentCodeId";
	public static final String ADJUSTMENT_GROUP_CODE = "adjustmentGroupCode";
	public static final String REJECTION_CODE = "rejectionCode";
	public static final String REJECTION_CODE_ID = "rejectionCodeId";
	public static final String ERA = "ERA";
	public static final String EOB = "EOB";
	public static final String REFERENCE = "Reference";
	public static final String ICDCODE = "icdcode";
	public static final String CUSTOM_CPT = "CustomCPTCode";
	public static final String CUSTOM_CPT_ID = "customCPTCodeId";
	public static final String CUSTOM_CPT_CODE = "cptcode";
	public static final String TASK_TYPE = "taskType";
	public static final String CUSTOM_ICD = "CustomICDCode";
	public static final String CUSTOM_ICD_ID = "customICDCodeId";
	public static final String CUSTOM_ICD_CODE = "icdcode";
	public static final String ENTITYBUSINESS = "entityBusiness";
	public static final String ENTITY_BUSINESS_ID = "entityBusinessId";
	public static final String BUSINESS_ID = "businessId";
	public static final String CLINIC_IDS = "clinicIds";
	public static final String ENTITY_ID = "entityId";
	public static final String IS_ENTITY = "isEntity";
	public static final String PARENT_ENTITY = "parentEntity";
	public static final String ENTITY_NAME = "entityName";
	public static final String BUSINESS_NAME = "entityName";
	public static final String BUSINESS_CLINIC_IDS = "businessClinicIds";
	public static final String SELECTD_HOLIDAYS = "selectedHolidays";
	public static final String RESOURCE = "resource";
	public static final String LOCATION = "location";
	public static final String ATTORNEY = "attorney";
	public static final String ATTORNEYID = "attorneyId";
	public static final String ATTORNEY_NAME = "attorneyName";
	
	public static final String ADDRESS = "address1";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP = "zip";
	public static final String EMAIL = "email";
	public static final String FLTYPE14 = "14";
	public static final String FLTYPE15 = "15";
	public static final String FLTYPE17 = "17";
	public static final String FLTYPE1828 = "1828";
	public static final String FLTYPE3134 = "3134";
	public static final String FLTYPE3536 = "3536";
	public static final String FLTYPE3941 = "3941";
	public static final String FLTYPE81 = "81";
	public static final String FLTYPE80 = "80";
	public static final String ERAHEADERID = "eraHeaderId";
	public static final String FROM = "from";

	public static final String ADJUSTER = "adjuster";
	public static final String ADJUSTERID = "adjusterId";
	public static final String ADJUSTER_NAME = "adjusterName";

	public static final String FOLLOWUP_MEHOD = "followupMethod";
	public static final String FOLLOWUP_TYPE = "followupType";
	public static final String FOLLOWUP_GROUP = "followupGroup";
	public static final String DESCRIPTION = "description";
	public static final String NOTES = "notes";
	public static final String ADDRESS2 = "address2";
	public static final String COMPANY_NAME = "companyName";
	public static final String BUCKET_DETAIL_ID = "bucketDetailId";
	public static final String BUCKET_DETAIL_IDS = "bucketDetailIds";

	public static final String EMPLOYER = "employer";
	public static final String EMPLOYERID = "employerId";
	public static final String EMPLOYER_NAME = "employerName";

	public static final String CLINIC = "clinic";
	public static final String INSURANCE_PLAN_GROUP = "insurancePlanGroup";
	public static final String INSURANCE_COMPANY = "insuranceCompany";
	public static final String INSURANCE_COMPANY_ID = "insuranceCompanyId";
	public static final String INSURANCE_PlAN_GROUP_ID = "insurancePlanGroupId";
	public static final String NAME = "name";
	public static final String REFERRING_PROVIDER_ID = "referringProviderId";
	public static final String PHYSICIAN_TYPE = "physicianType";
	public static final String NPI = "npi";
	public static final String REFERRING_PROVIDER = "referringProvider";
	public static final String CONTACT_TYPE = "contactType";
	public static final String CONATCT_MASTER_ID = "contactMasterId";

	// OCUH And SOCH History element type
	public static final String ELEMENT_TYPE = "elementType";
	public static final String CLAIMEDIPAYERNAME = "claimEDIPayerName";
	public static final String EDIPAYERID = "ediPayerId";
	public static final String CLINICCODE = "clinicCode";
	public static final String CLINICID = "clinicId"; // Dont change this name
														// depend is there

	public static final String PARENT_ENTITY_ID = "parentEntity";
	public static final String ADDRESS1 = "address1";
	public static final String ENTITY_CODE = "entityCode";
	public static final String ENTITY = "entity";
	public static final String VERSION_ID = "versionId";
	public static final String MAXRESULT = "maxResult";

	// wc
	public static final String PROVIDER_ID = "providerId";
	public static final String LOCATION_IDS = "locationIds";
	public static final String LOCATION_ID = "locationId"; // Dont change this
															// name depend is
															// there
	public static final String WC_GROUP_NAME = "Follow-up and Appeal";
	public static final String WC_GROUP_TYPE = "WC";
	public static final String NF_GROUP_TYPE = "AU";
	public static final String WORKER_COMPENSATION = "Worker Compensation";
	public static final String NO_FAULT = "No Fault";
	public static final String FOLLOWUP_TYPE_ID = "followupTypeId";
	public static final String FOLLOWUP_METHOD_ID = "cfmethodId";
	public static final String FOLLOWUP_METHOD = "followupMethod";
	public static final String FOLLOWUP_METHOD_NAME = "methodName";
	public static final String FOLLOWUP_GROUP_ID = "followupGroupId";
	public static final String FOLLOWUP_ACTION_ID = "followupActionId";
	public static final String PATIENT_CATEGORY_ID = "patientCategoryId";
	public static final String STATUS_ID = "statusId";
	public static final String FOLLOW_UP_ACTION = "followupAction";
	public static final String PATIENT_CATEGORY = "patientCategory";
	public static final String CF_REMITTANCE_GAR_MAP = "cfRemittanceGARMap";
	public static final String REMITTANCE_GAR_MAP_ID = "remittanceGarmapId";
	public static final String CFACTION = "CFAction";
	public static final String PATIENTCATEGORY = "PatientCategory";
	public static final String PATIENT_COMMUNICATION = "PatientCommunication";

	public static final String CFREASON = "CFReason";
	public static final String CFGROUP = "CFGroup";
	public static final String CFTYPE = "CFType";
	public static final String FOLLOWUP_REASON = "followupReason";
	public static final String FOLLOWUP_REASON_ID = "followupReasonId";
	public static final String FOLLOWUP_SCRIPT = "followupScript";
	public static final String ID = "id";
	public static final String CF_SCRIPT_ID = "cfscriptId";
	public static final String CASE_ID = "caseId";
	public static final String RCMSID = "rcmsId";
	public static final String CASENAME = "caseName";
	public static final String FORM_SHORT_NAME = "formshortName";
	public static final String CHARGE_POSTING_ID = "chargePostingId";
	public static final String CHARGE_POSTING_IDS = "chargePostingIds";
	public static final String PROCEDURE_ID = "procedureId";
	public static final String PERSON = "Person";
	public static final String NON_PERSON = "Non-Person";
	public static final String IS_FORM_PRINT = "isFormPrint";
	public static final String WC_FORM_STATE = "wcFormState";
	public static final String WC_FORM_NAME = "wcFormName";
	public static final String WC_FORM_TITLE = "wcFormTitle";
	public static final String WC_FORM_GROUP = "wcFormGroup";
	public static final String FEE_SCHEDULE = "feeSchedule";
	public static final String FEE_SCHEDULE_SCOPE = "feeScheduleScope";
	public static final String FEE_SCHEDULE_ID = "feeScheduleId";
	// PatientInsurance
	public static final String PATIENT_INSURANCE_ID = "patientInsuranceId";
	public static final String INSURANCE_ORDER_CODE = "insuranceOrderCodeMaster";
	public static final String INSURANCE_ORDER_CODE_ID = "insuranceOrderCodeId";
	public static final String UPDATE_ANOTHER_INSURANCE_TO_CASE = "updateAnotherInsuranceTCase";
	public static final String CASE_INSURANCE_DETAIL_ID = "CaseInsuranceDetailId";
	// Start : SAAL
	public static final String SUBMODULEID = "subModuleId"; // dont change this
															// name dependent is
															// there
	public static final String MODULEID = "moduleId";
	public static final String MODULENAME = "moduleName";
	public static final String SUBMODULENAME = "subModuleName";
	public static final String FLEXIBLEWORKFLOWID = "flexibleWorkFlowID";
	public static final String ISSAAL = "isSaal";
	public static final String IS_SYSTEM_DEFINED = "isSystemDefined";
	public static final String ORDER = "Order";
	public static final String ORDER_BY = " ORDER BY ";
	public static final String ADDITIONALCOLUMNS = "AdditionalColumns";
	public static final String COLUMNS = "Columns";
	public static final String FROMCLAUSE = "fromClause";
	public static final String WHERECLAUSE = "whereClause";
	public static final String TOTALCOUNT = "totalCount";
	public static final String WHERE = " WHERE ";
	public static final String AND = " AND ";
	public static final String SPRESULT = "spResult";
	public static final String SELECTCLAUSE = "selectClause";
	public static final String ALPHABETICCOLUMN = "alphabeticColumn";
	public static final String DEFAULTSEARCHCRITERIA = "defaultSearchCriteria";
	public static final String SYS_DEFINED_CRITERIA = "sysDefinedCriteria";
	public static final String SAALSETUPITEMS = "saalSetupItems";
	public static final String SINGLEQUOTES = "''";
	public static final String NEXTNEVIGATORCOLUMS = "NextNevigatorColumns";

	// End : SAAL

	// Start : for generic dao
	public static final String ALIAS = "alias";

	public static final String PROVIDER = "provider";
	public static final String INSURANCE_PLAN_ID = "insurancePlanId";
	public static final String INSURANCE_PLAN_ADDRESS_ID = "insurancePlanAddressId";
	public static final String INSURANCE_PLAN_IDS = "insurancePlanIds";
	public static final String INSURANCE_PLAN = "insurancePlan";
	public static final String PROVIDER_CREDENTIAL_MASTER = "providerCredentialMaster";

	// Start : for Lab
	public static final String LABID = "labId";
	public static final String ISRADIOLOGY = "isRadiology";
	public static final String TESTID = "testId";
	public static final String LAB_TEST_ID = "labTestId";
	public static final String TEST_ALREADY_EXIST = "Test already exist";

	// Start : for Immnunization
	public static final String IMMUNIZATIONADMINISTERID = "immunizationAdministerId";
	public static final String IMMUNIZATIONID = "immunizationId";
	public static final String VISID = "visId";
	public static final String IMMUNIZATION = "Immunization";
	public static final Integer IMM_MODULE_ID = 82;
	public static final Integer SM_ID_FOR_IMM_ADMINISTER_SIGN = 390;

	// FileUtils
	public static final String MIME_TYPE = "application/octet-stream";
	public static final String APPZIP = "application/zip";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT = "attachment";
	public static final String FILE_NAME = "filename";
	public static final String SEMICOLON = ";";
	public static final String EBPLP_DOCUMENTS = "EBPLPDocuments";
	public static final String MAPPING_PATH = "MappingPath";
	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	public static final String MM_DD_YY = "MMddyy";

	public static final String SAVED_SUCCESSFULLY = "Record saved successfully.";
	public static final String UPDATED_SUCCESSFULLY = "Record updated successfully.";
	public static final String EDIT = "edit";
	String ADD = "add";
	public static final String EBP_UPDATE_SUCCESSFULLY = "EBP Tree Update Successfully";

	// for document controller
	public static final String DOCUMENTBEAN = "documentBean";
	public static final String DOCUMENT_FORWARD_BEAN = "documentForwardDetailBean";
	public static final String DOCUMENT_TYPE_BEAN = "documentTypeBean";
	public static final String BASE64IMAGE = "base64Image";
	public static final String DOCIDCAMELCASE = "docId";
	public static final String DOCIDSCAMELCASE = "docIds";
	public static final String DOCUMENTIDCAMELCASE = "documentId";
	public static final String DOCUMENTTYPEIDCAMELCASE = "documentTypeId";
	public static final String ANNOTATIONDATA = "annotationData";
	public static final String EXTENSION = "extension";
	public static final String DATAFILES = "dataFiles";
	public static final String HOSTINGTYPE = "HostingType";
	public static final String MAPPINGPATH = "MappingPath";
	public static final String CLINICDOCCONFIGID = "ClinicDocConfigId";
	public static final String SOURCE = "source";
	public static final String COMMENT = "comment";
	public static final String DOCURL = "docUrl";
	public static final String PATIENTDETAILS = "patientDetails";
	public static final String PANGENO = "pageNo";
	public static final String LOCAL = "LOCAL";
	public static final String FTP = "FTP";
	public static final String ANNOTATIONS = "annotations";
	public static final String PROVIDERNAMECAMELCASE = "providerName";
	public static final String DOCUMENTANNOTATIONDETAILID = "documentAnnotationDetailId";
	public static final String ISACTIVE = "isActive";
	public static final String DOCUMENTMAPPINGID = "documentMappingId";
	public static final String FORWARDNOTE = "forwardNote";
	public static final String REVIEWNOTE = "reviewNote";
	public static final String URLSTR = "url";
	public static final String CONTENTTYPE = "contentType";
	public static final String DSURL = "DocumentServiceURL";
	public static final String DSUSERNAME = "DocumentServiceUserName";
	public static final String DSPASSWORD = "DocumentServicePassword";
	public static final String EBPLMAPPING = "ebplMapping";
	public static final String ACTIONNAME = "actionName";
	// For HttpConnectors
	public static String POST = "POST";
	public static String GET = "GET";
	public static String XUSERNAME = "X-Username";
	public static String XPASSWORD = "X-Password";
	public static String CONTENT_TYPE = "Content-Type";
	public static String ACCEPT = "Accept";
	public static String XAUTH_TOKEN = "X-Auth-Token";
	public static String JSON = "json";

	// content types
	public static String JSON_CONTENT_TYPE = "application/json";
	public static String FORM_URL_ENCODED_TYPE = "application/x-www-form-urlencoded";

	public static final String ALREADY_EXIST = "Already Exist in System";
	// Starts : Appointment
	public static final String APPOINTMENTID = "appointmentId";
	public static final String RESOURCE_ID = "resourceId";
	public static final String RESOURCE_NAME = "resourceName";
	public static final String Procedure_NAME = "procedureName";
	public static final String APPOINTMENTVISITTYPE_Name = "aptVisitType";
	public static final String CALENDARBLOCK_ID = "calendarBlockId";
	public static final String CardView_ID = "cardViewId";
	public static final String CardView_NAME = "cardViewName";
	public static final String CalendarBlock_NAME = "calendarBlockName";
	public static final String APPOINTMENTVISITTYPE_ID = "appointmentVisitTypeId";
	public static final String HOLIDAY_ID = "holidayId";
	public static final String HOLIDAY = "holiday";
	public static final String APPOINTMENT = "appointment";
	public static final String APPOINTMENTIDS = "appointmentIds";
	public static final String CALENDARBLOCK = "calendarBlock";
	public static final String CHIEFCOMPLAINTARRAY = "chiefComplaintArray";
	public static final String MANDATORYFIELDS = "mandatoryFields";
	public static final String CARDVIEW = "cardView";
	public static final String CARDVIEWID = "cardViewId";
	public static final String FOLLOWUPFLAG = "followupFlag";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final Long VISITTYPE_PROCEDURES_MASTERID = 12L;
	// End Appointment

	public static final String BUSINESS = "business";
	

	public static final String REGULATION_NAME = "regulationName";
	public static final String INSURANCEPLAN_ADDRESS = "insurancePlanAddress";
	public static final String FOLDER_NAME = "folderName";
	public static final String MESSAGEUSERFOLDER = "messageUserFolder";
	public static final String SENDER = "sender";

	// Start Action Center : setup
	public static final String GROUP_ID = "groupId";
	public static final String FROM_ACTION = "fromAction";
	public static final String FOLDER_ID = "folderId";
	public static final String INBOX = "Inbox";
	public static final String SENT = "Sent";
	public static final String DRAFT = "Draft";
	public static final String DELETED = "Deleted";
	public static final String TAG_MSG_TASK_REM_ID = "tagMessageTaskReminderId";
	public static final String MESSAGE_ID = "messageId";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String CC_USER = "ccUser";
	public static final String TO_USER = "toUser";
	public static final String ACTION = "action";
	public static final String EMPTYARR = "[]";
	public static final String FROM_EDIT = "fromEdit";
	public static final String FOLDER_RULE_DELETED_SUCCESSFULLY = "Folder Rule Deleted Successfully";
	public static final String FOLDER_DELETED_SUCCESSFULLY = "Folder Deleted Successfully";
	public static final String SUBJECT = "subject";
	public static final String SUBJECT_TYPE = "subjectType";
	public static final String ATTACHED_DOCS = "attachedDocuments";
	public static final String DOC_ID = "documentId";
	public static final String MESSAGE_USER_FOLDER_RULE = "messageUserFolderRule";

	// End Action Center : setup

	// Paresh insert start

	// QM server details keywords
	public static final String DOCUMENTSERVICE = "DocumentService";
	public static final String BASEURL = "baseUrl";
	public static final String LOGINURL = "loginUrl";
	public static final String UPDATEFAXSTATUS = "updateFaxStatus";

	// Fax constants
	public static final String FAX_TYPE_BEAN = "faxTypeBean";
	public static final String LOGGER_DEBUG_EXECUTE = "execute method : ";
	public static final String REQUESTED = "Requested";
	public static final String REQUEST_FAILED = "Unsent";
	public static final String QUEUE = "Queued";
	public static final String SENT_SUCCESFULLY = "Complete";
	public static final String EVENTSTATUS_FAILED = "Failed";
	public static final String UPDOX_ERROR = "Failed";
	public static final String CANCEL = "Cancel";
	public static final String SENDTOUPDOX = "Send To Updox";
	public static final String SENDTOQMSSERVER = "Send Request To QMServer";
	public static final String PENDING = "Pending";

	public static final String FAXSTATUS = "faxStatus";
	public static final String FAXSTATUSMESSAGE = "faxStatusMessage";
	public static final String QMFAXREQUESTID = "qMFaxRequestId";
	public static final String SQMFAXREQUESTID = "qmfaxRequestId";
	public static final String FAXFILES = "FaxFiles";
	public static final String PROPDOCUMENTFAXSTORAGEFOLDER = "document.documentFaxStorageFolder";
	public static final String FAXREQUESTID = "faxRequestId";

	public static final String REQUESTID = "requestId";

	// Paresh insert end

	/* Start By Priyank */
	public static final String QUOTES = "'";
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String FOUR = "4";
	public static final String FIVE = "5";
	public static final String NINE = "9";
	public static final String IS_DELETED = "isDeleted";
	public static final String DELETED_DRUG = "Record(s) deleted successfully";
	public static final String TYPE = "type";
	public static final String PROVIDERID = "providerId"; // don't change this
															// name dependent is
															// there
	public static final String REQUIRED_PARAMS_MSG = "Required parameters are missing";
	public static final String COMPONENT_NAME = "componentName";
	public static final String STATIC_WHERE_CLAUSE = "staticWhereClause";
	public static final String SUB_TYPE = "subType";
	public static final String SEARCH_PARAM = "searchParam";
	public static final String DRUG_NAME = "drugName";
	public static final String CUSTOM_DRUG_ID = "customDrugId";
	public static final String DRUG_ID = "drugId";
	public static final String FAVORITE_DRUG_ID = "favoriteDrugId";
	public static final String FAVORITE_CUSTOM_SIG = "favdrugSig";
	public static final String PHARMACY_ID = "pharmacyId";
	public static final String PHARMACY_IDS = "pharmacyIds";
	public static final String PHARMACY = "pharmacy";
	public static final String CustomDrug = "customDrug";
	public static final String ERROR = "Error";
	public static final String SPI = "SPI";
	/* End By Priyank */

	/* Start Claim Management */
	public static final String SUBMISSION_TYPE = "submissionType";
	public static final String CHSUBMITTER_ID = "chSubmitterId";
	public static final String CLEARINGHOUSE_ID = "clearingHouseId";
	public static final String CHARGEPOSTING_DETAIL_ID = "chargePostingDetailId";
	public static final String CHARGEPOSTING_DETAIL_IDS = "chargePostingDetailIds";
	public static final String TRANSACTION_ID = "transactionId";
	public static final String CLAIM_ZIP_FORMAT = "%s-%s";
	public static final String GENDER_FEMALE = "Female";
	public static final String GENDER_MALE = "Male";
	public static final String PRIOR_AUTH = "prior_auth";
	public static final String EPSDT = "epsdt";
	public static final String CLAIM_PROCEDURE_IDS = "claimProcedureIds";
	public static final String CLAIM_ID = "claimId";
	public static final String PROCEDURE = "procedure";
	public static final String IS_PREVIEW = "isPreview";
	public static final String MASTER_TEMPLATE = "masterTemplates";
	public static final String CLAIM = "claim";
	public static final String CPT = "cpt";

	/* End Claim Management */

	/* Start inventory category */
	public static final String CATEGORY = "inventoryCategory";
	public static final String CATEGORY_ID = "inventoryCategoryId";
	public static final String DELETED_BY_NAME = "deleteByName";
	public static final String DELETED_BY_ID = "deleteById";
	/* End inventory category */

	/* Start inventory type */
	public static final String TYPE_ID = "inventoryTypeId";
	public static final String TYPE_CANNOT_BE_DELETED = "Type cannot be deleted as Category(s) is attached to it";
	/* End inventory type */

	/* Start inventory product */
	public static final String PRODUCT = "inventoryProduct";
	public static final String PRODUCT_ID = "inventoryProductId";
	public static final String CATEGORY_CANNOT_BE_DELETED = "Category cannot be deleted as product(s) is attached to it";
	public static final String PRODUCT_SAVED_SUCCESS = "Product added successfully";
	public static final String PRODUCT_UPDATED_SUCCESS = "Product updated successfully";
	public static final String PRODUCT_DELETED_SUCCESS = "Product deleted successfully";
	public static final String PRODUCT_EXIST = "Product already exist";
	/* End inventory product */

	/* Start inventory product detail */
	public static final String PRODUCT_DETAIL = "inventoryProductDetail";
	public static final String PRODUCT_DETAIL_ID = "inventoryProductDetailId";
	/* End inventory product detail */

	/* Start Preference */
	public static final String PREFERENCE_ID = "preferenceId";
	/* End Preference */

	/* Start RFV */

	/* Start RFV */

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	/* Start Lab Order */
	public static final String LAB_ORDER_STATUS_PENDING = "PENDING";
	public static final String LAB_ORDER_STATUS_ORDERED = "ORDERED";
	public static final String LAB_ORDER_STATUS_COMPLETED = "COMPLETED";
	public static final String LAB_ORDER_STATUS_INCOMPLETE = "INCOMPLETE";
	public static final String LAB_ORDER_STATUS_CANCELLED = "CANCELLED";
	public static final String INPROCESS = "INPROCESS";

	public static final String LAB_TEST_STATUS_PENDING = "PENDING";
	public static final String LAB_TEST_STATUS_ORDERED = "ORDERED";
	public static final String LAB_TEST_STATUS_COMPLETED = "COMPLETED";
	public static final String LAB_TEST_STATUS_INCOMPLETE = "INCOMPLETE";
	public static final String LAB_TEST_STATUS_CANCELLED = "CANCELLED";

	public static final String PROVIDERIDS = "providerIds"; // Dont change this
															// name depend is
															// there

	public static final String LAB_ORDER_ID = "LabOrderId";
	public static final String LAB_FAV_TESTID = "labFavoriteTestId";
	public static final String LAB_ORDER = "labOrder";
	public static final String LAB_ORDER_DETAIL = "labOrderDetail";
	public static final String LAB_ORDER_DETAIL_ID = "labOrderDetailId";
	public static final String TEST_NOTES = "note";
	public static final String TEST_URL = "url"; // also used in
													// documentcontroller
	public static final String LAB_FAV_ORDER_ID = "labFavOrderId";
	public static final String LAB_FAV_ORDER_DETAIL_ID = "labFavOrderDetailId";
	public static final String LABORDERID = "labOrderId";
	public static final String EXISTINGLABORDERDETAILID = "existingLabOrderDetailId";

	/* End Lab Order */

	/* Start Lab Order Result */
	static final String LAB_RESULT_TEMPLATE = "labResultTemplate";
	static final String LAB_RESULT_TEMPLATE_DETAIL = "labResultTemplateDetails";
	static final String LAB_ORDER_RESULT = "labOrderResult";
	static final String TEST_ID = "testId"; // DONT CHANGE DEPEND IS THERE
	static final String COMMENTS = "labOrderResultDetailComment";
	static final String REVIEWS = "labOrderReview";
	static final String ASSIGN_REVIEWS = "labOrderAssignReview";
	static final String LAB_ORDER_REPORT_ID = "labOrderReportId";
	static final String DELTED_NOTE = "deletedNote";
	static final String LAB_RESULT_TEMPLATE_ID = "labResultTemplateId";
	static final String LABRESULTCOMPARE = "labResultCompare";
	static final String RESULT = "Result";

	/* End Lab Order Result */

	/* start user and role */
	public static final String PRACTICEID = "practiceId";
	public static final String USERDOCUMENTID = "userDocumentId";
	public static final String DOCUMENTID = "documentId"; // dont change it
	public static final String OBJECTID = "objectId"; // dont change this name
														// dependency is there
	public static final String DOCID = "DocId";
	public static final String USER_SAVED_SUCCESS = "User saved successfully";
	public static final String USER_SAVED_FAIL = "Save user fail";
	public static final String USERNAME = "userName"; // dont change this name
														// dependency is there
	public static final String PASSWORD = "password";
	public static final String DEFAULT_PLP = "defaultPLP";
	public static final String USER_EBP_MAP = "userEBPMap";
	public static final String DOCUMENT_LIST = "documentList";
	public static final String GET_PROVIDER = "getProvider";
	public static final String ACTION_LIST = "actionList";
	public static final String GET_MENU_ACTION_TREE = "getMenuActionTree";
	public static final String ROLE_LIST = "roleList";
	public static final String DOC_LIST = "docList";
	public static final String ACTION_NAME = "actionName";
	public static final String ROLE_TYPE = "roleType";
	public static final String ACTIONIDS = "actionIds";
	public static final String ROLE_ACCESS_PERMISSION = "roleActionPermission";
	public static final String TEMPROLE = "tempRole_";
	public static final String BYROLE = "By Role";
	public static final String EBPLP = "ebplp";
	public static final String ROLEID = "roleId";
	public static final String WIDGET = "widget";
	public static final String WIDGET_COMPONENT_NAME = "widgetComponentName";
	public static final String REPORT = "report";
	public static final String WIDGET_REPORT = "widgetReport";
	public static final String ACTION_TREE = "actionTree";
	public static final String ROLE_SAVED_SUCCESS = "Role updated successfully";
	public static final String REPORT_TREE = "reportTree";
	public static final String ROLEIDS = "roleIds";
	public static final String ROLE = "role";
	public static final String LINKED_DEVICE_LIST = "linkedDevices";
	public static final String ROLE_ACTIONS_SAVED_SUCCESS = "Role Actions saved successfully";
	/* end user and role */

	/**
	 * for SAAL
	 */
	public static final String FROMRSP = "fromRsp";
	public static final String ANDOR = "andOr";
	public static final String NESTEDCONDITION = "NestedCondition";
	public static final String AGGREGATE = "aggregate";
	public static final String COLUMN = "column";
	public static final String COLUMNNAMEONLY = "columnNameOnly";
	public static final String CONDITION = "condition";
	public static final String VALUE = "value";
	public static final String VALUEOBJ = "valueObj";
	public static final String CHECKBOX = "checkbox";
	public static final String MAINJSON = "mainJson";
	public static final String SELECTEDVALUE = "selectedValue";
	public static final String ISEQLTO = "is equals to";
	public static final String ISBTWN = "is between";
	public static final String QUICKLINKCRITERIA = "QuickLinkCriteria";
	public static final String INDEX = "index";
	public static final String AND1 = "And";
	public static final String BLANKSTRING = "";
	public static final String QUICKLINK = "quickLink";
	public static final String DATE = "Date";
	public static final String MULTISELECTION = "MultiSelection";
	public static final String IN = "in";
	public static final String ISSYSTEMDEFINED = "isSystemDefined";
	public static final String EBPCOMPONENT = "EBP component";
	public static final String TXTBOX = "Textbox";
	public static final String CONTAINS = "contains";

	public static final String CHARGE_POSTING_INSURANCE_IDS = "chargePostingInsIds";
	public static final String CHARGE_POSTING_INSURANCE_ID = "chargePostingInsId";

	/* for family history */
	public static final String USER_CAN_REVIEW = "userCanReview";
	public static final String USER_PROVIDER_ID = "userProviderId";
	public static final String IS_REVIEW_NEEDED = "isReviewNeeded";
	public static final String FAMILY_HISTORY_ID = "familyHistoryId";
	public static final String DELETE_MODE = "deleteMode";
	public static final String SUCCESS_MSG = "Family History Saved Successfully";
	public static final String SUCCESS_DELETE = "Family History Deleted Successfully";
	public static final String PATIENTIDs = "patientIds";
	public static final String NOTFOUND_EOBFILE = "There is no EOB file attachment for this Reference #";
	public static final String _PATIENT = "Patient";
	public static final String PAYMENT_SAVED = "Payment saved successfully.";
	public static final String REFUND_SAVED = "Refund saved successfully.";
	public static final String ERA_LINKED = "ERA Claim Linked successfully.";
	public static final String CANCEL_NOTES = "cancelledNotes";
	public static final String PAYMENT_DETAILID = "paymentDetailId";
	public static final String WRITE_OFF = "WRITE-OFF";
	public static final String NOREFERENCE = "NOREFERENCE";
	public static final String ONLY_PAID_COMPLETED = "onlyPaidCompleted";
	public static final String FORWARD_SLASH = "/";
	public static final String MODULE_NAME = "moduleName";
	public static final String SUB_MODULE_NAME = "subModuleName";
	public static final String IS_CANCELLED = "isCancelled";
	public static final String UNAPPLIED = "Unapplied";
	public static final String MAPPLIED = "MApplied";
	public static final String DISABLED = "Disabled";

	/* for past medical history */
	public static final String PMH_SUCCESS = "Past medical history saved successfully.";
	public static final String PMH_DELETE = "Past medical history deleted successfully.";

	/* for past surgical history */
	public static final String HISTORY_TYPE = "historyType";
	public static final String PSH_SUCCESS = "Past surgical history saved successfully.";
	public static final String PSH_DELETE = "Past surgical history deleted successfully.";
	// for social history
	public static final String SOCH_SUCCESS = "Social history saved successfully.";

	/* Start : For Contact RelationType */
	public static final String ATTORNEY_RELATIONTYPEID = "33";
	public static final String EMPLOYER_RELATIONTYPEID = "9";
	public static final String ADJUSTER_RELATIONTYPEID = "34";
	/* End : For Contact RelationType */

	/* Start : For Refill */
	public static final String APPROVE_REFILL = "approveRefill";
	public static final String REFILL_NOTE = "refillNote";
	public static final String REFILL_REQUESTED = "refillRequested";
	public static final String REFILL_APPROVED = "refillApproved";
	public static final String DENIED = "DENIED";
	public static final String PRIOR_AUTH_NO = "priorAuthNo";
	public static final String RXCHANGEREQUEST = "RxChangeRequest";
	public static final String DATE_TIME = "DateTime";
	public static final String CERTIFICATE_TO_PRESCRIBE = "CertificateToPrescribe";
	public static final String DATA_2000_WAIVER_ID = "Data2000WaiverID";
	public static final String DEA_NUMBER = "DEANumber";
	public static final String FACILITY_ID = "FacilityID";
	public static final String HIN = "HIN";
	public static final String IIN_NUMBER = "IINNumber";
	public static final String MEDICAID_NUMBER = "MedicaidNumber";
	public static final String MEDICAL_RECORD_IDENTIFICATION_NUMBER_EHR = "MedicalRecordIdentificationNumberEHR";
	public static final String MEDICARE_NUMBER = "MedicareNumber";
	public static final String MUTUALLY_DEFINED = "MutuallyDefined";
	public static final String NAIC_CODE = "NAICCode";
	public static final String PATIENT_ACCOUNT_NUMBER = "PatientAccountNumber";
	public static final String PAYER_ID = "PayerID";
	public static final String NCPDPID = "NCPDPID";
	public static final String PRIOR_AUTHORIZATION = "PriorAuthorization";
	public static final String PROCESSOR_IDENTIFICATION_NUMBER = "ProcessorIdentificationNumber";
	public static final String REMS_HEALTHCARE_PROVIDER_ENROLLMENT_ID = "REMSHealthcareProviderEnrollmentID";
	public static final String REMS_HEALTHCARE_SETTING_ENROLLMENT_ID = "REMSHealthcareSettingEnrollmentID";
	public static final String REMS_PATIENT_ID = "REMSPatientID";
	public static final String SOCIAL_SECURITY = "SocialSecurity";
	public static final String STANDARD_UNIQUE_HEALTH_PLAN_IDENTIFIER = "StandardUniqueHealthPlanIdentifier";
	public static final String STATE_CONTROL_SUBSTANCE_NUMBER = "StateControlSubstanceNumber";
	public static final String STATE_LICENSE_NUMBER = "StateLicenseNumber";

	/* End */

	// following code is added for add manufacturer
	public static final String ADD_MANUFACTURER_SUCCESS = "Supplier Details Saved successfully";
	public static final String DISABLE_MANUFACTURE = "Supplier disabled successfully";
	public static final String ENABLE_MANUFACTURE = "Supplier enabled successfully";

	/* Start : Prescription Setup */
	public static final String PRESCRIPTION_SETUP = "prescriptionSetup";
	/* End : Prescription Setup */

	/* START : Elastic Search Controller */
	public static final String DRUG_ALLERGY_INTERACTION_DETAIL = "drugallergyinteractiondetail";
	public static final String ICD9CODE = "ICD9Code";
	public static final String ICD10CODE = "ICD10Code";
	public static final String ARRAY_FROM_PRESCRIPTION = "arrayFromPrescription";

	public static final String REQUEST_GET = "GET";
	public static final String REQUEST_POST = "POST";

	public static final String DRUGDRUGINTERACTIONDETAIL = "drugdruginteractiondetail";
	public static final String DRUGDESIASEINTERACTIONDETAIL = "drugdesiaseinteractiondetail";

	public static final String PRETTY = "pretty";
	public static final String HITS = "hits";
	public static final String TOTAL = "total";
	public static final int SUCCESS_CODE = 200;

	public static final String DRUG_ID_ARRAY = "drugIds";
	public static final String COUNT = "count";
	public static final String SEVIRITY = "sevirity";

	public static final String HTTPS = "https";
	public static final String HTTP = "http";
	/* END : Elastic Search Controller */

	// for storageRoom
	public static final String STORAGESUCCESS = "Storage Room Added Successfully";
	public static final String STORAGEUPDATE = "Storage Room Updated Successfully";
	public static final String STORAGAESTATUSUPDATE = "Storage Room Status Updated Successfully";
	public static final String STORAGESTATUSACTIVATED = "Storage Room activated Successfully";
	public static final String STORAGESTATUSINACTIVATED = "Storage Room In-activated Successfully";

	// for Rack
	public static final String RACKSUCCESS = "Rack Added Successfully";
	public static final String RACKSTATUSUPDATE = "Rack Status Updated Successfully";
	public static final String RACKUPDATE = "Rack Updated Successfully";
	public static final String RACKSTATUSACTIVATED = "Rack activated Successfully";
	public static final String RACKSTATUSINACTIVATED = "Rack In-activated Successfully";

	// for pallet
	public static final String PALLETUPDATE = "Pallet Updated Successfully";
	public static final String PALLETSUCCESS = "Pallet Saved Successfully";
	public static final String PALLETSTATUSACTIVATED = "Pallet activated Successfully";
	public static final String PALLETSTATUSINACTIVATED = "Pallet In-activated Successfully";

	// for EPCS
	public static final String VALIDATE_OMNI_PASS = "Validate OmniOne Password";
	public static final String PASS_MATCHED = "Password matched successfully";
	public static final String PASS_NOT_MATCHED = "Password doesn't match";
	public static final String PROVIDER_PASS = "ProviderPassword";
	public static final String ARRAY = "array";
	public static final String VALIDATE_PASSCODE = "ValidatePasscode";
	public static final String VALIDATE_IDME_PASSCODE = "Validate Id.Me Passcode";

	// Prescription
	public static final String PRESCRIPTION_ID = "prescriptionId";
	public static final String PRESCRIPTION_DETAIL_ID = "prescriptionDetailId";

	// prescription audit
	public static final String AUDIT_SUCCESS = "Success";
	public static final String AUDIT_FAILED = "Failure";

	// module name
	public static final String LAB = "Lab";
	public static final String RADIOLOGY = "Radiology";
	public static final String RX = "Rx";

	// patient Reaction
	public static final String PATIENT_REACTION = "patientReaction";
	public static final String PATIENT_REACTION_ID = "patientReactionId";

	// Prescription Administer
	public static final String PRESCRIPTION_ADMINISTER = "prescriptionAdminister";

	public static final String SCAN_DRUG = "Scan_Drug";
	public static final String BARCODE = "barcode";
	public static final String REQUEST_REQUEST_TYPE = "requestType";
	public static final String RESPONSE_BODY_STR = "responseBody";
	public static final String SUCCESS_MSG_1 = "Data Received Successfully";
	public static final String ERROR_MSG_1 = "Please provide required json";
	public static final String REQUEST_BODY_STR = "requestBody";
	public static final String FROM_ROW = "fromRow";
	public static final String ROW_COUNT = "rowCount";
	public static final String FROM_ROW_COUNT = "fromRowCount";

	public static final String SCAN_BARCODE = "Scan_Barcode";
	public static final String SCAN_INSUREANCE_CARD = "Scan Insurance Card";
	public static final String DL_FRONT_PHOTO = "DL Front Photo";

	public static final String MENU_ID = "menuId";
	public static final String USER_MENU_ID = "userMenuId";
	public static final String CONTROLLER_NAME = "ControllerName";

	// updox method names
	public static final String UPDOXPRACTICESAVE = "PracticeSave";
	public static final String UPDOXUSERSAVE = "userSave";
	public static final String PROVIDERDIRECTORYSEARCH = "ProviderDirectorySearch";
	public static final String MESSAGECOUNTFORACCOUNT = "MessageCountForAccount";
	public static final String APPLICATIONOPEN = "ApplicationOpen";
	public static final String DIRECTMESSAGESEND = "DirectMessageSend";
	public static final String USERLIST = "userList";
	public static final String EVENTNOTIFICATIONBULKCREATE = "EventNotificationBulkCreate";
	public static final String EVENTNOTIFICATIONSTATUSESGETBYBULKIDS = "EventNotificationStatusesGetByBulkIds";
	public static final String DIRECTADDRESSVALIDATE = "directAddressValidate";

	// real time formulary
	public static final String FORMULARYID = "formularyId";
	public static final String COPAYID = "copayId";
	public static final String COVERAGEID = "coverageId";
	public static final String ALTERNATEID = "alternateId";
	public static final String PAYERID = "payerId";
	public static final String PBMMEMBERID = "pbmMemberId";
	public static final String COUNTRY_CODE = "US";
	public static final String RTF = "RTF";
	public static final String RTPB = "RTPB";
	public static final String TEST_MESSAGE_1 = "1";
	public static final String TEST_MESSAGE_0 = "0";
	public static final String REAL_TIME_FORMULARY = "RealTimeFormulary";
	public static final String REAL_TIME_PRESCRIPTION_BENEFIT = "RealTimePrescriptionBenefit";

	public static final String FAVORITE_MAPPING_SUCCESS_MESSAGE = "Favorite Mapping updated successfully.";
	public static final String STATUS_UPDATED_SUCCESSFULLY = "Status updated successfully.";

	public static final String CF_BUCKET_CHARGE_IDS = "cfbucketChargeIds";

	public static final String CANCELED = "Canceled";
	public static final String DOT_HTML = ".html";
	public static final String DOT_PDF = ".pdf";
	public static final String DOT_TXT = ".txt";

	// Document Upload Path for server.
	public static final String SEARCHDOCUMENTUPLOADPATH = "Common Documents";

	String ERROR_MESSAGE_SUPPORT_TEAM = "Please contact to support team.";
	String REPORT_WIDGET_ACCESS_ID = "ReportWidgetAccessId";
	String POWER_BI_REST_API_URL = "POWER_BI_REST_API_URL";
	String PATIENT_PORTAL_REGISTERATION = "PATIENT_PORTAL_REGISTERATION";
	String PATIENT_PORTAL_TOKEN = "PATIENT_PORTAL_TOKEN";
	String POWER_BI_WORKSPACE_ID = "POWER_BI_WORKSPACE_ID";
	String POWER_BI_USERNAME = "POWER_BI_USERNAME";
	String POWER_BI_PASSWORD = "POWER_BI_PASSWORD";
	String POWER_BI_ACCESS_LEVEL = "POWER_BI_ACCESS_LEVEL";
	String POWER_BI_CLIENT_ID = "POWER_BI_CLIENT_ID";
	String POWER_BI_CONTENT_TYPE_VALUE_XWWW_FORM_URLENCODED = "POWER_BI_CONTENT_TYPE_VALUE_XWWW_FORM_URLENCODED";
	String POWER_BI_CONTENT_TYPE_VALUE_JSON = "POWER_BI_CONTENT_TYPE_VALUE_JSON";
	String POWER_BI_RESOURCE_URL = "POWER_BI_RESOURCE_URL";
	String POWER_BI_APPLICATION_ID = "POWER_BI_APPLICATION_ID";

	String FOLLOWUP = "FollowUp";
	String COLLECTION_FOLLOWUP = "CollectionFollowup";
	String APPEAL_LETTER = "AppealLetter";
	String APPEAL_LETTER_ATTACHED_DOCUMENTS = "AppealLetterAttachedDocuments";
	String FOLLOWUP_ACTIVITY = "FollowupActivity";
	String PERIODS = "periods";
	String REPORT_WIDGET_ID = "reportWidgetId";
	String CUSTOM_DATE = "customDate";
	String CUSTOM_FILTER = "customFilter";
	String BROWSER_DATE_TIME = "browserDateTime";

	String EVENT_FAILED = "Event Failed";
	String EVENT_SUCCESSFUL = "Event Successful";
	String FAILED = "Failed";
	String LOGIN_FAILED = "Login Failed";
	String LOGIN_SUCCESS = "Login Success";
	String NOT_AUTHORIZED = "Not Authorized";

	String LOG = "log";
	String LOG_FILE_DIRECTORY = "logFileDirectory";
	String AUDIT_FILE_ACCESS_DIRECTORY = "auditFileAccessDirectory";
	String PROCESSED_AUDIT_FILES = "ProcessedAuditFiles";
	String LOG_FILES = "LogFiles";

	public static final String PLAN = "Plan";

	String DYNAMIC_TEMPLATE_SETUP_ID = "dynamicTemplateSetupId";
	String TEMPLATE_DATA = "templateData";
	String MASTER_TEMPLATE_ID = "masterTemplateId";
	String PATTERN_DETAIL = "patternDetail";
	String PATTERNS = "patterns";
	String TEMPLATE_CATEGORY = "templateCategory";

	String OMNIMD_API_AUDIT = "OmniMDAPI_Audit_";
	String PAINTER_IMAGE = "painterImage";
	String PAINTER_TYPE = "patternType";
	String TEMP_ELEMENT_DATA = "tempElementData";
	String LABEL = "label";
	String _BASE64IMAGE = "Base64Image";
	String DISPLAY_NAME = "displayName";
	String DYNAMIC_TEMPLATE_COMPONENT_IMAGE = "DynamicTemplateComponentImage";
	String APPOINTMENT_CHARTING = "AppointmentCharting";
	String JPG = "jpg";
	String UNDER_SCORE = "_";
	String DASH = "-";
	String _NOTES = "Notes";
	String EMPTY_STRING = "";
	String _SNOMED_CODE = "SnomedCode";
	String _SNOMED_DESC = "SnomedDesc";
	String BASE64STRING = "base64String";
	String UNIQUE_ID = "uniqueId";
	String USERNAME_ = "username";
	String JSON_DATA = "JsonData";
	String MESSAGE = "message";
	String AUTH_TOKEN = "auth_token";
	String STATUS_CODE = "status_code";
	String SUB_RESOURCE = "sub_resource";
	String ACCESS_TOKEN = "access_token";
	String ML = "ML";
	String DATA = "data";
	String CALCULATOR_NAME = "calculator_Name";
	String CHA2DS2_VASC_SCORE = "Cha2ds2_vasc_Score";
	String VILLALTA_VCSS_SCORE = "Villalta_vcss_Score";
	String HAS_BLED_SCORE = "has_bled_score";
	String RUTHERFORD_SCORE = "Rutherford Score";
	String Right_ABI_score = "Right ABI score";
	String Left_ABI_score = "Left ABI score";
	String HEART_SCORE_FOR_MAJOR_CARDIAC_EVENTS = "heart_score_for_major_cardiac_events";
	String HISTORYOFMAJORBLEEDING = "HistoryOfMajorBleeding";
	String HISTORYOFLIBILEINR = "HistoryOfLibileINR";
	String FROM_SEARCH = "fromSearch";
	String IS_ALL_MAPPING_DELETED = "isAllMappingDeleleted";
	String ACCESS_LEVEL = "AccessLevel";
	String DATASET_ID = "DatasetId";
	String ALLOW_SAVE_AS = "AllowSaveAs";
	String ACCESSTOKEN = "AccessToken";
	String POWER_BI = "PowerBI";
	String CODE = "code";
	
	String EGFR = "GFR Score";
	// social - history
	String REPORTED_DATE = "reportedDate";

	// Medical Transcriptions
	String MEDICAL_TRANSCRIPTION_SYNC_ID = "medicalTranscribeSyncId";

	// Patient- Education
	String DOC_TITLE = "docTitle";

	// pharmacy
	public static final String PHARMACY_SAVE = "Pharmacy saved successfully";
	public static final String PHARMACY_UPDATE = "Pharmacy updated successfully";
	public static final String PHARMACY_DELETE = "Pharmacy deleted successfully";

	String SUSPENDED = "Suspended";
	String OUTSTANDING_FOR = "outStandingsFor";
	String CF_BUCKETDETAIL_ID = "cfBucketDetailId";
	String ORGANIZED_BY = "organizedBy";

	// erx
	public static final String VERSION = "20170715";
	public static final String ELEMENT_LABEL = "elementLabel";
	public static final String ELEMENT_VALUE = "elementValue";
	public static final String CAPTURED_DATE = "captureDate";
	public static final String LOINC_VERSION = "loinc_version";
	public static final String UCUM_VERSION = "ucum_version";
	public static final String BMI = "bmi";
	public static final String VITAL_BMI_SIGN = "vital_bmi_sign";
	public static final String BMI_UNIT_OF_MEASURE = "kg/m2";
	public static final String WEIGHT = "weight";
	public static final String VITAL_WEIGHT_SIGN = "vital_weight_sign";
	public static final String WEIGHT_UNIT_OF_MEASURE = "lb_av";
	public static final String HEIGHT = "height";
	public static final String VITAL_HEIGHT_SIGN = "vital_height_sign";
	public static final String HEIGHT_UNIT_OF_MEASURE = "in_i";
	public static final String ICD_CODE = "icdCode";
	public static final String ICD_SHORT_DESC = "icdShortDesc";
	public static final String DIAGNOSIS_QUALIFIER = "ABF";
	public static final String NCITCODE = "NCItCode";

	String MODIFIED_ID = "modifiedById";
	String MODIFIED_DATE = "modifiedDate";
	String TEMPLATE_IDS = "templateIds";
	String DYNAMIC_TEMPLATE_SETUP_IDS = "dynamicTemplateSetupIds";
	String DYNAMIC_TEMPLATE_MASTER_IDS = "dynamicTemplateMasterIds";
	String CUSTOME_ELEMENT = "customeElement";
	String TEMPLATEID = "TemplateId";
	String DISPLAYNAME = "DisplayName";
	String TEMPLATE_NAME = "TemplateName";
	String BUSINESS_IDS = "businessIds";

	// device integration related constants
	public static final String VITALS_LOWER = "vitals";
	public static final String HOLTER_LOWER = "holter";
	public static final String ECG_LOWER = "ecg";
	public static final String REQUIRED_PARAMS_MSG_DEVICE_TYPE = "Required parameter is missing \"Device Type\"";
	public static final String REQUIRED_PARAMS_MSG_PATIENTID_REPORT_TYPE = "Required parameter is missing \"Patient Id\" or \"Report Type\"";
	public static final String DATA_NOT_FOUND_MSG_PATIENTID_REPORT_TYPE = "Data not found for provided \"Patient Id\" and \"Report Type\"";
	public static final String MIDMARK = "MidMark";
	public static final String MIDMARK_LOWER = "midmark";
	public static final String DEVICE_TYPE = "DeviceType";
	public static final String REPORT_TYPE = "ReportType";
	// public static final String IQVITALS_REPORT_LOWER = "iqvitals report";
	// public static final String IQHOLTER_REPORT_LOWER = "iqholter report";
	// public static final String IQECG_REPORT_LOWER = "ecg report";
	public static final String REPORT_DATA_IQVITALS = "ReportDataIQVitals";
	public static final String REPORT_DATA_IQHOLTER = "ReportDataIQHolter";
	public static final String REPORT_DATA_IQECG = "ReportDataIQecgs";
	public static final String DEVICE_PATIENT = "Patient";
	public static final String DEVICE_PATIENTID = "PatientId";

	// visit activity
	public static final String REASON_FOR_VISIT = "Reason For Visit";
	public static final String VISIT_REPORT = "visitReport";
	public static final String DISPOSITON = "disposition";

	String HTML = "html";
	String CFBUCKET_CHARGE_ID = "cfBucketChargeId";
	String APPEAL_LETTER_DETAIL_ID = "appealLetterDetilaId";
	String GENERATE_APPEAL_LETTER = "generateAppealLetter";
	public static final long VISITREPORT_MODULEID = 117L;
	public static final long VISITREPORT_SUBMODULEID = 360L;

	// hotbuttonsetup start
	public static final String HotbuttonSetup = "hotButtonSetup";
	// hotbuttonsetup end

	String IMPORT_HOSTING_URL = "importHostingURL";
	String IMPORT_PORT = "importPort";
	String IMPORT_USERNAME = "importUsername";
	String IMPORT_PASSWORD = "importPassword";

	// AWS Transcription Details
	public static final String AWSREGION = "region";
	public static final String AWSSTREAMINGLANGUAGE = "audioTranscribeStreamingLanguage";

	// Update Work-Up Plan
	public static final String PLANNUM = "planNum";
	public static final String SOURCEPLAN = "sourcePlan";

	// Tablea
	String GROWTH_CHART_DATA = "growthChartData";
	String PDF_CONTENT_TYPE = "application/pdf";
	String X_TABLEAU_AUTH = "X-Tableau-Auth";
	String FORM_TYPE_CODE = "formTypeCode";

	// Assessment Note
	public static final String DISCUSSION_TEXT = "DiscussionText";
	public static final String ASSESSMENT_NOTE_TABLE_ID = "assessmentNoteTableId";

	String INSURANCE_COMPANY_IDS = "insuranceCompanyIds";
	String INSURANCE_GROUP_IDS = "insuranceGroupIds";
	String INSURANCE_PLAN_CATEGORY_IDS = "insurancePlanCategoryIds";
	String PATIENT_CATEGORY_IDS = "patientCategoryIds";
	String PROCEDURE_IDS = "procedureIds";
	String VISIT_TYPE_IDS = "visitTypeIds";
	String CPT_CODES = "cptCodes";
	String ICD_CODES = "icdCodes";
	String CHARGE_TYPE = "chargeType";

	String IMAGING_CENTER_IDS = "imagingCenterIds";
	String LAB_TEST_IDS = "labTestIds";
	String LAB_IDS = "labIds";

	// referrals constant
	String INCOMING_REFERRAL = "incomingReferral";
	
}
