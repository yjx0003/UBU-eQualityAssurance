package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase de bases de datos. Almacena datos de todos los años Encargado de las
 * instaciar varios de las clases del paquete {@link model}. En BBDD mantiene
 * solo una instancía por cada id que haya de cada elemento. Las instancias de
 * cada BBDD no son los mismos a pesar de que tengan los mismos ids.
 * 
 * En la versión actual de la aplicación, el id de los grade item es el orden
 * que devuelve en la funcion de moodle gradereport_user_get_grades_table.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
public class DataBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private SiteInfo siteInfo;
	
	private SubDataBase<Role> roles;
	private SubDataBase<Group> groups;
	private SubDataBase<EnrolledUser> users;
	private SubDataBase<CourseModule> modules;
	private SubDataBase<Course> courses;
	private SubDataBase<GradeItem> gradeItems;
	private SubDataBase<CourseCategory> courseCategories;
	private SubDataBase<Section> sections;
	private SubDataBase<ForumDiscussion> forumDiscussions;
	private SubDataBase<DiscussionPost> discussionPosts;
	private SubDataBase<CourseEvent> courseEvents;
	private SubDataBase<Block> blocks;

	
	
	/**
	 * Curso actual del usuario
	 */
	private Course actualCourse;

	public DataBase() {
		checkSubDatabases();
	}

	public void checkSubDatabases() {

		roles = checkSubDataBase(roles, Role::new);
		groups = checkSubDataBase(groups, Group::new);
		users = checkSubDataBase(users, EnrolledUser::new);
		modules = checkSubDataBase(modules, CourseModule::new);
		courses = checkSubDataBase(courses, Course::new);
		gradeItems = checkSubDataBase(gradeItems, GradeItem::new);
		courseCategories = checkSubDataBase(courseCategories, CourseCategory::new);
		sections = checkSubDataBase(sections, Section::new);
		
		
		forumDiscussions = checkSubDataBase(forumDiscussions, ForumDiscussion::new);
		discussionPosts = checkSubDataBase(discussionPosts, DiscussionPost::new);
		courseEvents = checkSubDataBase(courseEvents, CourseEvent::new);
		blocks = checkSubDataBase(blocks, Block::new);
	}

	public <E extends Serializable> SubDataBase<E> checkSubDataBase(SubDataBase<E> subDataBase,
			SerializableFunction<Integer, E> creatorFunction) {
		if (subDataBase == null) {
			return new SubDataBase<>(creatorFunction);
		}
		return subDataBase;
	}


}
