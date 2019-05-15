package controllers.general;

import controllers.cardreader.CardReaderThread;
import controllers.temporary.InitializeStudents;
import controllers.temporary.Lesson;
import controllers.temporary.Student;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainScreenController implements Initializable {

    @FXML
    private TableView historyTable;

    @FXML
    private TableView presenceTable;

    @FXML
    private ChoiceBox<String> subjectChoiceBox;

    @FXML
    private ChoiceBox<String> roomChoiceBox;

    @FXML
    private ChoiceBox<String> hourChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button startListeningButton;


    @FXML
    private TableColumn<Student, String> cardIdColumn;

    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> surnameColumn;

    @FXML
    private TableColumn<Student, String> facultyColumn;

    @FXML
    private TableColumn<Student, String> deanGroupColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;


    @FXML
    private ChoiceBox<String> subjectHistoryChoiceBox;

    @FXML
    private ChoiceBox<String> dateHistoryChoiceBox;

    @FXML
    private TableColumn<Lesson, String> dateHistoryColumn;

    @FXML
    private TableColumn<Lesson, String> hourHistoryColumn;

    @FXML
    private TableColumn<Lesson, String> subjectHistoryColumn;

    @FXML
    private TableColumn<Lesson, Integer> frequencyColumn;

    @FXML
    private TableColumn<Lesson, String> roomColumn;

    @FXML
    private ChoiceBox<?> semesterStatsChoiceBox;

    @FXML
    private ChoiceBox<?> subjectStatsChoiceBox;

    @FXML
    private ChoiceBox<?> deanGroupStatsChoiceBox1;

    @FXML
    private ChoiceBox<?> studentStatsChoiceBox;

    public static HashMap<String, Student> studentHashMap;
    static ObservableList<Student> students;
    static ObservableList<Lesson> lessons;

    private ArrayList<String> subjectList = new ArrayList<>(Arrays.asList("", "AWSI", "IO", "PT", "PZ", "WTI"));
    private ArrayList<String> roomList = new ArrayList<>(Arrays.asList(
            "E 210", "E 217", "E 110", "BM A1", "BM A2", "M 215", "M 216", "CW 4"));
    private ArrayList<String> hourList = new ArrayList<>(Arrays.asList("8:00", "9:45", "11:45", "13:30", "15:10", "16:45"));
    private ArrayList<String> dateList = new ArrayList<>(Arrays.asList(""));

    private boolean listeningButtonPressed;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeningButtonPressed = false;

        initializeStudentsData();
        initializeColumns();
        initializeChoicebox();
        initializeObservableArrays();

        students.addListener((InvalidationListener) observable -> {
            presenceTable.setItems(students);
        });
        lessons.addListener((InvalidationListener) observable -> {
            historyTable.setItems(lessons);

        });

        filterHistoryTable();
        configureHistoryTable();

    }

    private void initializeObservableArrays() {
        students = FXCollections.observableArrayList();
        lessons = FXCollections.observableArrayList();
    }

    private void initializeChoicebox() {
        subjectChoiceBox.getItems().addAll(subjectList);
        roomChoiceBox.getItems().addAll(roomList);
        hourChoiceBox.getItems().addAll(hourList);
        subjectHistoryChoiceBox.getItems().addAll(subjectList);
        dateHistoryChoiceBox.getItems().addAll(dateList);
        subjectHistoryChoiceBox.getSelectionModel().select(0);
        dateHistoryChoiceBox.getSelectionModel().select(0);
    }

    private void initializeColumns() {
        cardIdColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("cardId")
        );
        studentIdColumn.setCellValueFactory(
                new PropertyValueFactory<Student, Integer>("albumNumber")
        );
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("firstName")
        );
        surnameColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("lastName")
        );
        facultyColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("faculty")
        );
        deanGroupColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("deanGroup")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<Student, String>("eMail")
        );

        subjectHistoryColumn.setCellValueFactory(
                new PropertyValueFactory<Lesson, String>("subject")
        );

        roomColumn.setCellValueFactory(
                new PropertyValueFactory<Lesson, String>("room")
        );

        hourHistoryColumn.setCellValueFactory(
                new PropertyValueFactory<Lesson, String>("hour")
        );

        dateHistoryColumn.setCellValueFactory(
                new PropertyValueFactory<Lesson, String>("date")
        );

        frequencyColumn.setCellValueFactory(
                new PropertyValueFactory<Lesson, Integer>("frequency")
        );
    }

    private void initializeStudentsData() {
        studentHashMap = InitializeStudents.initializeStudents();
    }

    private void filterHistoryTable() {

        dateHistoryChoiceBox.valueProperty().addListener((Observable) -> {
            filterHistory();
        });

        subjectHistoryChoiceBox.valueProperty().addListener((Observable) -> {
            filterHistory();
        });

    }

    private void filterHistory() {
        if (dateHistoryChoiceBox.getSelectionModel().isEmpty() || subjectHistoryChoiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        ObservableList<Lesson> filtered = FXCollections.observableArrayList(lessons);

        if (!dateHistoryChoiceBox.getValue().equals("")) {
            String date = dateHistoryChoiceBox.getValue();
            filtered = filtered.filtered(lesson -> lesson.getDate().equals(date));
        }

        if (!subjectHistoryChoiceBox.getValue().equals("")) {
            String subject = subjectHistoryChoiceBox.getValue();
            filtered = filtered.filtered(lesson -> lesson.getSubject().equals(subject));
        }

        historyTable.setItems(filtered);
    }

    private void configureHistoryTable() {
        ;
        historyTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(this.getClass().getResource("/views/general/DetailedStudentsPresenceScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage = new Stage();
                stage.setTitle("Przegląd szczegółowy");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        });
    }

    @FXML
    void startListeningButtonAction() {

        if(checkIfFieldsAreEmpty()!=true){

            listeningButtonPressed = !listeningButtonPressed;

            if (listeningButtonPressed) {
                setDisableFields(true);
                CardReaderThread cardReaderThread = new CardReaderThread();
                Thread readerThread = new Thread(cardReaderThread);
                readerThread.start();
                startListeningButton.setText("Wyślij listę");
            } else {
                setDisableFields(false);
                CardReaderThread.setRunning(new AtomicBoolean(Boolean.FALSE));
                String date = datePicker.getValue().format(DateTimeFormatter.ISO_DATE);

                boolean checkIfDateIsInDatabase = true;
                for (String dateList : dateList) {
                    if (dateList.equals(date)) {
                        checkIfDateIsInDatabase = false;
                    }
                }
                if (checkIfDateIsInDatabase) {
                    dateList.add(date);
                    dateHistoryChoiceBox.setItems(FXCollections.observableArrayList(dateList));
                    dateHistoryChoiceBox.getSelectionModel().select(0);
                }


                Lesson lesson = new Lesson(subjectChoiceBox.getValue(), roomChoiceBox.getValue(), hourChoiceBox.getValue(), date, presenceTable.getItems().size());
                lessons.add(lesson);

                students.clear();
                startListeningButton.setText("Sprawdź obecność");
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wprowadź wymagane dane");
            alert.showAndWait();
        }
    }

    public Boolean checkIfFieldsAreEmpty(){
        return subjectChoiceBox.getSelectionModel().isEmpty() ||
                roomChoiceBox.getSelectionModel().isEmpty() ||
                hourChoiceBox.getSelectionModel().isEmpty() ||
                datePicker.getValue()==null;
    }

    public void setDisableFields(Boolean bool){
        subjectChoiceBox.setDisable(bool);
        roomChoiceBox.setDisable(bool);
        hourChoiceBox.setDisable(bool);
        datePicker.setDisable(bool);
    }

    public static void addRow(String cardId) {
        boolean registeredStudent = studentHashMap.containsKey(cardId);
        if (registeredStudent == false) {
            System.out.println("Dodaj nowego studenta");
            return;
        }
        for (Map.Entry<String, Student> entry : studentHashMap.entrySet()) {
            if (entry.getKey().equals(cardId)) {
                Student student = entry.getValue();
                for (Student student_in_list : students) {
                    if (student_in_list.getAlbumNumber() == student.getAlbumNumber()) {
                        return;
                    }
                }
                students.add(student);
            }
        }
    }

}

