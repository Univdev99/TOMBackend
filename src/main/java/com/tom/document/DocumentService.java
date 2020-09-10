package com.tom.document;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tom.common.exception.ExceptionWrapper;

public interface DocumentService {

	public List<Document> getMappingDocument(Long transactionId, String moduleName, String source) throws Exception;

	public List<Document> commonUpload(DocumentUpload uploadBean, MultipartFile file, Long documentSize,
			boolean isOnlySingleFileForModuleAndSubModule) throws Exception;

	public void updateObjectId(Long documentId, Long professionalProfileId) throws Exception;

	public void deleteDocument(Long documentId) throws Exception;

}