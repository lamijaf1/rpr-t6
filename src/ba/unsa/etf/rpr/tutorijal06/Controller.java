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

       /* imePrezimeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoImePrezime(n)) {
                    imePrezimeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imePrezimeField.getStyleClass().add("poljeIspravno");
                    imePrezimeValidno = true;
                } else {
                    imePrezimeField.getStyleClass().removeAll("poljeIspravno");
                    imePrezimeField.getStyleClass().add("poljeNijeIspravno");
                    imePrezimeValidno = false;
                }
            }
        });*/
    }

}
