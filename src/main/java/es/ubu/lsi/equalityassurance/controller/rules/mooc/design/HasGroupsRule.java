package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class HasGroupsRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		return !dataBase.getGroups().getValues().isEmpty();
	}

}
