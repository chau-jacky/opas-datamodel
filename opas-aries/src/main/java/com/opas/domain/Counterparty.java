package com.opas.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Counterparty implements Serializable {

	private static final long serialVersionUID = 3678107792576131001L;

	private String counterpartyId;
	private String counterpartyAcronym;
	private String customerIndicator;
	private String swiftAddress;
	private String faxNumber;
	private String counterpartyType;

	public Counterparty() {
		super();
	}

	public Counterparty(String counterpartyId, String counterpartyAcronym) {
		super();
		this.counterpartyId = counterpartyId;
		this.counterpartyAcronym = counterpartyAcronym;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterpartyAcronym() {
		return counterpartyAcronym;
	}

	public void setCounterpartyAcronym(String counterpartyAcronym) {
		this.counterpartyAcronym = counterpartyAcronym;
	}

	public String getCustomerIndicator() {
		return customerIndicator;
	}

	public void setCustomerIndicator(String customerIndicator) {
		this.customerIndicator = customerIndicator;
	}

	public String getSwiftAddress() {
		return swiftAddress;
	}

	public void setSwiftAddress(String swiftAddress) {
		this.swiftAddress = swiftAddress;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getCounterpartyType() {
		return counterpartyType;
	}

	public void setCounterpartyType(String counterpartyType) {
		this.counterpartyType = counterpartyType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Counterparty other = (Counterparty) obj;
		if (counterpartyId == null) {
			if (other.counterpartyId != null)
				return false;
		} else if (!counterpartyId.equals(other.counterpartyId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((counterpartyId == null) ? 0 : counterpartyId.hashCode());
		return result;
	}
	
}