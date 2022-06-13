package es.ubu.lsi.equalityassurance.controller.rules.ubucev.participant_group;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class HasGroupsRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		return !dataBase.getGroups().getMap().isEmpty();
	}

}
