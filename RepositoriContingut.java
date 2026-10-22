import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RepositoriContingut {

    public List<String> llegirFitxer(Path fitxer, boolean isHeader) throws IOException{
        
        List <String> contingut = new ArrayList<>();
        int numLinies = 0;
        try (BufferedReader br = Files.newBufferedReader(fitxer, StandardCharsets.UTF_8)) {
            
            String linia;
            while ((linia = br.readLine()) != null) {   // null: s'ha acabat el fitxer
                numLinies++;
                if (numLinies > 1 || !isHeader) {
                        contingut.add(linia);
                    }
                }
        }
        return contingut; 
    }

    public void escriureFitxer(Path fitxer, List<String> contingut) throws IOException {
        
        try (BufferedWriter bw = Files.newBufferedWriter(fitxer, StandardCharsets.UTF_8)) {
            for (String linia: contingut) {
                bw.write(linia);   // escriu el text (sense salt de línia)
                bw.newLine();      // afegeix el salt de línia del sistema operatiu
            }
        }
    }

    public void afegirLinia(Path fitxer, String linia) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(fitxer, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(linia);
            bw.newLine();                       // salt de línia del sistema
        }
    }

    public void serialitzarLlista(Path fitxer, List<Statements> llista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(fitxer))) {
            oos.writeObject(llista); // Guarda la lista entera de objetos en formato binario
        }
    }
}