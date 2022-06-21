package es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class AssignmentRules extends CompositeRule {

	public AssignmentRules() {
		leafRules = Arrays.asList(new AssignmentTitleRule(), new AssignmentTimeRule(), new AssignmentGroupRule());
	}
}
