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
 * 为学生创建一个模型类
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
	
	//照片
	private StringProperty photo = null;
	
	//默认构建器，必须传入姓名和班级
	public Student() {
		this(" ", " ", " ", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, " ");
	}
	
	//初始化数据，必须传入姓名和班级
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
	
	//定义name属性方法
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getClassIn() {
		return classIn.get();
	}
	
	public void setclassIn(String classIn) {
		this.classIn.set(classIn);
	}
	
	//定义classIn属性方法
	public StringProperty classInProperty() {
		return classIn;
	}
	
	public String getid() {
		return id.get();
	}
	
	public void setid(String id) {
		this.id.set(id);
	}
	
	//定义id属性方法
	public StringProperty idProperty() {
		return id;
	}
	
	public double getbiology() {
		return biology.get();
	}
	
	public void setbiology(double biology) {
		this.biology.set(biology);
	}
	
	//定义biology属性方法
	public DoubleProperty biologyProperty() {
		return biology;
	}
	
	public double getMath() {
		return Math.get();
	}
	
	public void setMath(double Math) {
		this.Math.set(Math);
	}
	
	//定义Math属性方法
	public DoubleProperty MathProperty() {
		return Math;
	}
	
	public double getphysics() {
		return physics.get();
	}
	
	public void setphysics(double physics) {
		this.physics.set(physics);
	}
	
	//定义physics属性方法
	public DoubleProperty physicsProperty() {
		return physics;
	}
	
	public double getchemistry() {
		return chemistry.get();
	}
	
	public void setchemistry(double chemistry) {
		this.chemistry.set(chemistry);
	}
	
	//定义chemistry属性方法
	public DoubleProperty chemistryProperty() {
		return Chinese;
	}
	
	public double getChinese() {
		return Chinese.get();
	}
	
	public void setChinese(double Chinese) {
		this.Chinese.set(Chinese);
	}
	
	//定义Chinese属性方法
	public DoubleProperty ChineseProperty() {
		return Chinese;
	}
	
	public double getEnglish() {
		return English.get();
	}
	
	public void setEnglish(double English) {
		this.English.set(English);
	}
	
	//定义English属性方法
	public DoubleProperty EnglishProperty() {
		return English;
	}
	
	public String getPhoto() {
		return photo.get();
	}
	
	public void setPhoto(String photo) {
		this.photo.set(photo);
	}
	
	//定义photo属性方法
	public StringProperty photoProperty() {
		return photo;
	}
	
	
}
