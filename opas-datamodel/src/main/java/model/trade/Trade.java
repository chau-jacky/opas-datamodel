package model.trade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.OpasOrganizationObject;
import model.book.Book;
import model.party.Counterparty;
import model.workflow.TradeStatus;

@Entity
@SequenceGenerator(name = "trade_seq", sequenceName = "trade_seq", allocationSize = 1, initialValue = 1000)
@Table(name = "TRADE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Trade extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479132378844987426L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_seq")
	@Column(name = "TRADE_ID", nullable = false)
	private Long tradeId;

	@Column(name = "SOURCE_SYSTEM_DEAL_REFERENCE", nullable = false, length = 32)
	private String sourceSystemReferenceId;

	@Column(name = "SOURCE_SYSTEM_NAME", nullable = false, length = 32)
	private String sourceSystemName;

	@Column(name = "TRANSACTION_REFERENCE", nullable = false, length = 16)
	private String transactionReference;

	@Transient
	private Trade originalTrade;
	
	private Trade associatedTrade;

	@Transient
	private TradeEventJournal tradeEventJournal;

	@Enumerated(EnumType.STRING)
	@Column(name = "SCOPE_OF_OPERATION")
	private ScopeOfOperation scopeOfOperation;

	@Column(name = "VALUE_DATE", nullable = false)
	private Date valueDate;

	@Column(name = "CAPTURE_DATE", nullable = false)
	private Date captureDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRADE_STATUS", nullable = false)
	private TradeStatus status;

	@ManyToOne
	@JoinColumn(name = "COUNTERPARTY_ID")
	private Counterparty counterparty;

	@Transient
	private Book book;

	@Column(name = "TRADE_CHANNEL", nullable = false, length = 32)
	private String channel;

	@Column(name = "BROKER_ID", nullable = false)
	private Long borkerId;

	@Column(name = "DEALER_ID", nullable = false)
	private Long dealerId;

	@Transient
	private List<TradeSsi> tradeSsis;

	private DoddFrankReportingInformation doddFrankReportingInfo;
	
	@Transient
	private List<TradeAttribute> tradeAttributes;

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	public String getSourceSystemReferenceId() {
		return sourceSystemReferenceId;
	}

	public void setSourceSystemReferenceId(String sourceSystemReferenceId) {
		this.sourceSystemReferenceId = sourceSystemReferenceId;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public Trade getOriginalTrade() {
		return originalTrade;
	}

	public void setOriginalTrade(Trade originalTrade) {
		this.originalTrade = originalTrade;
	}

	public Trade getAssociatedTrade() {
		return associatedTrade;
	}

	public void setAssociatedTrade(Trade associatedTrade) {
		this.associatedTrade = associatedTrade;
	}

	public TradeEventJournal getTradeEventJournal() {
		return tradeEventJournal;
	}

	public void setTradeEventJournal(TradeEventJournal tradeEventJournal) {
		this.tradeEventJournal = tradeEventJournal;
	}

	public ScopeOfOperation getScopeOfOperation() {
		return scopeOfOperation;
	}

	public void setScopeOfOperation(ScopeOfOperation scopeOfOperation) {
		this.scopeOfOperation = scopeOfOperation;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Date getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	public Counterparty getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getBorkerId() {
		return borkerId;
	}

	public void setBorkerId(Long borkerId) {
		this.borkerId = borkerId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public List<TradeAttribute> getTradeAttributes() {
		return tradeAttributes;
	}

	public void setTradeAttributes(List<TradeAttribute> tradeAttributes) {
		this.tradeAttributes = tradeAttributes;
	}

	public List<TradeSsi> getTradeSsis() {
		return tradeSsis;
	}
	
	public List<TradeSsi> getTradePaySsis() {

		List<TradeSsi> tradePaySsis = new ArrayList<TradeSsi>();
		
		for (Iterator<TradeSsi> iterator = tradeSsis.iterator(); iterator.hasNext();) {
			TradeSsi tradeSsi = iterator.next();
			
			if (PaymentIndicator.PAY.equals(tradeSsi.getPaymentIndicator())) {
				
				tradePaySsis.add(tradeSsi);	
				
			}
		}

		return tradePaySsis;
			
	}
	
	public List<TradeSsi> getTradeReceiveSsis() {
		List<TradeSsi> tradePaySsis = new ArrayList<TradeSsi>();
		
		for (Iterator<TradeSsi> iterator = tradeSsis.iterator(); iterator.hasNext();) {
			TradeSsi tradeSsi = iterator.next();
			if (PaymentIndicator.RECEIVE.equals(tradeSsi.getPaymentIndicator())) {
				tradePaySsis.add(tradeSsi);	
			}
		}

		return tradePaySsis;
	}

	public void setTradeSsis(List<TradeSsi> tradeSsis) {
		this.tradeSsis = tradeSsis;
	}

	public DoddFrankReportingInformation getDoddFrankReportingInfo() {
		return doddFrankReportingInfo;
	}

	public void setDoddFrankReportingInfo(DoddFrankReportingInformation doddFrankReportingInfo) {
		this.doddFrankReportingInfo = doddFrankReportingInfo;
	}
}
