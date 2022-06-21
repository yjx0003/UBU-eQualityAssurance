package es.ubu.lsi.equalityassurance.controller.rules.ubucev.quiz;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Quiz;

public class QuizTimeRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Collection<Quiz> quizzes = dataBase.getQuizzes().getValues();
		Instant start = dataBase.getActualCourse().getStartDate();
		Instant end = dataBase.getActualCourse().getEndDate();
		for(Quiz quiz: quizzes) {
			Instant quizOpen = quiz.getTimeopen();
			Instant quizClose = quiz.getTimeclose();
			if(quizOpen.isBefore(start) || quizOpen.isAfter(end) || quizClose.isBefore(start)|| quizClose.isAfter(end) || quiz.getTimelimit() == 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<Object> failQuizzes = new ArrayList<>();
		Collection<Quiz> quizzes = dataBase.getQuizzes().getValues();
		Instant start = dataBase.getActualCourse().getStartDate();
		Instant end = dataBase.getActualCourse().getEndDate();
		for(Quiz quiz: quizzes) {
			Instant quizOpen = quiz.getTimeopen();
			Instant quizClose = quiz.getTimeclose();
			if(quizOpen.isBefore(start) || quizOpen.isAfter(end) || quizClose.isBefore(start)|| quizClose.isAfter(end) ||  quiz.getTimelimit() == 0) {
				failQuizzes.add(quiz.getName());
			}
		}
		return failQuizzes;
	}

}
