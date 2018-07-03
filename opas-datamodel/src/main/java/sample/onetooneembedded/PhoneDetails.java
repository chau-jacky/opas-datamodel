package sample.onetooneembedded;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneDetails {

	private String provider;

	private String technology;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

}
