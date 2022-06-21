package es.ubu.lsi.equalityassurance.controller.rules.ubucev.quiz;

import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Quiz;

public class QuizTitleRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getQuizzes()
				.getValues()
				.stream()
				.noneMatch(q -> q.getName()
						.isEmpty() || !q.isVisible());
	}
	
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		return dataBase.getQuizzes()
				.getValues()
				.stream()
				.filter(q -> q.getName()
						.isEmpty() || !q.isVisible())
				.map(Quiz::getName)
				.collect(Collectors.toList());
	}

}
