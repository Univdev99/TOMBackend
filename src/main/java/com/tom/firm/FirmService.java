package com.tom.firm;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tom.common.exception.ExceptionWrapper;

@Service
public interface FirmService {

	FirmProfile createFirmProfile(FirmProfile firmProfile) throws Exception;

	FirmProfile getFirmProfile(Long firmProfileId) throws Exception;

	FirmProfile getFirmByUserId(Long userId) throws Exception;

	List<Map<String, Object>> getAccpetedFirmProject(Long userId) throws Exception;

	List<Map<String, Object>> getFirmProjectHistory(Long firmProfileId) throws Exception;

	Integer projectCompleted(Long projectCompletedId) throws Exception;

}
