package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	//����һ��Statement
	public Statement statement = null;
	
	//������
	public MainApp() {
		//�����ݿ��ж�ȡѧ����Ϣ
		try {
			initializeDB();
			
			ResultSet resultSet = statement.executeQuery("select *\r\n" + 
					"	from student\r\n" + 
					"go");
			
			while(resultSet.next() ) {
				//����ת��
				studentData.add(new Student(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2),
						Double.parseDouble(resultSet.getString(4)), Double.parseDouble(resultSet.getString(5)), 
						Double.parseDouble(resultSet.getString(6)), Double.parseDouble(resultSet.getString(7)), 
						Double.parseDouble(resultSet.getString(8)), Double.parseDouble(resultSet.getString(9)),
						resultSet.getString(10)) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//studentData.add(new Student("����","����","2001"));
		
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
		
		//initializeDB();
		
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
	
	//��ʼ�����ݿ�
	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�����Ѽ���");
			String url = "jdbc:mysql://localhost:3306/studentinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false\r\n"; 
					
			Connection connection = DriverManager.getConnection(url, "root", "123456");
			System.out.println("���ݿ�������");
			
			statement = connection.createStatement();
			
		} catch (Exception e) {
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
			//�������Ѿ�д�õĽ��������
			StudentViewController controller = loader.getController();
			//�������������setMainApp����
			controller.setMainApp(this);//����ֵΪObservableList
			
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
	public boolean showStudentEditDialog(Student student) {
		
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
