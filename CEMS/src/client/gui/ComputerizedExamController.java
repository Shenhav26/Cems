package client.gui;

import static common.ModelWrapper.Operation.GET_EXAMS_LIST;
import static common.ModelWrapper.Operation.GET_EXAMS_LIST_BY_COURSE;
import static common.ModelWrapper.Operation.GET_EXAMS_LIST_BY_SUBJECT;
import static common.ModelWrapper.Operation.START_EXAM;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import client.Client;
import client.ClientUI;
import common.ModelWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exam;
import models.ExamProcess;
import models.ExamProcess.ExamType;

public class ComputerizedExamController implements Initializable {

	@FXML
	private JFXComboBox<String> cbExamCourse;

	@FXML
	private JFXComboBox<String> cbExamSubject;

	@FXML
	private TableView<Exam> tvExamPool;

	@FXML
	private TableColumn<Exam, String> tcID;

	@FXML
	private TableColumn<Exam, String> tcTeacher;

	@FXML
	private TableColumn<Exam, String> tcSubject;

	@FXML
	private TableColumn<Exam, String> tcCourse;

	@FXML
	private TableColumn<Exam, String> tcDuration;

	@FXML
	private TableColumn<Exam, JFXButton> tcQuestionList;

	@FXML
	private JFXTextField tfCodeComputerized;

	@FXML
	private JFXButton btnStartComputerized;

	@FXML
	private JFXComboBox<String> cbManualSubject;

	@FXML
	private JFXComboBox<String> cbManualCourse;

	@FXML
	private JFXTextField tfManualDuration;

	@FXML
	private JFXButton btnUpload;

	@FXML
	private JFXButton btnStartManual;

	@FXML
	private JFXTextField tfCodeManual;

	@FXML
	private Label masgeLabel;

	private ExamType examType;

	private String filePath;

	@FXML
	void onClickExamSubject(ActionEvent event) {
		String subjectSelected = cbExamSubject.getSelectionModel().getSelectedItem();
		cbExamCourse.getItems().clear();
		cbExamCourse.getItems().addAll(Client.getSubjectCollection().getCourseListBySubject(subjectSelected));

		ModelWrapper<String> modelWrapper = new ModelWrapper<>(subjectSelected, GET_EXAMS_LIST_BY_SUBJECT);
		ClientUI.getClientController().sendClientUIRequest(modelWrapper);

		ObservableList<Exam> exams = FXCollections.observableArrayList();
		exams.addAll(Client.getExams());
		tvExamPool.setItems(exams);
		setExamQuestioListButtons(Client.getExams());

	}

	private List<Exam> setExamQuestioListButtons(List<Exam> exams) {

		for (Exam exam : exams) {
			JFXButton questionListButton = new JFXButton();
			questionListButton.setPrefSize(90, 15);
			questionListButton
					.setStyle("-fx-background-color:#616161;" + "-fx-background-radius:10;" + "-fx-text-fill:white;");
			questionListButton.setText("List");
			questionListButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MainGuiController.getMenuHandler().setQuestionListScreen(exam.getExamQuestions());
				}

			});

			exam.setQuestionListButton(questionListButton);
		}
		return exams;
	}

	@FXML
	void onClickExamCourse(ActionEvent event) {
		String courseSlected = cbExamCourse.getSelectionModel().getSelectedItem();

		ModelWrapper<String> modelWrapper = new ModelWrapper<>(courseSlected, GET_EXAMS_LIST_BY_COURSE);
		ClientUI.getClientController().sendClientUIRequest(modelWrapper);

		ObservableList<Exam> exams = FXCollections.observableArrayList();
		exams.addAll(Client.getExams());
		tvExamPool.setItems(exams);
		setExamQuestioListButtons(Client.getExams());

	}

	@FXML
	void onClickStartComputerized(ActionEvent event) {
		String code = tfCodeComputerized.getText();
		String focusedExamID = tvExamPool.getFocusModel().getFocusedItem().getId();
		boolean validInput = true;
		masgeLabel.setStyle("-fx-text-fill: RED;");

		if (code.isEmpty()) {
			masgeLabel.setText("You need to insert exam code");
			validInput = false;
		} else if (code.length() != 4) {
			masgeLabel.setText("Exam code should have 4 digits");
			validInput = false;
		}

		if (validInput) {
			masgeLabel.setText("");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date date = new Date();
			String currentDate = formatter.format(date).toString();
			String teacherID = Client.getUser().getUserID();

			ExamProcess examProcess = new ExamProcess(focusedExamID, currentDate, teacherID, code);

			ModelWrapper<ExamProcess> modelWrapper = new ModelWrapper<>(examProcess, START_EXAM);
			ClientUI.getClientController().sendClientUIRequest(modelWrapper);

			for (Exam exam : Client.getExams()) {
				if (exam.getId().equals(focusedExamID)) {
					ExamManagementWindow examManagementWindow = new ExamManagementWindow(code,
							Integer.valueOf(exam.getDuration()));
					examManagementWindow.open();
				}
			}
		}

	}

	/**
	 * Setting all table column and creating new data set from client request that
	 * has been sent to database.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbExamSubject.getItems().addAll(Client.getSubjectCollection().getSubjects());

		tcID.setCellValueFactory(new PropertyValueFactory<Exam, String>("id"));
		tcTeacher.setCellValueFactory(new PropertyValueFactory<Exam, String>("teacherName"));
		tcSubject.setCellValueFactory(new PropertyValueFactory<Exam, String>("subject"));
		tcCourse.setCellValueFactory(new PropertyValueFactory<Exam, String>("course"));
		tcDuration.setCellValueFactory(new PropertyValueFactory<Exam, String>("duration"));
		tcQuestionList.setCellValueFactory(new PropertyValueFactory<Exam, JFXButton>("questionListButton"));

		ModelWrapper<String> modelWrapper = new ModelWrapper<>(GET_EXAMS_LIST);
		ClientUI.getClientController().sendClientUIRequest(modelWrapper);
		ObservableList<Exam> exams = FXCollections.observableArrayList();
		List<Exam> examList = Client.getExams();
		examList = setExamQuestioListButtons(examList);
		exams.addAll(examList);
		tvExamPool.setItems(exams);
		tvExamPool.getSelectionModel().select(0);
	}

}