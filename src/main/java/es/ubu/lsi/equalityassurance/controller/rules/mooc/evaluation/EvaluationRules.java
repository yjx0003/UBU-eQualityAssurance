package es.ubu.lsi.equalityassurance.controller.rules.mooc.evaluation;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class EvaluationRules extends CompositeRule{

	public EvaluationRules() {
		leafRules = Arrays.asList(new HasSurveysRule());
	}
}
