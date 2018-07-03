package model.event;

import java.sql.Timestamp;

public abstract class Event {

	private long eventId;
	private Timestamp createDatetime;
	private Timestamp startProcessDateTime;
	private Timestamp endProcessDateTime;

	private EventStatus status;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Timestamp getStartProcessDateTime() {
		return startProcessDateTime;
	}

	public void setStartProcessDateTime(Timestamp startProcessDateTime) {
		this.startProcessDateTime = startProcessDateTime;
	}

	public Timestamp getEndProcessDateTime() {
		return endProcessDateTime;
	}

	public void setEndProcessDateTime(Timestamp endProcessDateTime) {
		this.endProcessDateTime = endProcessDateTime;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

}
