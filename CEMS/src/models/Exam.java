package models;

import java.io.Serializable;
import java.util.List;

/**
 * Class that describe test on project system, store all necessary attributes
 * inside this model.
 * 
 * @author Arikz ,Dvir ben simon
 *
 */
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String subject;
	private String course;
	private String duration;
	private List<ExamQuestion> examQuestions;

	public Exam(String id, String subject, String course, String duration, List<ExamQuestion> examQuestions) {
		super();
		this.id = id;
		this.subject = subject;
		this.course = course;
		this.duration = duration;
		this.examQuestions = examQuestions;
	}

	public Exam(String subject, String course, String duration, List<ExamQuestion> examQuestions) {
		super();
		this.subject = subject;
		this.course = course;
		this.duration = duration;
		this.examQuestions = examQuestions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<ExamQuestion> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(List<ExamQuestion> examQuestions) {
		this.examQuestions = examQuestions;
	}

}
