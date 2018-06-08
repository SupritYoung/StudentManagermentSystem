package Student.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ϊѧ������һ��ģ����
 * @author sup_yang
 *
 */
public class Student {
	
	private final StringProperty name;
	private final StringProperty classIn;
	private final StringProperty id;
	private final DoubleProperty Chinese;
	private final DoubleProperty Math;
	private final DoubleProperty English;
	private final DoubleProperty physics;
	private final DoubleProperty chemistry;
	private final DoubleProperty biology;
	
	//��Ƭ
	private StringProperty photo = null;
	
	//Ĭ�Ϲ����������봫�������Ͱ༶
	public Student() {
		this(" ", " ", " ", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, " ");
	}
	
	//��ʼ�����ݣ����봫�������Ͱ༶
	public Student(String name, String classIn, String id, Double Chinese, Double Math, Double English
			, Double phycics, Double chemistry, Double biology, String photo) {
		this.name = new SimpleStringProperty(name);
		this.classIn = new SimpleStringProperty(classIn);
		this.id = new SimpleStringProperty(id);
		
		this.biology = new SimpleDoubleProperty(Chinese);
		this.chemistry = new SimpleDoubleProperty(Math);
		this.Chinese = new SimpleDoubleProperty(English);
		this.English = new SimpleDoubleProperty(phycics);
		this.Math = new SimpleDoubleProperty(chemistry);
		this.physics = new SimpleDoubleProperty(biology);	
		
		this.photo = new SimpleStringProperty(photo);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	//����name���Է���
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getClassIn() {
		return classIn.get();
	}
	
	public void setclassIn(String classIn) {
		this.classIn.set(classIn);
	}
	
	//����classIn���Է���
	public StringProperty classInProperty() {
		return classIn;
	}
	
	public String getid() {
		return id.get();
	}
	
	public void setid(String id) {
		this.id.set(id);
	}
	
	//����id���Է���
	public StringProperty idProperty() {
		return id;
	}
	
	public double getbiology() {
		return biology.get();
	}
	
	public void setbiology(double biology) {
		this.biology.set(biology);
	}
	
	//����biology���Է���
	public DoubleProperty biologyProperty() {
		return biology;
	}
	
	public double getMath() {
		return Math.get();
	}
	
	public void setMath(double Math) {
		this.Math.set(Math);
	}
	
	//����Math���Է���
	public DoubleProperty MathProperty() {
		return Math;
	}
	
	public double getphysics() {
		return physics.get();
	}
	
	public void setphysics(double physics) {
		this.physics.set(physics);
	}
	
	//����physics���Է���
	public DoubleProperty physicsProperty() {
		return physics;
	}
	
	public double getchemistry() {
		return chemistry.get();
	}
	
	public void setchemistry(double chemistry) {
		this.chemistry.set(chemistry);
	}
	
	//����chemistry���Է���
	public DoubleProperty chemistryProperty() {
		return Chinese;
	}
	
	public double getChinese() {
		return Chinese.get();
	}
	
	public void setChinese(double Chinese) {
		this.Chinese.set(Chinese);
	}
	
	//����Chinese���Է���
	public DoubleProperty ChineseProperty() {
		return Chinese;
	}
	
	public double getEnglish() {
		return English.get();
	}
	
	public void setEnglish(double English) {
		this.English.set(English);
	}
	
	//����English���Է���
	public DoubleProperty EnglishProperty() {
		return English;
	}
	
	public String getPhoto() {
		return photo.get();
	}
	
	public void setPhoto(String photo) {
		this.photo.set(photo);
	}
	
	//����photo���Է���
	public StringProperty photoProperty() {
		return photo;
	}
	
	
}
