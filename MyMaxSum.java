import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner; 
public class MyMaxSum {

	private static int[][] values = null ;

	public static void main(String[] args) {

		String name = "pi.txt";
		int length;

		File file = new File("pi.txt");

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = new byte[(int)file.length()];
		try {
			fis.read(byteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = new String(byteArray);
		String[] stringArray = data.split("\r\n");


		length = stringArray.length;

		values = readFile(name, length);
		int sum = findMaxSum(values, length);
		System.out.println("The sum is " + sum);
		 
		
		
	}
	   public static int findMaxSum(int [] [] values, int param) {


	        if (isPrime(values[0][0])) {
	            return 0;
	        } else {
	            return calculate(0, 0, param);
	        }

	    }
	
	public static int calculate(int i, int j, int param) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		int diag = 0;
		int down = 0;
	

        if (i >= param) {
            return 0;
        } else if (i == (param - 1)) {
            return values[i][j];
        }

        if (!isPrime(values[i + 1][j])) {
            down = calculate(i + 1, j, param);
        }

        if (!isPrime(values[i + 1][j + 1])) {
            diag = calculate(i + 1, j + 1, param);
        }

        if (isPrime(values[i + 1][j + 1]) && isPrime(values[i + 1][j]))
            return Integer.MIN_VALUE;	
        
        
     
        if (diag > down)
            return diag + values[i][j];
       
            return down + values[i][j];
	}
	
	public static boolean isPrime(int num) {

        if (num <= 1) 
            return false; 
  
        for (int i = 2; i < num; i++) 
            if (num % i == 0) 
                return false; 
  
        return true; 
	}

	public static int[][] readFile(String name, int length){

		int[][] values = new int[length][length];
		for (int i = 0; i < values.length; i ++) {
			for(int j = 0; j <values.length; j++) {
				values[i][j] = 0;
			}
		}
		try {

			InputStream inputStream = new FileInputStream(name);
			Scanner scan = new Scanner(inputStream);

			int index = 0;

			while (scan.hasNextLine()) {
				String value = scan.nextLine();
				String[] line = value.split("\\s+");
				for (int i = 0; i < line.length; ++i) {
					values[index][i] = Integer.parseInt(line[i]);
				}
				index = index + 1;

			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
			java.lang.System.exit(1);
		}
		return values;
	}
}
