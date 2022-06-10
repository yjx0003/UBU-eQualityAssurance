package es.ubu.lsi.equalityassurance.controller.rules.block;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class UpcomingEventsRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getBlocks()
				.getValues()
				.stream()
				.anyMatch(b -> "calendar_month".equals(b.getName()));
	}

}
