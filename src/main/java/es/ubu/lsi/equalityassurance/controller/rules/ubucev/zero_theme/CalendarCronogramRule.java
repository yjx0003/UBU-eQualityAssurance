package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Section;

public class CalendarCronogramRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Section sectionZero = ZeroTheme.getSectionZero(dataBase);
		if (sectionZero == null) {
			return false;
		}
		return dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> sectionZero.equals(cm.getSection()))
				.anyMatch(cm -> cm.getModuleName()
						.contains("Calendario")
						|| cm.getModuleName()
								.contains("Cronograma"));

	}

}
