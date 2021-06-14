package client.gui;

import static common.ModelWrapper.Operation.CREATE_EXAM;
import static common.ModelWrapper.Operation.EDIT_EXAM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import client.ClientUI;
import common.ModelWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Exam;
import models.ExamQuestion;

/**
 * ConfirmExamController class handle screen of exam confirmation in the teacher menu
 *
 */
public class ConfirmExamController implements Initializable {

	@FXML
	private TableView<ExamQuestion> tvQuestions;

	@FXML
	private TableColumn<ExamQuestion, String> tcID;

	@FXML
	private TableColumn<ExamQuestion, String> tcSubject;

	@FXML
	private TableColumn<ExamQuestion, String> tcTeacher;

	@FXML
	private TableColumn<ExamQuestion, Integer> tcPoints;

	@FXML
	private TableColumn<ExamQuestion, JFXButton> tcDetails;

	@FXML
	private JFXButton btnBack;

	@FXML
	private JFXButton btnCreate;

	@FXML
	private JFXTabPane tpNote;

	@FXML
	private Tab tabTeacherNote;

	@FXML
	private Tab tabStudentNote;

	private static Exam newExam;

	private static Exam oldExam;

	private static String operation;

	public ConfirmExamController() {
	}

<<<<<<< HEAD
	public ConfirmExamController(Exam newExam, String operation) {
		ConfirmExamController.newExam = newExam;
		ConfirmExamController.operation = operation;
	}

	public ConfirmExamController(Exam oldExam, Exam newExam, String operation) {
		ConfirmExamController.newExam = newExam;
		ConfirmExamController.oldExam = oldExam;
=======
	/**
	 * Constructor for ConfirmExamController
	 * @param exam
	 * @param operation
	 */
	public ConfirmExamController(Exam exam, String operation) {
		ConfirmExamController.exam = exam;
>>>>>>> branch 'master' of https://github.com/arikz-tech/CEMS
		ConfirmExamController.operation = operation;
	}

	/**
	 * This method load the fxml and display to the screen
	 */
	public void start() {
		try {
			Pane questionListPane = (Pane) FXMLLoader.load(getClass().getResource("ConfirmNewExamPage.fxml"));
			MainGuiController.getMenuHandler().getMainFrame().setCenter(questionListPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for back button: set the previous screen
	 * @param event
	 */
	@FXML
	void onClickBack(ActionEvent event) {
		MainGuiController.getMenuHandler().setCreateExamScreen();

	}

	/**
	 * Create exam on click
	 * @param event
	 */
	@FXML
	void onClickCreate(ActionEvent event) {
		deletExamButtons();

		switch (operation) {

		case "Create":
			ModelWrapper<Exam> CreateModelWrapper = new ModelWrapper<>(newExam, CREATE_EXAM);
			ClientUI.getClientController().sendClientUIRequest(CreateModelWrapper);
			MainGuiController.getMenuHandler().setCreateExamSucceeded();
			break;

		case "Edit":
			List<Exam> newOldExams = new ArrayList<>();
			newOldExams.add(oldExam);
			newOldExams.add(newExam);
			System.out.println(newOldExams);
			ModelWrapper<Exam> EditModelWrapper = new ModelWrapper<>(newOldExams, EDIT_EXAM);
			ClientUI.getClientController().sendClientUIRequest(EditModelWrapper);
			MainGuiController.getMenuHandler().setCreateExamSucceeded();
			break;
		}

	}

	/**
	 * Reset question buttons
	 */
	private void deletExamButtons() {
		newExam.setQuestionListButton(null);
		newExam.setEditExamButton(null);
		for (ExamQuestion question : newExam.getExamQuestions()) {
			question.setEditButton(null);
			question.setAddRemoveButton(null);
			question.setDetailsButton(null);
			question.setNoteDetails(null);
			question.setTfPoints(null);
		}

		if (oldExam != null) {
			oldExam.setEditExamButton(null);
			oldExam.setQuestionListButton(null);
			for (ExamQuestion question : oldExam.getExamQuestions()) {
				question.setEditButton(null);
				question.setAddRemoveButton(null);
				question.setDetailsButton(null);
				question.setNoteDetails(null);
				question.setTfPoints(null);
			}
		}
	}

	/**
	 * Setting the table and insert data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcID.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("questionID"));
		tcSubject.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("subject"));
		tcTeacher.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("teacherName"));
		tcPoints.setCellValueFactory(new PropertyValueFactory<ExamQuestion, Integer>("points"));
		tcDetails.setCellValueFactory(new PropertyValueFactory<ExamQuestion, JFXButton>("detailsButton"));

		TextArea taTeacher = (TextArea) tabTeacherNote.getContent();
		taTeacher.setText(newExam.getTeacherNote());

		TextArea taStudent = (TextArea) tabStudentNote.getContent();
		taStudent.setText(newExam.getStudentNote());

		ObservableList<ExamQuestion> examQuestions = FXCollections.observableArrayList();
		List<ExamQuestion> examQuestionsList = newExam.getExamQuestions();
		examQuestions.addAll(examQuestionsList);
		tvQuestions.setItems(examQuestions);

	}

}
