package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que indica item de calificación.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GradeItem implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Grade item id
	 */
	@EqualsAndHashCode.Include
	private int id;

	/**
	 * Grade item name
	 */
	private String itemname;

	private ModuleType itemModule;
	private CourseModule module;
	private double weightraw;
	private Map<EnrolledUser, Double> graderaw;
	private Map<EnrolledUser, Double> percentages;
	private double grademin;
	private double grademax;

	private GradeItem father;
	private List<GradeItem> children;

	private int level;

	/**
	 * Constructor sin parametros.
	 */
	public GradeItem() {
		children = new ArrayList<>();
		graderaw = new HashMap<>();
		percentages= new HashMap<>();
	}

	/**
	 * Constructor inicializando con el id de grade item.
	 * 
	 * @param id
	 *            id de grade item
	 */
	public GradeItem(int id) {
		this("");
		this.id = id;
	}

	/**
	 * Contructor inicializado con el nombre del grade item.
	 * 
	 * @param name
	 *            el nombre del grade item
	 */
	public GradeItem(String name) {
		this();
		this.itemname = name;
	}

	/**
	 * Añade una nota de un usuario.
	 * 
	 * @param enrolledUser
	 *            el usuario
	 * @param grade
	 *            nota del usuario
	 */
	public void addUserGrade(EnrolledUser enrolledUser, double grade) {
		graderaw.put(enrolledUser, grade);

	}
	

	public void addUserPercentage(EnrolledUser enrolledUser, double percentage) {
		percentages.put(enrolledUser, percentage);
	}
	

	/**
	 * Devuelve la nota de un usuaro o NaN si no existe
	 * 
	 * @param user
	 *            usuario que se busca la nota
	 * @return la nota o NaN si no existe
	 */
	public double getEnrolledUserGrade(EnrolledUser user) {
		return graderaw.getOrDefault(user, Double.NaN);
	}
	
	/**
	 * Devuelve el porcentaje del usuario
	 * @param user usuario
	 * @return el porcentaje o NaN si no existe.
	 */
	public double getEnrolledUserPercentage(EnrolledUser user) {
		return percentages.getOrDefault(user, Double.NaN);
	}

	/**
	 * Normaliza la nota de 0 a 10.
	 * 
	 * @param user
	 *            la nota de un usuario
	 * @return nota normalizada de 0 a 10 o NaN si grade es NaN
	 */
	public double adjustTo10(EnrolledUser user) {
		double grade = getEnrolledUserGrade(user);

		return adjustTo10(grade);
	}

	/**
	 * Normalizar la nota de 0 a 10.
	 * 
	 * @param grade
	 *            nota
	 * @return nota normalizada de 0 a 10 o NaN si grade es NaN
	 */
	public double adjustTo10(double grade) {
		if (!Double.isNaN(grade)) {
			// Normalizacion de la nota del alumno en rango [0,10]
			return ((grade - grademin) / (grademax - grademin)) * 10;

		}

		return grade;
	}
	
	/**
	 * Elimina todos los hijos del grade item.
	 */
	public void clearChildren() {
		this.children.clear();
	}

	/**
	 * Añade un hijo del grade item.
	 * 
	 * @param children
	 *            nuevo hijo
	 */
	public void addChildren(GradeItem children) {
		this.children.add(children);
	}

	@Override
	public String toString() {
		return this.itemname;
	}

	

}
