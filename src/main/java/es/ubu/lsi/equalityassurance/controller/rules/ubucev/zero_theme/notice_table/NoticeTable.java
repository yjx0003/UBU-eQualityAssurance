package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.notice_table;

import java.util.Arrays;

import es.ubu.lsi.equalityassurance.controller.rules.CompositeRule;

public class NoticeTable extends CompositeRule{
	
	public NoticeTable() {
		leafRules = Arrays.asList(new NoticeRule(), new NoticeVisibleRule(), new WelcomeMessageRule(), new ForceSubscriptionRule());
	}

}
