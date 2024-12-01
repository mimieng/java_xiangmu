package pojo;

public class Student {
	private int id;
	private String name;
	private int classId;
	private String password;
	private String sex;

	public Student(int id, String name, int classId, String password, String sex) {
		this.id = id;
		this.name = name;
		this.classId = classId;
		this.password = password;
		this.sex = sex;
	}
	public Student() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", classId=" + classId +
				", password='" + password + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}
}
