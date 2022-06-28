package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.notice_table.NoticeTable;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.question_forum.QuestionForum;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Section;

public class ZeroThemeRules extends CompositeRule {

	public ZeroThemeRules() {
		leafRules = Arrays.asList(new NoticeTable(), new QuestionForum(), new CalendarCronogramRule(),
				new EnableCompletionRule(), new EvaluationRule(), new ScheduleRule(), new PlacementQuiz());
	}

	public static Section getSectionZero(DataBase dataBase) {
		return dataBase.getSections()
				.getValues()
				.stream()
				.filter(s -> s.getSectionNumber() == 0)
				.findFirst()
				.orElse(null);
	}
}
