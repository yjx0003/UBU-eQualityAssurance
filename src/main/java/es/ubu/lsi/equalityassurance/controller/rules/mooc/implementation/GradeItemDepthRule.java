package es.ubu.lsi.equalityassurance.controller.rules.mooc.implementation;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class GradeItemDepthRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getGradeItems()
				.getValues()
				.iterator()
				.next()
				.getMaxdepth() < 5;
	}

}
