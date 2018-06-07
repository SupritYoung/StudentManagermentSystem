package Student;

import java.io.IOException;

import Student.model.Student;
import Student.view.StudentEditDialogController;
import Student.view.StudentViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	//����һ��ObserveableListȥʹ����ͼ������ͬ��
	private ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	//������
	public MainApp() {
		//���һЩ����
		studentData.add(new Student("����","����","2001"));
		studentData.add(new Student("����","���","5001"));
		studentData.add(new Student("����","һ��","1001"));
		studentData.add(new Student("����","�İ�","4001"));
		studentData.add(new Student("����","����","3001"));
		studentData.add(new Student("����","����","2002"));
		studentData.add(new Student("����","һ��","1002"));
		studentData.add(new Student("Ǯ��","�İ�","4002"));
		studentData.add(new Student("�ܷ�","���","5002"));
		studentData.add(new Student("������","���","5003"));
		Student sample1 = new Student("�Ȱ���","һ��","1001");
		sample1.setChinese(100);
		sample1.setMath(100);
		studentData.add(sample1);
	}
	
	//����ѧ���۲�������
	public ObservableList<Student> getStudentData(){
		return studentData;
	}
	
	//����RootLayout��ͬʱ���StudentView��RootLayout��
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ѧ������ϵͳV1.0 - sup_Yang");
		
		initRootLayout();
		
		showStudentView();
	}
	
	//��ʼ��RootLayout����
	private void initRootLayout() {
		try {
			//����fxml�е�������
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//չʾ������
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//չʾStudenView����
	private void showStudentView() {
		try {
			//����ѧ������
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StudentView.fxml"));
			AnchorPane StudentView = (AnchorPane) loader.load();
			
			//��������������ѧ������
			rootLayout.setCenter(StudentView);
			
			//��������������ķ���
			StudentViewController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * ��һ������༭ѧ����ϸ��
	 * ������ȷ�ϣ��򱣴�Ķ�����true
	 * 
	 * @return ������Ķ�����true
	 */
	public boolean showPersonEditDialog(Student student) {
		
		try {
			//����FXML�ļ�
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StudentEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//�����Ի������̨
			Stage dialogStage = new Stage();
			dialogStage.setTitle("�༭��Ϣ");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//��ѧ�����������
			StudentEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStudent(student);
			
			//��ʾ����Ի���ֱ����ر���
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	//�������
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
