package persistence.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.event.EventConfig;

public class EventConfigDaoHibernateImpl implements EventConfigDaoIface {

	@Override
	public List<EventConfig> selectByEventClass(EntityManager entityManager, Long organizationId, String eventClass) {

		List<EventConfig> eventConfigs = new ArrayList<EventConfig>();

		return eventConfigs;
	}

}
