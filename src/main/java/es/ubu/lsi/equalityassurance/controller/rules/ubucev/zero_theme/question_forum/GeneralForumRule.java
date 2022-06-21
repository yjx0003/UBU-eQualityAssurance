package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroThemeRules;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.model.Section;

public class GeneralForumRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Section sectionZero = ZeroThemeRules.getSectionZero(dataBase);

		if (sectionZero == null) {
			return false;
		}
		return dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm-> cm.getSection().equals(sectionZero))
				.filter(cm -> cm.getModuleType() == ModuleType.FORUM)
				.anyMatch(cm -> "general".equals(dataBase.getForums()
						.getById(cm.getInstance())
						.getType()));
	}

}
