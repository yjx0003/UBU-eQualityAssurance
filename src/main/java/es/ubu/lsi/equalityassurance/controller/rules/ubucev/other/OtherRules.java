package es.ubu.lsi.equalityassurance.controller.rules.ubucev.other;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class OtherRules extends CompositeRule {
	public OtherRules() {
		leafRules = Arrays.asList(new TeacherStartDiscussionRule(), new TeacherResponseRule(),
				new TeacherResponse48Rule(), new GradedAssignmentRule());
	}
}
