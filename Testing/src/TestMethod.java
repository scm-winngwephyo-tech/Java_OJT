import java.util.Scanner;

public class TestMethod {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Father Name: ");
		String father =sc.nextLine();
		System.out.println("Enter Your Mother Name: ");
		String mother =sc.nextLine();
		 parent(father,mother);
		
	}

	private static void parent(String father, String mother) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Your Name :");
	String name= sc.nextLine();
	System.out.println("Your Father Name : " + father);
	System.out.println("Your Mother Name : " + mother);
	System.out.println("Your  Name : " + name);
	
		
	}
	

}
