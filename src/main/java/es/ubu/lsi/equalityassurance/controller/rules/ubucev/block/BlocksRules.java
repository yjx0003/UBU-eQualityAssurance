package es.ubu.lsi.equalityassurance.controller.rules.ubucev.block;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class BlocksRules extends CompositeRule {

	public BlocksRules() {
		leafRules = Arrays.asList(new CalendarRule(), new UpcomingEventsRule(), new RepeatedBlockRule(), new SmowlRule());
	}
}
