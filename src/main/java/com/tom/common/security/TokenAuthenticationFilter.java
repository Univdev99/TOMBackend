package com.tom.common.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.GenericFilterBean;


import com.tom.common.constant.Constant;
import com.tom.common.encryption.SHA1Encryptor;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.user.UserToken;
import com.tom.user.UserTokenService;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;
import com.tom.util.StringUtil;

/**
 * Takes care of HTTP request/response pre-processing for login/logout and token
 * check. Login can be performed on any URL, logout only on specified
 * {@link #logoutLink}. All the interaction with Spring Security should be
 * performed via {@link AuthenticationService}.
 * <p>
 * {@link SecurityContextHolder} is used here only for debug outputs. While this
 * class is configured to be used by Spring Security (configured filter on
 * FORM_LOGIN_FILTER position), but it doesn't really depend on it at all.
 * 
 * @author Chandani Joshi
 *
*/
public class TokenAuthenticationFilter extends GenericFilterBean implements Constant {
	static final Logger Logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";

	/**
	 * Request attribute that indicates that this filter will not continue with
	 * the chain. Handy after login/logout, etc.
	 */
	private static final String REQUEST_ATTR_DO_NOT_CONTINUE = "MyAuthenticationFilter-doNotContinue";

	private final String logoutLink;
	private final AuthenticationService authenticationService;

	@Autowired
	UserTokenService userTokenService;

	public TokenAuthenticationFilter(AuthenticationService authenticationService, String logoutLink) {
		this.authenticationService = authenticationService;
		this.logoutLink = logoutLink;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Logger.debug(" *** MyAuthenticationFilter.doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean authenticated = checkToken(httpRequest, httpResponse);

		if (canRequestProcessingContinue(httpRequest) && "POST".equals(httpRequest.getMethod())) {
			// If we're not authenticated, we don't bother with logout at all.
			// Logout does not work in the same request with login - this does
			// not make sense,
			// because logout works with token and login returns it.
			if (authenticated) {
				checkLogout(httpRequest, httpResponse);
			}

			// Login works just fine even when we provide token that is valid up
			// to this request,
			// because then we get a new one.
			try {
				checkLogin(httpRequest, httpResponse);
			} catch (NoSuchAlgorithmException e) {
				throw new TOMRuntimeException("NoSuchAlgorithmException", e);
			} catch (BadCredentialsException e) {
				httpRequest.getRequestDispatcher("/tom/login/badCredentials").forward(request, response);
				return;
			} catch (DisabledException e) {
				httpRequest.getRequestDispatcher("/omnione/login/disabledUser").forward(request, response);
				return;
			} catch (LockedException e) {
				httpRequest.getRequestDispatcher("/omnione/login/lockedUser").forward(request, response);
				return;
			} catch (Exception e) {
				throw new TOMRuntimeException("Exception", e);
			}
		}

		if (canRequestProcessingContinue(httpRequest)) {

			chain.doFilter(request, response);
		}
		Logger.debug(" === AUTHENTICATION: " + SecurityContextHolder.getContext().getAuthentication());

	}

	private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
		String authorization = httpRequest.getHeader("Authorization");
		String username = httpRequest.getHeader(HEADER_USERNAME);
		String password = httpRequest.getHeader(HEADER_PASSWORD);
		String role = httpRequest.getHeader(ROLE);
		String randomKey = httpRequest.getHeader("X-RandomKey");

		if (authorization != null) {
			checkBasicAuthorization(authorization, httpResponse, httpRequest);
			doNotContinueWithRequestProcessing(httpRequest);
		} else if (username != null && password != null) {
			String encryptedPassword = SHA1Encryptor.sHA1(password);
			checkUsernameAndPassword(username, encryptedPassword,role, httpResponse, httpRequest);
		} else if (randomKey != null) {
//			loginUsingRandomKey(randomKey, httpResponse, httpRequest);
		} else {
			
		}
	}

//AA	private void loginUsingRandomKey(String randomKey, HttpServletResponse httpResponse, HttpServletRequest httpRequest)
//			throws Exception {
//		TokenInfo tokenInfo = authenticationService.authenticate(randomKey, httpRequest.getRemoteAddr());
//		setTokenInfo(tokenInfo, httpResponse, httpRequest);
//	}

	private void checkBasicAuthorization(String authorization, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(authorization);
		if (tokenizer.countTokens() < 2) {
			return;
		}
		if (!"Basic".equalsIgnoreCase(tokenizer.nextToken())) {
			return;
		}

		String base64 = tokenizer.nextToken();
		String loginPassword = new String(Base64.decode(base64.getBytes(StandardCharsets.UTF_8)));

		tokenizer = new StringTokenizer(loginPassword, ":");
//		checkUsernameAndPassword(tokenizer.nextToken(), tokenizer.nextToken(), httpResponse, httpRequest);
	}

	private void checkUsernameAndPassword(String username, String password,String role, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) throws Exception {

		// TokenInfo tokenInfo = authenticationService.authenticate(username,
		// password);
		String clientIP = httpRequest.getHeader("X-FORWARDED-FOR");
		if (StringUtil.isNullorEmptyWithNullString(clientIP)) {
			clientIP = httpRequest.getRemoteAddr();
		}
		TokenInfo tokenInfo = authenticationService.authenticate(username, password, role);
		setTokenInfo(tokenInfo, httpResponse, httpRequest);

	}

	private void setTokenInfo(TokenInfo tokenInfo, HttpServletResponse httpResponse, HttpServletRequest httpRequest)
			throws Exception {
		if (tokenInfo != null) {
			httpResponse.setHeader(XAUTHTOKEN, tokenInfo.getToken());
			httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, null);
			UserToken userToken = new UserToken();
			userToken.setTokenId(tokenInfo.getToken());
			userToken.setUser(CommonUtil.getContextUser());
			userToken.setNode(httpRequest.getServerName());
			userToken.setCreatedDate(DateUtil.getCurrentTimestamp());
			try {
				userTokenService.save(userToken);
//				userTokenService.saveSessionDetail(httpRequest, userToken.getUserTokenId());
			} catch (Exception e) {
				throw new TOMRuntimeException("Exception in saving token", e);
			}
		} else {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/** Returns true, if request contains valid authentication token. */
	private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String token = httpRequest.getHeader(XAUTHTOKEN);
		if (token == null) {
			return false;
		}

		if (authenticationService.checkToken(token)) {
			Logger.debug(" *** X-Auth-Token valid for: "
					+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return true;
		} else {
			Logger.debug(" *** Invalid X-Auth-Token : " + token);
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			doNotContinueWithRequestProcessing(httpRequest);
		}
		return false;
	}

	private void checkLogout(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		if (currentLink(httpRequest).equals(logoutLink)) {
			String token = httpRequest.getHeader(XAUTHTOKEN);
			// we go here only authenticated, token must not be null
			try {
				httpRequest.getRequestDispatcher("/tom/login/logout").forward(httpRequest, httpResponse);
			} catch (Exception e) {
				throw new TOMRuntimeException("Exception in delete token", e);
			}
			authenticationService.logout(token);
			doNotContinueWithRequestProcessing(httpRequest);
			try {
				userTokenService.deleteToken(token);
			} catch (Exception e) {
				throw new TOMRuntimeException("Exception in delete token", e);
			}
		}
	}

	// or use Springs util instead: new
	// UrlPathHelper().getPathWithinApplication(httpRequest)
	// shame on Servlet API for not providing this without any hassle :-(
	private String currentLink(HttpServletRequest httpRequest) {
		if (httpRequest.getPathInfo() == null) {
			return httpRequest.getServletPath();
		}
		return httpRequest.getServletPath() + httpRequest.getPathInfo();
	}

	/**
	 * This is set in cases when we don't want to continue down the filter
	 * chain. This occurs for any {@link HttpServletResponse#SC_UNAUTHORIZED}
	 * and also for login or logout.
	 */
	private void doNotContinueWithRequestProcessing(HttpServletRequest httpRequest) {
		httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, "");
	}

	private boolean canRequestProcessingContinue(HttpServletRequest httpRequest) {
		return httpRequest.getAttribute(REQUEST_ATTR_DO_NOT_CONTINUE) == null;
	}
}
