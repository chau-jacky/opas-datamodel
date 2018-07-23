package model.party;

import java.io.Serializable;

import model.OpasObject;

public class PartyAttribute extends OpasObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2103044167257888937L;

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
