package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.company.Entity.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;

public class FXMLController {
    private ObservableList<Integer> day = FXCollections.observableArrayList();
    private ObservableList<String> month = FXCollections.observableArrayList();
    private ObservableList<Integer> year = FXCollections.observableArrayList();
    private ObservableList<Feedback> listFeedback = FXCollections.observableArrayList();
    private String transmission;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputCar;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ChoiceBox<Integer> dayChoiceBox;

    @FXML
    private ChoiceBox<String> monthChoiceBox;

    @FXML
    private ChoiceBox<Integer> yearChoiceBox;

    @FXML
    private RadioButton ARadioButton;

    @FXML
    private RadioButton MRadioButton;

    @FXML
    private Button clearBtn;

    @FXML
    private Button addBtn;

    @FXML
    private ListView<Feedback> ListView;

    @FXML
    void addBtnAction(ActionEvent event) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,yearChoiceBox.getValue());
        cal.set(Calendar.MONTH,getIntMonth(monthChoiceBox.getValue()));
        cal.set(Calendar.DAY_OF_MONTH,dayChoiceBox.getValue());

        Feedback feedback = new Feedback(inputName.getText().trim(), inputCar.getText().trim(), cal,
                descriptionTextArea.getText().trim(), transmission);
        listFeedback.add(feedback);
        ListView.setItems(listFeedback);
    }

    @FXML
    void clearBtnAction(ActionEvent event) {
        inputName.clear();
        inputCar.clear();
        descriptionTextArea.clear();
        dayChoiceBox.setValue(day.get(0));
        monthChoiceBox.setValue(month.get(0));
        yearChoiceBox.setValue(year.get(0));
        MRadioButton.setSelected(false);
        ARadioButton.setSelected(false);
    }

    @FXML
    void initialize() {
        initChoiceBox();
        ToggleGroup group = new ToggleGroup();
        MRadioButton.setToggleGroup(group);
        ARadioButton.setToggleGroup(group);
        initListBooks();

        MainApp.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("feedback.dat"))) {
                    oos.writeObject(new ArrayList<Feedback>(listFeedback));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    private void initChoiceBox() {
        for (int i = 1; i <= 30; i++)
            day.add(i);
        month.addAll("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октсябрь", "Ноябрь", "Декабрь");
        for (int i = 2015; i <= 2021; i++)
            year.add(i);

        dayChoiceBox.setItems(day);
        dayChoiceBox.setValue(day.get(0));
        monthChoiceBox.setItems(month);
        monthChoiceBox.setValue(month.get(0));
        yearChoiceBox.setItems(year);
        yearChoiceBox.setValue(year.get(0));
    }

    private void initListBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("feedback.dat"))) {
            ArrayList<Feedback> books = (ArrayList) ois.readObject();
            listFeedback.addAll(books);
            ListView.setItems(listFeedback);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void ARadioButtonAction(ActionEvent event) {
        transmission = "АКПП";
    }

    @FXML
    void MRadioButtonAction(ActionEvent event) {
        transmission = "МКПП";
    }

    private int getIntMonth(String month) {
        switch (month) {
            case "Январь":
                return 1;
            case "Февраль":
                return 2;
            case "Март":
                return 3;
            case "Апрель":
                return 4;
            case "Май":
                return 5;
            case "Июнь":
                return 6;
            case "Июль":
                return 7;
            case "Август":
                return 8;
            case "Сентябрь":
                return 9;
            case "Октсябрь":
                return 10;
            case "Ноябрь":
                return 11;
            case "Декабрь":
                return 12;
        }
        return 1;
    }
}
