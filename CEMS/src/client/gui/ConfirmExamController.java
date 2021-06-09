package client.gui;

import static common.ModelWrapper.Operation.CREATE_EXAM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import client.Client;
import client.ClientUI;
import common.ModelWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Exam;
import models.ExamQuestion;

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
	private TableColumn<ExamQuestion, JFXButton> tcNote;

	@FXML
	private JFXButton btnBack;

	@FXML
	private JFXButton btnCreate;

	private static Exam exam;

	public ConfirmExamController() {
	}

	public ConfirmExamController(Exam exam) {
		ConfirmExamController.exam = exam;
	}

	public void start() {
		try {
			Pane questionListPane = (Pane) FXMLLoader.load(getClass().getResource("ConfirmNewExamPage.fxml"));
			MainGuiController.getMenuHandler().getMainFrame().setCenter(questionListPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickBack(ActionEvent event) {
		MainGuiController.getMenuHandler().setCreateExamScreen();

	}

	@FXML
	void onClickCreate(ActionEvent event) {
		exam.setQuestionListButton(null);
		for (ExamQuestion examQuestion : exam.getExamQuestions()) {
			examQuestion.setDetailsButton(null);
			examQuestion.setRemoveButton(null);
		}
		ModelWrapper<Exam> modelWrapper = new ModelWrapper<>(exam, CREATE_EXAM);
		ClientUI.getClientController().sendClientUIRequest(modelWrapper);
		MainGuiController.getMenuHandler().setCreateExamSucceeded();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcID.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("questionID"));
		tcSubject.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("subject"));
		tcTeacher.setCellValueFactory(new PropertyValueFactory<ExamQuestion, String>("teacherName"));
		tcPoints.setCellValueFactory(new PropertyValueFactory<ExamQuestion, Integer>("points"));
		tcNote.setCellValueFactory(new PropertyValueFactory<ExamQuestion, JFXButton>("noteDetails"));
		tcDetails.setCellValueFactory(new PropertyValueFactory<ExamQuestion, JFXButton>("detailsButton"));

		ObservableList<ExamQuestion> examQuestions = FXCollections.observableArrayList();
		examQuestions.addAll(exam.getExamQuestions());
		tvQuestions.setItems(examQuestions);

	}

}