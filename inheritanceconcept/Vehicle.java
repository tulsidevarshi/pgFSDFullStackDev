package inheritanceconcept;

public class Vehicle {

	public static void main(String[] args) {
		Honda h=new Honda(4,100,"Two Wheeler",1200,150,4);
		h.showDetails();
	
	}

}
class Vehicles{
	int gear, speed;
	String vtype;
	public Vehicles(int gear, int speed, String vtype) {
		this.gear = gear;
		this.speed = speed;
		this.vtype = vtype;
	}
	public void showDetails() {
		System.out.println(this.gear+" "+this.speed+" "+this.vtype);
	}
}
class Bike1 extends Vehicles{
	int rpm,cc;
	public Bike1(int gear, int speed, String vtype, int rpm, int cc) {
		super(gear, speed, vtype);
		this.rpm = rpm;
		this.cc = cc;
	}
	public void showDetails() {
		System.out.println(this.gear+" "+this.speed+" "+this.vtype+""+this.cc+" "+this.rpm );
	}
	}

    class Honda extends Bike1{
        int valve;
    
        public Honda(int gear, int speed, String vtype, int rpm, int cc, int valve) {
            super(gear, speed, vtype, rpm, cc);
            this.valve = valve;
        }
        public void showDetails() {
            System.out.println(this.gear+" "+this.speed+" "+this.vtype+""+this.cc+" "+this.rpm+" "+this.valve );
        }
        
    }
