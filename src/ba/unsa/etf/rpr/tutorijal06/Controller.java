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
    public TextField jmbgField;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
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
    private boolean validnoImePrezime(String n) {
        if(n.length()<2 || n.length()>20)return false;
        for(int i=0;i<n.length();i++){
            if(n.charAt(i)<'A'|| n.charAt(i)>'Ž' || n.charAt(i)<'a' ||  n.charAt(i)>'ž') return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean validanUnos(String n) {
        return !n.trim().isEmpty();
    }
    private boolean validanIndeks(String n) {
        if(n.length()>5)return false;
        for(int i=0;i<n.length();i++){
            if(!(n.charAt(i)>='0'&& n.charAt(i)<'9')) return false;
        }
        return !n.trim().isEmpty();
    }
    public boolean formularValidan() {
        return (imeValidno&&prezimeValidno && indeksValidan);
    }
    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno=false;
        indeksValidan=false;
        imeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        indeksField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.getStyleClass().add("poljeNijeIspravno");

        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoImePrezime(n)) {
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
                if (validnoImePrezime(n)) {
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
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanUnos(n)) {
                    jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    jmbgValidno= true;
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNijeIspravno");
                    jmbgValidno = false;
                }
            }
        });
    }

}
