package model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.OpasObject;

@Entity
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1000)
@Table(name = "SECURITY_USER")
public class User extends OpasObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -262089161589506708L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "USER_NAME", nullable = false, length = 64)
	private String userName;

	@Column(name = "USER_STAFF_ID", nullable = false, length = 8)
	private String userStaffId;

	@Column(name = "USER_EMAIL_ADDRESS", nullable = false, length = 64)
	private String userEmailAddress;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStaffId() {
		return userStaffId;
	}

	public void setUserStaffId(String userStaffId) {
		this.userStaffId = userStaffId;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

}
