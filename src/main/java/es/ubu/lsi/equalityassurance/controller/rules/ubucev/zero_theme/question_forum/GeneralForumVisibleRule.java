package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroThemeRules;
import es.ubu.lsi.equalityassurance.model.CourseModule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.model.Section;

public class GeneralForumVisibleRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Section sectionZero = ZeroThemeRules.getSectionZero(dataBase);
		if (sectionZero == null) {
			return false;
		}
		List<CourseModule> forumModules = dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getSection()
						.equals(sectionZero))
				.filter(cm -> cm.getModuleType() == ModuleType.FORUM)
				.filter(cm -> "general".equals(dataBase.getForums()
						.getById(cm.getInstance())
						.getType()))
				.collect(Collectors.toList());
		
		if (forumModules.isEmpty()) {
			return false;
		}
		return forumModules.stream().allMatch(CourseModule::isVisible);
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
				.filter(cm -> "general".equals(dataBase.getForums()
						.getById(cm.getInstance())
						.getType()))
				.filter(cm -> !cm.isVisible())
				.map(CourseModule::getModuleName)
				.collect(Collectors.toList());
	}

}
