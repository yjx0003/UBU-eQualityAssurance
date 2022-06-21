package es.ubu.lsi.equalityassurance.controller.rules.ubucev.other;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.EnrolledUser;

public class TeacherStartDiscussionRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getForumDiscussions()
				.getValues()
				.stream()
				.anyMatch(d -> hasTeacherRole(d.getUser()));
	}

	public static boolean hasTeacherRole(EnrolledUser user) {
		return user.getRoles()
				.stream()
				.anyMatch(r -> r.getRoleShortName()
						.contains("teacher"));
	}

}
