package es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class ParticipantsGroupsRules extends CompositeRule{
	
	public  ParticipantsGroupsRules() {
		leafRules =  Arrays.asList(new StudentRule(), new TeacherRule(), new HasRolesRule(), new HasGroupsRule(), new StudentGroupRule());
	}
	

}
