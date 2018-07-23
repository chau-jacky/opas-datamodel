package model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.OpasOrganizationObject;

@Entity
@Table(name = "EVENT_INSTANCE")
public class EventInstance extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7418373882929512757L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "EVENT_INSTANCE_ID", nullable = false)
	private Long eventInstanceId;

	@Column(name = "EVENT_TYPE", nullable = false)
	private EventType eventType;

	@Column(name = "EVENT_CLASS_NAME", nullable = false)
	private String eventClassName;

	@Column(name = "EVENT_CLASS_ID", nullable = false)
	private Long eventClassId;

	@Column(name = "EVENT_QUEUE_NAME", nullable = false)
	private String eventQueueName;

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getEventClassName() {
		return eventClassName;
	}

	public void setEventClassName(String eventClassName) {
		this.eventClassName = eventClassName;
	}

	public Long getEventClassId() {
		return eventClassId;
	}

	public void setEventClassId(Long eventClassId) {
		this.eventClassId = eventClassId;
	}

	public String getEventQueueName() {
		return eventQueueName;
	}

	public void setEventQueueName(String eventQueueName) {
		this.eventQueueName = eventQueueName;
	}

	public String toString() {
		StringBuffer bf = new StringBuffer();
		bf.append("EventType=");
		bf.append(eventType);
		bf.append("/EventClassName=");
		bf.append(eventClassName);
		bf.append("/EventClassId=");
		bf.append(eventClassId);
		return bf.toString();
	}
}
