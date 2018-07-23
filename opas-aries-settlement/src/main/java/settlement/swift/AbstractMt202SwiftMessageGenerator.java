package settlement.swift;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.SwiftBlock1;
import com.prowidesoftware.swift.model.SwiftBlock2;
import com.prowidesoftware.swift.model.SwiftBlock3;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.mt.mt2xx.MT202;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;

public abstract class AbstractMt202SwiftMessageGenerator implements SwiftMessageGenerator {
	
	public abstract SwiftBlock1 getSwiftBlock1(CashFlow cashFlow);
	
	public abstract SwiftBlock2 getSwiftBlock2(CashFlowSi cashFlowSi);
	
	public abstract SwiftBlock3 getSwiftBlock3(CashFlow cashFlow);

	public abstract Field20 getField20(CashFlow cashFlow);
	
	public abstract Field21 getField21(CashFlow cashFlow);
	
	public abstract Field13C getField13C(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field32A getField32A(CashFlow cashFlow);
	
	public abstract Field getField52a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField53a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField54a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField56a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField57a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField58a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field72 getField72(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	/**
	 * Format and return MT202 settlement message based on the given trade cash flow.
	 * 
	 * @param cashFlow trade cash flow that requires generation of MT202
	 * @param cashFlowSsi settlement instruction of the trade cash flow
	 * 
	 * @return Formatted MT202 message
	 */
	public MT202 generate(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		MT202 mt202 = new MT202();
		
		if(getSwiftBlock1(cashFlow).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt202.getSwiftMessage().setBlock1(getSwiftBlock1(cashFlow));
			
		}
		
		if(getSwiftBlock2(cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt202.getSwiftMessage().setBlock2(getSwiftBlock2(cashFlowSi));
			
		}
		
		if(getSwiftBlock3(cashFlow).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt202.getSwiftMessage().setBlock3(getSwiftBlock3(cashFlow));
			
		}

		/* Generate Mandatory Field 20 */
		mt202.append(getField20(cashFlow));
		
		/* Generate Mandatory Field 21 */
		mt202.append(getField21(cashFlow));
		
		/* Generate Optional Field 13C */ 
		if(getField13C(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing if Field 13C is empty
			
		} else {
			
			//Append the Field 13C to the MT202 message
			mt202.append(getField13C(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Mandatory Field 32A */		
		mt202.append(getField32A(cashFlow));
		
		/* Generate Optional Field 52a */
		if(getField52a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing if Field 52a is empty
			
		} else {
			
			//Append the formatted Field 52a to the MT202 message
			mt202.append(getField52a(cashFlow, cashFlowSi)); 
			
		}
		
		/* Generate Optional Field 53a */
		if(getField53a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing if Field 53a is empty
			
		} else {
			
			//Append the formatted Field 53a to the MT202 message
			mt202.append(getField53a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional Field 54a */
		if(getField54a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing if Field 54a is empty
			
		} else {
			
			//Append the formatted Field 54a to the MT202 message
			mt202.append(getField54a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional Field 56a */
		if(getField56a(cashFlow, cashFlowSi).isEmpty()) {
		
			//Do nothing if Field 56a is empty

		} else {
			
			//Append the formatted Field 56a to the MT202 message
			mt202.append(getField56a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional Field 57a */
		/* MT202 Network Validated Rules C1: If Field 56a is present, then Field 57a must also be present (Error code(s): C81) */
		if(getField56a(cashFlow, cashFlowSi).isEmpty()) {
			
			String ourSettlementSwiftBic = cashFlowSi.getOurSettlementSwiftBicCode();
			String beneficiaryBankSwiftBic = cashFlowSi.getSettlementBankSwiftBicCode();
			
			if(StringUtils.isNotBlank(ourSettlementSwiftBic) && StringUtils.equals(ourSettlementSwiftBic, beneficiaryBankSwiftBic)) {
				
				//Not need to format Field 57a if Our Settlement Bank Swift Bic = Beneficiary Bank Swift Bic
				
			} else {
				
				if(getField57a(cashFlow, cashFlowSi).isEmpty()) {
					
					//Do nothing if Field 57a is empty
					
				} else {
					
					//Append formatted Field 57a to the MT202 message
					mt202.append(getField57a(cashFlow, cashFlowSi));
					
				}
				
			}
			
		} else {
			
			//Append conditional Field 57a to the MT202 message 
			mt202.append(getField57a(cashFlow, cashFlowSi));
				
		}
		
		/* Generate Mandatory Field 58a */
		mt202.append(getField58a(cashFlow, cashFlowSi));

		
		/* Generate Optional Field 72 */
		if(getField72(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing if Field 72 is empty
			
		} else {
			
			//Append formatted Field 72 to the MT202 message
			mt202.append(getField72(cashFlow, cashFlowSi));
			
		}
		
		return mt202;
	}
	
}
