package com.tom.common.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.tom.user.User;

public interface TokenManager {
	/**
	 * Creates a new token for the user and returns its {@link TokenInfo}. It may add it to the token list or replace the previous one for the user. Never returns {@code null}.
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	TokenInfo createNewToken(UserDetails userDetails) throws Exception;

	/** Removes all tokens for user. */
	void removeUserDetails(UserDetails userDetails);

	/** Removes a single token. */
	UserDetails removeToken(String token);

	/** Returns user details for a token. */
	UserDetails getUserDetails(String token);

	/** Returns a collection with token information for a particular user. */
	Collection<TokenInfo> getUserTokens(UserDetails userDetails);

	/** Returns a map from valid tokens to users. */
	Map<String, UserDetails> getValidUsers();

	/**
	 * @param userDetails
	 * @param token
	 * @return
	 * @author CCJoshi 
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Jun 26, 2017
	 */
	TokenInfo addToken(UserDetails userDetails, String token);
	
	/**
	 * @param token
	 * @return
	 * @author CCJoshi 
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Jun 26, 2017
	 */
	User checkDBToken(String token);
	
	void logoutUser(User user) throws Exception;
}
