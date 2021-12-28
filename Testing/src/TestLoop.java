
public class TestLoop {

	public static void main(String[] args) {
		String str[]= {"Green","Yellow","Blue","Pink","Red"};
		 for (String color : str) {
			 System.out.println(color);
			
		}
		 System.out.println();
		 for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
		 System.out.println();
		 int i=0;
		 while (i<str.length) {
			System.out.println(str[i]);
			i++;
		}
		 System.out.println();
		 int j=0;
		 do {
			System.out.println(str[j]);
			j++;
		} while (j<str.length);

	}

}
