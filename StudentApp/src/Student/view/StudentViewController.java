package Student.view;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import Student.MainApp;
import Student.model.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	@FXML
	private Label limage;
	
	
	@FXML
	private ImageView iv;
	
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
			
			//���ұ���ʾ��Ƭ
			Image image = new Image(new File(student.getPhoto()).toURI().toString(), true);
			System.out.println(new File(student.getPhoto()).toURI().toString());
			iv = new ImageView(image);
			iv.setFitHeight(112);
			iv.setFitWidth(89);
			iv.setImage(image);
			limage.setGraphic(iv);
			
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
			
			//��ʾĬ����Ƭ
			Image image = new Image(new File("E:/eclipse/workSpace/StudentApp/src/image/default.jpg").toURI().toString(), true);
			iv = new ImageView(image);
			iv.setFitHeight(112);
			iv.setFitWidth(89);
			iv.setImage(image);
			
		}
	}
	
	//ɾ����ť
	@FXML
	private void handleDeletStudent() {
		//��û���û�ʱ�����Ի�����ʾ
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			studentTable.getItems().remove(selectedIndex);
			//studentTable.get(selectedIndex);
			
			//ɾ�����ݿ��еĵ�selectedIndex��ѧ��,Ĭ������ʽ
			try {
				String id = mainApp.studentData.get(selectedIndex).getid();
				
				mainApp.statement.execute("delete from student\r\n" + 
						"where Stu_id = " + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
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
		boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
		if(okClicked) {
			mainApp.getStudentData().add(tempStudent);
			
			//���޸Ĺ����½�ѧ����ӵ����ݿ�
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
	
	//�༭��ť
	@FXML
	private void handleEditStudent() {
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
		if(selectedStudent != null) {
			boolean okClicked = mainApp.showStudentEditDialog(selectedStudent);
			if(okClicked) {
				showStudentDetails(selectedStudent);
				
				//����ӵ����������ݿ����޸�
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
			//û��ѡ���ʱ�򵯳��Ի���
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�༭ʧ��");
			alert.setHeaderText("û��ѧ����ѡ��");
			alert.setContentText("���ڱ���ѡ��һ��ѧ��");
			alert.showAndWait();
		}
	}
	
	//����word��ť
	@FXML
	private void handleWordExport() {
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
	//	Student selectedStudent = mainApp.studentData.get(selectedIndex);
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
		if(selectedIndex >= 0) {
			//ֻ����ѡ��һ��ѧ����ʱ��Żᵼ��
			WordTest test = new WordTest(selectedStudent);  
	        test.createWord(); 
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����ʧ��");
			alert.setHeaderText("û��ѧ������ѡ��");
			alert.setContentText("��ѡ��һ��ѧ��");
			alert.showAndWait();
		}
		
	}
	
	//����pdf��ť
	@FXML
	private void handlePDFExport() {
		int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
		Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
			if(selectedIndex >= 0) {
				//ֻ����ѡ��һ��ѧ����ʱ��Żᵼ��
				PDFTest test = new PDFTest(selectedStudent);  
		        test.fillTemplate();

			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����ʧ��");
				alert.setHeaderText("û��ѧ������ѡ��");
				alert.setContentText("��ѡ��һ��ѧ��");
				alert.showAndWait();
			}
	}
	
	//����word��ͷ���
	public class WordTest {  
	      
	    private Configuration configuration = null;  
	    Student student;
	      
	    public WordTest(Student selectedStudent){  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("UTF-8");
	        configuration.setEncoding(Locale.getDefault(), "utf-8");
	        //����һ��Configurationʵ�� �ĺ��������������ñ���Ϊutf-8��
	        
	        this.student = selectedStudent;
	    }   
	      
	    public void createWord(){  
	        Map<String,Object> dataMap=new HashMap<String,Object>();  
	        getData(dataMap);  
	        configuration.setClassForTemplateLoading(this.getClass(), "/Student/model");//ģ���ļ�����·��
	        Template t = null;
	        try {  
	            t = configuration.getTemplate("exportModel.ftl"); //��ȡģ���ļ�
	            System.out.println("��ȡģ���ļ��ɹ�");
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        File outFile = new File("E:/outFile/"+student.getName()+"������.doc"); //�����ļ�
	        System.out.println("����λ��ΪE:/outFile");
	        Writer out = null;  
	        try {  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        }  
	           
	        try {  
	            t.process(dataMap, out); //�������������ģ���ļ��������Ŀ���ļ� 
	            
	            //�ر���
	            out.flush();  
	            out.close(); 
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("�����ɹ�");
	    }  
	  
	    //�������
	    private void getData(Map<String, Object> dataMap) {  
	        dataMap.put("Name", student.getName());  
	        dataMap.put("Photo", student.getPhoto());  
	        dataMap.put("Id", student.getid());  
	        dataMap.put("Class", student.getClassIn());         
	        dataMap.put("Chinese", student.getChinese());  
	        dataMap.put("Math", student.getMath());
	        dataMap.put("English", student.getEnglish());
	        dataMap.put("Physics", student.getphysics());
	        dataMap.put("Chemistry", student.getchemistry());
	        dataMap.put("biology", student.getbiology());
	           
	    }  
	} 
	
	//����PDF��ͷ���
	public class PDFTest { 
		
		Student student;
		
		public PDFTest(Student selectedStudent) {
			this.student = selectedStudent;
		}
		
	    // ����ģ������pdf  
	    public void fillTemplate() {  
	        // ģ��·��  
	        String templatePath = "/Student/model/exportModel.pdf";  
	        // ���ɵ����ļ�·��  
	        String newPDFPath = "E:/outFile/"+student.getName()+"������.pdf";  
	        System.out.println("·��������");
	        PdfReader reader;  
	        FileOutputStream out;  
	        ByteArrayOutputStream bos;  
	        PdfStamper stamper;  
	        try {  
	            out = new FileOutputStream(newPDFPath);// �����  
	            reader = new PdfReader(templatePath);// ��ȡpdfģ��  
	            bos = new ByteArrayOutputStream();  
	            stamper = new PdfStamper(reader, bos);  
	            AcroFields form = stamper.getAcroFields();  
	  
	            String[] str = { student.getName(), student.getid(), student.getClassIn(), String.valueOf(student.getPhoto()), String.valueOf(student.getChinese()),
	            		String.valueOf(student.getMath()), String.valueOf(student.getEnglish()), String.valueOf(student.getphysics()), String.valueOf(student.getchemistry()),
	            		(String.valueOf(student.getbiology()))};  
	            int i = -1;  
	            java.util.Iterator<String> it = form.getFields().keySet().iterator();  
	            while (it.hasNext()) {  
	                String name = it.next().toString();             
	                form.setField(name, str[++i]);  
	                System.out.println(name +"<->"+ str[i]);  
	            }  
	            System.out.println("�����ɹ�");
	            stamper.setFormFlattening(true);// ���Ϊfalse��ô���ɵ�PDF�ļ����ܱ༭��һ��Ҫ��Ϊtrue  
	            stamper.close();  
	  
	            Document doc = new Document();  
	            PdfCopy copy = new PdfCopy(doc, out);  
	            doc.open();  
	            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);  
	            copy.addPage(importPage);  
	            doc.close();  
	  
	        } catch (IOException e) {  
	            System.out.println(1);  
	        } catch (DocumentException e) {  
	            System.out.println(2);  
	        }  
	  
	    }  

	} 
}
