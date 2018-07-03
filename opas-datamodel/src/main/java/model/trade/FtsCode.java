package model.trade;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum FtsCode {
	
	BLANK(""), 
	BOJ("BOJ"), 
	BSB("BSB"), 
	CSH("CSH"), 
	CHAPS("CHAPS"), 
	CHIPS("CHIPS"), 
	CUA("CUA"), 
	EURO("EURO"), 
	FED("FED"), 
	NET("NET"), 
	RT("RT"), 
	RTGS("RTGS"), 
	RTGSP("RTGSP"), 
	SUS("SUS");
	
	private String code;
	private static final Map<String, FtsCode> FTS_MAP;
	
	FtsCode(String code) { 
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	static {
		
		Map<String, FtsCode> map = new ConcurrentHashMap<String, FtsCode>();
		for(FtsCode instance: FtsCode.values()) {
			map.put(instance.getCode(), instance);
		}
		FTS_MAP = Collections.unmodifiableMap(map);
		
	}
	
	public static FtsCode getFtsCode(String code) {
		return FTS_MAP.get(code);
	}
	
}
