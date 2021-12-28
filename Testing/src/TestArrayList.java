import java.util.ArrayList;

public class TestArrayList {

	public static void main(String[] args) {
		ArrayList<String> color=new ArrayList<String>();
		color.add("red");
		color.add("green");
		color.add("blue");
		System.out.println(color);
		System.out.println(color.get(0));
		System.out.println(color.size());
		color.set(0, "pink");
		System.out.println(color);
		

	}

}
