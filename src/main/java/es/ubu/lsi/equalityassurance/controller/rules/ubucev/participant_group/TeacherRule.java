package es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class TeacherRule extends BasicRule{

	private static final String TEACHER_ROLE = "teacher";

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getRoles()
				.getValues()
				.stream()
				.anyMatch(r -> r.getRoleShortName().contains(TEACHER_ROLE));
	}
	
	
}
