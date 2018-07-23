package model.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRADE_ATTRIBUTE")
public class TradeAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135501424766022257L;

	@Id
	private TradeAttributePK id;

	@Column(name = "VALUE")
	private String attributeValue;

	public TradeAttributePK getId() {
		return id;
	}

	public void setId(TradeAttributePK id) {
		this.id = id;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
