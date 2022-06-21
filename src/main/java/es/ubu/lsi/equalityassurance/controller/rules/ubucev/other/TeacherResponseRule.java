package es.ubu.lsi.equalityassurance.controller.rules.ubucev.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ForumDiscussion;

public class TeacherResponseRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Collection<ForumDiscussion> forumDiscussions = dataBase.getForumDiscussions()
				.getValues();
		for (ForumDiscussion forumDiscussion : forumDiscussions) {
			boolean value = dataBase.getDiscussionPosts()
					.getValues()
					.stream()
					.filter(p -> forumDiscussion.equals(p.getDiscussion()))
					.anyMatch(p -> TeacherStartDiscussionRule.hasTeacherRole(p.getUser()));
			if (!value) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<Object> fails = new ArrayList<>();
		Collection<ForumDiscussion> forumDiscussions = dataBase.getForumDiscussions()
				.getValues();
		for (ForumDiscussion forumDiscussion : forumDiscussions) {
			boolean value = dataBase.getDiscussionPosts()
					.getValues()
					.stream()
					.filter(p -> forumDiscussion.equals(p.getDiscussion()))
					.anyMatch(p -> TeacherStartDiscussionRule.hasTeacherRole(p.getUser()));
			if (!value) {
				fails.add(forumDiscussion.getName());
			}
		}
		return fails;
	}
}
