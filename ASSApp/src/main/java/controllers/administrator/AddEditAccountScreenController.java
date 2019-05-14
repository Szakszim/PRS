package controllers.administrator;

import controllers.general.LoginScreenController;
import controllers.general.NewPopupScreenController;
import dtos.base.AccountDto;
import dtos.base.AddressDto;
import dtos.base.ContactDto;
import dtos.base.PersonDto;
import entities.Address;
import entities.Contact;
import entities.Dictionary;
import entities.Person;
import enums.AccountType;
import enums.DictionaryType;
import enums.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Setter;
import providers.DictionaryProvider;
import requests.*;
import utils.DateUtil;
import utils.StringUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEditAccountScreenController implements Initializable {

    public static final String RESIGN_WARNING_QUESTION = "Unsaved changes will be lost. Are you sure you want to resign?";

    @FXML
    private Button signUpButton;

    @FXML
    private Button signUpLabelButton;

    @FXML
    private Button remindButton;

    @FXML
    private Button remindLabelButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cancelLabelButton;

    @FXML
    private Label title;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    private Spinner<AccountType> accountType;

    @FXML
    private TextField firstName;

    @FXML
    private TextField secondName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pesel;

    @FXML
    private DatePicker birthdayDate;

    @FXML
    private TextField nip;

    @FXML
    private TextField country;

    @FXML
    private TextField city;

    @FXML
    private Spinner<Dictionary> streetType;

    @FXML
    private TextField street;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField localNumber;

    @FXML
    private TextField postCode;

    @FXML
    private TextField countryCodePhone;

    @FXML
    private TextField codePhone;

    @FXML
    private TextField phone;

    @FXML
    private TextField countryCodeFax;

    @FXML
    private TextField codeFax;

    @FXML
    private TextField fax;
    ;

    @FXML
    private TextField countryCode;

    @FXML
    private TextField cellphone;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    public ObservableList<AccountType> list = FXCollections.observableArrayList();
    public ObservableList<Dictionary> dictionaryPositionList = FXCollections.observableArrayList();

    private AddressDto addressDto;
    private ContactDto contactDto;
    private PersonDto personDto;
    private AccountDto accountDto;

    private AddressRequest addressRequest;
    private ContactRequest contactRequest;
    private PersonRequest personRequest;
    private AccountRequest accountRequest;
    private AuthenticationRequest authenticationRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countryCodePhone.textProperty().addListener((observable, oldValue, newValue) -> {
            countryCodeFax.setText(newValue);
            countryCode.setText(newValue);

        });
        countryCodeFax.textProperty().addListener((observable, oldValue, newValue) -> {
            countryCodePhone.setText(newValue);
            countryCode.setText(newValue);
        });
        countryCode.textProperty().addListener((observable, oldValue, newValue) -> {
            countryCodePhone.setText(newValue);
            countryCodeFax.setText(newValue);
        });

        codePhone.textProperty().addListener((observable, oldValue, newValue) -> {
            codeFax.setText(newValue);
        });
        codeFax.textProperty().addListener((observable, oldValue, newValue) -> {
            codePhone.setText(newValue);
        });

        list = FXCollections.observableArrayList(AccountType.CUSTOMER);
        SpinnerValueFactory<AccountType> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<AccountType>(list);
        valueFactory.setValue(AccountType.CUSTOMER);
        accountType.setValueFactory(valueFactory);

        DictionaryProvider dictionaryProvider = new DictionaryProvider();
//        dictionaryProvider.getEntry(DictionaryType.STREET_TYPE).getDictionary();//.findDictionaryByBusinessCode("000");
        dictionaryPositionList = FXCollections.observableArrayList(dictionaryProvider.getEntry(DictionaryType.STREET_TYPE).getDictionary());
        SpinnerValueFactory<Dictionary> streetTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Dictionary>(dictionaryPositionList);
        streetTypeValueFactory.setValue(dictionaryPositionList.get(0));
        streetType.setValueFactory(streetTypeValueFactory);

        addressDto = new AddressDto();
        contactDto = new ContactDto();
        personDto = new PersonDto();
        accountDto = new AccountDto();

        addressRequest = new AddressRequest();
        contactRequest = new ContactRequest();
        personRequest = new PersonRequest();
        accountRequest = new AccountRequest();
        authenticationRequest = new AuthenticationRequest();

    }

    @FXML
    void onSignUp() {
        collectData();
        if (validate()) {
            saveData();
            goToLoginScreenWithMessage(MessageType.CONFIRMATION, "Your request has been sent successfully! Wait for mail and confirm your registration. Have a nice day!");
        }
    }

    @FXML
    void onResign() {
        Boolean answer = callPopup(MessageType.WARNING, RESIGN_WARNING_QUESTION);
        if (answer == Boolean.TRUE) {
            goToLoginScreenWithMessage(null, null);
        }
    }


    private void goToLoginScreenWithMessage(MessageType messageType, String message) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/LoginScreen.fxml"));

        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {

            Parent innerRoot = innerLoader.load();
            LoginScreenController loginScreenController = innerLoader.getController();

            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            loginScreenController.setScene(scene);
            loginScreenController.setStage(stage);
            stage.show();
            if (messageType != null && message != null) {
                loginScreenController.callPopup(messageType, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void collectData() {
        collectAddressData();
        collectContactData();
        collectPersonData();
        collectAccountData();
    }

    private void collectAddressData() {
        addressDto.setCountry(country.getText());
        addressDto.setCity(city.getText());
        addressDto.setStreetType(streetType.getValue());
        addressDto.setStreet(street.getText());
        addressDto.setHouseNumber(houseNumber.getText());
        addressDto.setLocalNumber(localNumber.getText());
        addressDto.setPostCode(postCode.getText());
    }

    private void collectContactData() {
        contactDto.setCountryCode(countryCode.getText());
        contactDto.setCode(codePhone.getText());
        contactDto.setPhone(phone.getText());
        contactDto.setFax(fax.getText());
        contactDto.setEmail(email.getText());
        contactDto.setCellphone(cellphone.getText());
    }

    private void collectPersonData() {
        personDto.setFirstName(firstName.getText());
        personDto.setSecondName(secondName.getText());
        personDto.setSurname(lastName.getText());
        personDto.setPesel(pesel.getText());
        personDto.setBirthDate(DateUtil.toDate(birthdayDate.getValue()));
        personDto.setNip(nip.getText());
    }

    private void collectAccountData() {
        accountDto.setEmail(email.getText());
        accountDto.setLogin(username.getText());
        accountDto.setPassword(password.getText());
        accountDto.setAccountType(accountType.getValue());
    }

    private Boolean validate() {
        if (checkAccountData()) {
            if (checkPasswordsCorrectness()) {
                if (checkPersonData()) {
                    if (checkAddressData()) {
                        if (checkContactData()) {
                            if (checkUsernameAvailability()) {
                                if (checkEmailAvailability()) {
                                    return Boolean.TRUE;
                                }
                            }
                        }
                    }
                }
            }
        }

        return Boolean.FALSE;
    }

    private Boolean checkEmailAvailability() {
        if (authenticationRequest.checkEmailAvailability(email.getText())) {
            return Boolean.TRUE;
        }
        callPopup(MessageType.ERROR, "This mail is in use!");
        return Boolean.FALSE;
    }

    private Boolean checkUsernameAvailability() {
        if (authenticationRequest.checkUsernameAvailability(username.getText())) {
            return Boolean.TRUE;
        }
        callPopup(MessageType.ERROR, "This username is not available!"); //TODO
        return Boolean.FALSE;
    }

    private Boolean checkPasswordsCorrectness() {
        if (password.getText().equals(repeatPassword.getText())) {
            return Boolean.TRUE;
        }
        callPopup(MessageType.ERROR, "Passwords are not the same!"); //TODO
        return Boolean.FALSE;
    }

    private Boolean checkAddressData() {
        String message = "You have to enter a country!";
        if (!StringUtil.isEmpty(addressDto.getCountry())) {
            if (!StringUtil.isEmpty(addressDto.getCity())) {
                if (addressDto.getStreetType() != null) {
                    if (addressDto.getStreetType().getValue() != "bez ulicy") {
                        if (StringUtil.isEmpty(addressDto.getStreet())) {
                            message = "You have to enter a street name";
                            callPopup(MessageType.ERROR, message);
                            return Boolean.FALSE;
                        }
                    }
                    if (!StringUtil.isEmpty(addressDto.getHouseNumber())) {
                        if (!StringUtil.isEmpty(addressDto.getPostCode())) {
                            return Boolean.TRUE;
                        } else {
                            message = "Tou have to enter a post code!";
                        }
                    } else {
                        message = "Tou have to enter a house number!";
                    }
                } else {
                    message = "Tou have to enter a street type!";
                }
            } else {
                message = "Tou have to enter a city!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkContactData() {
        String message = "You have to enter one or more contact!";
        if (contactDto.getCellphone() != null || contactDto.getPhone() != null || contactDto.getFax() != null) {
            if (contactDto.getCode() != null && contactDto.getCountryCode() != null) {
                return Boolean.TRUE;
            }
            message = "Tou have to enter a country code or code!";
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkPersonData() {
        String message = "You have to enter a first name!";
        if (!StringUtil.isEmpty(personDto.getFirstName())) {
            if (!StringUtil.isEmpty(personDto.getSurname())) {
                if (!StringUtil.isEmpty(personDto.getPesel())) {
                    if (personDto.getBirthDate() != null) {
                        return Boolean.TRUE;
                    } else {
                        message = "You have to enter a birthday date!";
                    }
                } else {
                    message = "You have to enter a pesel!";
                }
            } else {
                message = "You have to enter a surname!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkAccountData() {
        String message = "You have to enter a email!";

        if (!StringUtil.isEmpty(accountDto.getEmail())) {
            if (!StringUtil.isEmpty(accountDto.getLogin())) {
                if (!StringUtil.isEmpty(accountDto.getPassword())) {
                    if (accountDto.getAccountType() != null) {
                        return Boolean.TRUE;
                    }else{
                        message = "You have to select a account type!";
                    }
                } else {
                    message = "You have to enter a password!";
                }
            }else{
                message = "You have to enter a login!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private void saveData() {
        Address address = addressRequest.save(addressDto.toEntity());
        Contact contact = contactRequest.save(contactDto.toEntity());
        personDto.setAddress(address);
        personDto.setContact(contact);
        Person person = personRequest.save(personDto.toEntity());
        accountDto.setPerson(person);
        accountRequest.save(accountDto.toEntity());
    }

    public Boolean callPopup(MessageType messageType, String message) {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/NewPopupScreen.fxml"));
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NewPopupScreenController popupScreenController;
        popupScreenController = innerLoader.getController();

        Scene popupScene = new Scene(root);
        Stage newWindow = new Stage();

        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);

//       this.stage.getScene();
        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setScene(popupScene);
        newWindow.setResizable(false);

        popupScreenController.setScene(popupScene);
        popupScreenController.setStage(newWindow);

        popupScreenController.setMessage(message);

        switch (messageType) {
            case ERROR:
                popupScreenController.setImage("icons/error_symbol_icon.png");
                popupScreenController.disableSecondButton();
                break;
            case WARNING:
                popupScreenController.setImage("icons/warning_symbol_icon.png");
//                popupScreenController.disableSecondButton();
                break;
            case CONFIRMATION:
                popupScreenController.setImage("icons/confirmation_symbol_icon.png");
                popupScreenController.disableSecondButton();
                break;
            case QUESTION:
                popupScreenController.setImage("icons/error_symbol_icon.png");
                break;
        }

        newWindow.showAndWait();

        return popupScreenController.getAnswer();
    }
}
