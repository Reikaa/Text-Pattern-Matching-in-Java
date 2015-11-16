import java.io.FileNotFoundException;


public class Programrun {
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("beg of prog");
		PatternMatching x = new PatternMatching("b.h", "src\\testFile.txt");
		x.matchPattern();
		System.out.println("endOfProgram");
	}
	

}
