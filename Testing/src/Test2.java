import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your favourite Color: y/r/g/b");
		String color=sc.nextLine();
	switch (color) {
	case "y": {
		System.out.println("Your Favourite color is :" + color+" is : yellow");
		break;	
	}
	case "r": {
		System.out.println("Your Favourite color is :" + color+" is : red");
		break;	
	}
	case "g": {
		System.out.println("Your Favourite color is :" + color+" is : green");
		break;	
	}
	case "b": {
		System.out.println("Your Favourite color is :" + color+" is : blue");
		break;	
	}
	default:
		throw new IllegalArgumentException("Your Favourite color is :" + color+"is not invalid");
	}

	}

}
