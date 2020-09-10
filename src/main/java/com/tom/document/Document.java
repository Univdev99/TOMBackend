
package com.tom.document;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.tom.common.beans.StatusDefaultImport;
import com.tom.common.view.View;

@Entity
@Table(name = "DocumentTable")
public class Document extends StatusDefaultImport implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonView(View.GenericView.class)
	private Long documentId;
	private String docTitle;
	private String docPath;
	private String moduleName;
	private String source;
	private Long transactionId;
	private String extension;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "documentId", unique = true, nullable = false)
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Transient
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
