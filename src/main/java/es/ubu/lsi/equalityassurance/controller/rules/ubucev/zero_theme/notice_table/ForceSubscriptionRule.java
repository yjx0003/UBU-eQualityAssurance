package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.notice_table;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Forum;

public class ForceSubscriptionRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getForums()
				.getValues()
				.stream()
				.filter(f -> "news".equals(f.getType()))
				.findAny()
				.map(Forum::isForcesubscribe)
				.orElse(false);
	}

}
