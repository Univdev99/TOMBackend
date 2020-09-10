package com.tom.document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.net.URL;
import java.nio.channels.AsynchronousFileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.blob.BlockBlobURL;
import com.microsoft.azure.storage.blob.ContainerURL;
import com.microsoft.azure.storage.blob.PipelineOptions;
import com.microsoft.azure.storage.blob.ServiceURL;
import com.microsoft.azure.storage.blob.SharedKeyCredentials;
import com.microsoft.azure.storage.blob.StorageURL;
import com.microsoft.azure.storage.blob.TransferManager;
import com.microsoft.azure.storage.blob.models.ContainerCreateResponse;
import com.microsoft.rest.v2.RestException;
import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.common.project.ProjectProperties;
import com.tom.common.util.FileUtil;
import com.tom.util.DateUtil;

@Service
public class DocumentServiceImpl implements DocumentService, Constant {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	FileUtil fileUtil;

	@SuppressWarnings("rawtypes")
	@Autowired
	GenericDao<Serializable> genericDao;

	@Autowired
	GenericDao<Document> documentDao;

	@Override
	public List<Document> getMappingDocument(Long transactionId, String moduleName, String source) throws Exception {
		LOGGER.debug("Execute Method: getMappingDocument()");
		Map<String, Object> param = new HashMap<>();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("from Document ").append(" where transactionId =:transactionId ");
		param.put("transactionId", transactionId);
//		if (transactionId != null) {
//			sqlQuery.append(" and DMT.objectId =:objectId ");
//			param.put(OBJECTID, objectId);
//		}
//		if (docId != null) {
//			sqlQuery.append(" and DT.DocId in ( :documentId )");
//			String[] docIds = null;
//			if (docId.contains(",")) {
//				docIds = docId.split(",");
//				param.put(DOCUMENTID, docIds);
//			} else {
//				param.put(DOCUMENTID, docId);
//			}
//		}
		return documentDao.executeHqlSelect(sqlQuery, param);
	}

	// this method is used for temporary upload document on API server.
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Document> commonUpload(DocumentUpload documentUpload, MultipartFile file, Long documentSize,
			boolean isOnlySingleFileForModuleAndSubModule) throws Exception {
		LOGGER.debug("Execute method: commonUpload()");

		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + "_"
				+ DateUtil.getCurrentTimestamp().toString().replace(":", "-").replaceAll("[. ]", "_")
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		String ext = "_" + DateUtil.getCurrentTimestamp().toString().replace(":", "-").replaceAll("[. ]", "_")
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//		***********************
		ContainerURL containerURL;

		// Creating a sample file to use in the sample

		File sampleFile = null;
		sampleFile = File.createTempFile(
				file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")), ext);
		System.out.println(">> Creating a sample file at: " + sampleFile.toString());
		OutputStream os = new FileOutputStream(sampleFile);

		// Starts writing the bytes in it
		os.write(file.getBytes());
		System.out.println("Successfully" + " byte inserted");
// Close the file 
		os.close();

		// Retrieve the credentials and initialize SharedKeyCredentials
		String accountName = "csg10032000ddd7da37";
		String accountKey = "RZ1zb4TcOW4Aqko6HtNWJQrfiM8T+kMK1bHZbwtRRC9eFS9EndvIAWeM3wnLIRyzYN0U69fAzYdMTCeQRxzZsw==";

		// Create a ServiceURL to call the Blob service. We will also use this to
		// construct the ContainerURL
		SharedKeyCredentials creds = new SharedKeyCredentials(accountName, accountKey);
		// We are using a default pipeline here, you can learn more about it at
		// https://github.com/Azure/azure-storage-java/wiki/Azure-Storage-Java-V10-Overview
		final ServiceURL serviceURL = new ServiceURL(new URL("https://" + accountName + ".blob.core.windows.net"),
				StorageURL.createPipeline(creds, new PipelineOptions()));

		// Let's create a container using a blocking call to Azure Storage
		// If container exists, we'll catch and continue
		containerURL = serviceURL.createContainerURL("tomcontainer");

		try {
			ContainerCreateResponse response = containerURL.create(null, null, null).blockingGet();
			System.out.println("Container Create Response was " + response.statusCode());

		} catch (RestException e) {
			if (e instanceof RestException && ((RestException) e).response().statusCode() != 409) {
				throw e;
			} else {
				System.out.println("quickstart container already exists, resuming...");
			}
		}

		// Create a BlockBlobURL to run operations on Blobs
		BlockBlobURL blobURL = containerURL.createBlockBlobURL(fileName);
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(sampleFile.toPath());

		// Uploading a file to the blobURL using the high-level methods available in
		// TransferManager class
		// Alternatively call the PutBlob/PutBlock low-level methods from BlockBlobURL
		// type
		TransferManager.uploadFileToBlockBlob(fileChannel, blobURL, 8 * 1024 * 1024, null, null).subscribe(response -> {
			System.out.println("Completed upload request.");
			System.out.println(response.response().statusCode());
		});

//		***********************

		fileUtil.uploadFileInDir(file, fileName, ProjectProperties.get(DOC_PATH), null);

		List<Document> savedDocList = new ArrayList<Document>();
		// ------------ DOCUMENT OBJECT SAVE ---------------------
		Document documentBean = new Document();
		documentBean.setModuleName(documentUpload.getModuleName());
		documentBean.setSource(documentUpload.getSource());
		Integer docTitleIndex = file.getOriginalFilename().lastIndexOf('.');
		documentBean.setDocTitle(file.getOriginalFilename().substring(0, docTitleIndex));
		documentBean.setDocPath(ProjectProperties.get(DOC_PATH) + File.separator + fileName);
		documentBean
				.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1));
		genericDao.saveOrUpdate(documentBean);
		// -------------- RESPONSE -------------------------------------
		savedDocList.add(documentBean);
		return savedDocList;
	}

	@Override
	public void updateObjectId(Long documentId, Long objectId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("update documentTable SET transactionId = :objectId  where documentId = :documentId");
		Map<String, Object> map = new HashMap<>();
		map.put(DOCUMENT_ID, documentId);
		map.put(OBJECT_ID, objectId);
		documentDao.executeSqlUpdate(queryString, map);

	}

	@Override
	public void deleteDocument(Long documentId) throws Exception {
		StringBuilder queryString = new StringBuilder();
		queryString.append("update documentTable SET transactionId = NULL  where documentId = :documentId");
		Map<String, Object> map = new HashMap<>();
		map.put(DOCUMENT_ID, documentId);
		documentDao.executeSqlUpdate(queryString, map);
	}

}
