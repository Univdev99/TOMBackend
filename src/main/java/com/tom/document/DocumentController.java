package com.tom.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Status;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.common.util.FileUtil;
import com.tom.util.JsonUtil;

@RestController
@RequestMapping("/tom/document")
@PropertySource("classpath:project.properties")
public class DocumentController implements Constant {

	static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	Environment environment;

	@Autowired
	private DocumentService documentService;

	@Autowired
	FileUtil fileUtil;

	@RequestMapping(value = { "/get" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status commonGetDocument(HttpServletRequest request) {
		LOGGER.debug("Execute method: commonGetDocument()");
		List<Map<String, Object>> dataMap = new ArrayList<>();
		Long transactionId = null;
		String docId = null;
		String documentTitle = null;
		String moduleName = null;
		String source = null;
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (!JsonUtil.isRequiredParamNull(json, new String[] { "transactionId" })) {
				transactionId = json.get("transactionId").asLong();
			}
			if (!JsonUtil.isRequiredParamNull(json, new String[] { DOCIDCAMELCASE })) {
				docId = json.get(DOCIDCAMELCASE).asText();
			}
			if (!JsonUtil.isRequiredParamNull(json, new String[] { "source" })) {
				source = json.get("source").asText();
			}
//			Long moduleId = json.get("moduleId").asLong();
//			Long subModuleId = json.get("subModuleId").asLong();
//			if (json.get("documentTitle") != null) {
//				documentTitle = json.get("documentTitle").asText();
//			}

			if (!JsonUtil.isRequiredParamNull(json, new String[] { "moduleName" })) {
				moduleName = json.get("moduleName").asText();
			}
			if (transactionId != null) {
//				documentService.getMappingDocument(transactionId, moduleName,source)
			}
			return new Status(ConstantStatus.OK, "",
					documentService.getMappingDocument(transactionId, moduleName, source));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new TOMRuntimeException("Error in commonGetDocument()", ex);
		}

	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object commonDocumentUpload(HttpServletRequest request, @RequestParam String dataObject,
			@RequestParam MultipartFile file) {
		LOGGER.debug("Execute method: commonDocumentUpload()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			DocumentUpload uploadBean = mapper.readValue(dataObject, DocumentUpload.class);
			Integer fileSize = file.getBytes().length;
			Long documentSize = fileUtil.getFileSizeMegaBytes(Long.parseLong(fileSize.toString()));
			if (documentSize < Double.parseDouble(environment.getProperty("document.uploadFileSize"))) {

				return new Status(ConstantStatus.OK, null,
						documentService.commonUpload(uploadBean, file, Long.parseLong(fileSize.toString()), false));
			} else {
				return new Status(ConstantStatus.INTERNAL_SERVER_ERROR, "Document must be bellow 50 MB.");
			}
		} catch (TOMRuntimeException o) {

			LOGGER.error(o.getMessage());
			return new Status(ConstantStatus.INTERNAL_SERVER_ERROR,
					"Some information missing during uploading document. Contact system team.");
		} catch (Exception ex) {

			ex.printStackTrace();
			throw new TOMRuntimeException("Error in commonDocumentUpload()", ex);
		}
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getWholeProProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : getWholeProProfile()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { DOCUMENT_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long documentId = json.get(DOCUMENT_ID).asLong();
			documentService.deleteDocument(documentId);
			return new Status(ConstantStatus.OK_NO_MESSAGE, "");
		} catch (Exception e) {
			LOGGER.error("Error in getWholeProProfile() method :: ", e);
			throw new TOMRuntimeException("Error in getWholeProProfile()", e);
		}
	}

}
