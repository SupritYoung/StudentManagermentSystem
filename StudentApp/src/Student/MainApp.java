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
	
	//创建一个ObserveableList去使得视图和数据同步
	public ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	//创建一个Statement
	public Statement statement = null;
	
	//构建器
	public MainApp() {
		//从数据库中读取学生信息
		try {
			initializeDB();
			
			ResultSet resultSet = statement.executeQuery("select *\r\n" + 
					"	from student\r\n" + 
					"go");
			
			while(resultSet.next() ) {
				//类型转换
				studentData.add(new Student(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2),
						Double.parseDouble(resultSet.getString(4)), Double.parseDouble(resultSet.getString(5)), 
						Double.parseDouble(resultSet.getString(6)), Double.parseDouble(resultSet.getString(7)), 
						Double.parseDouble(resultSet.getString(8)), Double.parseDouble(resultSet.getString(9)),
						resultSet.getString(10)) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//studentData.add(new Student("李明","二班","2001"));
		
	}
	
	//返回学生观察表的数据
	public ObservableList<Student> getStudentData(){
		return studentData;
	}
	
	//加载RootLayout，同时添加StudentView到RootLayout中
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("学生管理系统V1.0 - sup_Yang");
			
		initRootLayout();
		
		//initializeDB();
		
		showStudentView();
	}
	
	//初始化RootLayout界面
	private void initRootLayout() {
		try {
			//加载fxml中的主界面
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//展示主界面
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//初始化数据库
	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动已加载");
			String url = "jdbc:mysql://localhost:3306/studentinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false\r\n"; 
					
			Connection connection = DriverManager.getConnection(url, "root", "123456");
			System.out.println("数据库已连接");
			
			statement = connection.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//展示StudenView界面
	private void showStudentView() {
		try {
			//加载学生界面
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StudentView.fxml"));
			AnchorPane StudentView = (AnchorPane) loader.load();
			
			//在主界面中设置学生界面
			rootLayout.setCenter(StudentView);
			
			//给控制器主程序的访问
			//加载你已经写好的界面控制器
			StudentViewController controller = loader.getController();
			//控制器方法里的setMainApp方法
			controller.setMainApp(this);//返回值为ObservableList
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 打开一个界面编辑学生的细节
	 * 如果点击确认，则保存改动返回true
	 * 
	 * @return 保存则改动返回true
	 */
	public boolean showStudentEditDialog(Student student) {
		
		try {
			//加载FXML文件
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StudentEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//创建对话框的舞台
			Stage dialogStage = new Stage();
			dialogStage.setTitle("编辑信息");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//把学生放入控制器
			StudentEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStudent(student);
			
			//显示这个对话框直到你关闭它
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

	//程序入口
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
