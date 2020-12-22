package com.papdhut;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class PrimeController {
    @FXML
    public ImageView uploadImageViewFx;
    @FXML
    public TextArea textTaFx;

    public ITesseract instance;
    private File selectedFile;

    @FXML
    public void uploadClicked(MouseEvent mouseEvent) {
        FileChooser chooseFile = new FileChooser();
        chooseFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.png"),
                new FileChooser.ExtensionFilter("Image", "*.bmp"),
                new FileChooser.ExtensionFilter("Image", "*.gif")
        );

        if (uploadImageViewFx.getImage() == null) {
            selectedFile = chooseFile.showOpenDialog(null);
        }

        if (selectedFile != null) {
            String pathName = selectedFile.toURI().toString();
            Image image = new Image(pathName);
            uploadImageViewFx.setImage(image);
        }

    }
    @FXML
    public void extractClicked(MouseEvent mouseEvent) {
        if (uploadImageViewFx.getImage() != null) {
            instance = new Tesseract();
            try {
                String result = instance.doOCR(selectedFile);
                textTaFx.setText(result);

            } catch (TesseractException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Reading Image");
                alert.setHeaderText("Error reading the image at location : \n" +
                        selectedFile.toURI().toString());
                alert.setContentText("Try using image of better quality with less noise");
                alert.show();
            }
        } else {
            Alert noImage = new Alert(Alert.AlertType.INFORMATION);
            noImage.setTitle("Image Not Found");
            noImage.setHeaderText("Please upload Image to begin extraction process");
            noImage.show();
        }
    }
    @FXML
    public void copyClicked(MouseEvent mouseEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(textTaFx.getText());
        content.putHtml("");
        clipboard.setContent(content);
        textTaFx.copy();
    }
    @FXML
    public void removeClicked(MouseEvent mouseEvent) {
        if (uploadImageViewFx.getImage() != null) {
            uploadImageViewFx.setImage(null);
            textTaFx.setText(null);
        }
        if(textTaFx.getText() != null){
            textTaFx.setText(null);
        }
    }
}
