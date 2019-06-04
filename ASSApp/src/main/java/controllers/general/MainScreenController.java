package controllers.general;

import context.ContextHandler;
import controllers.cardreader.CardReaderThread;
import controllers.temporary.Lesson;
import dtos.CardDto;
import dtos.LectureDto;
import dtos.PresenceOnLectureDto;
import dtos.StudentDto;
import entities.Lecture;
import entities.PresenceOnLecture;
import entities.Student;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.DateUtil.convertDateFormat;

public class MainScreenController implements Initializable {

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
    private Button cancelButton;

    @FXML
    private Button openLastButton;


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
    private TableColumn<StudentDto, String> wasLateColumn;


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
    private ChoiceBox<String> subjectStatsChoiceBox;

    @FXML
    private ChoiceBox<String> studentStatsChoiceBox;

    @FXML
    private TextField presentTextField;

    @FXML
    private TextField absentTextField;

    @FXML
    private TextField totalPresenceTextField;

    @FXML
    private PieChart diagram;

    //TODO: to trzeba będzie zmienić na StudentDto oraz PresenceOnLectureDto
    private static HashMap<String, StudentDto> studentHashMap;
    private static ObservableList<StudentDto> students;
    private static ObservableList<Lesson> lessons;
    Integer temporaryLectureId = 0;
    private static Boolean areStudentsLate;

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

        areStudentsLate = false;

        initializeRequests();
        initializeStudentsData();
        initializeColumns();
        initializeChoiceboxes();
        initializeObservableArrays();
        initializeLogged();
        initializeHistory();

        students.addListener((InvalidationListener) observable -> presenceTable.setItems(students));
        lessons.addListener((InvalidationListener) observable -> historyTable.setItems(lessons));

        filterHistoryTable();
        configureHistoryTable();
        filterStudentStatistics();

        cancelButton.setDisable(Boolean.TRUE);
//        cancelButton.setVisible(Boolean.FALSE);
        datePicker.setValue(DateUtil.toLocalDate(new Date()));

    }

    private void initializeHistory() {


        HashSet<String> dateHourRoom = new HashSet<>();
        List<PresenceOnLecture> frequency;
        Lecture lecture;
        List<PresenceOnLecture> presenceOnLectureDtos = presenceOnLectureRequest.findAllByLecturer_Id(ContextHandler.getLecturerDto().getId());
        for (PresenceOnLecture p : presenceOnLectureDtos) {
            if (!dateHourRoom.contains(DateUtil.convertDateFormat(p.getPresenceDate().getTime()) + p.getHourTime() + p.getRoom())) {
                frequency = presenceOnLectureRequest.findAllByPresenceDateAndHourTimeAndRoom(DateUtil.convertDateFormat(p.getPresenceDate().getTime()), p.getHourTime(), p.getRoom());
                lecture = p.getLecture();
                if (lecture.getLectureType().getLectureTypeName().equals("Wykład")) {
                    lessons.add(new Lesson(lecture.getLectureName() + " - " + "Semestr " + lecture.getDeanGroup().getSemester() + " - " + lecture.getLectureType().getLectureTypeName(), p.getRoom(), p.getHourTime(), DateUtil.convertDateFormat(p.getPresenceDate().getTime()), frequency.size()));
                } else {
                    lessons.add(new Lesson(lecture.getLectureName() + " - " + lecture.getDeanGroup().getDeanName() + " - " + lecture.getLectureType().getLectureTypeName(), p.getRoom(), p.getHourTime(), DateUtil.convertDateFormat(p.getPresenceDate().getTime()), frequency.size()));
                }
                dateHourRoom.add(DateUtil.convertDateFormat(p.getPresenceDate().getTime()) + p.getHourTime() + p.getRoom());
            }
        }
        historyTable.setItems(lessons);
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

    private void initializeChoiceboxes() {
        initializeLists();

        subjectChoiceBox.getItems().addAll(subjectList);
        roomChoiceBox.getItems().addAll(roomList);
        hourChoiceBox.getItems().addAll(hourList);
        subjectHistoryChoiceBox.getItems().addAll(subjectList);
        dateHistoryChoiceBox.getItems().addAll(dateList);
        subjectStatsChoiceBox.getItems().addAll(subjectList);
        subjectHistoryChoiceBox.getSelectionModel().select(0);
        dateHistoryChoiceBox.getSelectionModel().select(0);

    }

    private void initializeLists() {
        roomList = new ArrayList<>(Arrays.asList(
                "E-210", "E-217", "E-110", "BM-A1", "BM-A2", "M-215", "M-216", "CW-4"));
        hourList = new ArrayList<>(Arrays.asList(
                "8:00", "9:45", "11:45", "13:30", "15:10", "16:50", "18:30"));

        initializeLectureNamesList();
        initializeDateList();
    }

    private void filterStudentStatistics() {
        subjectStatsChoiceBox.valueProperty().addListener((Observable) -> {
            filterSubjectStatistics();
        });
        studentStatsChoiceBox.valueProperty().addListener((Observable) -> {
            filterStudentsStatistics();
        });
    }

    private void filterSubjectStatistics() {
        if (subjectStatsChoiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        if (!subjectStatsChoiceBox.getValue().equals("")) {
            //TODO optymalizacja metody
            initializeStudentsListBySelectedLecture();
        }
    }

    private void initializeStudentsListBySelectedLecture() {
        String subject = subjectStatsChoiceBox.getValue();
        String[] splitted = subject.split(" - ");
        List<PresenceOnLecture> presenceOnLecturesList = new ArrayList<>();
        List<LectureDto> lectureDtoList = lectureRequest.findAllByLecturer_Id(ContextHandler.getLecturerDto().getId());
        Integer lectureId = 0;
        for (LectureDto l : lectureDtoList) {
            if (l.getLectureName().equals(splitted[0]) && l.getLectureType().getLectureTypeName().equals(splitted[2])) {
                lectureId = l.getId();
                temporaryLectureId = l.getId();
                break;
            }
        }
        presenceOnLecturesList = presenceOnLectureRequest.findAllByLecture_Id(lectureId);
        ObservableList<String> students = FXCollections.observableArrayList();
        students.add("");
        for (PresenceOnLecture p : presenceOnLecturesList) {
            String nameAndSurname = p.getStudent().getFirstName() + " " + p.getStudent().getLastName();
            if (!students.contains(nameAndSurname)) {
                students.add(nameAndSurname);
            }
        }
        studentStatsChoiceBox.setItems(students);
    }

    private void filterStudentsStatistics() {
        if (studentStatsChoiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        if (!studentStatsChoiceBox.getValue().equals("")) {
            getStudentPresence(studentStatsChoiceBox.getValue());
        }
    }

    private void getStudentPresence(String nameAndSurname) {
        String name = nameAndSurname.split(" ")[0];
        String surname = nameAndSurname.split(" ")[1];
        Student student = studentRequest.findByFirstNameAndLastName(name, surname);

        Integer frequencyCounter =0;
        Integer absenceCounter =0;
        Integer totalPresenceCounter =0;

        frequencyCounter = presenceOnLectureRequest.findAllByStudent_IdAndLecture_Id(student.getId(), temporaryLectureId).size();

        List<PresenceOnLecture> totalPresenceList = presenceOnLectureRequest.findAllByLecture_Id(temporaryLectureId);
        Set<String> uniqueDates = new HashSet<>();
        for (PresenceOnLecture p : totalPresenceList) {
            uniqueDates.add(p.getPresenceDate().toString() + p.getHourTime());
        }
        totalPresenceCounter = uniqueDates.size();

        if(!(totalPresenceCounter < frequencyCounter)){
            absenceCounter = totalPresenceCounter - frequencyCounter;
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Obecność", frequencyCounter),
                        new PieChart.Data("Nieobecność", absenceCounter));

        diagram.setData(pieChartData);

        presentTextField.setText(Integer.toString(frequencyCounter));
        absentTextField.setText(Integer.toString(absenceCounter));
        totalPresenceTextField.setText(Integer.toString(totalPresenceCounter));
    }

    private void initializeDateList() {
        dateList = new ArrayList<>();
        dateList.add("");
        Set<String> dates = new HashSet<>();
        for (PresenceOnLecture presenceOnLecture : presenceOnLectureRequest.findAllByLecturer_Id(ContextHandler.getLecturerDto().getId())) {
            String formattedDate = convertDateFormat(presenceOnLecture.getPresenceDate().getTime());
            dates.add(formattedDate);
        }
        dateList.addAll(dates);
    }

    private void initializeLectureNamesList() {
        subjectList = new ArrayList<>();
        subjectIdList = new ArrayList<>();
        subjectList.add("");
        List<LectureDto> lectureNames = lectureRequest.findAllByLecturer_Id(ContextHandler.getLecturerDto().getId());
        for (LectureDto lectureDto : lectureNames) {
            if (lectureDto.getLectureType().getLectureTypeName().equals("Wykład")) {
                subjectList.add(lectureDto.getLectureName() + " - " + "Semestr " + lectureDto.getDeanGroup().getSemester() + " - " + lectureDto.getLectureType().getLectureTypeName());
            } else {
                subjectList.add(lectureDto.getLectureName() + " - " + lectureDto.getDeanGroup().getDeanName() + " - " + lectureDto.getLectureType().getLectureTypeName());
            }
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
        wasLateColumn.setCellValueFactory(cellData -> {
            boolean indicator = cellData.getValue().getIsLate();
            String indicatorAsString;
            indicatorAsString = indicator == Boolean.TRUE ? "TAK" : "NIE";
//            if (indicator == true) {
//                indicatorAsString = "Male";
//            } else {
//                indicatorAsString = "Female";
//            }
            return new ReadOnlyStringWrapper(indicatorAsString);
        });
    }

    private void initializeStudentsData() {
        List<CardDto> cards = cardRequest.getAll();
        for (CardDto c : cards) {
            studentHashMap.put(c.getId(), new StudentDto(c.getStudent()));
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
            filtered = filtered.filtered(lesson -> subject.contains(lesson.getSubject()));
        }

        historyTable.setItems(filtered);
    }

    private void configureHistoryTable() {
        historyTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/general/DetailedStudentsPresenceScreen.fxml"));
                    Parent root = loader.load();

                    DetailedStudentsPresenceController controller = loader.getController();
                    Lesson lesson = (Lesson) historyTable.getSelectionModel().getSelectedItem();
                    controller.initData(lesson.getDate(), lesson.getHour(), lesson.getRoom());

                    stage = new Stage();
                    stage.setTitle("Przegląd szczegółowy");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        });
    }

    @FXML
    void startListeningButtonAction() {

        if (!checkIfFieldsAreEmpty()) {

            listeningButtonPressed = !listeningButtonPressed;

            if (listeningButtonPressed) {

//                cancelButton.setVisible(Boolean.TRUE);
                cancelButton.setDisable(Boolean.FALSE);

//                openLastButton.setVisible(Boolean.FALSE);
                openLastButton.setDisable(Boolean.TRUE);

                setDisableFields(true);
                checkIfPresenceExists();
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

//                Lesson lesson = new Lesson(subjectChoiceBox.getValue(), roomChoiceBox.getValue(), hourChoiceBox.getValue(), date, presenceTable.getItems().size());
                lessons.clear();
                initializeHistory();
                addPresenceToDatabase();

//                lessons.add(lesson);

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
        PresenceOnLectureDto presenceOnLectureDto = new PresenceOnLectureDto();
        presenceOnLectureDto.setLecture(lectureRequest.findById(subjectIdList.get(subjectChoiceBox.getSelectionModel().getSelectedIndex() - 1)).toEntity());
        presenceOnLectureDto.setLecturer(ContextHandler.getLecturerDto().toEntity());
        presenceOnLectureDto.setPresenceDate(DateUtil.toDate(datePicker.getValue()));
        presenceOnLectureDto.setHourTime(hourChoiceBox.getValue());
        presenceOnLectureDto.setRoom(roomChoiceBox.getValue());
        presenceOnLectureDto.setWasLate(areStudentsLate);
        for (StudentDto s : students) {
            presenceOnLectureDto.setStudent(s.toEntity());
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
                student.setIsLate(areStudentsLate);
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

    private void checkIfPresenceExists() {
        Date date = DateUtil.toDate(datePicker.getValue());
        List<PresenceOnLectureDto> allPresences = presenceOnLectureRequest.getAll();
        String room = roomChoiceBox.getValue();
        String hourTime = hourChoiceBox.getValue();
        Lecture lecture = lectureRequest.findById(subjectIdList.get(subjectChoiceBox.getSelectionModel().getSelectedIndex() - 1)).toEntity();
        for (PresenceOnLectureDto p : allPresences) {
            if (p.getPresenceDate().equals(date) && p.getRoom().equals(room) && p.getLecture().equals(lecture) && p.getHourTime().equals(hourTime)) {
                MainScreenController.areStudentsLate = true;
                return;
            }
        }
        MainScreenController.areStudentsLate = false;
    }


    public void onRefreshPresenceClick(ActionEvent actionEvent) {
        lessons.clear();
        initializeHistory();
    }

    @FXML
    public void cancelButtonAction() {
        lessons.clear();
        startListeningButton.setText("Sprawdź obecność");

        cancelButton.setDisable(Boolean.TRUE);
//        cancelButton.setVisible(Boolean.FALSE);

//        openLastButton.setVisible(Boolean.TRUE);
        openLastButton.setDisable(Boolean.FALSE);

        setDisableFields(false);
        CardReaderThread.setRunning(new AtomicBoolean(Boolean.FALSE));

        listeningButtonPressed = !listeningButtonPressed;

    }

    @FXML
    public void openLastButtonAction() {
        if(!historyTable.getItems().isEmpty()){
            Lesson lesson = (Lesson) historyTable.getItems().get(0);
            subjectChoiceBox.setValue(lesson.getSubject());
            roomChoiceBox.setValue(lesson.getRoom());
            hourChoiceBox.setValue(lesson.getHour());
            datePicker.setValue(DateUtil.toLocalDate(new Date(lesson.getDate().replace('-', '/'))));
        }
    }
}

