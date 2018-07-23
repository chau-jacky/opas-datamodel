package persistence.security;

import model.security.User;
import persistence.OpasPersistenceException;

public interface UserDaoIface {

	public User select(Long UserId) throws OpasPersistenceException;

	public Long insert(User User) throws OpasPersistenceException;

	public void update(User User) throws OpasPersistenceException;

	public void delete(User User) throws OpasPersistenceException;
}
