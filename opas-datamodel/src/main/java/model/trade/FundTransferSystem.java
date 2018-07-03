package model.trade;

import java.util.HashMap;
import java.util.Map;
import model.OpasOrganizationObject;

public class FundTransferSystem extends OpasOrganizationObject {
	
	private Map<FtsCode, NationalClearingSystemCode> ftsToClearingSystemCodeMap;
	
	private FtsCode ftsCode;
	private NationalClearingSystemCode nationalClearingSystemCode;
	
	public FundTransferSystem(String ftsCode) {
		
		ftsToClearingSystemCodeMap = new HashMap<FtsCode, NationalClearingSystemCode>();
		
		ftsToClearingSystemCodeMap.put(FtsCode.BLANK, NationalClearingSystemCode.XX);
		ftsToClearingSystemCodeMap.put(FtsCode.BOJ, NationalClearingSystemCode.XX);
		ftsToClearingSystemCodeMap.put(FtsCode.BSB, NationalClearingSystemCode.AU);
		ftsToClearingSystemCodeMap.put(FtsCode.CHAPS, NationalClearingSystemCode.SC);
		ftsToClearingSystemCodeMap.put(FtsCode.CHIPS, NationalClearingSystemCode.CH);
		ftsToClearingSystemCodeMap.put(FtsCode.CUA, NationalClearingSystemCode.XX);
		ftsToClearingSystemCodeMap.put(FtsCode.EURO, NationalClearingSystemCode.SC);
		ftsToClearingSystemCodeMap.put(FtsCode.FED, NationalClearingSystemCode.FW);
		ftsToClearingSystemCodeMap.put(FtsCode.EURO, NationalClearingSystemCode.SC);
		ftsToClearingSystemCodeMap.put(FtsCode.NET, NationalClearingSystemCode.XX);
		ftsToClearingSystemCodeMap.put(FtsCode.RT, NationalClearingSystemCode.RT);
		ftsToClearingSystemCodeMap.put(FtsCode.RTGS, NationalClearingSystemCode.XX);
		ftsToClearingSystemCodeMap.put(FtsCode.RTGSP, NationalClearingSystemCode.RT);
		ftsToClearingSystemCodeMap.put(FtsCode.SUS, NationalClearingSystemCode.XX);
		
		this.setFtsCode(ftsCode);
		this.setNationalClearingSystemCode();
		
	}
	
	public Map<FtsCode, NationalClearingSystemCode> getFtsToClearingSystemCodeMap() {
		return ftsToClearingSystemCodeMap;
	}

	public void setFtsToClearingSystemCodeMap(Map<FtsCode, NationalClearingSystemCode> ftsToClearingSystemCodeMap) {
		this.ftsToClearingSystemCodeMap = ftsToClearingSystemCodeMap;
	}

	public FtsCode getFtsCode() {
		return ftsCode;
	}

	public void setFtsCode(FtsCode ftsCode) {
		this.ftsCode = ftsCode;
	}
	
	public void setFtsCode(String ftsCode) {
		this.ftsCode = FtsCode.getFtsCode(ftsCode);
	}

	public NationalClearingSystemCode getNationalClearingSystemCode() {
		return nationalClearingSystemCode;
	}
	
	public void setNationalClearingSystemCode() {
		this.nationalClearingSystemCode = ftsToClearingSystemCodeMap.get(ftsCode);
	}
	
}
