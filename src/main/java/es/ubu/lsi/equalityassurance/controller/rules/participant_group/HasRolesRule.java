package es.ubu.lsi.equalityassurance.controller.rules.participant_group;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class HasRolesRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getRoles()
				.getMap()
				.size() >= 2;
	}

	

}
