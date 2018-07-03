package persistence.security;

import java.sql.Timestamp;

import javax.persistence.OptimisticLockException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.RecordStatus;
import model.security.User;
import persistence.OpasPersistenceException;
import persistence.TestConstants;

public class TestUser {

	private UserDaoIface dao;
	private Long userId;

	@Before
	public void setUp() throws Exception {
		dao = new UserDaoHibernateImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasPersistenceException {

		// Create and save a new user
		User user1 = new User();
		user1.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		user1.setLastUpdatedUserId(TestConstants.USER_ID);
		user1.setRecordStatus(RecordStatus.ACTIVE);
		user1.setUserEmailAddress("user1@test.com");
		user1.setUserName("User1");
		user1.setUserStaffId("10000001");
		this.userId = dao.insert(user1);

		// Select the user created using the userId
		User user2 = dao.select(this.userId);

		// Check all attributes
		Assert.assertEquals("LastUpdatedDatetime", user1.getLastUpdatedDatetime(), user2.getLastUpdatedDatetime());
		Assert.assertEquals("LastUpdatedUserId", user1.getUserId(), user2.getUserId());
		Assert.assertEquals("RecordStatus", user1.getRecordStatus(), user2.getRecordStatus());

		Assert.assertEquals("EmailAddress", user1.getUserEmailAddress(), user2.getUserEmailAddress());
		Assert.assertEquals("UserName", user1.getUserName(), user2.getUserName());
		Assert.assertEquals("UserStaffId", user1.getUserStaffId(), user2.getUserStaffId());
		Assert.assertEquals("Version", user1.getVersion(), user2.getVersion());
		Assert.assertEquals("UserId", user1.getUserId(), user2.getUserId());

		// Update a user
		User user3 = dao.select(userId);
		user3.setUserName(user3.getUserName() + System.currentTimeMillis());
		dao.update(user3);

		User user4 = dao.select(userId);

		// Check updated attribute(s)
		Assert.assertEquals("UserName", user3.getUserName(), user4.getUserName());

		User user5 = dao.select(userId);
		User user6 = dao.select(userId);

		user5.setUserName(user5.getUserName() + System.currentTimeMillis());
		user6.setUserName(user6.getUserName() + (System.currentTimeMillis() + 1));

		try {

			dao.update(user5);

			dao.update(user6);
			// fail if update is successful

			Assert.fail();

		} catch (OptimisticLockException ole) {
			// do nothing
		}

		// delete user
		User user7 = dao.select(userId);
		dao.delete(user7);

		User user8 = dao.select(userId);
		Assert.assertEquals("RecordStatus", user7.getRecordStatus(), user8.getRecordStatus());

	}
}
