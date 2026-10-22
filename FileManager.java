import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public void crearCarpetaProcessats(Path p) throws IOException {
        Files.createDirectories(p);
    }

    public boolean existeixCarpeta(Path file) {
        return Files.isDirectory(file);
    }

    public void esborrarFitxer(Path fitxer) throws IOException {
        Files.deleteIfExists(fitxer);
    }

    public List<Path> cercarFitxersMoviments(Path carpeta) throws IOException {
        List<Path> fitxersTrobats = new ArrayList<>();
        
        // Obrim la carpeta amb DirectoryStream per veure què hi ha a dins
        try (DirectoryStream<Path> contingut = Files.newDirectoryStream(carpeta)) {
            for (Path p : contingut) {
                String nom = p.getFileName().toString();
                // Comprovem si és un fitxer i si el nom quadra amb "acc_statement_yyyy.csv"
                if (Files.isRegularFile(p) && nom.matches("acc_statement_\\d{4}\\.csv")) {
                    fitxersTrobats.add(p);
                }
            }
        }
        return fitxersTrobats;
    }
}