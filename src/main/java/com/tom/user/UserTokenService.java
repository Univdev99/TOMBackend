package com.tom.user;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CCJoshi UserTokenService
 */
public interface UserTokenService {

	/**
	 * To Save UserToken Object
	 * 
	 * @param userToken
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Jun 23, 2017
	 */
	public UserToken save(UserToken userToken) throws Exception;

	/**
	 * To Get UserToken Object
	 * 
	 * @param tokenId
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Jun 23, 2017
	 */
	public UserToken getUserToken(String tokenId) throws Exception;

	/**
	 * To Delete UserToken Object
	 * 
	 * @param tokenId
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Jun 26, 2017
	 */
	public void deleteToken(String tokenId) throws Exception;

	public SessionDetail saveSessionDetail(HttpServletRequest httpRequest, Long tokenId) throws Exception;
}
