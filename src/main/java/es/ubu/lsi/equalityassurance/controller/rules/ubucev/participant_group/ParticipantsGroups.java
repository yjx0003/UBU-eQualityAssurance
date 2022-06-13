package es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class ParticipantsGroups extends CompositeRule{
	
	public  ParticipantsGroups() {
		leafRules =  Arrays.asList(new StudentRule(), new TeacherRule(), new HasRolesRule(), new HasGroupsRule(), new StudentGroupRule());
	}
	

}
