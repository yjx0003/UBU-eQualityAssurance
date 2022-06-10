package es.ubu.lsi.equalityassurance.controller.rules.participant_group;

import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.EnrolledUser;

public class StudentGroupRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getUsers()
				.getValues()
				.stream()
				.noneMatch(u -> u.getGroups()
						.isEmpty());
	}

	@Override
	public String reasonFail(DataBase dataBase) {
		String usernames = dataBase.getUsers()
				.getValues()
				.stream()
				.filter(u -> u.getGroups()
						.isEmpty())
				.map(EnrolledUser::getFullName)
				.collect(Collectors.joining(" || "));
		
		return super.reasonFail(dataBase) + usernames;

	}

}
