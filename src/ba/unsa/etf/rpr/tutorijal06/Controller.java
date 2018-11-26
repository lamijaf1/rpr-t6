package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public ComboBox mjestoRod;
    public ChoiceBox status;
    public ChoiceBox godina;
    public ChoiceBox smjer;
    public CheckBox pripadnost;
    public TextField imeField;
    public TextField prezimeField;
    public TextField indeksField;
    public TextField jmbgField;
    public TextField datumField;
    public TextField emailField;
    public TextField ulicaField;
    public TextField telefonField;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    private  boolean telephoneValidno;
    public String uporediSaJmbg = "";
    public String datumZaIspis = "";
    private Controller novi;
    public boolean formularValidan() {
        return (imeValidno && prezimeValidno && indeksValidan && jmbgValidno && datumValidno && emailValidno && telephoneValidno);
    }

    private boolean validnoImePrezime(String n) {
        if (n.length() < 2 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Ž') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'ž'))
                return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean validanUnosDatum(String n) {
        return !n.trim().isEmpty();
    }

    private boolean validanUnos(String n) {
        if (n.length() != 13) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean validanUnosEmail(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;
    }

    private boolean validanIndeks(String n) {
        if (n.length() > 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) < '9')) return false;
        return !n.trim().isEmpty();
    }

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        jmbgValidno = false;
        datumValidno = false;
        emailValidno = false;
        telephoneValidno=false;
        imeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        indeksField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        datumField.getStyleClass().add("poljeNijeIspravno");
        emailField.getStyleClass().add("poljeNijeIspravno");
        telefonField.getStyleClass().add("poljeNijeIspravno");
        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validnoImePrezime(n)) {
                    imeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imeField.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
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
                    prezimeValidno = true;
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
                    indeksValidan = true;
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
                    jmbgValidno = true;
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
                    datumValidno = true;
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
                    emailValidno = true;
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    emailValidno = false;
                }
            }
        });
    }

    public void dugmeKliknuto(ActionEvent actionEvent) {
        String rod = mjestoRod.getEditor().getText();
        String ime = imeField.getText();
        String prezime = prezimeField.getText();
        String datum = datumField.getText();
        String jmbg = jmbgField.getText();
        String izdvojiDatum = "";
        if (jmbg.length() == 13) izdvojiDatum = jmbg.substring(0, 7);
        //System.out.println(izdvojiDatum);
        if (isDateValid(datum)) {
            //System.out.println(datum);
            datumValidno = true;
        } else {
            datumValidno = false;
            datumField.getStyleClass().add("poljeNijeIspravno");
        }

        if (izdvojiDatum != "" && izdvojiDatum.equals(uporediSaJmbg)) {
            jmbgValidno = true;
            // System.out.println(jmbg);
        } else {
            jmbgValidno = false;
            jmbgField.getStyleClass().add("poljeNijeIspravno");
        }
        String email = emailField.getText();
        if (isValidEmail(email)) {
            emailValidno = true;

        } else {
            emailValidno = false;
            emailField.getStyleClass().add("poljeNijeIspravno");
        }
        telephoneValidno=isValidTelephone(telefonField.getText());
        if(telephoneValidno)telefonField.getStyleClass().add("poljeIspravno");
        if (formularValidan()) {
            System.out.println("Student: " + ime + " " + prezime + " ( " + indeksField.getText() + " )");
            System.out.println("JMBG: " + jmbg + ", datum rođenja: " + datumZaIspis);
            System.out.println("Ulica stanovanja: " + ulicaField.getText() + ",broj telefona: " + telefonField.getText());
            System.out.println("Email adresa: " + email);
            System.out.println(status.getValue().toString() + " student, smjer: " + smjer.getValue().toString() + " godina: " + godina.getValue());
            if (pripadnost.isSelected()) System.out.println("Postoji neka od boračke pripadnosti");
            else System.out.println("Ne postoji nikakva boračka pripadnost");
        }
        if (!formularValidan()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno");
            alert.setHeaderText("Popunjeni formular nije validan");
            alert.setContentText("Podaci označeni crvenom bojom su pogrešni, ili nedostaju");
            alert.show();
        }


    }

    public static boolean isValidEmail(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    public static boolean isValidTelephone(String nmbr){
        //System.out.println(nmbr);
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Pattern pattern1 = Pattern.compile("\\d{3}-\\d{6}");
        Matcher matcher = pattern.matcher(nmbr);
        Matcher matcher1 = pattern1.matcher(nmbr);
        return (matcher.matches()|| matcher1.matches());
    }
    public boolean isDateValid(String s) {
        if(s.length()<8)return false;
        int d = 0, m = 0, g = 0;
        String dan = "", mjesec = "", godina = "";
        if (s.length() == 8) {
            dan = s.substring(0, 2);
            mjesec = s.substring(2, 4);
            godina = s.substring(4, 8);
        }
        else if (s.length() == 10) {
            if (s.charAt(2) == s.charAt(5) && (s.charAt(2) == '.' || s.charAt(2) == '/' | s.charAt(2) == '-')) {
                dan = s.substring(0, 2);
                mjesec = s.substring(3, 5);
                godina = s.substring(6, 10);
            }
        } else return false;
        d=Integer.parseInt(dan);
        m=Integer.parseInt(mjesec);
        g=Integer.parseInt(godina);
        datumZaIspis=""+ d+"/"+m+"/"+g;
        int novaG=Integer.parseInt(godina.substring(1,godina.length()));
        uporediSaJmbg=""+dan+mjesec+novaG;
        //System.out.println("Uporedi:"+ uporediSaJmbg);
        if(d==0 || m==0 || g==0 )return false;
        boolean dateIsValid = true;
        try {
            LocalDate.of(g, m, d);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;

    }

}
