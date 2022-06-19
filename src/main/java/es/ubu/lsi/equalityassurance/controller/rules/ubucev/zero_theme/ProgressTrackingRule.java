package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class ProgressTrackingRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getActualCourse().isEnablecompletion();
	}

}
