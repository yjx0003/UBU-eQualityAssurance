package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroThemeRules;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Forum;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.model.Section;

public class GeneralForumDescriptionRule extends BasicRule {

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
				.filter(cm -> cm.getModuleType() == ModuleType.FORUM)
				.map(cm -> dataBase.getForums()
						.getById(cm.getInstance()))
				.filter(f -> "general".equals(f.getType()))
				.noneMatch(f -> f.getIntro()
						.isEmpty());
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		Section sectionZero = ZeroThemeRules.getSectionZero(dataBase);
		
		if (sectionZero == null) {
			return Collections.emptyList();
		}
		return dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getSection()
						.equals(sectionZero))
				.filter(cm -> cm.getModuleType() == ModuleType.FORUM)
				.map(cm -> dataBase.getForums()
						.getById(cm.getInstance()))
				.filter(f -> "general".equals(f.getType()))
				.filter(f -> f.getIntro()
						.isEmpty())
				.map(Forum::getName)
				.collect(Collectors.toList());
	}

}
