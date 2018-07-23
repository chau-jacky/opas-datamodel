package settlement.swift;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import model.product.ProductCategory;

public class SwiftMessageLiterals {
	
	public static final String SLASH = "/";
	public static final String HASH = "#";
	public static final String SPACE = " ";
	public static final String CHIPS_PARTICIPANT = "CP";
	public static final String CHIPS_UID = "CH";
	public static final String BOT_PURPOSE_CODE_PREFIX = "BOT PURPOSE CODE";
	public static final String BOT_FXSWAP_PURPOSE_CODE = "318160";
	public static final String BOT_OTHER_PURPOSE_CODE = "318164";
	public static final Map<ProductCategory, String> BOT_PURPOSE_CODE_MAP;
	
	static {
		
		BOT_PURPOSE_CODE_MAP = new ConcurrentHashMap<ProductCategory, String>();
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.FX_SPOT, "318158");
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.FX_FORWARD, "318159");
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.FUTURES, "318164"); //OTHER
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.OPTION_ON_FUTURES, "318164"); //OTHER
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.FRA, "318161");
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.SWAP, "318161");
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.MCNNI, "318164"); //OTHER
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.MCNI, "318163");
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.ZCNI, "318164"); //OTHER
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.ZCNNI, "318164"); //OTHER
		BOT_PURPOSE_CODE_MAP.put(ProductCategory.CALL_TERM, "318164"); //OTHER
			
	}
	
	public static String retrieveCountryCodeFromSwiftBic(String swiftBic) {
		
		String countryCode = StringUtils.EMPTY;
		
		countryCode = StringUtils.substring(swiftBic, 4, 6);
		
		return countryCode;
		
	}
	
	public enum Mt202Field21CCodes {
		NONREF;
	}
	
	public enum Mt202Field13CCodes {
		CLSTIME, RNCTIME, SNDTIME;
	}
	
	public enum Mt202Field52ACodes {
		AT, AU, BL, CC, CN, ES, FW, GR, HK, IE, IN, IT, PL, PT, SC;
	}
	
	public enum Mt202Field52DCodes {
		AT, AU, BL, CC, CH, CN, CP, ES, FW, GR, HK, IE, IN, IT, PL, PT, RU, SC, SW;
	}
	
	public enum Mt202Field56ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CN"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IN"), PL("PL"), PT("PT"), RT("RT"), SC("SC"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field56ACodes> MT202_FIELD56A_CODE_MAP;
		
		Mt202Field56ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field56ACodes> map = new ConcurrentHashMap<String, Mt202Field56ACodes>();
			for(Mt202Field56ACodes instance: Mt202Field56ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD56A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field56ACodes getMt202Field56ACodes(String code) {
			return MT202_FIELD56A_CODE_MAP.get(code);
		}
	}
	
	public enum Mt202Field56DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RT("RT"), RU("RU"), 
		SC("SC"), SW("SW"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field56DCodes> MT202_FIELD56D_CODE_MAP;
		
		Mt202Field56DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field56DCodes> map = new ConcurrentHashMap<String, Mt202Field56DCodes>();
			for(Mt202Field56DCodes instance: Mt202Field56DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD56D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field56DCodes getMt202Field56DCodes(String code) {
			return MT202_FIELD56D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt202Field57ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CN"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RT("RT"), SC("SC"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field57ACodes> MT202_FIELD57A_CODE_MAP;
		
		Mt202Field57ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field57ACodes> map = new ConcurrentHashMap<String, Mt202Field57ACodes>();
			for(Mt202Field57ACodes instance: Mt202Field57ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD57A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field57ACodes getMt202Field57ACodes(String code) {
			return MT202_FIELD57A_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt202Field57DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RT("RT"), RU("RU"), 
		SC("SC"), SW("SW"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field57DCodes> MT202_FIELD57D_CODE_MAP;
		
		Mt202Field57DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field57DCodes> map = new ConcurrentHashMap<String, Mt202Field57DCodes>();
			for(Mt202Field57DCodes instance: Mt202Field57DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD57D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field57DCodes getMt202Field57DCodes(String code) {
			return MT202_FIELD57D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt202Field58ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CC"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RT("RT"), SC("SC"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field58ACodes> MT202_FIELD58A_CODE_MAP;
		
		Mt202Field58ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field58ACodes> map = new ConcurrentHashMap<String, Mt202Field58ACodes>();
			for(Mt202Field58ACodes instance: Mt202Field58ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD58A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field58ACodes getMt202Field58ACodes(String code) {
			return MT202_FIELD58A_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt202Field58DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RT("RT"), RU("RU"), 
		SC("SC"), SW("SW"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt202Field58DCodes> MT202_FIELD58D_CODE_MAP;
		
		Mt202Field58DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt202Field58DCodes> map = new ConcurrentHashMap<String, Mt202Field58DCodes>();
			for(Mt202Field58DCodes instance: Mt202Field58DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT202_FIELD58D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt202Field58DCodes getMt202Field58DCodes(String code) {
			return MT202_FIELD58D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt202Field72Codes {
		ACC, BNF, INS, INT, PHON, PHONBEN, PHONIBK, REC, TELE, TELEBEN, TELEIBK, TSU;
	}
	
	public static String retrieveCountryDescription(String countryCode) {
		
		String countryDescription = StringUtils.EMPTY;
		
		Locale locale = new Locale("", countryCode);
		countryDescription = StringUtils.upperCase(locale.getDisplayCountry(Locale.ENGLISH));
		
		return countryDescription;
		
	}
	
	public enum Mt103Field13CCodes {
		CLSTIME, RNCTIME, SNDTIME;
	}
	
	public enum Mt103Field23BCodes {
		CRED, CRTS, SPAY, SPRI, SSTD;
	}
	
	public enum Mt103Field23ECodes {
		CHQB, CORT, HOLD, INTC, PHOB, PHOI, PHON, REPA, SDVA, TELB, TELE, TELI;
	}
	
	public enum Mt103Field50FCodes {
		ARNU, CCPT, CUST, DRLC, EMPL, NIDN, SOSE, TXID;
	}
	
	public enum Mt103Field52ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CN"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IN"), PL("PL"), PT("PT"), SC("SC");
		
		private String code;
		private static final Map<String, Mt103Field52ACodes> MT103_FIELD52A_CODE_MAP;
		
		Mt103Field52ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field52ACodes> map = new ConcurrentHashMap<String, Mt103Field52ACodes>();
			for(Mt103Field52ACodes instance: Mt103Field52ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD52A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field52ACodes getMt103Field52ACodes(String code) {
			return MT103_FIELD52A_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field52DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), PL("PL"), PT("PT"), RU("RU"), SC("SC"), 
		SW("SW");
		
		private String code;
		private static final Map<String, Mt103Field52DCodes> MT103_FIELD52D_CODE_MAP;
		
		Mt103Field52DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field52DCodes> map = new ConcurrentHashMap<String, Mt103Field52DCodes>();
			for(Mt103Field52DCodes instance: Mt103Field52DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD52D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field52DCodes getMt103Field52DCodes(String code) {
			return MT103_FIELD52D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field56ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CN"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IN"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), SC("SC");
		
		private String code;
		private static final Map<String, Mt103Field56ACodes> MT103_FIELD56A_CODE_MAP;
		
		Mt103Field56ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field56ACodes> map = new ConcurrentHashMap<String, Mt103Field56ACodes>();
			for(Mt103Field56ACodes instance: Mt103Field56ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD56A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field56ACodes getMt103Field56ACodes(String code) {
			return MT103_FIELD56A_CODE_MAP.get(code);
		}
	}
	
	public enum Mt103Field56CCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), 
		RU("RU"), SC("SC"), SW("SW");
		
		private String code;
		private static final Map<String, Mt103Field56CCodes> MT103_FIELD56C_CODE_MAP;
		
		Mt103Field56CCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field56CCodes> map = new ConcurrentHashMap<String, Mt103Field56CCodes>();
			for(Mt103Field56CCodes instance: Mt103Field56CCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD56C_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field56CCodes getMt103Field56CCodes(String code) {
			return MT103_FIELD56C_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field56DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), 
		RU("RU"), SC("SC"), SW("SW");
		
		private String code;
		private static final Map<String, Mt103Field56DCodes> MT103_FIELD56D_CODE_MAP;
		
		Mt103Field56DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field56DCodes> map = new ConcurrentHashMap<String, Mt103Field56DCodes>();
			for(Mt103Field56DCodes instance: Mt103Field56DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD56D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field56DCodes getMt103Field56DCodes(String code) {
			return MT103_FIELD56D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field57ACodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CN("CN"), ES("ES"), FW("FW"), GR("GR"), HK("HK"), 
		IE("IE"), IN("IN"), IT("IT"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), SC("SC"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt103Field57ACodes> MT103_FIELD57A_CODE_MAP;
		
		Mt103Field57ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field57ACodes> map = new ConcurrentHashMap<String, Mt103Field57ACodes>();
			for(Mt103Field57ACodes instance: Mt103Field57ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD57A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field57ACodes getMt103Field57ACodes(String code) {
			return MT103_FIELD57A_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field57CCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), 
		RU("RU"), SC("SC"), SW("SW"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt103Field57CCodes> MT103_FIELD57C_CODE_MAP;
		
		Mt103Field57CCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field57CCodes> map = new ConcurrentHashMap<String, Mt103Field57CCodes>();
			for(Mt103Field57CCodes instance: Mt103Field57CCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD57C_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field57CCodes getMt103Field57CCodes(String code) {
			return MT103_FIELD57C_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field57DCodes {
		AT("AT"), AU("AU"), BL("BL"), CC("CC"), CH("CH"), CN("CN"), CP("CP"), ES("ES"), FW("FW"), 
		GR("GR"), HK("HK"), IE("IE"), IN("IN"), IT("IT"), NZ("NZ"), PL("PL"), PT("PT"), RT("RT"), 
		RU("RU"), SC("SC"), SW("SW"), ZA("ZA");
		
		private String code;
		private static final Map<String, Mt103Field57DCodes> MT103_FIELD57D_CODE_MAP;
		
		Mt103Field57DCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field57DCodes> map = new ConcurrentHashMap<String, Mt103Field57DCodes>();
			for(Mt103Field57DCodes instance: Mt103Field57DCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD57D_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field57DCodes getMt103Field57DCodes(String code) {
			return MT103_FIELD57D_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field59Codes {
		CH("CH");
		
		private String code;
		private static final Map<String, Mt103Field59Codes> MT103_FIELD59_CODE_MAP;
		
		Mt103Field59Codes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field59Codes> map = new ConcurrentHashMap<String, Mt103Field59Codes>();
			for(Mt103Field59Codes instance: Mt103Field59Codes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD59_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field59Codes getMt103Field59Codes(String code) {
			return MT103_FIELD59_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field59ACodes {
		CH("CH");
		
		private String code;
		private static final Map<String, Mt103Field59ACodes> MT103_FIELD59A_CODE_MAP;
		
		Mt103Field59ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field59ACodes> map = new ConcurrentHashMap<String, Mt103Field59ACodes>();
			for(Mt103Field59ACodes instance: Mt103Field59ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD59A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field59ACodes getMt103Field59ACodes(String code) {
			return MT103_FIELD59A_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field59FCodes {
		
		CH("CH");
		
		private String code;
		private static final Map<String, Mt103Field59FCodes> MT103_FIELD59F_CODE_MAP;
		
		Mt103Field59FCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field59FCodes> map = new ConcurrentHashMap<String, Mt103Field59FCodes>();
			for(Mt103Field59FCodes instance: Mt103Field59FCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD59F_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field59FCodes getMt103Field59FCodes(String code) {
			return MT103_FIELD59F_CODE_MAP.get(code);
		}
		
	}
	
	public enum Mt103Field70Codes {
		INV, IPI, RFB, ROC, TSU;
	}
	
	public enum Mt103Field71ACodes {
		
		BEN("BEN"), OUR("OUR"), SHA("SHA");
		
		private String code;
		private static final Map<String, Mt103Field71ACodes> MT103_FIELD71A_CODE_MAP;
		
		Mt103Field71ACodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103Field71ACodes> map = new ConcurrentHashMap<String, Mt103Field71ACodes>();
			for(Mt103Field71ACodes instance: Mt103Field71ACodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_FIELD71A_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103Field71ACodes getMt103Field71ACodes(String code) {
			return MT103_FIELD71A_CODE_MAP.get(code);
		}
	}
	
	public enum Mt103Field72Codes {
		ACC, INS, INT, REC;
	}
	
	public enum Mt103Field77BCodes {
		BENEFRES, ORDERRES;
	}
	
	public enum Mt103SenderAndReceiverBicCountryCodes {
		
		AD("AD"), AT("AT"), BE("BE"), BG("BG"), BV("BV"), CH("CH"), CY("CY"), CZ("CZ"), DE("DE"), 
		DK("DK"), ES("ES"), EE("EE"), FI("FI"), FR("FR"), GB("GB"), GF("GF"), GI("GI"), GP("GP"), 
		GR("GR"), HU("HU"), IE("IE"), IS("IS"), IT("IT"), LI("LI"), LT("LT"), LU("LU"), LV("LV"), 
		MC("MC"), MQ("MQ"), MT("MT"), NL("NL"), NO("NO"), PL("PL"), PM("PM"), PT("PT"), RE("RE"), 
		RO("RO"), SE("SE"), SI("SI"), SJ("SJ"), SK("SK"), SM("SM"), TF("TF"), VA("VA");
		
		private String code;
		private static final Map<String, Mt103SenderAndReceiverBicCountryCodes> MT103_BIC_COUNTRY_CODE_MAP;
		
		Mt103SenderAndReceiverBicCountryCodes(String code) { 
			this.code = code;
		}
		
		public String getCode() {
			return this.code;
		}
		
		static {
			
			Map<String, Mt103SenderAndReceiverBicCountryCodes> map = new ConcurrentHashMap<String, Mt103SenderAndReceiverBicCountryCodes>();
			for(Mt103SenderAndReceiverBicCountryCodes instance: Mt103SenderAndReceiverBicCountryCodes.values()) {
				map.put(instance.getCode(), instance);
			}
			MT103_BIC_COUNTRY_CODE_MAP = Collections.unmodifiableMap(map);
			
		}
		
		public static Mt103SenderAndReceiverBicCountryCodes getMt103SenderAndReceiverBicCountryCodes(String code) {
			return MT103_BIC_COUNTRY_CODE_MAP.get(code);
		}
		
	}
	
}
