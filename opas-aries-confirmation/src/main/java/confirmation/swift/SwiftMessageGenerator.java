package confirmation.swift;

import model.trade.Trade;

public interface SwiftMessageGenerator {

	public Object generate(Trade trade);

}
