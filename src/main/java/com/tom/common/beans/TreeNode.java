package com.tom.common.beans;

import java.util.List;

public class TreeNode {
	private String title;
	private String key;
	private Boolean select;
	private List<TreeNode> children;
	private boolean status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Boolean getSelect() {
		return select;
	}

	public void setSelect(Boolean select) {
		this.select = select;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
