package model.event;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.OpasOrganizationObject;

@Entity
@Table(name = "EVENT_CONFIG")
public class EventConfig extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8805622235234656587L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false)
	private Long eventConfigId;

	@Column(name = "CLASS_NAME", nullable = false)
	private String className;

	@Enumerated(EnumType.STRING)
	@Column(name = "EVENT_TYPE", nullable = false)
	private EventType eventType;

	@Column(name = "EVENT_QUEUE", nullable = false)
	private String eventQueueName;

	public Long getEventConfigId() {
		return eventConfigId;
	}

	public void setEventConfigId(Long eventConfigId) {
		this.eventConfigId = eventConfigId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getEventQueueName() {
		return eventQueueName;
	}

	public void setEventQueueName(String eventQueueName) {
		this.eventQueueName = eventQueueName;
	}

}
