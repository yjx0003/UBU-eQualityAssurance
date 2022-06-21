package es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment;

import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Quiz;

public class AssignmentTitleRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getAssignments()
				.getValues()
				.stream()
				.noneMatch(a -> a.getName()
						.isEmpty()
						|| !a.getCourseModule()
								.isVisible());
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		return dataBase.getQuizzes()
				.getValues()
				.stream()
				.filter(a -> a.getName()
						.isEmpty()
						|| !a.getCourseModule()
								.isVisible())
				.map(Quiz::getName)
				.collect(Collectors.toList());
	}

}
