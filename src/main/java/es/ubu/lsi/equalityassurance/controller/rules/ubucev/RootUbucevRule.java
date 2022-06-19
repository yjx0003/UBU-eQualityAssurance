package es.ubu.lsi.equalityassurance.controller.rules.ubucev;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.block.BlocksRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group.ParticipantsGroups;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes.Themes;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroTheme;

public class RootUbucevRule extends CompositeRule{

	public RootUbucevRule() {
		leafRules = Arrays.asList(new ParticipantsGroups(),new BlocksRule(), new ZeroTheme(), new Themes());
	}

}
