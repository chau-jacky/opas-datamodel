package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class OpasObject {

	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@Column(name = "LAST_UPDATED_USER_ID", nullable = false)
	private Long lastUpdatedUserId;

	@Column(name = "LAST_UPDATED_DATETIME", nullable = false)
	private Timestamp lastUpdatedDatetime;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "RECORD_STATUS", nullable = false)
	private RecordStatus recordStatus;

	public OpasObject() {
		super();
		this.version = new Long(0);
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(Long lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	public Timestamp getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(Timestamp lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (version ^ (version >>> 32));
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
		OpasObject other = (OpasObject) obj;
		if (version != other.version)
			return false;
		return true;
	}

}
