package model.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class FxSupplementaryInformation extends OpasOrganizationObject implements Serializable {

	private final static String EMPTY_STRING = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5967812648090725378L;
	
	@Id
	@Column(name = "FX_SUPPLEMENTARY_INFO_ID")
	private long fxSupplementaryInfoId;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_1", length = 35)
	private String senderToReceiverInfoLine1;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_2", length = 35)
	private String senderToReceiverInfoLine2;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_3", length = 35)
	private String senderToReceiverInfoLine3;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_4", length = 35)
	private String senderToReceiverInfoLine4;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_5", length = 35)
	private String senderToReceiverInfoLine5;
	@Column(name = "SENDER_TO_RECEIVER_INFO_LINE_6", length = 35)
	private String senderToReceiverInfoLine6;
	
	public FxSupplementaryInformation () {
		senderToReceiverInfoLine1 = new String(EMPTY_STRING);
		senderToReceiverInfoLine2 = new String(EMPTY_STRING);
		senderToReceiverInfoLine3 = new String(EMPTY_STRING);
		senderToReceiverInfoLine4 = new String(EMPTY_STRING);
		senderToReceiverInfoLine5 = new String(EMPTY_STRING);
		senderToReceiverInfoLine6 = new String(EMPTY_STRING);
	}
	
	public long getFxSupplementaryInfoId() {
		return fxSupplementaryInfoId;
	}
	
	public void setFxSupplementaryInfoId(long fxSupplementaryInfoId) {
		this.fxSupplementaryInfoId = fxSupplementaryInfoId;
	}
	
	public String getSenderToReceiverInfoLine1() {
		return senderToReceiverInfoLine1;
	}
	
	public void setSenderToReceiverInfoLine1(String senderToReceiverInfoLine1) {
		this.senderToReceiverInfoLine1 = senderToReceiverInfoLine1;
	}
	public String getSenderToReceiverInfoLine2() {
		return senderToReceiverInfoLine2;
	}
	
	public void setSenderToReceiverInfoLine2(String senderToReceiverInfoLine2) {
		this.senderToReceiverInfoLine2 = senderToReceiverInfoLine2;
	}
	
	public String getSenderToReceiverInfoLine3() {
		return senderToReceiverInfoLine3;
	}
	
	public void setSenderToReceiverInfoLine3(String senderToReceiverInfoLine3) {
		this.senderToReceiverInfoLine3 = senderToReceiverInfoLine3;
	}
	
	public String getSenderToReceiverInfoLine4() {
		return senderToReceiverInfoLine4;
	}
	
	public void setSenderToReceiverInfoLine4(String senderToReceiverInfoLine4) {
		this.senderToReceiverInfoLine4 = senderToReceiverInfoLine4;
	}
	
	public String getSenderToReceiverInfoLine5() {
		return senderToReceiverInfoLine5;
	}
	
	public void setSenderToReceiverInfoLine5(String senderToReceiverInfoLine5) {
		this.senderToReceiverInfoLine5 = senderToReceiverInfoLine5;
	}
	
	public String getSenderToReceiverInfoLine6() {
		return senderToReceiverInfoLine6;
	}
	
	public void setSenderToReceiverInfoLine6(String senderToReceiverInfoLine6) {
		this.senderToReceiverInfoLine6 = senderToReceiverInfoLine6;
	}
}
