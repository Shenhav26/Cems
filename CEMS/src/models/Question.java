package models;

import java.io.Serializable;

import com.jfoenix.controls.JFXButton;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private String questionID;
	private String teacherName;
	private String subject;
	private String details;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int correctAnswer;
	private JFXButton detailsButton;
	private JFXButton addButton;

	public Question(String questionID, String teacherName, String subject, String details,
			String answer1, String answer2, String answer3, String answer4, int correctAnswer,
			JFXButton detailsButton) {
		super();
		this.questionID = questionID;
		this.teacherName = teacherName;
		this.subject = subject;
		this.details = details;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correctAnswer = correctAnswer;
		this.detailsButton = detailsButton;
	}

	public Question(String teacherName, String subject, String details, String answer1, String answer2, String answer3,
			String answer4, int correctAnswer) {
		super();
		this.teacherName = teacherName;
		this.subject = subject;
		this.details = details;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correctAnswer = correctAnswer;
	}

	public Question(String questionID, String teacherName, String subject, String details, String answer1,
			String answer2, String answer3, String answer4, int correctAnswer) {
		super();
		this.questionID = questionID;
		this.teacherName = teacherName;
		this.subject = subject;
		this.details = details;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correctAnswer = correctAnswer;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public JFXButton getDetailsButton() {
		return detailsButton;
	}

	public void setDetailsButton(JFXButton detailsButton) {
		this.detailsButton = detailsButton;
	}

	public JFXButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JFXButton addButton) {
		this.addButton = addButton;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", teacherName=" + teacherName + ", subject=" + subject
				+ ", details=" + details + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3
				+ ", answer4=" + answer4 + ", correctAnswer=" + correctAnswer + "]";
	}

}
