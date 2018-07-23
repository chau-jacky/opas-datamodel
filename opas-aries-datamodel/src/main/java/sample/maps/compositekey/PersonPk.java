package sample.maps.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Embeddable
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8282213750279762774L;

	@Column(name = "ID")
	private Long id;

	@Column(name = "VERSION")
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + version;
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
		PersonPk other = (PersonPk) obj;
		if (id != other.id)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
