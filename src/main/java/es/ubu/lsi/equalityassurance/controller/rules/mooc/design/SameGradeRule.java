package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import java.util.Collection;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class SameGradeRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		Collection<Assignment> assignments = dataBase.getAssignments().getValues();
		if(assignments.isEmpty()) {
			return true;
		}
		Assignment first = assignments.iterator().next();
		for(Assignment assignment: assignments) {
			if(Math.abs(first.getGrade() - assignment.getGrade()) > 0.01) {
				return false;
			}
		}
		return true;
	}

}
