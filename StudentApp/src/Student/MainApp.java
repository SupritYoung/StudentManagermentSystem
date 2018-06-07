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
	
	//创建一个ObserveableList去使得试图和数据同步
	private ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	//构建器
	public MainApp() {
		//添加一些样例
		studentData.add(new Student("李明","二班","2001"));
		studentData.add(new Student("王浩","五班","5001"));
		studentData.add(new Student("王杰","一班","1001"));
		studentData.add(new Student("孙玲","四班","4001"));
		studentData.add(new Student("王聪","三班","3001"));
		studentData.add(new Student("李娜","二班","2002"));
		studentData.add(new Student("李晗","一班","1002"));
		studentData.add(new Student("钱坤","四班","4002"));
		studentData.add(new Student("周芳","五班","5002"));
		studentData.add(new Student("杨树仁","五班","5003"));
		Student sample1 = new Student("慈爱明","一班","1001");
		sample1.setChinese(100);
		sample1.setMath(100);
		studentData.add(sample1);
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
			StudentViewController controller = loader.getController();
			controller.setMainApp(this);
			
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
	public boolean showPersonEditDialog(Student student) {
		
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
