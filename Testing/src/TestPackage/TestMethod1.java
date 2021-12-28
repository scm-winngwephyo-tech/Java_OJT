package TestPackage;

import java.util.Scanner;

public class TestMethod1 {

	public static void main(String[] args) {
	
	}

	public int sum(int num1, int num2) {
		return num1 +num2;
	}
}
class TestMethod2 extends TestMethod1 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter number 1 :");
		int num1=sc.nextInt();
		System.out.println("Enter number 2 :");
		int num2=sc.nextInt();
		TestMethod2  t=new TestMethod2();
	int sum=t.sum(num1, num2);
	System.out.println(sum);
		

	}

}
