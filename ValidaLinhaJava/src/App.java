import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        

        Path fileName = Path.of("./src/prog.txt");

        String actual = Files.readString(fileName); 

        String lines[] = actual.split("\\r?\\n");

        String resultado="";

        for (String string : lines) {           
            if(ValidaLinha(string)){
                resultado=resultado+string+"- OK"+"\r\n";
            }
            else{
                resultado=resultado+string+"- Inválido"+"\r\n";
            }          
        }
        
        FileWriter myWriter = new FileWriter("./src/prog-checked.txt");
        myWriter.write(resultado);
        myWriter.close();       
    }

    static boolean ValidaLinha(String linha)
    {
        Deque<Character> stack = new ArrayDeque<Character>();

        for (int i = 0; i < linha.length(); i++)
        {
            char carectere = linha.charAt(i);
 
            if (carectere == '(' || carectere == '[' || carectere == '{')
            {
                stack.push(carectere);
                continue;
            }
 
            if (stack.isEmpty())
                return false;
            char caractereValidar;
            switch (carectere) {
            case ')':
                caractereValidar = stack.pop();
                if (caractereValidar == '{' || caractereValidar == '[')
                    return false;
                break;
 
            case '}':
                caractereValidar = stack.pop();
                if (caractereValidar == '(' || caractereValidar == '[')
                    return false;
                break;
 
            case ']':
                caractereValidar = stack.pop();
                if (caractereValidar == '(' || caractereValidar == '{')
                    return false;
                break;
            }
        }
 
        return (stack.isEmpty());
    }


}
