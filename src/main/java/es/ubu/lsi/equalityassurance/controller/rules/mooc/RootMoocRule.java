package es.ubu.lsi.equalityassurance.controller.rules.mooc;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.mooc.design.DesignRules;
import es.ubu.lsi.equalityassurance.controller.rules.mooc.evaluation.EvaluationRules;
import es.ubu.lsi.equalityassurance.controller.rules.mooc.implementation.ImplementationRules;
import es.ubu.lsi.equalityassurance.controller.rules.mooc.realisation.RealisationRules;

public class RootMoocRule extends CompositeRule {
	public RootMoocRule() {
		leafRules = Arrays.asList(new DesignRules(), new ImplementationRules(), new RealisationRules(),
				new EvaluationRules());
	}
}
