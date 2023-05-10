package inheritanceconcept;

public class Hiearchy {

	public static void main(String[] args) {
		Honda2 h1=new Honda2(5,100,2);
		Bajaj2 b1=new Bajaj2(4,120,1200);
		b1.changeGear();
		h1.changeGear();
		b1.increaseSpeed();
		h1.increaseSpeed();

	}

}
class Bike2{
	int gear,speed;
	
	public Bike2(int gear, int speed) {
	
		this.gear = gear;
		this.speed = speed;
	}
	public void changeGear(){
		System.out.println("Bike's gear has been changed to"+this.gear);
	}
	public void increaseSpeed() {
		System.out.println("Bike's speed is"+this.speed);
}
	}
class Bajaj2 extends Bike2{
	
	public Bajaj2(int gear, int speed, int rpm) {
		super(gear, speed);
		this.rpm = rpm;
	}

	int rpm;
	
}
class Honda2 extends Bike2{
	
	public Honda2(int gear, int speed, int valve) {
		super(gear, speed);
		this.valve = valve;
	}

	int valve;
	
}

