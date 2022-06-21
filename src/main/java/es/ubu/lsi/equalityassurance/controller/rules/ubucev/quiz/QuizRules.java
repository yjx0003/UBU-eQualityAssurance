package es.ubu.lsi.equalityassurance.controller.rules.ubucev.quiz;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class QuizRules extends CompositeRule {

	public QuizRules() {
		leafRules = Arrays.asList(new QuizTitleRule(), new QuizDescriptionRule(), new QuizTimeRule(), new QuizFeedbackRule());
	}
}
