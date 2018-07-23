package confirmation;

import java.io.Serializable;

import model.OpasObject;

public class Message extends OpasObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166513783892484018L;

	private String status;
	private long tradeId;
	private String type;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
