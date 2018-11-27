package ba.unsa.etf.rpr.tutorijal06;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)


class MainTest {

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }
    @Test
    public void start (FxRobot robot) {
        TextField imeField= robot.lookup("#imeField").queryAs(TextField.class);
        assertEquals("", imeField.getText());
    }
    @Test
    public void nameFieldTest(FxRobot robot) {
        TextField nameField = robot.lookup("#imeField").queryAs(TextField.class);
        robot.clickOn("#imeField").write("Lamija");
        assertEquals("Lamija", nameField.getText());
    }
    @Test
    public void prezimeFieldTest(FxRobot robot) {
        TextField prezimeField = robot.lookup("#prezimeField").queryAs(TextField.class);
        robot.clickOn("#prezimeField").write("Fazlija");
        assertEquals("Fazlija", prezimeField.getText());
    }
    @Test
    public void indeksFieldTest(FxRobot robot) {
        TextField indeksField = robot.lookup("#indeksField").queryAs(TextField.class);
        robot.clickOn("#indeksField").write("17617");
        assertEquals("17617", indeksField.getText());
    }
    @Test
    public void datumFieldTest(FxRobot robot) {
        TextField datumField = robot.lookup("#datumField").queryAs(TextField.class);
        robot.clickOn("#datumField").write("07061996");
        assertEquals("07061996", datumField.getText());
    }
    @Test
    public void jmbgFieldTest(FxRobot robot) {
        TextField jmbg = robot.lookup("#jmbgField").queryAs(TextField.class);
        robot.clickOn("#jmbgField").write("0706996176304");
        assertEquals("0706996176304", jmbg.getText());
    }
    @Test
    public void mjestoRodTest(FxRobot robot) {
        ComboBox mjestoRod = robot.lookup("#mjestoRod").queryAs(ComboBox.class);
        robot.clickOn("#mjestoRod").write("Sarajevo");
        assertEquals("Sarajevo", mjestoRod.getEditor().getText());
    }
    @Test
    public void validacijajmbg(FxRobot robot) {
        TextField datumField = robot.lookup("#datumField").queryAs(TextField.class);
        robot.clickOn("#datumField").write("07061996");
        TextField jmbg = robot.lookup("#jmbgField").queryAs(TextField.class);
        robot.clickOn("#jmbgField").write("0706996176304");
        assertEquals(true,Controller.isJmbgValid(jmbg.getText()));
    }
    @Test
    public void jmbgValidationTest(FxRobot robot) {
        TextField jmbg = robot.lookup("#jmbgField").queryAs(TextField.class);
        robot.clickOn("#jmbgField").write("0706996176304");
        assertEquals(true, jmbg.getStyleClass().contains("poljeIspravno"));
    }
    @Test
    public void emailValidation(FxRobot robot) {
        TextField email = robot.lookup("#emailField").queryAs(TextField.class);
        robot.clickOn("#emailField").write("lamija.com");
        assertEquals(true, email.getStyleClass().contains("poljeNijeIspravno"));
    }
    @Test
    public void borackaPripadnostTest(FxRobot robot) {
        CheckBox priapdnost = robot.lookup("#pripadnost").queryAs(CheckBox.class);
        robot.clickOn("#pripadnost");
        assertEquals(true, priapdnost.isSelected());
    }
    @Test
    public void indeksTooltip(FxRobot robot) {
        TextField indeksField = robot.lookup("#indeksField").queryAs(TextField.class);
        robot.clickOn("#indeksField").write("17617");
        assertEquals("Unesite indeks", indeksField.getTooltip().getText());
    }
    @Test
    public void prezimeTooltip(FxRobot robot) {
        TextField prezimeField = robot.lookup("#prezimeField").queryAs(TextField.class);
        robot.clickOn("#prezimeField").write("Fazlija");
        assertEquals("Unesite vaše prezime", prezimeField.getTooltip().getText());
    }
    @Test
    public void imeTooltip(FxRobot robot) {
        TextField imeField = robot.lookup("#imeField").queryAs(TextField.class);
        robot.clickOn("#imeField").write("Lamija");
        assertEquals("Unesite vaše ime", imeField.getTooltip().getText());
    }


}