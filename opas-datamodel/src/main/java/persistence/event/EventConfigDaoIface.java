package persistence.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import model.event.EventConfig;

public interface EventConfigDaoIface {

	public List<EventConfig> selectByEventClass(EntityManager entityManager, Long organizationId, String eventClass)
			throws PersistenceException;

}
