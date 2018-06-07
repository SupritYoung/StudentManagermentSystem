package Student.view;

import Student.MainApp;
import Student.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

	/**
	 * @author lenovo
	 * ��student���ݼ��뵽studentView�еĿ�����
	 */
public class StudentViewController {
	
	//���ʱ��ͱ�ǩ��ʵ����������Щ���Ժ�һЩ������Ҫ@FXMLע��
	@FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Student, String> nameColumn;
	@FXML
	private TableColumn<Student, String> classInColumn;
	@FXML
	private TableColumn<Student, String> idColumn;
	
	@FXML
	private Label nameLabel1;
	@FXML
	private Label classInLabel1;
	@FXML
	private Label idLabel1;
	@FXML
	private Label ChineseLabel1;
	@FXML
	private Label EnglishLabel1;
	@FXML
	private Label MathLabel1;
	@FXML
	private Label biologyLabel1;
	@FXML
	private Label chemistryLabel1;
	@FXML
	private Label physicsLabel1;
	
	//����MainApp
	private MainApp mainApp;
	
	public StudentViewController() {
	
	}
	
	//��ʼ���������࣬��Fxml�����غ󷽷��Զ�����
	//ʹ��setCellValueFactory(...) ��ȷ��Ϊ�ض���ʹ��Student�����ĳ������.
	/*��ͷ -> ��ʾ������ʹ��Java8��Lambdas����. */
	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		classInColumn.setCellValueFactory(cellData -> cellData.getValue().classInProperty());
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	
		//�����Ϣ
		showStudentDetails(null);
		
		//�����ı䣬���û�ѡ��һ���˵�ʱ����֪ͨ
		studentTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showStudentDetails(newValue));
	}
	
	//mainApp�ķ���ֵ�����Լ�
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Ϊ�����һ���ɹ۲����
		studentTable.setItems(mainApp.getStudentData());
	}
	
	//��ѡ�������Աʱ�����ұ���ʾ��Ա����ϸ��Ϣ
	private void showStudentDetails(Student student) {
		
		if(student != null) {
			//��setText�������
			nameLabel1.setText(student.getName());
			classInLabel1.setText(student.getClassIn());
			idLabel1.setText(student.getid());
			ChineseLabel1.setText(Double.toString(student.getChinese()));
			EnglishLabel1.setText(Double.toString(student.getEnglish()));
			MathLabel1.setText(Double.toString(student.getMath()));
			physicsLabel1.setText(Double.toString(student.getphysics()));
			chemistryLabel1.setText(Double.toString(student.getchemistry()));
			biologyLabel1.setText(Double.toString(student.getbiology()));
		}else {
			//�����һ���ǿյģ���ѧ��������Ϣ���ᱻ���
			nameLabel1.setText("");
			classInLabel1.setText("");
			idLabel1.setText("");
			ChineseLabel1.setText("");
			EnglishLabel1.setText("");
			MathLabel1.setText("");
			chemistryLabel1.setText("");
			physicsLabel1.setText("");
			biologyLabel1.setText("");
		}
	}
	
	//ɾ����ť
	@FXML
	private void handleDeletStudent() {
		//��û���û�ʱ�����Ի�����ʾ
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			studentTable.getItems().remove(selectedIndex);
		}else{
			//û��ѧ����Ϣʱ�������Ի���
			/*Dialogs.create()
				.title("ɾ��ʧ��")
				.masthead("û��ѧ������ѡ��")
				.message("��ѡ��һ��ѧ��")
				.showWarning();*/
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ɾ��ʧ��");
			alert.setHeaderText("û��ѧ������ѡ��");
			alert.setContentText("��ѡ��һ��ѧ��");
			alert.showAndWait();
		}
	}
	
	//��Ӱ�ť
	@FXML
	private void handleNewStudent() {
		Student tempStudent = new Student();
		boolean okClicked = mainApp.showPersonEditDialog(tempStudent);
		if(okClicked) {
			mainApp.getStudentData().add(tempStudent);
		}
	}
	
	//�༭��ť
	@FXML
	private void handleEditStudent() {
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
		if(selectedStudent != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedStudent);
			if(okClicked) {
				showStudentDetails(selectedStudent);
			}
		}else {
			//û��ѡ���ʱ�򵯳��Ի���
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�༭ʧ��");
			alert.setHeaderText("û��ѧ����ѡ��");
			alert.setContentText("���ڱ���ѡ��һ��ѧ��");
			alert.showAndWait();
		}
	}

}
