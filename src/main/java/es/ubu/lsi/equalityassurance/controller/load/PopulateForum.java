package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.equalityassurance.model.CourseModule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.DescriptionFormat;
import es.ubu.lsi.equalityassurance.model.DiscussionPost;
import es.ubu.lsi.equalityassurance.model.Forum;
import es.ubu.lsi.equalityassurance.model.ForumDiscussion;
import es.ubu.lsi.equalityassurance.util.Constants;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.forum.ModForumGetForumDiscussionPosts;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.forum.ModForumGetForumDiscussions;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.forum.ModForumGetForumsByCourses;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateForum {

	private static final Logger LOGGER = LoggerFactory.getLogger(PopulateForum.class);
	
	private DataBase dataBase;
	private WebService webService;
	private static final ModForumGetForumDiscussions MOD_FORUM_GET_FORUM_DISCUSSIONS = new ModForumGetForumDiscussions(
			0);
	private static final ModForumGetForumDiscussionPosts MOD_FORUM_GET_FORUM_DISCUSSION_POSTS = new ModForumGetForumDiscussionPosts(
			0);
	

	public PopulateForum(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}
	

	public List<Forum> populateForum(int courseid){
		
		try {
			List<Forum> forums = new ArrayList<>();
			JSONArray jsonArray = UtilResponse.getJSONArrayResponse(webService, new ModForumGetForumsByCourses(courseid));
			for(int i =  0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Forum forum = dataBase.getForums().getById(jsonObject.getInt("id"));
				forum.setCourseModule(dataBase.getModules().getById(jsonObject.getInt("cmid")));
				forum.setName(jsonObject.getString("name"));
				forum.setType(jsonObject.getString("type"));
				forum.setIntro(jsonObject.getString("intro"));
				forum.setForcesubscribe(jsonObject.getInt("forcesubscribe") == 1);
			}
			return forums;
		} catch (IOException e) {
			LOGGER.info("No se ha podido recargar los foros");
			return Collections.emptyList();
		}
	}
	
	public List<ForumDiscussion> populateForumDiscussions(Collection<CourseModule> forums) {
		List<ForumDiscussion> forumDiscussions = new ArrayList<>();
		for (CourseModule forum : forums) {

			forumDiscussions.addAll(populateForumDiscussion(forum.getInstance(), forum));

		}
		return forumDiscussions;
	}

	public List<ForumDiscussion> populateForumDiscussion(int forumid, CourseModule forum) {
		try {
			MOD_FORUM_GET_FORUM_DISCUSSIONS.setForumid(forumid);
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, MOD_FORUM_GET_FORUM_DISCUSSIONS);
			return populateForumDiscussions(jsonObject, forum);
		} catch (Exception e) {
			LOGGER.error("No forum discussion", e);
			return Collections.emptyList();
		}
	}

	public List<ForumDiscussion> populateForumDiscussions(JSONObject jsonObject, CourseModule forum) {
		JSONArray jsonArray = jsonObject.getJSONArray(Constants.DISCUSSIONS);
		List<ForumDiscussion> forumDiscussions = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); ++i) {
			ForumDiscussion forumDiscussion = populateForumDiscussion(jsonArray.getJSONObject(i));
			forumDiscussion.setForum(forum);
			forumDiscussions.add(forumDiscussion);
		}
		return forumDiscussions;
	}

	public ForumDiscussion populateForumDiscussion(JSONObject jsonObject) {
		ForumDiscussion forumDiscussion = dataBase.getForumDiscussions()
				.getById(jsonObject.getInt(Constants.DISCUSSION));
		forumDiscussion.setName(jsonObject.optString(Constants.NAME));
		forumDiscussion.setTimemodified(Instant.ofEpochSecond(jsonObject.optLong(Constants.TIMEMODIFIED)));
		forumDiscussion.setUsermodified(dataBase.getUsers()
				.getById(jsonObject.optInt(Constants.USERMODIFIED)));
		forumDiscussion.setTimestart(Instant.ofEpochSecond(jsonObject.optLong(Constants.TIMESTART)));
		forumDiscussion.setTimeend(Instant.ofEpochSecond(jsonObject.optLong(Constants.TIMEEND)));
		forumDiscussion.setUser(dataBase.getUsers()
				.getById(jsonObject.optInt(Constants.USERID)));
		forumDiscussion.setCreated(Instant.ofEpochMilli(jsonObject.optLong(Constants.CREATED)));
		forumDiscussion.setModified(Instant.ofEpochMilli(jsonObject.optLong(Constants.MODIFIED)));
		forumDiscussion.setNumreplies(jsonObject.optInt(Constants.NUMREPLIES));
		forumDiscussion.setPinned(jsonObject.optBoolean(Constants.PINNED));
		forumDiscussion.setLocked(jsonObject.optBoolean(Constants.LOCKED));
		forumDiscussion.setStarred(jsonObject.optBoolean(Constants.STARRED));
		forumDiscussion.setCanreply(jsonObject.optBoolean(Constants.CANREPLY));
		forumDiscussion.setCanlock(jsonObject.optBoolean(Constants.CANLOCK));
		forumDiscussion.setCanfavourite(jsonObject.optBoolean(Constants.CANFAVOURITE));

		return forumDiscussion;
	}

	public List<DiscussionPost> populateDiscussionPosts(Collection<Integer> discussionids) {
		List<DiscussionPost> discussionPosts = new ArrayList<>();
		for (int discussionid : discussionids) {
			discussionPosts.addAll(populateDiscussionPosts(discussionid));
		}
		return discussionPosts;
	}

	public List<DiscussionPost> populateDiscussionPosts(int discussionid) {
		try {
			MOD_FORUM_GET_FORUM_DISCUSSION_POSTS.setDiscussionid(discussionid);
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, MOD_FORUM_GET_FORUM_DISCUSSION_POSTS);
			return populateDiscussionPosts(jsonObject);
		} catch (Exception e) {
			LOGGER.error("No forum discussion posts", e);
			return Collections.emptyList();
		}
	}

	public List<DiscussionPost> populateDiscussionPosts(JSONObject jsonObject) {
		List<DiscussionPost> discussionPosts = new ArrayList<>();
		JSONArray jsonArray = jsonObject.getJSONArray(Constants.POSTS);
		for (int i = 0; i < jsonArray.length(); ++i) {
			discussionPosts.add(populateDiscussionPost(jsonArray.getJSONObject(i)));
		}
		 return discussionPosts;
	}

	public DiscussionPost populateDiscussionPost(JSONObject jsonObject) {
		DiscussionPost discussionPost = dataBase.getDiscussionPosts().getById(jsonObject.getInt(Constants.ID));
		discussionPost.setDiscussion(dataBase.getForumDiscussions().getById(jsonObject.optInt(Constants.DISCUSSION)));
		int parent = jsonObject.optInt(Constants.PARENT);
		
		discussionPost.setParent(dataBase.getDiscussionPosts().getById(parent));
		
		discussionPost.setUser(dataBase.getUsers().getById(jsonObject.optInt(Constants.USERID)));
		discussionPost.setCreated(Instant.ofEpochSecond(jsonObject.optLong(Constants.CREATED)));
		discussionPost.setModified(Instant.ofEpochMilli(jsonObject.optLong(Constants.MODIFIED)));
		discussionPost.setMailed(jsonObject.optInt(Constants.MAILED) == 1);
		discussionPost.setSubject(jsonObject.optString(Constants.SUBJECT));
		discussionPost.setMessage(jsonObject.optString(Constants.MESSAGE));
		discussionPost.setMessageformat(DescriptionFormat.get(jsonObject.optInt(Constants.MESSAGEFORMAT)));
		discussionPost.setMessagetrust(jsonObject.optInt(Constants.MESSAGETRUST) == 1);
		discussionPost.setAttachment(jsonObject.optString(Constants.ATTACHMENT));
		discussionPost.setTotalscore(jsonObject.optInt(Constants.TOTALSCORE));
		discussionPost.setMailnow(jsonObject.optInt(Constants.MAILNOW) ==1);
		discussionPost.setCanreply(jsonObject.optBoolean(Constants.CANREPLY));
		discussionPost.setDeleted(jsonObject.optBoolean(Constants.DELETED));
		discussionPost.setIsprivatereply(jsonObject.optBoolean(Constants.ISPRIVATEREPLY));
		
		return discussionPost;
		
	}

}
