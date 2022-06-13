package es.ubu.lsi.equalityassurance.controller.rules.ubucev.block;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class BlocksRule extends CompositeRule{

	public BlocksRule() {
		leafRules = Arrays.asList(new CalendarRule(), new UpcomingEventsRule(), new SmowlRule());
	}
}
