package es.ubu.lsi.equalityassurance.controller.rules.ubucev;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.block.BlocksRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group.ParticipantsGroups;

public class RootUbucevRule extends CompositeRule{

	public RootUbucevRule() {
		leafRules = Arrays.asList(new ParticipantsGroups(), new BlocksRule());
	}

}
