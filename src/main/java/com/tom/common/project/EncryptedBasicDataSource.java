package com.tom.common.project;

import java.sql.SQLException;
import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tom.common.encryption.TripleDES;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * This class provides ability to perform decryption of properies of the
 * {@link org.apache.commons.dbcp.BasicDataSource BasicDataSource} while
 * creating the {@link javax.sql.DataSource DataSource}
 * 
 * <p>
 * The following properties of the
 * {@link org.apache.commons.dbcp.BasicDataSource BasicDataSource} will be
 * decrypted using {@link com.omnimd.pms.util.TripleDES TripleDES}, so you need
 * to pass these properties in encypted format (using
 * {@link com.omnimd.pms.util.TripleDES#decrypt(String)
 * TripleDES.decrypt(String)}).
 * <ul>
 * <li>{@link org.apache.commons.dbcp.BasicDataSource#url url}</li>
 * <li>{@link org.apache.commons.dbcp.BasicDataSource#username username}</li>
 * <li>{@link org.apache.commons.dbcp.BasicDataSource#password password}</li>
 * </ul>
 * 
 * 
 * @author branpariya
 *
 */
public class EncryptedBasicDataSource extends HikariDataSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptedBasicDataSource.class);
	String username;
	String maximumPoolSize;
	String cachePrepStmts;
	String prepStmtCacheSize;
	String prepStmtCacheSqlLimit;

	/*
	 * (non-Javadoc)
	 * 
	 * Before creating the DataSource it will get the encrypted properties from
	 * DataSource then decrypt then and set them agin to the Datasource
	 * 
	 * @see org.apache.commons.dbcp.BasicDataSource#createDataSource()
	 */

	protected synchronized HikariConfig createDataSource() throws SQLException {
		HikariConfig config = new HikariConfig();

		// String decryptedDriverClassName =
		// decrypt(super.getDriverClassName());
		config.setDriverClassName(super.getDriverClassName());

		// String decryptedURL = decrypt(super.getJdbcUrl());
		config.setJdbcUrl(super.getJdbcUrl());

		// String decryptedUsername = decrypt( super.getUsername() );
		config.setUsername(super.getUsername());

		// String decryptedPassword = decrypt( super.getPassword() );
		config.setPassword(super.getPassword());
		config.setMaximumPoolSize(250);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setConnectionTestQuery("SELECT 1");
		config.setIdleTimeout(180000);
		return config;
	}

	/**
	 * Performs decryption using {@link TripleDES#decrypt(String)}.
	 * 
	 * @param message
	 *            The String (in hex) to be decrypted
	 * @return The decrypted String
	 */
	private String decrypt(String message) {
		try {
			return TripleDES.decrypt(message);
		} catch (DecoderException de) { // This means the message is not
										// encrypted
			LOGGER.debug("DecoderException in decrypt", de);
			return message;
		} catch (Exception e) {
			LOGGER.error("ERROR occured in decrypt", e);
			return message;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCachePrepStmts() {
		return cachePrepStmts;
	}

	public void setCachePrepStmts(String cachePrepStmts) {
		this.cachePrepStmts = cachePrepStmts;
	}

	public String getPrepStmtCacheSize() {
		return prepStmtCacheSize;
	}

	public void setPrepStmtCacheSize(String prepStmtCacheSize) {
		this.prepStmtCacheSize = prepStmtCacheSize;
	}

	public String getPrepStmtCacheSqlLimit() {
		return prepStmtCacheSqlLimit;
	}

	public void setPrepStmtCacheSqlLimit(String prepStmtCacheSqlLimit) {
		this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
	}
}
