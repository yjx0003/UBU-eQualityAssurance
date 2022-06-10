package es.ubu.lsi.equalityassurance.controller.rules;

import java.util.Collections;
import java.util.List;

import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.util.I18n;

public abstract class BasicRule implements Rule{

	@Override
	public List<Rule> getChildrenRules() {
		return Collections.emptyList();
	}
	
	@Override
	public double getValue(DataBase dataBase) {
		if(apply(dataBase)) {
			return 1.0;
		}
		return 0.0;
	}
	
	@Override
	public String reasonFail(DataBase dataBase) {
		return I18n.get("fail." + getClass().getSimpleName());
	}
}
