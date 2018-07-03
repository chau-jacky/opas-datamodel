package model.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class LegalAgreement extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7754935388499918241L;

	@Id
	@Column(name = "LEGAL_AGREEMENT_ID")
	private long legalAgreementId;

	@Column(name = "LEGAL_AGREEMENT_LINE_1", length = 35)
	private String legalAgreementLine1;
	@Column(name = "LEGAL_AGREEMENT_LINE_2", length = 35)
	private String legalAgreementLine2;
	@Column(name = "LEGAL_AGREEMENT_LINE_3", length = 35)
	private String legalAgreementLine3;
	@Column(name = "LEGAL_AGREEMENT_LINE_4", length = 35)
	private String legalAgreementLine4;
	@Column(name = "LEGAL_AGREEMENT_LINE_5", length = 35)
	private String legalAgreementLine5;
	@Column(name = "LEGAL_AGREEMENT_LINE_6", length = 35)
	private String legalAgreementLine6;

	private String yearOfVersion;

	public long getLegalAgreementId() {
		return legalAgreementId;
	}

	public void setLegalAgreementId(long legalAgreementId) {
		this.legalAgreementId = legalAgreementId;
	}

	public String getLegalAgreementLine1() {
		return legalAgreementLine1;
	}

	public void setLegalAgreementLine1(String legalAgreementLine1) {
		this.legalAgreementLine1 = legalAgreementLine1;
	}

	public String getLegalAgreementLine2() {
		return legalAgreementLine2;
	}

	public void setLegalAgreementLine2(String legalAgreementLine2) {
		this.legalAgreementLine2 = legalAgreementLine2;
	}

	public String getLegalAgreementLine3() {
		return legalAgreementLine3;
	}

	public void setLegalAgreementLine3(String legalAgreementLine3) {
		this.legalAgreementLine3 = legalAgreementLine3;
	}

	public String getLegalAgreementLine4() {
		return legalAgreementLine4;
	}

	public void setLegalAgreementLine4(String legalAgreementLine4) {
		this.legalAgreementLine4 = legalAgreementLine4;
	}

	public String getLegalAgreementLine5() {
		return legalAgreementLine5;
	}

	public void setLegalAgreementLine5(String legalAgreementLine5) {
		this.legalAgreementLine5 = legalAgreementLine5;
	}

	public String getLegalAgreementLine6() {
		return legalAgreementLine6;
	}

	public void setLegalAgreementLine6(String legalAgreementLine6) {
		this.legalAgreementLine6 = legalAgreementLine6;
	}

	public String getYearOfVersion() {
		return yearOfVersion;
	}

	public void setYearOfVersion(String yearOfVersion) {
		this.yearOfVersion = yearOfVersion;
	}

}
