package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.notice_table;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class NoticeRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getForums()
				.getValues()
				.stream()
				.anyMatch(f -> "news".equals(f.getType()));
	}

}
