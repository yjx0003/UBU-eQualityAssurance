package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class ShowCompletionRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getActualCourse()
				.isShowcompletionconditions();
	}

}
