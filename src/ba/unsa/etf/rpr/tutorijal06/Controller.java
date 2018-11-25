package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    public TextField imeField;
    public TextField prezimeField;
    public TextField indeksField;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;

    private boolean validnoIme(String n) {
        return !n.trim().isEmpty();
    }
    private boolean validnoPrezime(String n) {
        return !n.trim().isEmpty();
    }
    private boolean validanIndeks(String n) {
        return !n.trim().isEmpty();
    }
    public boolean formularValidan() {
        return (imeValidno&&prezimeValidno && indeksValidan);
    }
    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno=false;
        imeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        indeksField.getStyleClass().add("poljeNijeIspravno");

        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoIme(n)) {
                    imeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imeField.getStyleClass().add("poljeIspravno");
                    imeValidno= true;
                } else {
                    imeField.getStyleClass().removeAll("poljeIspravno");
                    imeField.getStyleClass().add("poljeNijeIspravno");
                    imeValidno = false;
                }
            }
        });
        prezimeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoIme(n)) {
                    prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeField.getStyleClass().add("poljeIspravno");
                    prezimeValidno= true;
                } else {
                    prezimeField.getStyleClass().removeAll("poljeIspravno");
                    prezimeField.getStyleClass().add("poljeNijeIspravno");
                    prezimeValidno = false;
                }
            }
        });
    }

}
