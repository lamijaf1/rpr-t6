package ba.unsa.etf.rpr.tutorijal06;

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

    public boolean formularValidan() {
        return imePrezimeValidno;
    }

}
