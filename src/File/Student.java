package File;

public class Student {
	private String stuid; //�й�
	private String atd; //�⼮
	private String middle; //�߰�
	private String fin; //�⸻
	private String assignment; //����
	private String name; //�̸�
	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", atd=" + atd + ", middle=" + middle + ", fin=" + fin + ", assignment="
				+ assignment + ", name=" + name + "]";
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}