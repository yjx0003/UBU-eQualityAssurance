package es.ubu.lsi.equalityassurance.controller.rules.mooc.implementation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Resource;

public class ResourcesUpdated extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Instant courseStart = dataBase.getActualCourse()
				.getStartDate()
				.minus(365, ChronoUnit.DAYS);
		return dataBase.getResources()
				.getValues()
				.stream()
				.allMatch(r -> r.getTimemodified()
						.isAfter(courseStart));
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		Instant courseStart = dataBase.getActualCourse()
				.getStartDate()
				.minus(365, ChronoUnit.DAYS);
		return dataBase.getResources()
				.getValues()
				.stream()
				.filter(r -> r.getTimemodified()
						.isBefore(courseStart))
				.map(Resource::getName)
				.collect(Collectors.toList());
	}

}
