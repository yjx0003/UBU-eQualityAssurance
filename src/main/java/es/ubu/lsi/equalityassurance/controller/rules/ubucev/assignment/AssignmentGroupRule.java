package es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment;

import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class AssignmentGroupRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getAssignments()
				.getValues()
				.stream()
				.allMatch(Assignment::isTeamsubmission);
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		return dataBase.getAssignments()
				.getValues()
				.stream()
				.filter(a -> !a.isTeamsubmission())
				.map(Assignment::getName)
				.collect(Collectors.toList());
	}

}
