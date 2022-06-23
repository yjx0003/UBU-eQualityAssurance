package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class HasTeamSubmission extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getAssignments()
				.getValues()
				.stream()
				.anyMatch(Assignment::isTeamsubmission);
	}

}
