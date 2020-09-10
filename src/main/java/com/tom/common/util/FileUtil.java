
package com.tom.common.util;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;

@Service
public class FileUtil implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	public Long getFileSizeMegaBytes(Long bytes) {
		return bytes / (1024 * 1024);
	}

	public MultipartFile uploadFileInDir(MultipartFile file, String setFileName, String uploadFilePath,
			String mappingPath) throws Exception {
		LOGGER.debug("Execute method : uploadFileInDir()");
		if (file != null) {
			File localDir = new File(uploadFilePath);
			if (!localDir.exists()) {
				localDir.mkdirs();
			}
			File localFile = new File(uploadFilePath + File.separator + setFileName);
			try (FileOutputStream fos = new FileOutputStream(localFile)) {
				fos.write(file.getBytes());
				fos.close();
			} catch (Exception e) {
				LOGGER.error("Error in uploadFileInDir()" + e);
				throw new TOMRuntimeException("Error in uploadFileInDir()", e);
			}
		}
		return file;
	}

}
