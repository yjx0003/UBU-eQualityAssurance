package es.ubu.lsi.equalityassurance.controller.rules.participant_group;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class StudentRule extends BasicRule{

	private static final String STUDENT_ROLE = "student";

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getRoles()
				.getValues()
				.stream()
				.anyMatch(r -> r.getRoleShortName().contains(STUDENT_ROLE));
	}

	
}
