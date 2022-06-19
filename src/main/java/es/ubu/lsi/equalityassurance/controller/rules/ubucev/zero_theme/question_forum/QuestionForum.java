package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class QuestionForum extends CompositeRule{

	public QuestionForum() {
		leafRules = Arrays.asList(new GeneralForumRule(), new GeneralForumVisibleRule(), new GeneralForumDescriptionRule());
	}
}
