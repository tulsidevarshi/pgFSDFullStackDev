package Collection1;

import java.util.PriorityQueue;
import java.util.jar.Attributes.Name;

public class QueueExample {

	public static void main(String[] args) {
		PriorityQueue<String> pq=new PriorityQueue<String>();
		pq.add("Ashish");
		pq.add("Pritesh");
		pq.add("Bharat");
		pq.add("Navin");
		pq.add("Chetan");
		pq.offer("Akhay");
		
		System.out.println("What is at the head of the queue"+pq.peek());
		System.out.println(pq);
		System.out.println("What is at the head of the queue"+pq.element());

	}

}

class Employee implements Comparable<Employee>{
	private String name;
	private String age;

	@Override
	public int compareTo(Employee emp){
		return age.compareTo(emp.age);
	}
}