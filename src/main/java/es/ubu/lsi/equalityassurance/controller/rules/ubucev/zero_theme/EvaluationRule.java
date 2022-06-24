package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Section;

public class EvaluationRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		Section sectionZero = ZeroThemeRules.getSectionZero(dataBase);
		if (sectionZero == null) {
			return false;
		}
		
		return dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getSection()
						.equals(sectionZero))
				.anyMatch(cm-> cm.getModuleName().contains("normativa") && cm.getModuleName().contains("evaluación"));
		
		
	}

}
