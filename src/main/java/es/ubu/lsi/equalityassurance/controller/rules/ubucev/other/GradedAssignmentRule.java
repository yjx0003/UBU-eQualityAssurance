package es.ubu.lsi.equalityassurance.controller.rules.ubucev.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class GradedAssignmentRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Collection<Assignment> assignments = dataBase.getAssignments()
				.getValues();
		for (Assignment assignment : assignments) {
			boolean value = dataBase.getSubmissions()
					.getValues()
					.stream()
					.filter(s -> assignment.equals(s.getAssignment()))
					.anyMatch(s -> "graded".equals(s.getGradingstatus()));
			if (!value) {
				return false;
			}
		}
		return true;

	}
	
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<Object> list = new ArrayList<>();
		Collection<Assignment> assignments = dataBase.getAssignments()
				.getValues();
		for (Assignment assignment : assignments) {
			boolean value = dataBase.getSubmissions()
					.getValues()
					.stream()
					.filter(s -> assignment.equals(s.getAssignment()))
					.anyMatch(s -> "graded".equals(s.getGradingstatus()));
			if (!value) {
				list.add(assignment.getName());
			}
		}
		return list;
	}

}
