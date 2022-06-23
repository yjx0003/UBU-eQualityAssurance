package es.ubu.lsi.equalityassurance.controller.rules.mooc.realisation;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.other.GradedAssignmentRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.other.TeacherResponse48Rule;

public class RealisationRules extends CompositeRule{

	
	public RealisationRules() {
		leafRules = Arrays.asList(new TeacherResponse48Rule(), new GradedAssignmentRule());
	}
}
