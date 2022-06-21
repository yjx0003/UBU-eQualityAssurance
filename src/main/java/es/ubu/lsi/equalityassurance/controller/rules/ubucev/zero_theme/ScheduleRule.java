package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.model.Section;

public class ScheduleRule extends BasicRule{

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
				.filter(cm -> cm.getModuleType() == ModuleType.URL)
				.anyMatch(cm-> cm.getModuleName().contains("horario") || cm.getModuleName().contains("tutorías"));
		
		
	}

}
