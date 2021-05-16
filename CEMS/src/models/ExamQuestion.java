package models;

import com.jfoenix.controls.JFXButton;

public class ExamQuestion extends Question {

	private static final long serialVersionUID = 1L;
	private String note;
	private int points;
	private NoteType noteType;
	private JFXButton noteDetails;

	public enum NoteType {
		Students, Teachers, None
	}

	public ExamQuestion(Question question, String note, int points, NoteType noteType) {
		super(question.getQuestionID(), question.getTeacherName(), question.getSubject(), question.getDetails(),
				question.getAnswer1(), question.getAnswer2(), question.getAnswer3(), question.getAnswer4(),
				question.getCorrectAnswer(), question.getDetailsButton());
		this.note = note;
		this.points = points;
		this.noteType = noteType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}

	public JFXButton getNoteDetails() {
		return noteDetails;
	}

	public void setNoteDetails(JFXButton noteDetails) {
		this.noteDetails = noteDetails;
	}

}