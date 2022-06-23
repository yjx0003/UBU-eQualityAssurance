package es.ubu.lsi.equalityassurance.controller.rules;

import java.util.Collections;
import java.util.List;

import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.util.I18n;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public abstract class BasicRule implements Rule{

	@Override
	public List<Rule> getChildrenRules() {
		return Collections.emptyList();
	}
	
	@Override
	public double getValue(DataBase dataBase) {
		try {
			if(apply(dataBase)) {
				return 1.0;
			}
			return 0.0;
		} catch(Exception e) {
			log.debug("Error en apply", e);
			return 0.0;
		}
		
	}
	
	@Override
	public String reasonFail(DataBase dataBase) {
		return I18n.get("fail." + getClass().getSimpleName());
	}
}
