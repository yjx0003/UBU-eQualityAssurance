package es.ubu.lsi.equalityassurance.controller.rules.mooc.evaluation;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;

public class HasSurveysRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getModules()
				.getValues()
				.stream()
				.anyMatch(m -> m.getModuleType() == ModuleType.SURVEY);
	}

}
