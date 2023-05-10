package inheritanceconcept;

public class Overriding {

	public static void main(String[] args) {
		CollegeTeacher ct=new CollegeTeacher(101,"Akash","ABC COllege");
		ct.teach();
		Teacher t=new Teacher(102,"Swati");
		t.teach();
	}

}
class Teacher{
	int tid;
	String tname;
	public Teacher(int tid, String tname) {
		this.tid = tid;
		this.tname = tname;
	}
public void teach() {
System.out.println("I am a Teacher!!!");	
}}
class CollegeTeacher extends Teacher{
	String collegeName;

	public CollegeTeacher(int tid, String tname, String collegeName) {
		super(tid, tname);
		this.collegeName = collegeName;
	}
	public void teach() {
		System.out.println("I am a College Teacher!!!");	
		}}
