package persistence.organization;

import java.sql.Timestamp;

import javax.persistence.OptimisticLockException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.RecordStatus;
import model.organization.Organization;
import persistence.OpasPersistenceException;
import persistence.TestConstants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrganization {

	private OrganizationDaoIface dao;
	private Long organizationId;

	@Before
	public void setUp() throws Exception {
		dao = new OrganizationDaoHibernateImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasPersistenceException {

		// Create and save a new organization
		Organization organization1 = new Organization();
		organization1.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		organization1.setLastUpdatedUserId(TestConstants.USER_ID);
		organization1.setRecordStatus(RecordStatus.ACTIVE);

		organization1.setOrganizationAcronym("HSXXHKH");
		organization1.setBicCode("HSXXHKHH");
		organization1.setBranchNumber(576);
		organization1.setCountryCode("HK");
		organization1.setGroupMemberAbbreviation("HSXX");
		organization1.setOrganizationName("HSXX Hong Kong");

		organizationId = dao.insert(organization1);

		// Select the organization created using the organizationId
		Organization organization2 = dao.select(organizationId);

		// Check all attributes
		Assert.assertEquals("LastUpdatedDatetime", organization1.getLastUpdatedDatetime(),
				organization2.getLastUpdatedDatetime());
		Assert.assertEquals("LastUpdatedUserId", organization1.getLastUpdatedUserId(),
				organization2.getLastUpdatedUserId());
		Assert.assertEquals("Version", organization1.getVersion(), organization2.getVersion());
		Assert.assertEquals("RecordStatus", organization1.getRecordStatus(), organization2.getRecordStatus());
		Assert.assertEquals("OrganizationId", organization1.getOrganizationId(), organization2.getOrganizationId());

		Assert.assertEquals("OrganizationAccronym", organization1.getOrganizationAcronym(),
				organization2.getOrganizationAcronym());
		Assert.assertEquals("BicCode", organization1.getBicCode(), organization2.getBicCode());
		Assert.assertEquals("BranchNumber", organization1.getBranchNumber(), organization2.getBranchNumber());
		Assert.assertEquals("CountryCode", organization1.getCountryCode(), organization2.getCountryCode());
		Assert.assertEquals("GroupMemberAbbreviation", organization1.getGroupMemberAbbreviation(),
				organization2.getGroupMemberAbbreviation());
		Assert.assertEquals("OrganizationName", organization1.getOrganizationName(),
				organization2.getOrganizationName());

		// Update a organization
		Organization organization3 = dao.select(organizationId);
		organization3.setOrganizationName(organization3.getOrganizationName() + System.currentTimeMillis());
		dao.update(organization3);

		Organization organization4 = dao.select(organizationId);

		// Check updated attribute(s)
		Assert.assertEquals("Name", organization3.getOrganizationName(), organization4.getOrganizationName());

		Organization organization5 = dao.select(organizationId);
		Organization organization6 = dao.select(organizationId);

		organization5.setOrganizationName(organization5.getOrganizationName() + System.currentTimeMillis());
		organization6.setOrganizationName(organization6.getOrganizationName() + (System.currentTimeMillis() + 1));

		try {

			dao.update(organization5);

			dao.update(organization6);
			// fail if update is successful

			Assert.fail();

		} catch (OptimisticLockException ole) {
			// do nothing
		}

		// delete organization
		Organization organization7 = dao.select(organizationId);
		dao.delete(organization7);

		Organization organization8 = dao.select(organizationId);
		Assert.assertEquals("RecordStatus", organization7.getRecordStatus(), organization8.getRecordStatus());
	}

}
