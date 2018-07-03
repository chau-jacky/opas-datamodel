package sample.maps.compositekey;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "SAMPLE_MAPS_PERSON_COMPOSITE_KEY")
public class Person {

	@EmbeddedId
	private PersonPk id;

	@ElementCollection
	@CollectionTable(name = "SAMPLE_MAPS_PERSON_COMPOSITE_KEY_ATTRIBUTE")
	Map<String, String> attributes;

	public Person() {
		this.attributes = new HashMap<String, String>();
	}

	public PersonPk getId() {
		return id;
	}

	public void setId(PersonPk id) {
		this.id = id;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
