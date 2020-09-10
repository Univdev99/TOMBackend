package com.tom.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "NotificationTable")
public class Notification extends StatusDefaultImport implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 5366969219107370613L;
	private Long notificationId;
	private String notificationMessage;
	private Long toUserId;
	private Long fromUserId;
	private Integer isRead;
	private String notificationFor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	@Column(name = "isRead")
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getNotificationFor() {
		return notificationFor;
	}

	public void setNotificationFor(String notificationFor) {
		this.notificationFor = notificationFor;
	}

}
