package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public ComboBox mjestoRod;
    public TextField imeField;
    public TextField prezimeField;
    public TextField indeksField;
    public TextField jmbgField;
    public TextField datumField;
    public TextField emailField;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    private boolean validnoImePrezime(String n) {
        if(n.length()<2 || n.length()>20)return false;
        for(int i=0;i<n.length();i++){
            if(n.charAt(i)<'A'|| n.charAt(i)>'Ž' || n.charAt(i)<'a' ||  n.charAt(i)>'ž') return false;
        }
        return !n.trim().isEmpty();
    }
    private boolean validanUnosDatum(String n){
        return !n.trim().isEmpty();
    }
    private boolean validanUnos(String n) {
        if(n.length()!=13)return false;
        return !n.trim().isEmpty();
    }
    private boolean validanUnosEmail(String n) {
        for(int i=0;i<n.length();i++)if(n.charAt(i)=='@')return true;
        return false;
    }
    private boolean validanIndeks(String n) {
        if(n.length()>5)return false;
        for(int i=0;i<n.length();i++) if(!(n.charAt(i)>='0'&& n.charAt(i)<'9')) return false;
        return !n.trim().isEmpty();
    }
    public boolean formularValidan() {return (imeValidno&&prezimeValidno && indeksValidan);}
    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno=false;
        indeksValidan=false;
        jmbgValidno=false;
        datumValidno=false;
        emailValidno=false;
        imeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        indeksField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        datumField.getStyleClass().add("poljeNijeIspravno");
        emailField.getStyleClass().add("poljeNijeIspravno");
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
        datumField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanUnosDatum(n)) {
                    datumField.getStyleClass().removeAll("poljeNijeIspravno");
                    datumField.getStyleClass().add("poljeIspravno");
                    datumValidno= true;
                } else {
                    datumField.getStyleClass().removeAll("poljeIspravno");
                    datumField.getStyleClass().add("poljeNijeIspravno");
                    datumValidno = false;
                }
            }
        });
        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanUnosEmail(n)) {
                    emailField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailField.getStyleClass().add("poljeIspravno");
                    emailValidno= true;
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    emailValidno = false;
                }
            }
        });
    }

    public void dugmeKliknuto(ActionEvent actionEvent){
        String rod=mjestoRod.getEditor().getText();
        String ime=imeField.getText();
        String prezime=prezimeField.getText();
        String datum=datumField.getText();
        String jmbg=jmbgField.getText();
        String email=emailField.getText();
        if(isValidEmail(email)==true){
            emailValidno=true;
            System.out.println(email);
        }
        else emailValidno=false;

        System.out.println(rod);
        System.out.println(ime+prezime+datum+jmbg+email);

    }
    public static boolean isValidEmail(String enteredEmail){
        String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_REGIX);
        Matcher matcher = pattern.matcher(enteredEmail);
        return ((!enteredEmail.isEmpty()) && (enteredEmail!=null) && (matcher.matches()));
    }
    /*public static boolean isValidEmailAddress(String email) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }*/


}
