package se2203.sdaher3_assignment1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SortingHubController implements Initializable {

    @FXML
    private ComboBox<String> algorithm;
    @FXML
    private Button reset;
    @FXML
    private Button sort;
    @FXML
    private Slider arraySize;
    @FXML
    private Label sizeDisplay;
    @FXML
    private Pane mainFrame;

    @FXML
    private int intArray[];
    @FXML
    private SortingStrategy sortingMethod;

    @FXML
    void displaySelectedItem() {
        sizeDisplay.setText(String.valueOf((int) arraySize.getValue()));
        intArray = new int[(int) arraySize.getValue()];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }

        for (int i = 0; i < intArray.length; i++) {
            arrayList.add(intArray[i]);
        }
        Collections.shuffle(arrayList);

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = arrayList.get(i); // passing thr values of arrlist to the array
        }
        updateGraph(intArray);

    }

    @FXML
    public void reset() {
        algorithm.getSelectionModel().select("Merge Sort");
        arrayList(64);
        arraySize.setValue(64);
        updateGraph(intArray);

    }

    public void arrayList(int size) {
          /* crate and arraylist and fill it up from 1 to the size given
        use the .shuffle then move the arraylist contents to the intarray field do that with afr loop
         */
        ArrayList<Integer> arrList = new ArrayList<>();
        intArray = new int[size];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }
        for (int i = 0; i < intArray.length; i++) {
            arrList.add(i + 1);
        }

        arraySize.setValue(Math.round(intArray.length));
        sizeDisplay.setText((String.valueOf((Math.round(intArray.length)))));

        Collections.shuffle(arrList);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = arrList.get(i); // passing thr values of arrList to the array
        }
    }

    @FXML
    public void updateGraph(int[] data) {

        Platform.runLater(() -> {
            mainFrame.getChildren().clear();
            for (int i = 0; i < data.length; i++) {
                double height = (data[i] * (mainFrame.getPrefHeight() / data.length));
                double width = (mainFrame.getPrefWidth() / data.length);
                double xV = (i * mainFrame.getPrefWidth() / data.length);
                double yV = (mainFrame.getPrefHeight() - height);

                Rectangle rectangle = new Rectangle(xV, yV, width - 2, height);
                rectangle.setFill(Color.RED);
                mainFrame.getChildren().add(rectangle);
            }
        });
    }

    @FXML
    public void setSortStrategy() {
        // create an object of the sorting type
        if (algorithm.getValue().equals("Merge Sort")) {
            sortingMethod = new MergeSort(intArray, this);
        } else if (algorithm.getValue().equals("Selection Sort")) {
            sortingMethod = new SelectionSort(intArray, this);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algorithm.getItems().addAll("Merge Sort", "Selection Sort");
        algorithm.getSelectionModel().select("Merge Sort");
        arraySize.setValue(64);
        arrayList(64);
        updateGraph(intArray);


    }

    public void sort() {
        setSortStrategy();
        new Thread(sortingMethod).start();
    }
}


