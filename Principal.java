import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args){
		FileReader f;
		try {

			f = new FileReader("abb.txt");

			Scanner entrada = new Scanner(f);

			String op;

			ABB abb = new ABB();
			
			while(entrada.hasNext()){
				op = entrada.next();
				switch(op){
				case "insert":
		    		abb.insert(Integer.parseInt(entrada.next()));
		    		abb.percursoPreOrdem(abb.getRoot());
		    		System.out.println();
			    	break;
		    	case "remove":
		    		abb.remove(Integer.parseInt(entrada.next()));
		    		abb.percursoPreOrdem(abb.getRoot());
		    		System.out.println();
		    		break;
		    	
		    	}
		    }

		}catch (FileNotFoundException e) {
			System.out.print("ERROR");
		}
	}
}
		
		
		

