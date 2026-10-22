import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppBanc {
    public static final Path RUTA_ENTRADA = Path.of("Entrada");
    public static final Path RUTA_PROCESSATS = Path.of("Processats");
    private static final RepositoriContingut rc = new RepositoriContingut();
    private static final FileManager fm = new FileManager();

    public static void main(String[] args) {
        System.out.println("Iniciant l'app del banc...");

        if (fm.existeixCarpeta(RUTA_ENTRADA)) {
            try {
                System.out.println("Creant la carpeta de Processats si no existeix...");
                fm.crearCarpetaProcessats(RUTA_PROCESSATS);
                System.out.println("Carpeta processats a punt...");

                System.out.println("Buscant fitxers de moviments...");
                List<Path> fitxers = fm.cercarFitxersMoviments(RUTA_ENTRADA);
                
                if (fitxers.isEmpty()) {
                    System.out.println("No s'ha trobat cap fitxer amb el patró 'acc_statement_yyyy.csv' a Entrada.");
                } else {
                    for (Path fitxer: fitxers) {
                        System.out.println("S'ha trobat el fitxer per processar: " + fitxer.getFileName());
                    
                        List<String> liniesMoviments = rc.llegirFitxer(fitxer, true);
                        
                        List<String> transferencies = new ArrayList<>();
                        List<String> pagaments = new ArrayList<>();
                        List<String> recarregues = new ArrayList<>();
                        List<String> altres = new ArrayList<>();

                        String novaCapcalera = "Producte; Data inici; Data fi; Descripció; Import; Comissió; Divisa; Estat; Saldo";
                        transferencies.add(novaCapcalera);
                        pagaments.add(novaCapcalera);
                        recarregues.add(novaCapcalera);
                        
                        for (String linia : liniesMoviments) {
                            String[] columnes = linia.split(";");
                            String tipusMoviment = columnes[0];

                            String liniaSenseTipus = linia.substring(linia.indexOf(";") + 1);

                            if (tipusMoviment.equals("Transferir")) {
                                transferencies.add(liniaSenseTipus);
                            }
                            else if (tipusMoviment.equals("Pago con tarjeta") || tipusMoviment.equals("Reembolso de tarjeta")) {
                                pagaments.add(liniaSenseTipus);
                            }
                            else if (tipusMoviment.equals("Recargas")) {
                                recarregues.add(liniaSenseTipus);
                            }
                            else{
                                altres.add(linia);
                            }
                        }

                            rc.escriureFitxer(RUTA_ENTRADA.resolve("transferencies.csv"),transferencies);
                            rc.escriureFitxer(RUTA_ENTRADA.resolve("pagaments.csv"), pagaments);
                            rc.escriureFitxer(RUTA_ENTRADA.resolve("recarregues.csv"), recarregues);
                            
                            List<String> transferenciesProcessades = new ArrayList<>();
                            transferenciesProcessades.add("Id; Producte; Data inici; Data fi; Descripció; Import; Comissió; Divisa; Estat; Saldo");
                            
                            int comptadorTR = 1;
                            for (int i = 1; i < transferencies.size(); i++) {
                                String liniaAntiga = transferencies.get(i);

                                String idFormatejat = "TR" + String.format("%05d", comptadorTR);
                                String liniaNova = idFormatejat + ";" + liniaAntiga;

                                transferenciesProcessades.add(liniaNova);
                                comptadorTR++;
                            }
                            rc.escriureFitxer(RUTA_PROCESSATS.resolve("transferencies.csv").resolveSibling("transferencies_p.csv"), transferenciesProcessades);

                            List<String> pagamentsProcessats = new ArrayList<>();
                            pagamentsProcessats.add("Id; Producte; Data inici; Data fi; Descripció; Import; Comissió; Divisa; Estat; Saldo");
                            
                            int comptadorPG = 1;
                            for (int i = 1; i < pagaments.size(); i++) {
                                String liniaAntiga = pagaments.get(i);

                                String idFormatejat = "PG" + String.format("%05d", comptadorPG);
                                String liniaNova = idFormatejat + ";" + liniaAntiga;

                                pagamentsProcessats.add(liniaNova);
                                comptadorPG++;
                            }
                            rc.escriureFitxer(RUTA_PROCESSATS.resolve("pagaments.csv").resolveSibling("pagaments_p.csv"), pagamentsProcessats);

                            List<String> recarreguesProcessades = new ArrayList<>();
                            recarreguesProcessades.add("Id; Producte; Data inici; Data fi; Descripció; Import; Comissió; Divisa; Estat; Saldo");
                            
                            int comptadorRE = 1;
                            for (int i = 1; i < recarregues.size(); i++) {
                                String liniaAntiga = recarregues.get(i);

                                String idFormatejat = "RE" + String.format("%05d", comptadorRE);
                                String liniaNova = idFormatejat + ";" + liniaAntiga;

                                recarreguesProcessades.add(liniaNova);
                                comptadorRE++;
                        }
                        rc.escriureFitxer(RUTA_PROCESSATS.resolve("recarregues.csv").resolveSibling("recarregues_p.csv"), recarreguesProcessades);
                        
                        List<Statements> llistaStatements = new ArrayList<>();

                        for (String linia: altres) {
                            String[] columnes = linia.split(";");
                            
                            if (columnes.length == 10) {
                                Statements objectStatement = new Statements( 
                                    columnes[0], columnes[1], columnes[2], columnes[3], columnes[4], 
                                     columnes[5], columnes[6], columnes[7], columnes[8], columnes[9]
                                );
                                llistaStatements.add(objectStatement);
                            }
                        }
                        rc.serialitzarLlista(RUTA_ENTRADA.resolve("altres.dat"), llistaStatements);
                    }
                }

            } catch(IOException e) {
                System.out.println("Error a l'intentar crear la carpeta Processats: " + e.getMessage());
            }
                
        } else {
            System.out.println("La carpeta Entrada no existeix");
        }
    }
}