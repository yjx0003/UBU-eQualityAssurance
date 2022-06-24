package es.ubu.lsi.equalityassurance.controller.rules.ubucev.zero_theme.notice_table;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Forum;
import es.ubu.lsi.equalityassurance.model.ForumDiscussion;

public class WelcomeMessageRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Forum forum = dataBase.getForums()
				.getValues()
				.stream()
				.filter(f -> "news".equals(f.getType()))
				.findAny()
				.orElse(null);
		if (forum == null ) {
			return false;
		}
		List<ForumDiscussion> discussions = dataBase.getForumDiscussions()
				.getValues()
				.stream()
				.filter(dp -> dp.getForum()
						.equals(forum.getCourseModule()))
				.collect(Collectors.toList());
		Instant startDate = dataBase.getActualCourse()
				.getStartDate().minus(7, ChronoUnit.DAYS);
		Instant startDatePlus7Days = startDate.plus(7, ChronoUnit.DAYS);
		for(ForumDiscussion forumDiscussion: discussions) {
			Instant instant = forumDiscussion.getTimestart();
			if(instant.isAfter(startDate) && instant.isBefore(startDatePlus7Days)) {
				return true;
			}
		}
		return false;
	}

}
