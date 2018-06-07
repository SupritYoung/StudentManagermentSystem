package Student.view;

import org.controlsfx.dialog.Dialogs;

import Student.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * 编辑学生信息的对话框
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
	
	private Stage dialogStage;
	private Student student;
	private boolean okClicked = false;
	
	//初始化编辑控制器，方法在FXML文件加载后自动调用
	@FXML
	private void initialize() {
		
	}
	
	//设置对话框
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	//设置对话框中的编辑
	public void setStudent(Student student) {
		this.student = student;
		
		nameField.setText(student.getName());
		classInField.setText(student.getClassIn());
		idField.setText(student.getid());
		//Field只能输入String，将Double转为String
		ChineseField.setText(Double.toString(student.getChinese()));
		EnglishField.setText(Double.toString(student.getEnglish()));
		MathField.setText(Double.toString(student.getMath()));
		physicsField.setText(Double.toString(student.getphysics()));
		chemistryField.setText(Double.toString(student.getchemistry()));
		biologyField.setText(Double.toString(student.getbiology()));
	}
	
	//okClicked当用户点击确认的时候返回true
	public boolean isOkClicked() {
		return okClicked;
	}
	
	//当okCliced为ture（用户点击确认时调用方法）
	@FXML
	private void handleOk() {
		//isInputValid判断输入是否有效，当输入有效的时候，把输入的值传给student赋值
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
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	//取消按钮
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**
	 * 判断数据是否有效
	 * @return 输入有效时返回true
	 */
	
	private boolean isInputValid() {
		String errorMessage = "";
		
		//当没有输入姓名,班级，学号时
		//将errorMessage相加，有一行错误，就提示一行
		if(nameField.getText() == null || nameField.getText().length() == 0)
				errorMessage += "姓名输入无效\n";
		if(classInField.getText() == null || classInField.getText().length() == 0)
			errorMessage += "班级输入无效\n";
		if(nameField.getText() == null || nameField.getText().length() == 0)
			errorMessage += "姓名输入无效\n";
		
		//用try来将Double解析为double
		if(ChineseField.getText() == null || ChineseField.getText().length() == 0) {
			errorMessage += "语文输入无效\n";
		}else {
			
			try {
				Double.parseDouble(ChineseField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "语文输入有误\n";
			}
		}
		if(MathField.getText() == null || MathField.getText().length() == 0) {
			errorMessage += "数学输入无效\n";
		}else {
			
			try {
				Double.parseDouble(MathField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "数学输入有误\n";
			}
		}
		if(EnglishField.getText() == null || EnglishField.getText().length() == 0) {
			errorMessage += "英语输入无效\n";
		}else {
			
			try {
				Double.parseDouble(EnglishField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "英语输入有误\n";
			}
		}
		if(physicsField.getText() == null || physicsField.getText().length() == 0) {
			errorMessage += "物理输入无效\n";
		}else {
			
			try {
				Double.parseDouble(physicsField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "物理输入有误\n";
			}
		}
		if(chemistryField.getText() == null || chemistryField.getText().length() == 0) {
			errorMessage += "化学输入无效\n";
		}else {
			
			try {
				Double.parseDouble(chemistryField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "化学输入有误\n";
			}
		}
		if(biologyField.getText() == null || biologyField.getText().length() == 0) {
			errorMessage += "生物输入无效\n";
		}else {
			
			try {
				Double.parseDouble(biologyField.getText());
			}catch(NumberFormatException e) {
				errorMessage += "生物输入有误\n";
			}
		}
		
		//当errorMessage长度为0时返回true
		if(errorMessage.length() == 0) {
			return true;
		}else {
			//输出各条错误信息
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("输入有误");
				alert.setHeaderText("请输入正确的数据!");
				alert.setContentText(errorMessage);
				alert.showAndWait();
			return false;
		}		
	}
	
}
