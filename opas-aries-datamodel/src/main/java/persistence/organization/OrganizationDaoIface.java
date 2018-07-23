package persistence.organization;

import model.organization.Organization;
import persistence.OpasPersistenceException;

public interface OrganizationDaoIface {

	public Organization select(Long organizationId) throws OpasPersistenceException;

	public Long insert(Organization organization) throws OpasPersistenceException;

	public void update(Organization organization) throws OpasPersistenceException;

	public void delete(Organization organization) throws OpasPersistenceException;

}
