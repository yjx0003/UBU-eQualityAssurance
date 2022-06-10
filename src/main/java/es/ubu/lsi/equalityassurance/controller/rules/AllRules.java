package es.ubu.lsi.equalityassurance.controller.rules;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.block.BlocksRule;
import es.ubu.lsi.equalityassurance.controller.rules.participant_group.ParticipantsGroups;

public class AllRules extends CompositeRule{

	public AllRules() {
		leafRules = Arrays.asList(new ParticipantsGroups(), new BlocksRule());
	}

}
