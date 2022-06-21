package es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class ThemesRules extends CompositeRule {
	public ThemesRules() {
		leafRules = Arrays.asList(new TitleRule(), new SectionVisibleRule(), new DescriptionRule());
	}
}
