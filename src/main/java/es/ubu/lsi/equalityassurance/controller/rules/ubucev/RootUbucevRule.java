package es.ubu.lsi.equalityassurance.controller.rules.ubucev;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment.AssignmentRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.block.BlocksRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.other.OtherRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group.ParticipantsGroupsRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.quiz.QuizRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes.ThemesRules;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroThemeRules;

public class RootUbucevRule extends CompositeRule {

	public RootUbucevRule() {
		leafRules = Arrays.asList(new ParticipantsGroupsRules(), new BlocksRules(), new ZeroThemeRules(),
				new ThemesRules(), new QuizRules(), new AssignmentRules(), new OtherRules());
	}

}
