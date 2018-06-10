package Student.view;

import java.io.File;

import Student.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * �༭ѧ����Ϣ�ĶԻ���
 * @author sup_yang
 *
 */
public class StudentEditDialogController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField classInField;
	@FXML
	private TextField idField;
	@FXML
	private TextField ChineseField;
	@FXML
	private TextField EnglishField;
	@FXML
	private TextField MathField;
	@FXML
	private TextField physicsField;
	@FXML
	private TextField chemistryField;
	@FXML
	private TextField biologyField;
	
	//��Ƭ
	@FXML
	private Button btimage = new Button();
	@FXML
	private ImageView iv;
	
	private Stage dialogStage;
	private Student student;
	private boolean okClicked = false;
	
	//��ʼ���༭��������������FXML�ļ����غ��Զ�����
	@FXML
	private void initialize() {
		
	}
	
	//���öԻ���
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	//���öԻ����еı༭
	@FXML
	public void setStudent(Student student) {
		this.student = student;
		
		//���������������ֵ
		nameField.setText(student.getName());
		classInField.setText(student.getClassIn());
		idField.setText(student.getid());
		//Fieldֻ������String����DoubleתΪString
		ChineseField.setText(Double.toString(student.getChinese()));
		EnglishField.setText(Double.toString(student.getEnglish()));
		MathField.setText(Double.toString(student.getMath()));
		physicsField.setText(Double.toString(student.getphysics()));
		chemistryField.setText(Double.toString(student.getchemistry()));
		biologyField.setText(Double.toString(student.getbiology()));
		
		//iv
		Image image = new Image(new File(student.getPhoto()).toURI().toString(), true);
		iv = new ImageView(image);
		iv.setFitHeight(112);
		iv.setFitWidth(89);
		iv.setImage(image);
		btimage.setGraphic(iv);
		
	}
	
	//okClicked���û����ȷ�ϵ�ʱ�򷵻�true
	public boolean isOkClicked() {
		return okClicked;
	}
	
	//��okClicedΪture���û����ȷ��ʱ���÷�����
	@FXML
	private void handleOk() {
		//isInputValid�ж������Ƿ���Ч����������Ч��ʱ�򣬰������ֵ����student��ֵ
		if(isInputValid()) {
			student.setName(nameField.getText());
			student.setclassIn(classInField.getText());
			student.setid(idField.getText());
			student.setChinese(Double.parseDouble(ChineseField.getText()));
			student.setMath(Double.parseDouble(MathField.getText()));
			student.setEnglish(Double.parseDouble(EnglishField.getText()));
			student.setphysics(Double.parseDouble(physicsField.getText()));
			student.setchemistry(Double.parseDouble(chemistryField.getText()));
			student.setbiology(Double.parseDouble(biologyField.getText()));
			
			//��ͼƬ��URL����student
			student.setPhoto(iv.getImage().toString());
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	//ȡ����ť
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	//���ͼƬ��ť
	@FXML
	private void setimage() {

		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(new Stage());
			String waytoimage = file.toURI().toString();// ����·��
			Image image = new Image(waytoimage, true);
			ImageView iv = new ImageView(image);
			iv.setFitHeight(112);
			iv.setFitWidth(89);
			iv.setImage(image);
			btimage.setGraphic(iv);

		} catch (RuntimeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�ļ���ȡʧ��");
			alert.setHeaderText("�ļ���ȡʧ��");
			alert.setContentText("��������ȷ��·��");
			alert.showAndWait();
			
			e.printStackTrace();
		}
	}

	
	/**
	 * �ж������Ƿ���Ч
	 * @return ������Чʱ����true
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		//��û����������,�༶��ѧ��ʱ
		//��errorMessage��ӣ���һ�д��󣬾���ʾһ��
		if(nameField.getText() == null || nameField.getText().length() == 0)
				errorMessage += "����������Ч\n";
		if(classInField.getText() == null || classInField.getText().length() == 0)
			errorMessage += "�༶������Ч\n";
		if(nameField.getText() == null || nameField.getText().length() == 0)
			errorMessage += "����������Ч\n";
		
		//��try����Double����Ϊdouble
		if(ChineseField.getText() == null || ChineseField.getText().length() == 0) {
			errorMessage += "����������Ч\n";
		}else {
			
			try {
				Double.parseDouble(ChineseField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "������������\n";
			}
		}
		if(MathField.getText() == null || MathField.getText().length() == 0) {
			errorMessage += "��ѧ������Ч\n";
		}else {
			
			try {
				Double.parseDouble(MathField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "��ѧ��������\n";
			}
		}
		if(EnglishField.getText() == null || EnglishField.getText().length() == 0) {
			errorMessage += "Ӣ��������Ч\n";
		}else {
			
			try {
				Double.parseDouble(EnglishField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "Ӣ����������\n";
			}
		}
		if(physicsField.getText() == null || physicsField.getText().length() == 0) {
			errorMessage += "����������Ч\n";
		}else {
			
			try {
				Double.parseDouble(physicsField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "������������\n";
			}
		}
		if(chemistryField.getText() == null || chemistryField.getText().length() == 0) {
			errorMessage += "��ѧ������Ч\n";
		}else {
			
			try {
				Double.parseDouble(chemistryField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "��ѧ��������\n";
			}
		}
		if(biologyField.getText() == null || biologyField.getText().length() == 0) {
			errorMessage += "����������Ч\n";
		}else {
			
			try {
				Double.parseDouble(biologyField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "������������\n";
			}
		}
		
		//��errorMessage����Ϊ0ʱ����true
		if(errorMessage.length() == 0) {
			return true;
		}else {
			//�������������Ϣ
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("��������");
				alert.setHeaderText("��������ȷ������!");
				alert.setContentText(errorMessage);
				alert.showAndWait();
			return false;
		}		
	}
	
}
