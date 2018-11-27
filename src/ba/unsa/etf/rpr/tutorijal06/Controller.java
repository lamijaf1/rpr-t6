package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
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

        return isDateValid(n);
    }
    private boolean validanUnos(String n) {
        if (n.length() != 13) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        }
        return !n.trim().isEmpty();
    }
    private boolean validanUnosEmail(String n) {
        //for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return isValidEmail(n);
    }
    private boolean validanIndeks(String n) {
        if (n.length() > 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) < '9')) return false;
        return !n.trim().isEmpty();
    }
    private boolean validanUnosTelefon(String n){

        return isValidTelephone(n);
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
        telefonField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (validanUnosTelefon(n)) {
                    telefonField.getStyleClass().removeAll("poljeNijeIspravno");
                    telefonField.getStyleClass().add("poljeIspravno");
                    telephoneValidno = true;
                } else {
                    telefonField.getStyleClass().removeAll("poljeIspravno");
                    telefonField.getStyleClass().add("poljeNijeIspravno");
                    telephoneValidno = false;
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
        if (isDateValid(datum)) {
            datumValidno = true;
        } else {
            datumValidno = false;
            datumField.getStyleClass().add("poljeNijeIspravno");
        }

        if (isJmbgValid(jmbg) && izdvojiDatum != "" && izdvojiDatum.equals(uporediSaJmbg)) {
            jmbgValidno = true;
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
            System.out.println("JMBG: " + jmbg + ", datum rođenja: " + datumZaIspis+", mjesto rodđenja: "+ rod);
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
        try {
            d = Integer.parseInt(dan);
            m = Integer.parseInt(mjesec);
            g = Integer.parseInt(godina);
        }catch (Exception ex){}
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
    public static boolean  isJmbgValid(String jmbg){
            int politickaRegija = (jmbg.charAt(7) - '0') * 10 + (jmbg.charAt(8) - '0');
            if (politickaRegija < 0 || politickaRegija > 96) return false;
            int jedinstveniBroj = (jmbg.charAt(9) - '0') * 100 + (jmbg.charAt(10) - '0') * 10 + (jmbg.charAt(11) - '0');
            if (!((jedinstveniBroj>  0 &&jedinstveniBroj <= 499)||(jedinstveniBroj>=500 &&jedinstveniBroj<=999))) return false;
            int kontrolnaCifra = 11 - ((7 * ((jmbg.charAt(0) - '0') + (jmbg.charAt(6) - '0')) + 6 * ((jmbg.charAt(1) - '0') + (jmbg.charAt(7) - '0')) + 5 * ((jmbg.charAt(2) - '0') + (jmbg.charAt(8) - '0')) + 4 * ((jmbg.charAt(3) - '0') + (jmbg.charAt(9) - '0')) + 3 * ((jmbg.charAt(4) - '0') + (jmbg.charAt(10) - '0')) + 2 * ((jmbg.charAt(5) - '0') + (jmbg.charAt(11) - '0'))) % 11);
            if (kontrolnaCifra > 9) kontrolnaCifra = 0;
            if (kontrolnaCifra != (jmbg.charAt(12) - '0')) return false;
            return true;
        }


}
