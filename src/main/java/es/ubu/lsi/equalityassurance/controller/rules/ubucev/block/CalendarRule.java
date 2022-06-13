package es.ubu.lsi.equalityassurance.controller.rules.ubucev.block;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class CalendarRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getBlocks()
				.getValues()
				.stream()
				.anyMatch(b -> "calendar_upcoming".equals(b.getName()));
	}

}
