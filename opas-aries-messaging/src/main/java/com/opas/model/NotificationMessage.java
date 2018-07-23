package com.opas.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationMessage implements Serializable {

	private static final long serialVersionUID = -5263535214872454813L;

	private static AtomicInteger at = new AtomicInteger(0);

	private int messageId;

	private String messageDetails;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}

	public NotificationMessage() {
		super();
		messageId = at.incrementAndGet();
		messageDetails = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageDetails == null) ? 0 : messageDetails.hashCode());
		result = prime * result + messageId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationMessage other = (NotificationMessage) obj;
		if (messageDetails == null) {
			if (other.messageDetails != null)
				return false;
		} else if (!messageDetails.equals(other.messageDetails))
			return false;
		if (messageId != other.messageId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotificationMessage [messageId=" + messageId + ", messageDetails=" + messageDetails + "]";
	}

}