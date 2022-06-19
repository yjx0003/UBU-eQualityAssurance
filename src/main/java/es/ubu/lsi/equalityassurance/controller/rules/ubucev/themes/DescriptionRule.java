package es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes;

import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Section;

public class DescriptionRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		return dataBase.getSections()
				.getValues()
				.stream()
				.noneMatch(s-> s.getSummary().isEmpty());
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		return dataBase.getSections()
				.getValues()
				.stream()
				.filter(s-> s.getSummary().isEmpty())
				.map(Section::getName)
				.collect(Collectors.toList());
	}

}
