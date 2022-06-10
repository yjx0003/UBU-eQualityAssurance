package es.ubu.lsi.equalityassurance.controller.rules;

import java.util.List;

import es.ubu.lsi.equalityassurance.model.DataBase;

public interface Rule {
	public boolean apply(DataBase dataBase);
	public double getValue(DataBase dataBase);
	public default String getName() {
		return "rule." + getClass().getSimpleName();
	}
	
	public default String getTooltip() {
		return "tooltip." + getClass().getSimpleName();
	}
	public List<Rule> getChildrenRules();
	
	
	public String reasonFail(DataBase dataBase);
}
