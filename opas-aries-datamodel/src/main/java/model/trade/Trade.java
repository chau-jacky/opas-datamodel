package model.trade;

import java.io.Serializable;
import java.util.Date;
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
import model.cashflow.CashFlow;
import model.party.Counterparty;
import model.product.Product;
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

	/* internal use, uniquely identify the trade record */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_seq")
	@Column(name = "TRADE_ID", nullable = false)
	private Long tradeId;

	/* record the last successful trade image */
	private Trade originalTrade;
	
	/* record the associated trade, e.g. for use in structured trade */
	private Trade associatedTrade;
	//private Trade associatedTrade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRADE_STATUS", nullable = false)
	private TradeStatus status;
	
	/* the dealer who made the trade */
	@Column(name = "DEALER_ID", nullable = false)
	private Long dealerId;
	
	/* the broker channel */
	@Column(name = "BROKER_ID", nullable = false)
	private Long brokerId;
	
	/* the date & time when the trade input into the PTS */
	@Column(name = "CAPTURE_DATE_TIME", nullable = false)
	private Date captureDateTime;
	
	private Product product;
	
	/* trade counterparty of the trade */
	@ManyToOne
	@JoinColumn(name = "COUNTERPARTY_ID")
	private Counterparty counterparty;
	
	/* list of trading books */
	private Book masterBook;
	private Book primaryBook;
	private Book secondaryBook;
	//private Book book;
	
	/* */
	private TradeEventJournal tradeEventJournal;
	
	@Column(name = "SOURCE_SYSTEM_NAME", nullable = false, length = 32)
	private String sourceSystemName;
	
	@Column(name = "SOURCE_SYSTEM_DEAL_REFERENCE", nullable = false, length = 32)
	private String sourceSystemReferenceId;

	/* unique trade transaction reference for swift confirmation or other purpose */
	@Column(name = "TRANSACTION_REFERENCE", nullable = false, length = 16)
	private String transactionReference;

	/* dodd frank reporting details of the trade */
	private DoddFrankReportingInfo doddFrankReportingInfo;

	/* list of cash flows associated with the trade */
	private List<CashFlow> cashFlows;
	
	@Transient
	private List<TradeAttribute> tradeAttributes;
	/*
	@Enumerated(EnumType.STRING)
	@Column(name = "SCOPE_OF_OPERATION")
	private ScopeOfOperation scopeOfOperation;
	*/

	/*
	@Column(name = "VALUE_DATE", nullable = false)
	private Date valueDate;
	*/
	
	/*
	@Column(name = "TRADE_CHANNEL", nullable = false, length = 32)
	private String channel;
	*/

	/*
	@Transient
	private List<TradeSsi> tradeSsis;
	*/
	
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
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
	
	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}
	
	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
	
	public Long getBrokerId() {
		return brokerId;
	}
	
	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Date getCaptureDateTime() {
		return captureDateTime;
	}
	
	public void setCaptureDateTime(Date captureDateTime) {
		this.captureDateTime = captureDateTime;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Counterparty getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	
	public Book getMasterBook() {
		return masterBook;
	}

	public void setMasterBook(Book masterBook) {
		this.masterBook = masterBook;
	}

	public Book getPrimaryBook() {
		return primaryBook;
	}

	public void setPrimaryBook(Book primaryBook) {
		this.primaryBook = primaryBook;
	}

	public Book getSecondaryBook() {
		return secondaryBook;
	}

	public void setSecondaryBook(Book secondaryBook) {
		this.secondaryBook = secondaryBook;
	}

	public TradeEventJournal getTradeEventJournal() {
		return tradeEventJournal;
	}

	public void setTradeEventJournal(TradeEventJournal tradeEventJournal) {
		this.tradeEventJournal = tradeEventJournal;
	}
	
	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}
	
	public String getSourceSystemReferenceId() {
		return sourceSystemReferenceId;
	}

	public void setSourceSystemReferenceId(String sourceSystemReferenceId) {
		this.sourceSystemReferenceId = sourceSystemReferenceId;
	}
	
	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public DoddFrankReportingInfo getDoddFrankReportingInfo() {
		return doddFrankReportingInfo;
	}

	public void setDoddFrankReportingInfo(DoddFrankReportingInfo doddFrankReportingInfo) {
		this.doddFrankReportingInfo = doddFrankReportingInfo;
	}
	
	public List<CashFlow> getCashFlows() {
		return cashFlows;
	}
	
	public void setCashFlows(List<CashFlow> cashFlows) {
		this.cashFlows = cashFlows;
	}

	public List<TradeAttribute> getTradeAttributes() {
		return tradeAttributes;
	}

	public void setTradeAttributes(List<TradeAttribute> tradeAttributes) {
		this.tradeAttributes = tradeAttributes;
	}

	/*
	public ScopeOfOperation getScopeOfOperation() {
		return scopeOfOperation;
	}
	
	public void setScopeOfOperation(ScopeOfOperation scopeOfOperation) {
		this.scopeOfOperation = scopeOfOperation;
	}
	*/

	/*
	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	*/
	
	/*
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	*/
	
	/*
	public List<TradeSsi> getTradeSsis() {
		return tradeSsis;
	}
	*/
	
	/*
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
	*/
	
	/*
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
	*/

	/*
	public void setTradeSsis(List<TradeSsi> tradeSsis) {
		this.tradeSsis = tradeSsis;
	}
	*/

}
