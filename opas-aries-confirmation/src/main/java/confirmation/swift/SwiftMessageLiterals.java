package confirmation.swift;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import model.cashflow.CashFlowSi;
import model.trade.PaymentIndicator;
import model.trade.Trade;
import model.trade.TradeFxForward;
import model.trade.TradeFxSpot;

public class SwiftMessageLiterals {

	public static final String NEW = "NEW";
	//public static final String EMPTY_STRING = "";
	public static final String COLON_STRING = ":";
	public static final String OFFSHORE_RENMINBI_CODE = "CNH";
	public static final String RENMINBI_CODE = "CNY";
	public static final String CHIPS_ACCOUNT_PREFIX = "/CH";
	public static final String COUNTERPARTY_ACCOUNT_PREFIX = "/CP";
	public static final String SLASH = "/";
	public static final String ZERO_TIMEZONE_OFFSET = "00";
	public static final String TIMEZONE_OFFSET_MINUS_SIGN = "N";
	public static final String CHINA_COUNTRY_CODE = "CN";
	public static final String AS_REQUIRED = "AS REQUIRED";
	public static final int MT300_FIELD22C_LENGTH = 16;
	
	public enum Field22ACodes {
		
		NEWT, AMND, CANC, DUPL, EXOP;
		
	}
	
	public enum Field94ACodes {
		AGNT, BILA, BROK;
	}
	
	public enum Field17ICodes {
		N, Y;
	}
	
	public enum Field82aCodes {
		ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, LEIC, NAME, USCH, USFW;
	}
	
	public enum Field87aCodes {
		ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, LEIC, NAME, USCH, USFW;
	}
	
	public enum Field83aCodes {
		ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, LEIC, NAME, USCH, USFW;
	}
	
	public enum Field77HCodes {
		AFB, DERV, FBF, FEOMA, ICOM, IFEMA, ISDA, ISDACN, OTHER;
	}
	
	public enum Field17FCodes {
		N, Y;
	}
	
	public enum Field17OCodes {
		N, Y;
	}
	
	public enum Field53aCodes {
		ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, NAME, USCH, USFW, NETS, SSIS;
	}
	
	public enum Field56aCodes {
		ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, NAME, USCH, USFW, NETS, SSIS;
	}
	
	public enum Field57aCodes {
		NET, NONE, SSI, UNKNOWN, ABIC, UKWN, ACCT, ADD1, ADD2, CITY, CLRC, GBSC, NAME, USCH, USFW, NETS, SSIS;
	}
	
	public enum Field29ACodes {
		DEPT, FAXT, NAME, PHON, TELX;
	}
	
	public enum Field22LCodes {
		ASIC, CAABASC, CABCBCSC, CAMBMSC, CANBFCSC, CANLDSS, CANSNSSC, CANTNTSO, CANUNSO, CAONOSC, CAPEIOSS,
		CAQCAMF, CASKFCAA, CAYTOSS, CFTC, ESMA, FFMS, HKMA, JFSA, MAS, OTHR, SEC;
	}
	

	/** 
	 * Return the party prefix value based on the given SWIFT Bic Code. 
	 * The prefix would be the first 4 characters of SWIFT Bic Code.
	 * 
	 * @param swiftBic SWIFT Bic Code
	 * @return
	 */
	public static String getPartyPrefixFromSwiftBic(String swiftBic) {
		
		String partyPrefix;
		
		if (StringUtils.length(swiftBic) >= 4) {
			/* Position 1 - 4 of the Swift Bic Code */
			partyPrefix = StringUtils.substring(swiftBic, 0, 4);
		} else {
			partyPrefix = StringUtils.EMPTY;
		}
			
		return partyPrefix;
	}

	/**
	 * Return the party suffix value based on the given SWIFT Bic Code. 
	 * The prefix would be the first 4 characters of SWIFT Bic Code.
	 * 
	 * @param swiftBic SWIFT Bic Code
	 * @return
	 */
	public static String getPartySuffixFromSwiftBic(String swiftBic) {
		
		String partySuffix;
		
		if (StringUtils.length(swiftBic) >= 8) {
			/* Position 7 - 8 of the Swift Bic Code */
			partySuffix = StringUtils.substring(swiftBic, 6, 8);
		} else {
			partySuffix = StringUtils.EMPTY;
		}
		
		return partySuffix;
		
	}

	/**
	 * Return the String representation of a four-digit number which taken taken out from the given rate. 
	 * The four-digit number would be consisted of the rightmost non-zero digit of the given rate, preceded 
	 * by the three digits to the left of it. If there is no digit to the left of it, the space would be zero 
	 * filled.
	 * 
	 * @param rate rate value in decimal format
	 * @return String representation of a four-digit number
	 */
	public static String getRightmost4DigitsFromRateInString(BigDecimal rate) {
		
		String rateInString = String.format("%04d",
				new Integer(String.valueOf(rate.doubleValue()).replace(".", "")));
		
		String rightmost4DigitsOfRate = StringUtils.EMPTY;
		int lengthOfRateInString = rateInString.length();
		if (lengthOfRateInString >= 4) {
			rightmost4DigitsOfRate = rateInString.substring(lengthOfRateInString - 4,
					lengthOfRateInString);
		}
		
		return rightmost4DigitsOfRate;
		
	}
	
	
	/**
	 * Compares two strings alphabetically. Letter is taking precedence over number.
	 * The result is zero if the two argument strings are equal, -1 if the argument String 1
	 * precedes the argument String 2 and +1 if the argument String 1 comes after the argument
	 * String 2.
	 * 
	 * @param string1 Input String Pattern 1
	 * @param string2 Input String Pattern 2
	 * @return -1 if Input String 1 precedes Input String 2 alphabetically <p>
	 *          0 if Input String 1 equals to Input String 2 <p>
	 *          1 if Input String 1 comes after String 2 alphabetically
	 * 
	 */
	public static int alphabeticalCompare(String string1, String string2) {
		
		int compareResult = 0;
		
		if (!StringUtils.equals(string1, string2)) {
			CharacterIterator charItr1 = new StringCharacterIterator(string1);
			CharacterIterator charItr2 = new StringCharacterIterator(string2);
			
			boolean isChar1Letter;
			boolean isChar2Letter;
			
			char char2 = charItr2.first();
			
			for (char char1 = charItr1.first(); char1 != CharacterIterator.DONE; char1 = charItr1.next()) {
				isChar1Letter = Character.isLetter(char2);
				
				if (char2 != CharacterIterator.DONE) {
					isChar2Letter = Character.isLetter(char2);
				} else {
					/* Input String 1 is longer than Input String 2 */
					compareResult = 1;
					break;
				}
				
				if (isChar1Letter == true & isChar2Letter == false) {
					compareResult = -1;
					break;
				} else if (isChar1Letter == false & isChar2Letter == true) {
					compareResult = 1;
					break;
				} else {
					compareResult = Character.compare(char1, char2);
					if (compareResult != 0) {
						break;
					}
				}
				charItr2.next();
			}
			
			if (compareResult == 0) {
				if (string1.length() < string2.length()) {
					/* Length of Input String 1 is shorter than Input String 2 */
				 	compareResult = -1;
				}	
			} else {
				/* Do nothing */
			}
			
		} else {
			/* Do nothing */
		}
			
		return compareResult;
	}
	
	public static List<String> formatCommonReference(String senderSwiftBic, String receiverSwiftBic, 
												BigDecimal contractRate) {
		
		List<String> commonReference = new ArrayList<String>();
		
		String senderPrefix = SwiftMessageLiterals.getPartyPrefixFromSwiftBic(senderSwiftBic);
		String senderSuffix = SwiftMessageLiterals.getPartySuffixFromSwiftBic(senderSwiftBic);
		String receiverPrefix = SwiftMessageLiterals.getPartyPrefixFromSwiftBic(receiverSwiftBic);
		String receiverSuffix = SwiftMessageLiterals.getPartySuffixFromSwiftBic(receiverSwiftBic);
		
		String senderCode = senderPrefix.concat(senderSuffix);
		String receiverCode = receiverPrefix.concat(receiverSuffix);
		
		String referenceCode = SwiftMessageLiterals.getRightmost4DigitsFromRateInString(contractRate);
		
		int compareResult = SwiftMessageLiterals.alphabeticalCompare(senderCode, receiverCode);
		
		if (compareResult < 0) {
			commonReference.add(senderPrefix);
			commonReference.add(senderSuffix);
			commonReference.add(referenceCode);
			commonReference.add(receiverPrefix);
			commonReference.add(receiverSuffix);
		} else {
			commonReference.add(receiverPrefix);
			commonReference.add(receiverSuffix);
			commonReference.add(referenceCode);
			commonReference.add(senderPrefix);
			commonReference.add(senderSuffix);
		}
		
		return commonReference;
		
	}
	
	public static boolean checkOffshoreDeliverableRmbFxTxn(Trade trade) {
		
		boolean offshoreDeliverableRmbFxTxn = false;
		
		if (trade instanceof TradeFxSpot || trade instanceof TradeFxForward) {
			
			String boughtCurrencyCode;
			String soldCurrencyCode;
			boolean tradeBookOffshoreRmbIndicator = false;
			CashFlowSi payCashFlowSi;
			CashFlowSi receiveCashFlowSi;
			
			
			if (trade instanceof TradeFxSpot) {
				boughtCurrencyCode =  ((TradeFxSpot) trade).getBoughtCurrency().getCurrencyCode();
				soldCurrencyCode = ((TradeFxSpot) trade).getSoldCurrency().getCurrencyCode();
				payCashFlowSi = ((TradeFxSpot) trade).getCashFlow(PaymentIndicator.PAY).getCashFlowSi();
				receiveCashFlowSi = ((TradeFxSpot) trade).getCashFlow(PaymentIndicator.RECEIVE).getCashFlowSi();
			} else {
				boughtCurrencyCode =  ((TradeFxForward) trade).getBoughtCurrency().getCurrencyCode();
				soldCurrencyCode = ((TradeFxForward) trade).getSoldCurrency().getCurrencyCode();
				payCashFlowSi = ((TradeFxForward) trade).getCashFlow(PaymentIndicator.PAY).getCashFlowSi();
				receiveCashFlowSi = ((TradeFxForward) trade).getCashFlow(PaymentIndicator.RECEIVE).getCashFlowSi();
			}
			
			/* Either currency bought or currency sold is CNH */
			if (StringUtils.equals(boughtCurrencyCode, SwiftMessageLiterals.OFFSHORE_RENMINBI_CODE) ||
					StringUtils.equals(soldCurrencyCode, SwiftMessageLiterals.OFFSHORE_RENMINBI_CODE)) {
				
				offshoreDeliverableRmbFxTxn = true;
				
			}
			
			/* Currency bought is CNY */
			if (StringUtils.equals(boughtCurrencyCode, SwiftMessageLiterals.RENMINBI_CODE)) {
				
				tradeBookOffshoreRmbIndicator = trade.getMasterBook().isOffshoreRmbIndicator();
				
				if (tradeBookOffshoreRmbIndicator) {
					
					offshoreDeliverableRmbFxTxn = true;
					
				} else {
					
					if (!StringUtils.equals(receiveCashFlowSi.getOurSettlementCountry(), CHINA_COUNTRY_CODE) &&
							StringUtils.isNotBlank(receiveCashFlowSi.getOurSettlementCountry())) {
						
						offshoreDeliverableRmbFxTxn = true;
						
					} else {
						
						//Do nothing
						
					}
				
				}
				
			}
			
			/* Currency sold is CNY */	
			if (StringUtils.equals(soldCurrencyCode, SwiftMessageLiterals.RENMINBI_CODE)) {
				
				tradeBookOffshoreRmbIndicator = trade.getMasterBook().isOffshoreRmbIndicator();
				
				if (tradeBookOffshoreRmbIndicator) {
					
					offshoreDeliverableRmbFxTxn = true;
					
				} else {
				
					if (!StringUtils.equals(payCashFlowSi.getOurSettlementCountry(), CHINA_COUNTRY_CODE) &&
							StringUtils.isNotBlank(payCashFlowSi.getOurSettlementCountry())) {
						
						offshoreDeliverableRmbFxTxn = true;
						
					} else {
						
						//Do nothing
						
					}
					
				}
				
			}
			
		} else {
			
			//Do nothing, if not FX Spot or Fx Forward
			
		}
		
		return offshoreDeliverableRmbFxTxn;
		
	}
	
}
