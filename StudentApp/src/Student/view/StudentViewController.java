package Student.view;

import java.io.File;
import java.sql.SQLException;

import Student.MainApp;
import Student.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

	/**
	 * @author lenovo
	 * 将student数据加入到studentView中的控制器
	 */
public class StudentViewController {
	
	//访问表格和标签用实例变量，这些属性和一些方法需要@FXML注解
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
	
	
	@FXML
	private ImageView iv;
	
	//参照MainApp
	private MainApp mainApp;
	
	public StudentViewController() {
	
	}
	
	//初始化控制器类，在Fxml被加载后方法自动调用
	//使用setCellValueFactory(...) 来确定为特定列使用Student对象的某个属性.
	/*箭头 -> 表示我们在使用Java8的Lambdas特性. */
	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		classInColumn.setCellValueFactory(cellData -> cellData.getValue().classInProperty());
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	
		//清除信息
		showStudentDetails(null);
		
		//监听改变，在用户选择一个人的时候获得通知
		studentTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showStudentDetails(newValue));
	}
	
	//mainApp的返回值给他自己
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//为表添加一个可观察的列
		studentTable.setItems(mainApp.getStudentData());
	}
	
	//当选择表中人员时，在右边显示人员的详细信息
	private void showStudentDetails(Student student) {
		
		if(student != null) {
			//用setText方法填充
			nameLabel1.setText(student.getName());
			classInLabel1.setText(student.getClassIn());
			idLabel1.setText(student.getid());
			ChineseLabel1.setText(Double.toString(student.getChinese()));
			EnglishLabel1.setText(Double.toString(student.getEnglish()));
			MathLabel1.setText(Double.toString(student.getMath()));
			physicsLabel1.setText(Double.toString(student.getphysics()));
			chemistryLabel1.setText(Double.toString(student.getchemistry()));
			biologyLabel1.setText(Double.toString(student.getbiology()));
			
			//在右边显示照片
			Image image = new Image(new File(student.getPhoto()).toURI().toString(), true);
			System.out.println(new File(student.getPhoto()).toURI().toString());
			iv = new ImageView(image);
			iv.setFitHeight(112);
			iv.setFitWidth(89);
			iv.setImage(image);
			
		}else {
			//如果有一项是空的，该学生所有信息都会被清除
			nameLabel1.setText("");
			classInLabel1.setText("");
			idLabel1.setText("");
			ChineseLabel1.setText("");
			EnglishLabel1.setText("");
			MathLabel1.setText("");
			chemistryLabel1.setText("");
			physicsLabel1.setText("");
			biologyLabel1.setText("");
			
			//可能有问题
			
			Image image = new Image(new File("E:/eclipse/workSpace/StudentApp/src/image/default.jpg").toURI().toString(), true);
			//Image image = new Image(, true);
			iv = new ImageView(image);
			iv.setFitHeight(112);
			iv.setFitWidth(89);
			iv.setImage(image); 
		}
	}
	
	//删除按钮
	@FXML
	private void handleDeletStudent() {
		//在没有用户时弹出对话框提示
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			studentTable.getItems().remove(selectedIndex);
			//studentTable.get(selectedIndex);
			
			//删除数据库中的第selectedIndex个学生,默认排序方式
			try {
				String id = mainApp.studentData.get(selectedIndex).getid();
				
				mainApp.statement.executeQuery("delete from student\r\n" + 
						"where Stu_id = " + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}else{
			//没有学生信息时，弹出对话框
			/*Dialogs.create()
				.title("删除失败")
				.masthead("没有学生可以选择！")
				.message("请选择一个学生")
				.showWarning();*/
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("删除失败");
			alert.setHeaderText("没有学生可以选择");
			alert.setContentText("请选择一个学生");
			alert.showAndWait();
		}
	}
	
	//添加按钮
	@FXML
	private void handleNewStudent() {
		Student tempStudent = new Student();
		boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
		if(okClicked) {
			mainApp.getStudentData().add(tempStudent);
			
			//把修改过的新建学生添加到数据库
			try {
				mainApp.statement.execute("insert into student(Stu_id, Stu_Name, Stu_classIn, Stu_Chinese,"
						+ " Stu_Math, Stu_English,	Stu_physics, Stu_chemistry, Stu_biology)\r\n" + 
						"		values('"+tempStudent.getid()+"', '"+tempStudent.getName()+"','"+tempStudent.getClassIn()+"',"
						+ ""+tempStudent.getChinese()+", "+tempStudent.getMath()+", "+tempStudent.getEnglish()+
						", "+tempStudent.getphysics()+", "+tempStudent.getchemistry()+", "+tempStudent.getbiology()+","
								+ ""+tempStudent.getPhoto()+")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//编辑按钮
	@FXML
	private void handleEditStudent() {
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
		if(selectedStudent != null) {
			boolean okClicked = mainApp.showStudentEditDialog(selectedStudent);
			if(okClicked) {
				showStudentDetails(selectedStudent);
				
				//把添加的数据在数据库中修改
				try {
					
					mainApp.statement.execute("update student\r\n" + 
							"	set Stu_Name = '"+selectedStudent.getName()+"', Stu_classIn = '"+selectedStudent.getClassIn()+"'"
							+ ", Stu_Chinese = "+selectedStudent.getChinese()+", Stu_Math = "+selectedStudent.getMath()+""
							+ ", Stu_English = "+selectedStudent.getEnglish()+", Stu_physics ="+selectedStudent.getphysics()+""
							+ ",	Stu_chemistry = "+selectedStudent.getchemistry()+", Stu_biology = "+selectedStudent.getbiology()+
							", Stu_photo = '"+selectedStudent.getPhoto()+"'\r\n" + 
							"	where Stu_id = '"+selectedStudent.getid()+"'");
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			//没有选择的时候弹出对话框
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("编辑失败");
			alert.setHeaderText("没有学生被选择");
			alert.setContentText("请在表中选择一个学生");
			alert.showAndWait();
		}
	}

}
