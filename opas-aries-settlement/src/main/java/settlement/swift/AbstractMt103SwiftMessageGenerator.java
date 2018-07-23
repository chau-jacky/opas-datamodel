package settlement.swift;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.SwiftBlock1;
import com.prowidesoftware.swift.model.SwiftBlock2;
import com.prowidesoftware.swift.model.SwiftBlock3;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field23B;
import com.prowidesoftware.swift.model.field.Field23E;
import com.prowidesoftware.swift.model.field.Field26T;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field36;
import com.prowidesoftware.swift.model.field.Field51A;
import com.prowidesoftware.swift.model.field.Field70;
import com.prowidesoftware.swift.model.field.Field71A;
import com.prowidesoftware.swift.model.field.Field71F;
import com.prowidesoftware.swift.model.field.Field71G;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.field.Field77B;
import com.prowidesoftware.swift.model.field.Field77T;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;

public abstract class AbstractMt103SwiftMessageGenerator implements SwiftMessageGenerator {

	public abstract SwiftBlock1 getSwiftBlock1(CashFlow cashFlow);
	
	public abstract SwiftBlock2 getSwiftBlock2(CashFlowSi cashFlowSi);
	
	public abstract SwiftBlock3 getSwiftBlock3(CashFlow cashFlow);
	
	public abstract Field20 getField20(CashFlow cashFlow);
	
	public abstract Field13C getField13C(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field23B getField23B(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field23E getField23E(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field26T getField26T(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field32A getField32A(CashFlow cashFlow);
	
	public abstract Field33B getField33B(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field36 getField36(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField50a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field51A getField51A(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField52a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField53a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField54a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField55a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField56a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField57a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field getField59a(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field70 getField70(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field71A getField71A(CashFlowSi cashFlowSi);
	
	public abstract Field71F getField71F(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field71G getField71G(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field72 getField72(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field77B getField77B(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	public abstract Field77T getField77T(CashFlow cashFlow, CashFlowSi cashFlowSi);
	
	/**
	 * Format and return MT103 settlement message based on the given trade cash flow.
	 * 
	 * @param cashFlow trade cash flow that requires generation of MT103
	 * @param cashFlowSsi settlement instruction of the trade cash flow
	 * 
	 * @return Formatted MT103 message
	 */
	public MT103 generate(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		MT103 mt103 = new MT103();
		
		if(getSwiftBlock1(cashFlow).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.getSwiftMessage().setBlock1(getSwiftBlock1(cashFlow));
			
		}
		
		if(getSwiftBlock2(cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.getSwiftMessage().setBlock2(getSwiftBlock2(cashFlowSi));
			
		}
		
		if(getSwiftBlock3(cashFlow).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.getSwiftMessage().setBlock3(getSwiftBlock3(cashFlow));
			
		}
		
		/* Generate Mandatory Field 20 */
		mt103.append(getField20(cashFlow));
		
		/* Generate Optional field 13C */
		if(getField13C(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField13C(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Mandatory Field 23B */
		mt103.append(getField23B(cashFlow, cashFlowSi));
		
		/* Generate Optional field 23E */
		if(getField23E(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField23E(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 26T */
		if(getField26T(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField26T(cashFlow, cashFlowSi));
			
		}

		/* Generate Mandatory Field 32A */
		mt103.append(getField32A(cashFlow));
		
		/* Generate Optional field 33B */
		if(getField33B(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField33B(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 36 */
		if(getField36(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField36(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 50a */
		if(getField50a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField50a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 51A */
		if(getField51A(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField51A(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 52a */
		if(getField52a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField52a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 53a */
		if(getField53a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField53a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 54a */
		if(getField54a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField54a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 55a */
		if(getField55a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField55a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 56a */
		if(getField56a(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField55a(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional Field 57a */
		/* MT103 Network Validated Rules C1: If Field 56a is present, then Field 57a must also be present (Error code(s): C81) */
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
					mt103.append(getField57a(cashFlow, cashFlowSi));
					
				}
				
			}
			
		} else {
			
			//Append conditional Field 57a to the MT202 message 
			mt103.append(getField57a(cashFlow, cashFlowSi));
				
		}
		
		/* Generate Mandatory Field 59a */
		mt103.append(getField59a(cashFlow, cashFlowSi));
		
		/* Generate Optional field 70 */
		if(getField70(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField70(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Mandatory Field 71AA */
		mt103.append(getField71A(cashFlowSi));
		
		/* Generate Optional field 71F */
		if(getField71F(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField71F(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 71G */
		if(getField71G(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField71G(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 72 */
		if(getField72(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField72(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 77B */
		if(getField77B(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField77B(cashFlow, cashFlowSi));
			
		}
		
		/* Generate Optional field 77T */
		if(getField77T(cashFlow, cashFlowSi).isEmpty()) {
			
			//Do nothing
			
		} else {
			
			mt103.append(getField77T(cashFlow, cashFlowSi));
			
		}
		
		return mt103;
		
	}
	
}
