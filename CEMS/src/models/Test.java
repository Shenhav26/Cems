package models;

import java.io.Serializable;

//
public class Test implements Serializable {
	private String id;
	private String subject;
	private String course;
	private String duration;
	private String pointsPerQuestion;

	public Test(String id, String subject, String course, String duration, String pointsPerQuestion) {
		super();
		this.id = id;
		this.subject = subject;
		this.course = course;
		this.duration = duration;
		this.pointsPerQuestion = pointsPerQuestion;
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

	public String getPointsPerQuestion() {
		return pointsPerQuestion;
	}

	public void setPointsPerQuestion(String pointsPerQuestion) {
		this.pointsPerQuestion = pointsPerQuestion;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", subject=" + subject + ", course=" + course + ", duration=" + duration
				+ ", pointsPerQuestion=" + pointsPerQuestion + "]";
	}

}
