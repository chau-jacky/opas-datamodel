package sample.maps;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "SAMPLE_MAPS_PERSON")
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	@CollectionTable(name = "SAMPLE_MAPS_PERSON_ATTRIBUTE")
	Map<String, String> attributes;

	public Person() {
		this.attributes = new HashMap<String, String>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
