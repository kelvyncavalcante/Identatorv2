package application.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLIndexController implements Initializable {

	@FXML
	private TextArea txaData, txaDataOutput;

	@FXML
	private Label lbTextCopy, lbAction, lbAction2, lbPath;

	@FXML
	private Button btnIndent, btnCopy;

	@FXML
	private ImageView btn_close1, btn_source1, btn_author, btn_minimize, btn_subMenu_Indent, btn_subMenu_SQL;

	@FXML
	private AnchorPane h_source, h_idented, h_author, h_idented_check, h_generate, h_subMenu;

	@FXML
	private CheckBox cbNewFileIndent, cbNewFileSQLGenerate;

	@FXML
	private ToggleGroup grupoSQL;

	@FXML
	private RadioButton rbSelect, rbInsert, rbUpdate, rbDelete;

	private String dataCopy = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void close() {
		Stage stage = (Stage) btn_close1.getScene().getWindow();
		stage.close();
	}

	@FXML
	void subMenuButtonAction(MouseEvent event) {

		h_subMenu.setVisible(false);

		if (event.getTarget() == btn_subMenu_Indent) {
			h_source.setVisible(true);
		} else if (event.getTarget() == btn_subMenu_SQL) {
			h_generate.setVisible(true);
		}
	}

	@FXML
	void topBarButtonAction(MouseEvent event) {

		if (event.getTarget() == btn_source1) {
			h_source.setVisible(false);
			h_author.setVisible(false);
			h_idented.setVisible(false);
			h_generate.setVisible(false);
			h_idented_check.setVisible(false);
			h_subMenu.setVisible(true);
		} else if (event.getTarget() == btn_author) {
			h_author.setVisible(true);
			h_source.setVisible(false);
			h_idented.setVisible(false);
			h_idented_check.setVisible(false);
			h_subMenu.setVisible(false);
		} else if (event.getTarget() == btn_minimize) {
			h_author.setVisible(false);
			h_source.setVisible(false);
			h_idented.setVisible(false);
			h_idented_check.setVisible(false);
			h_subMenu.setVisible(false);
			h_source.setVisible(false);
			h_generate.setVisible(false);
		} else
			close();
	}

	@FXML
	void IndentButtonAction() {

		String dataProcessed = processData(txaData.getText());

		if (dataProcessed.isEmpty() || dataProcessed == null)
			return;

		dataCopy = dataProcessed;
		txaData.clear();

		h_source.setVisible(false);

		if (isCheckNewFileIndentSelected()) {
			h_idented_check.setVisible(true);
			h_idented.setVisible(false);
		} else {
			txaDataOutput.setText(dataProcessed);
			lbAction.setText("INDENTADO!");
			lbPath.setText("C:\\temp_Indentator\\source_indented.txt");
			h_idented.setVisible(true);
			h_idented_check.setVisible(false);
			lbTextCopy.setText("");

		}
	}

	@FXML
	void generateButtonAction(ActionEvent event) {

		String sqlData = getRadioButtonSelected();
		dataCopy = sqlData;

		if (isCheckNewFileSQLGenerateSelected()) {
			h_idented_check.setVisible(true);
			h_idented.setVisible(false);
			lbAction.setText("GERADO!");
			lbPath.setText("C:\\temp_Indentator\\source_SQL.txt");
			generateFile(sqlData, "C:\\temp_Indentator", "source_SQL.txt");
		} else {
			txaDataOutput.setText(sqlData);
			lbAction2.setText("GERADO!");
			h_idented.setVisible(true);
			h_idented_check.setVisible(false);
			lbTextCopy.setText("");

		}

	}

	@FXML
	private boolean copyButtonAction(ActionEvent event) {

		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(dataCopy);
		lbTextCopy.setText("CÓDIGO COPIADO!");
		return clipboard.setContent(content);

	}

	private String processData(String data) {
		boolean containsParentheses = (data.contains("(") || data.contains(")"));
		// boolean containsBraces = (data.contains("{"));

		if (containsParentheses) {
			data = data.replace("(", "( ");
			data = data.replace(")", " )");
		}

		// if (containsBraces) {
		// data = data.replace("{", "\n{");
		// }

		generateFile(data, "C:\\temp_Indentator", "source_indented.txt");

		// System.out.println(data);

		return data;

	}

	private void generateFile(String data, String path, String nameArchive) {
		if (isCheckNewFileIndentSelected() || isCheckNewFileSQLGenerateSelected()) {
			try {
				File folder = new File(path);
				File archive = new java.io.File(folder, nameArchive);

				// System.out.println("Pasta criada ? " + (file.exists() ? "SIM" : "NÃO"));
				if (!folder.exists()) {
					folder.mkdir();
					// System.out.println("Pasta temp_Indentator criada!");
				}

				try {
					boolean statusArq = archive.createNewFile();
					// System.out.println("statusArq[" + statusArq + "]");

					FileWriter fileWriter = new FileWriter(archive, false);
					PrintWriter printWriter = new PrintWriter(fileWriter);

					printWriter.println(data);
					fileWriter.close();
					printWriter.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isCheckNewFileIndentSelected() {
		System.out.println("New File Indent Checked ? = " + cbNewFileIndent.selectedProperty().getValue());
		return cbNewFileIndent.selectedProperty().getValue();
	}

	private boolean isCheckNewFileSQLGenerateSelected() {
		System.out.println("New File SQL Generate Checked ? = " + cbNewFileSQLGenerate.selectedProperty().getValue());
		return cbNewFileSQLGenerate.selectedProperty().getValue();
	}

	String getRadioButtonSelected() {

		String sqlData = "";
		SQL sql = new SQL();
		RadioButton radio = (RadioButton) grupoSQL.getSelectedToggle();

		try {
			switch (radio.getText()) {
			case "SELECT":
				sqlData = sql.getInstructionSelect();
				break;

			case "INSERT":
				sqlData = sql.getInstructionInsert();
				break;

			case "UPDATE":
				sqlData = sql.getInstructionUpdate();
				break;

			case "DELETE":
				sqlData = sql.getInstructionDelete();
				break;
			default:
				System.out.println("INVALID PARAMETER!");
				break;
			}
		} catch (Exception e) {
			System.out.println("NENHUM RADIO BUTTON SELECIONADO!!!");
		}

		return sqlData;

	}

}