package es.ubu.lsi.equalityassurance.controller.rules.mooc.implementation;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment.AssignmentTimeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group.StudentGroupRule;

public class ImplementationRules extends CompositeRule{
	public ImplementationRules() {
		leafRules = Arrays.asList(new ResourcesUpdated(),new AssignmentTimeRule(), new GradeItemDepthRule(), new StudentGroupRule());
	}
}
