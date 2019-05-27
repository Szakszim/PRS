package controllers.general;

import context.ContextHandler;
import controllers.cardreader.CardReaderThread;
import controllers.temporary.Lesson;
import dtos.CardDto;
import dtos.LectureDto;
import dtos.PresenceOnLectureDto;
import dtos.StudentDto;
import entities.Lecture;
import entities.Lecturer;
import entities.PresenceOnLecture;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import requests.CardRequest;
import requests.LectureRequest;
import requests.PresenceOnLectureRequest;
import requests.StudentRequest;
import utils.DateUtil;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.DateUtil.convertDateFormat;

public class MainScreenController implements Initializable {

    //TODO: pobieraj przedmioty dla danego typka
    //TODO: podmiana danych w tabeli - Konrad

    @FXML
    private Text loggedText;

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
    private TableColumn<StudentDto, String> cardIdColumn;

    @FXML
    private TableColumn<StudentDto, Integer> studentIdColumn;

    @FXML
    private TableColumn<StudentDto, String> nameColumn;

    @FXML
    private TableColumn<StudentDto, String> surnameColumn;

    @FXML
    private TableColumn<StudentDto, String> facultyColumn;

    @FXML
    private TableColumn<StudentDto, String> deanGroupColumn;

    @FXML
    private TableColumn<StudentDto, String> emailColumn;


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

    //TODO: to trzeba będzie zmienić na StudentDto oraz PresenceOnLectureDto
    private static HashMap<String, StudentDto> studentHashMap;
    private static ObservableList<StudentDto> students;
    private static ObservableList<Lesson> lessons;


    // legit

    private StudentRequest studentRequest;
    private LectureRequest lectureRequest;
    private CardRequest cardRequest;

    private ArrayList<String> subjectList;
    private ArrayList<Integer> subjectIdList;
    private ArrayList<String> dateList;
    private ArrayList<String> roomList;
    private ArrayList<String> hourList;

    private PresenceOnLectureRequest presenceOnLectureRequest;
    private boolean listeningButtonPressed;

    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeningButtonPressed = false;
        studentHashMap = new HashMap<>();

        initializeRequests();
        initializeStudentsData();
        initializeColumns();
        initializeChoicebox();
        initializeObservableArrays();
        initializeLessons();
        initializeLogged();


        students.addListener((InvalidationListener) observable -> presenceTable.setItems(students));
        lessons.addListener((InvalidationListener) observable -> historyTable.setItems(lessons));

        filterHistoryTable();
        configureHistoryTable();

    }

    private void initializeRequests() {
        studentRequest = new StudentRequest();
        lectureRequest = new LectureRequest();
        presenceOnLectureRequest = new PresenceOnLectureRequest();
        cardRequest = new CardRequest();
    }

    private void initializeLogged() {
        loggedText.setText("Zalogowany jako: " + ContextHandler.getLecturerDto().getFirstName() + " " + ContextHandler.getLecturerDto().getLastName());
    }

    private void initializeObservableArrays() {
        students = FXCollections.observableArrayList();
        lessons = FXCollections.observableArrayList();
    }

    private void initializeChoicebox() {
        initializeLists();

        subjectChoiceBox.getItems().addAll(subjectList);
        roomChoiceBox.getItems().addAll(roomList);
        hourChoiceBox.getItems().addAll(hourList);
        subjectHistoryChoiceBox.getItems().addAll(subjectList);
        dateHistoryChoiceBox.getItems().addAll(dateList);
        subjectHistoryChoiceBox.getSelectionModel().select(0);
        dateHistoryChoiceBox.getSelectionModel().select(0);
    }

    private void initializeLists() {
        roomList = new ArrayList<>(Arrays.asList(
                "E 210", "E 217", "E 110", "BM A1", "BM A2", "M 215", "M 216", "CW 4"));
        hourList = new ArrayList<>(Arrays.asList(
                "8:00", "9:45", "11:45", "13:30", "15:10", "16:50", "18:30"));

        initializeLectureNamesList();
        initializeDateList();

    }

    private void initializeDateList() {
        dateList = new ArrayList<>();
        dateList.add("");
        Set<String> dates = new HashSet<>();
        for (PresenceOnLectureDto presenceOnLectureDto : presenceOnLectureRequest.getAll()) {
            String formattedDate = convertDateFormat(presenceOnLectureDto.getPresenceDate().getTime());
            dates.add(formattedDate);
        }
        dateList.addAll(dates);
    }

    private void initializeLectureNamesList() {
        subjectList = new ArrayList<>();
        subjectIdList = new ArrayList<>();
        List<LectureDto> lectureNames = lectureRequest.getAll();
        for (LectureDto lectureDto : lectureNames) {
            subjectList.add(lectureDto.getLectureName());
            subjectIdList.add(lectureDto.getId());
        }
    }

    private void initializeColumns() {

        initializePresenceColumns();
        initializeHistoryColumns();
    }

    private void initializeHistoryColumns() {

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

    private void initializePresenceColumns() {
        cardIdColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("cardId")
        );
        studentIdColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, Integer>("Id")
        );
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("firstName")
        );
        surnameColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("lastName")
        );
        facultyColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("faculty")
        );
        deanGroupColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("deanGroupName")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<StudentDto, String>("eMail")
        );
    }

    private void initializeStudentsData() {
        List<CardDto> cards = cardRequest.getAll();
        for (CardDto c: cards) {
            studentHashMap.put(c.getId(),new StudentDto(c.getStudent()));
        }
    }

    private void initializeLessons()
    {
        List<PresenceOnLectureDto> presenceOnLectures = presenceOnLectureRequest.getAll();
        List<PresenceOnLectureDto> presenceExamples = new ArrayList<>();
        HashMap<Date,Integer> lessonsFrequency = new HashMap<>();
        Lecturer contextLecturer = ContextHandler.getLecturerDto().toEntity();
        for (PresenceOnLectureDto p: presenceOnLectures ) {
            if(p.getLecturer().equals(contextLecturer))
            if(lessonsFrequency.containsKey(p.getPresenceDate()))
            {
                lessonsFrequency.replace(p.getPresenceDate(),lessonsFrequency.get(p.getPresenceDate())+1);
            }
            else
            {
                presenceExamples.add(p);
                lessonsFrequency.put(p.getPresenceDate(),1);
            }
        }
        for (PresenceOnLectureDto p:presenceExamples) {
            lessons.add(new Lesson(p.getLecture().getLectureName(),roomChoiceBox.getValue(),p.getHourTime(),p.getPresenceDate().toString(),lessonsFrequency.get(p.getPresenceDate())));
        }

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

        if (!checkIfFieldsAreEmpty()) {

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
                for (String data : dateList) {
                    if (data.equals(date)) {
                        checkIfDateIsInDatabase = false;
                    }
                }
                if (checkIfDateIsInDatabase) {
                    dateList.add(date);
                    dateHistoryChoiceBox.setItems(FXCollections.observableArrayList(dateList));
                    dateHistoryChoiceBox.getSelectionModel().select(0);
                }

                Lesson lesson = new Lesson(subjectChoiceBox.getValue(), roomChoiceBox.getValue(), hourChoiceBox.getValue(), date, presenceTable.getItems().size());
                addPresenceToDatabase();

                lessons.add(lesson);

                students.clear();
                startListeningButton.setText("Sprawdź obecność");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wprowadź wymagane dane");
            alert.showAndWait();
        }
    }

    private void addPresenceToDatabase() {
        PresenceOnLectureDto presenceOnLectureDto;
        for(StudentDto s : students) {
            presenceOnLectureDto = new PresenceOnLectureDto();
            presenceOnLectureDto.setLecture(lectureRequest.findById(subjectIdList.get(subjectChoiceBox.getSelectionModel().getSelectedIndex())).toEntity());
            presenceOnLectureDto.setLecturer(ContextHandler.getLecturerDto().toEntity());
            presenceOnLectureDto.setPresenceDate(DateUtil.toDate(datePicker.getValue()));
            presenceOnLectureDto.setHourTime(hourChoiceBox.getValue());
            presenceOnLectureDto.setStudent(s.toEntity());
            presenceOnLectureDto.setRoom(roomChoiceBox.getValue());

            presenceOnLectureRequest.save(presenceOnLectureDto);
        }

    }

    private Boolean checkIfFieldsAreEmpty() {
        return subjectChoiceBox.getSelectionModel().isEmpty() ||
                roomChoiceBox.getSelectionModel().isEmpty() ||
                hourChoiceBox.getSelectionModel().isEmpty() ||
                datePicker.getValue() == null;
    }

    private void setDisableFields(Boolean bool) {
        subjectChoiceBox.setDisable(bool);
        roomChoiceBox.setDisable(bool);
        hourChoiceBox.setDisable(bool);
        datePicker.setDisable(bool);
    }

    public static void addRow(String cardId) {
        boolean registeredStudent = studentHashMap.containsKey(cardId);
        if (!registeredStudent) {
            System.out.println("Dodaj nowego studenta");
            return;
        }
        for (Map.Entry<String, StudentDto> entry : studentHashMap.entrySet()) {
            if (entry.getKey().equals(cardId)) {
                StudentDto student = entry.getValue();
                student.setCardId(entry.getKey());
                for (StudentDto student_in_list : students) {
                    if (student_in_list.getId().equals(student.getId())) {
                        return;
                    }
                }
                students.add(student);
            }
        }
    }
}

