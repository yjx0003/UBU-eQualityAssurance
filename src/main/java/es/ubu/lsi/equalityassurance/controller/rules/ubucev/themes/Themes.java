package es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class Themes extends CompositeRule {
	public Themes() {
		leafRules = Arrays.asList(new TitleRule(), new SectionVisibleRule(), new DescriptionRule());
	}
}
