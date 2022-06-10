package es.ubu.lsi.equalityassurance.controller.rules;

import java.util.List;

import es.ubu.lsi.equalityassurance.model.DataBase;

public abstract class CompositeRule implements Rule{
	
	protected List<Rule> leafRules;
	
	@Override
	public boolean apply(DataBase dataBase) {
		return false;
	}
	
	@Override
	public List<Rule> getChildrenRules() {
		return leafRules;
	}
	
	@Override
	public double getValue(DataBase dataBase) {
		return leafRules.stream().mapToDouble(r-> r.getValue(dataBase)).sum() / leafRules.size();
	}
	
	@Override
	public String reasonFail(DataBase dataBase) {
		return "";
	}
			
}
