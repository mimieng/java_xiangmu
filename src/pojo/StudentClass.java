package pojo;
/**
 * 
 * @author 
 *班级信息entity
 */
public class StudentClass {
	private int id;
	private String name;
	private String info;

	public StudentClass(int id, String name, String info) {
		this.id = id;
		this.name = name;
		this.info = info;
	}

	public StudentClass() {
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString(){
		return this.name;
	}

}
