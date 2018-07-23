package confirmation.swift;

import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field14C;
import com.prowidesoftware.swift.model.field.Field14S;
import com.prowidesoftware.swift.model.field.Field17F;
import com.prowidesoftware.swift.model.field.Field17I;
import com.prowidesoftware.swift.model.field.Field17O;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field21A;
import com.prowidesoftware.swift.model.field.Field22A;
import com.prowidesoftware.swift.model.field.Field22C;
import com.prowidesoftware.swift.model.field.Field22L;
import com.prowidesoftware.swift.model.field.Field22M;
import com.prowidesoftware.swift.model.field.Field22N;
import com.prowidesoftware.swift.model.field.Field22P;
import com.prowidesoftware.swift.model.field.Field22R;
import com.prowidesoftware.swift.model.field.Field29A;
import com.prowidesoftware.swift.model.field.Field30T;
import com.prowidesoftware.swift.model.field.Field30U;
import com.prowidesoftware.swift.model.field.Field30V;
import com.prowidesoftware.swift.model.field.Field32B;
import com.prowidesoftware.swift.model.field.Field32E;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field36;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.field.Field77D;
import com.prowidesoftware.swift.model.field.Field77H;
import com.prowidesoftware.swift.model.field.Field94A;
import com.prowidesoftware.swift.model.field.Field98D;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300.SequenceA;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300.SequenceB;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300.SequenceC;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300.SequenceE;

import model.trade.Trade;

/** 
 * This class setup the skeleton of the MT300 message generator.
 * 
 * @author simonchau
 *
 */
public abstract class AbstractMt300SwiftMessageGenerator implements SwiftMessageGenerator {

	/* *** Sequence A *** */
	
	/**
	 * Format and return Sequence A Field 20 based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 20 
	 */
	public abstract Field20 getSequenceAField20(Trade trade);
	
	/**
	 * Format and return Sequence A Field 21 based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 21
	 */
	public abstract Field21 getSequenceAField21(Trade trade);

	/**
	 * Format and return Sequence A Field 22A based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 22A
	 */
	public abstract Field22A getSequenceAField22A(Trade trade);

	public abstract Field94A getSequenceAField94A(Trade trade);

	public abstract Field22C getSequenceAField22C(Trade trade);

	public abstract Field17I getSequenceAField17I(Trade trade);

	public abstract Field getSequenceAField82a(Trade trade);
	
	// public abstract Field82A getSequenceAField82A(Trade trade);

	// public abstract Field82D getSequenceAField82D(Trade trade);
	
	public abstract Field getSequenceAField87a(Trade trade);

	// public abstract Field87J getSequenceAField87J(Trade trade);

	// public abstract Field87A getSequenceAField87A(Trade trade);

	// public abstract Field87D getSequenceAField87D(Trade trade);

	public abstract Field getSequenceAField83a(Trade trade);
	
	//public abstract Field83J getSequenceAField83J(Trade trade);

	public abstract Field77H getSequenceAField77H(Trade trade);

	public abstract Field77D getSequenceAField77D(Trade trade);
	
	public abstract Field14C getSequenceAField14C(Trade trade);

	public abstract Field17F getSequenceAField17F(Trade trade);

	public abstract Field17O getSequenceAField17O(Trade trade);

	public abstract Field32E getSequenceAField32E(Trade trade);

	public abstract Field30U getSequenceAField30U(Trade trade);

	public abstract Field14S getSequenceAField14S(Trade trade);

	public abstract Field21A getSequenceAField21A(Trade trade);

	/* *** Sequence B *** */
	
	public abstract Field30T getSequenceBField30T(Trade trade);

	public abstract Field30V getSequenceBField30V(Trade trade);

	public abstract Field36 getSequenceBField36(Trade trade);
	
	/* *** Sequence B - Subsequence B1 *** */

	public abstract Field32B getSequenceB1Field32B(Trade trade);

	public abstract Field getSequenceB1Field53a(Trade trade);
	
	//public abstract Field53A getSequenceBField53A(Trade trade);

	public abstract Field getSequenceB1Field57a(Trade trade);
	
	// public abstract Field57A getSequenceBField57A(Trade trade);

	// public abstract Field57D getSequenceBField57D(Trade trade);

	/* Sequence B - Subsequence B2 */
	
	public abstract Field33B getSequenceB2Field33B(Trade trade);

	public abstract Field getSequenceB2Field53a(Trade trade);
	
	// public abstract Field53D getSequenceBField53D(Trade trade);

	public abstract Field getSequenceB2Field56a(Trade trade);
	
	// public abstract Field56A getSequenceBField56A(Trade trade);

	// public abstract Field56D getSequenceBField56D(Trade trade);
	
	public abstract Field getSequenceB2Field57a(Trade trade);

	/* *** Sequence C *** */
	
	public abstract Field29A getSequenceCField29A(Trade trade);

	public abstract Field72 getSequenceCField72(Trade trade);

	/* *** Sequence E *** */
	
	public abstract Field22L getSequenceEField22L(Trade trade);

	public abstract Field22M getSequenceEField22M(Trade trade);

	public abstract Field22N getSequenceEField22N(Trade trade);

	public abstract Field22P getSequenceEField22P(Trade trade);

	public abstract Field22R getSequenceEField22R(Trade trade);

	public abstract Field98D getSequenceEField98D(Trade trade);

	/**
	 * Format and return MT300 confirmation message based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted MT300 message
	 */
	public MT300 generate(Trade trade) {

		MT300 mt300 = new MT300();

		mt300.append(generateSequenceA(trade));
		mt300.append(generateSequenceB(trade));
		mt300.append(generateSequenceC(trade));
		mt300.append(generateSequenceE(trade));

		return mt300;
	}

	/**
	 * Format and return Sequence A based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Sequence A
	 */
	public SequenceA generateSequenceA(Trade trade) {

		SequenceA sequenceA = MT300.SequenceA.newInstance();

		/* Field 15A (New Sequence) */
		/* Status: Mandatory, automatically added */

		/* Field 20 (Sender's Reference) */
		/* Status: Mandatory */
		Field20 sequenceAField20 = getSequenceAField20(trade);
		if (!sequenceAField20.isEmpty()) {
			sequenceA.append(sequenceAField20);
		}
		
		/* Field 21 (Related Reference) */
		/* Status: Conditional, the presence depends on field 22A */
		Field21 sequenceAField21 = getSequenceAField21(trade);
		if (!sequenceAField21.isEmpty()) {
			sequenceA.append(sequenceAField21);
		}

		/* Field 22A (Type of Operation) */
		//* Status: Mandatory */
		Field22A sequenceAField22A = getSequenceAField22A(trade);
		if (!sequenceAField22A.isEmpty()) {
			sequenceA.append(sequenceAField22A);
		}

		/* Field 94A (Scope of Operation) */
		/* Status: Optional */
		Field94A sequenceAField94A = getSequenceAField94A(trade);
		if (!sequenceAField94A.isEmpty()) {
			sequenceA.append(sequenceAField94A);
		}
		
		/* Field 22C (Common Reference) */
		/* Status: Mandatory */
		Field22C sequenceAField22C = getSequenceAField22C(trade);
		if (!sequenceAField22C.isEmpty()) {
			sequenceA.append(sequenceAField22C);
		}

		/* Field 17I (Payment versus Payment Settlement Indicator) */
		/* Status: Mandatory */
		Field17I sequenceAField17I = getSequenceAField17I(trade);
		if (!sequenceAField17I.isEmpty()) { 
			sequenceA.append(sequenceAField17I);
		}

		/* Field 82a (Party A) */
		/* Format: A, D, J; Status: Mandatory */
		Field sequenceAField82a = getSequenceAField82a(trade);
		if (!sequenceAField82a.isEmpty()) {
			sequenceA.append(sequenceAField82a);
		}

		/* Field 87a (Party B) */
		/* Format: A, D, J; Status: Mandatory */
		Field sequenceAField87a = getSequenceAField87a(trade);
		if (!sequenceAField87a.isEmpty()) { 
			sequenceA.append(sequenceAField87a);
		}

		/* Field 83a (Fund or Beneficiary Customer) */
		/* Format: A, D, J; Status: Optional */
		Field sequenceAField83a = getSequenceAField83a(trade);
		if (!sequenceAField83a.isEmpty()) {
			sequenceA.append(sequenceAField83a);
		}
		
		/* Field 77H (Type, Date, Version of the Agreement) */
		/* Status: Optional */
		Field77H sequenceAField77H = getSequenceAField77H(trade);
		if (!sequenceAField77H.isEmpty()) {
			sequenceA.append(sequenceAField77H);
		}
		
		/* Field 77D (Terms and Conditions) */
		/* Status: Optional */
		Field77D sequenceAField77D = getSequenceAField77D(trade);
		if (!sequenceAField77D.isEmpty()) {
			sequenceA.append(sequenceAField77D);
		}

		
		/* Field 14C (Year of Definition) */
		/* Status: Optional (Currently not generated in TREATS) */
		Field14C sequenceAField14C = getSequenceAField14C(trade);
		if (!sequenceAField14C.isEmpty()) {
			sequenceA.append(sequenceAField14C);
		}

		/* Field 17F (Non-Deliverable Indicator) */
		/* Status: Optional */
		Field17F sequenceAField17F = getSequenceAField17F(trade);
		if (!sequenceAField17F.isEmpty()) {
			sequenceA.append(sequenceAField17F);
		}

		/* Field 17O (NDF Open Indicator) */
		/* Status: Conditional */
		Field17O sequenceAField17O = getSequenceAField17O(trade);
		if (!sequenceAField17O.isEmpty()) {
			sequenceA.append(sequenceAField17O);
		}

		/* Field 32E (Settlement Currency) */
		/* Status: Conditional */
		Field32E sequenceAField32E = getSequenceAField32E(trade);
		if (!sequenceAField32E.isEmpty()) {
			sequenceA.append(sequenceAField32E);
		}
		
		/* Field 30U (Valuation Date) */
		/* Status: Conditional */
		Field30U sequenceAField30U = getSequenceAField30U(trade);
		if (!sequenceAField30U.isEmpty()) {
			sequenceA.append(sequenceAField30U);
		}

		/* Field 14S (Settlement Rate Source) */
		/* Status: Conditional */
		Field14S sequenceAField14S = getSequenceAField14S(trade);
		if (!sequenceAField14S.isEmpty()) {
			sequenceA.append(sequenceAField14S);
		}

		/* Field 21A (Reference to Opening Confirmation) */
		/* Status: Conditional */
		Field21A sequenceAField21A = getSequenceAField21A(trade);
		if (!sequenceAField21A.isEmpty()) {
			sequenceA.append(sequenceAField21A);
		}
		
		return sequenceA;
	}

	/**
	 * Format and return Sequence B based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Sequence B
	 */
	public SequenceB generateSequenceB(Trade trade) {

		SequenceB sequenceB = MT300.SequenceB.newInstance();
		
		/* Field 15B (New Sequence) */
		/* Status: Mandatory, automatically added */
		
		/* Field 30T (Trade Date) */
		/* Status: Mandatory */
		Field30T sequenceBField30T = getSequenceBField30T(trade);
		if (!sequenceBField30T.isEmpty()) {
			sequenceB.append(sequenceBField30T);
		}

		/* Field 30V (Value Date) */
		/* Status: Mandatory */
		Field30V sequenceBField30V = getSequenceBField30V(trade);
		if (!sequenceBField30V.isEmpty()) {
			sequenceB.append(sequenceBField30V);
		}

		/* Field 36 (Exchange Rate) */
		/* Status: Mandatory */
		Field36 sequenceBField36 = getSequenceBField36(trade);
		if (!sequenceBField36.isEmpty()) {
			sequenceB.append(sequenceBField36);
		}
		
		/* *** Subsequence B1 *** */
		
		/* Field 32B (Currency, Amount) */
		/* Status: Mandatory */
		Field32B sequenceB1Field32B = getSequenceB1Field32B(trade);
		if (!sequenceB1Field32B.isEmpty()) {
			sequenceB.append(sequenceB1Field32B);
		}

		/* Field 53a (Delivery Agent) */
		/* Option: A, D, J; Status: Optional */
		Field sequenceB1Field53a = getSequenceB1Field53a(trade);
		if (!sequenceB1Field53a.isEmpty()) {
			sequenceB.append(sequenceB1Field53a);
		}

		/* Field 57a (Receiving Agent) */
		/* Option: A, D, J; Status: Mandatory */
		Field sequenceB1Field57a = getSequenceB1Field57a(trade);
		if (!sequenceB1Field57a.isEmpty()) {
			sequenceB.append(sequenceB1Field57a);
		}

		/* *** Subsequence B2 *** */

		/* Field 33B (Currency, Amount) */
		/* Status: Mandatory */
		Field33B seqeunceB2Field33B = getSequenceB2Field33B(trade);
		if (!seqeunceB2Field33B.isEmpty()) {
			sequenceB.append(seqeunceB2Field33B);
		}

		/* Field 53a (Delivery Agent) */
		/* Status: Optional */
		Field sequenceB2Field53a = getSequenceB2Field53a(trade);
		if (!sequenceB2Field53a.isEmpty()) {
			sequenceB.append(sequenceB2Field53a);
		}

		/* Field 56a (Intermediary) */
		/* Option: A, D, J; Status: Optional */
		Field sequenceB2Field56a = getSequenceB2Field56a(trade);
		if (!sequenceB2Field56a.isEmpty()) {
			sequenceB.append(sequenceB2Field56a);
		}

		/* Field 57a (Receiving Agent) */
		/* Option: A, D, J; Status: Mandatory */
		Field sequenceB2Field57a = getSequenceB2Field57a(trade);
		if (!sequenceB2Field57a.isEmpty()) {
			sequenceB.append(sequenceB2Field57a);
		}

		return sequenceB;
	}
	
	/**
	 * Format and return Sequence C based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Sequence C
	 */
	public SequenceC generateSequenceC(Trade trade) {

		SequenceC sequenceC = MT300.SequenceC.newInstance();
		
		/* Field 15C (New Sequence) */
		/* Status: Mandatory, automatically added */

		/* Field 29A (Contact Information) */
		/* Status: Optional */
		Field29A sequenceCField29A = getSequenceCField29A(trade);
		if (!sequenceCField29A.isEmpty()) {
			sequenceC.append(sequenceCField29A);
		}

		/* Field 72 (Sender to Receiver Information) */
		/* Status: Optional */
		Field72 sequenceCField72 = getSequenceCField72(trade);
		if (!sequenceCField72.isEmpty()) {
			sequenceC.append(sequenceCField72);
		}

		return sequenceC;
	}
	
	/**
	 * Format and return Sequence E based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Sequence E
	 */
	public SequenceE generateSequenceE(Trade trade) {

		SequenceE sequenceE = MT300.SequenceE.newInstance();

		/* Field 15E (New Sequence) */
		/* Status: Mandatory, automatically added */

		/* Field 22L (Reporting Jurisdiction) */
		/* Status: Mandatory */
		Field22L sequenceEField22L = getSequenceEField22L(trade);
		if (!sequenceEField22L.isEmpty()) {
			sequenceE.append(sequenceEField22L);
		}

		/* Field 22M (UTI Namespace/Issuer Code) */
		/* Status: Mandatory */
		Field22M sequenceEField22M = getSequenceEField22M(trade);
		if (!sequenceEField22M.isEmpty()) {
			sequenceE.append(sequenceEField22M);
		}

		/* Field 22N (Transaction Identifier) */
		/* Status: Mandatory */
		Field22N sequenceEField22N = getSequenceEField22N(trade);
		if (!sequenceEField22N.isEmpty()) {
			sequenceE.append(sequenceEField22N);
		}
		
		/* Field 22P (PUTI Namespace/Issuer Code) */
		/* Status: Mandatory */
		Field22P sequenceEField22P = getSequenceEField22P(trade);
		if (!sequenceEField22P.isEmpty()) {
			sequenceE.append(sequenceEField22P);
		}

		/* Field 22R (Prior Transaction Identifier) */
		/* Status: Mandatory */
		Field22R sequenceEField22R = getSequenceEField22R(trade);
		if (!sequenceEField22R.isEmpty()) {
			sequenceE.append(sequenceEField22R);
		}

		/* Field 98D (Execution Timestamp) */
		/* Status: Mandatory */
		Field98D sequenceEField98D = getSequenceEField98D(trade);
		if (!sequenceEField98D.isEmpty()) {
			sequenceE.append(sequenceEField98D);
		}

		return sequenceE;
	}

}
