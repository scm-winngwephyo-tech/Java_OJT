import java.util.Scanner;

public class BreakContinue {

	public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
System.out.println("Enter your birhtday");
int bd=sc.nextInt();

for (int i = 0; i <=31; i++) {
	if (i==bd) {
		break;
	}
	System.out.println("Count Your Bd : " + i);	
	
}
for (int i = 0; i <=31; i++) {
	if (i==bd) {
		continue;
	}
	System.out.println("Count Your Bd : " + i);	

}

	}

}
