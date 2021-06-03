package Client.Panel;

import Interfaces.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import org.omg.CORBA.Object;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerMainPanel {

    @FXML
    public VBox ClientPanel, ServerPanel;

    static ControllerMainPanel controllerMP;

    private static ControllerMainPanel getControllerMP() {
        if (controllerMP == null) {
            controllerMP = new ControllerMainPanel();
        }
        return controllerMP;
    }

    public static void inputMessage() {

    }

    public static void outputMessage() {

    }

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent) {
        ControllerCloudPanel leftPC = (ControllerCloudPanel) ClientPanel.getProperties().get("ctrl");
        ControllerCloudPanel rightPC = (ControllerCloudPanel) ServerPanel.getProperties().get("ctrl");

        if (leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No file selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        ControllerCloudPanel srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null) {
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFilename() != null) {
            srcPC = rightPC;
            dstPC = leftPC;
        }

        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFilename());
        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());

        try {
            Files.copy(srcPath, dstPath);
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't copy file", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void btnAboutAction(ActionEvent actionEvent) {
    }


}