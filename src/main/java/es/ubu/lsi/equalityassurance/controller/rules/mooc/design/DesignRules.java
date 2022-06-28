package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.EnableCompletionRule;

public class DesignRules extends CompositeRule {
	public DesignRules() {
		leafRules = Arrays.asList(new EnableCompletionRule(), new ResourceRule(), new HasGroupsRule(),
				new HasTeamSubmission(), new ShowCompletionRule(), new SameGradeRule());
	}
}
