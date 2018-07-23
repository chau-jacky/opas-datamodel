package model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class Product extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1885574273379276526L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	private String productCode;
	
	private String productGroup;
	
	private String productDescription;
	
	private ProductCategory productCategory;
	
	private boolean ourBankAsOrderingCustomer;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public boolean isOurBankAsOrderingCustomer() {
		return ourBankAsOrderingCustomer;
	}

	public void setOurBankAsOrderingCustomer(boolean ourBankAsOrderingCustomer) {
		this.ourBankAsOrderingCustomer = ourBankAsOrderingCustomer;
	}
	
}
