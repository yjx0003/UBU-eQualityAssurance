package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.ZeroTheme;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Forum;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.model.Section;

public class ForceSubscriptionQuestionForumRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Section sectionZero = ZeroTheme.getSectionZero(dataBase);

		if (sectionZero == null) {
			return false;
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
				.map(cm-> dataBase.getForums().getById(cm.getInstance()))
				.allMatch(Forum::isForcesubscribe);
	}
	
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		Section sectionZero = ZeroTheme.getSectionZero(dataBase);
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
				.map(cm-> dataBase.getForums().getById(cm.getInstance()))
				.filter(f-> !f.isForcesubscribe())
				.map(Forum::getName)
				.collect(Collectors.toList());
	}

}
