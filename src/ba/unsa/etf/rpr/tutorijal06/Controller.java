package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.property.SimpleStringProperty;
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
    private SimpleStringProperty prezime;
    public String getTekst() {
        return prezime.get();
    }
    public Controller() {
        prezime = new SimpleStringProperty("");
    }
    public SimpleStringProperty tekstProperty() {
        return prezime;
    }
    public void setTekst(String tekst) {
        this.prezime.set(tekst);
    }
    private boolean validnoIme(String n) {
        if(n.length()<2 || n.length()>20)return false;
        for(int i=0;i<n.length();i++){
            if(n.charAt(i)<'A'|| n.charAt(i)>'Ž' || n.charAt(i)<'a' ||  n.charAt(i)>'ž') return false;
        }
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
                if (validnoPrezime(n)) {
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
        indeksField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanIndeks(n)) {
                    indeksField.getStyleClass().removeAll("poljeNijeIspravno");
                    indeksField.getStyleClass().add("poljeIspravno");
                    indeksValidan= true;
                } else {
                    indeksField.getStyleClass().removeAll("poljeIspravno");
                    indeksField.getStyleClass().add("poljeNijeIspravno");
                    indeksValidan = false;
                }
            }
        });
    }

}
