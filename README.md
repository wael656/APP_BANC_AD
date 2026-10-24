# APP_BANC_AD
# Activitat AppBanc - Accés a Dades (RA1)

Aplicació en Java amb arquitectura en capes que processa extractes bancaris en format CSV utilitzant l'API **NIO.2** (`Path`, `Files`, `DirectoryStream`), fluxos de lectura/escriptura en **UTF-8** (`BufferedReader`, `BufferedWriter`) i **serialització d'objectes** (`ObjectOutputStream`).

## 🎥 Vídeo explicatiu (Demo de 3 minuts)
👉 **[Fes clic aquí per veure el vídeo de l'activitat](https://1drv.ms/v/c/01548eaa533a5f82/IQAgo3EP76BYRpc_Nh1pldDfAVa_feok-IUui4C5XxAfh98?e=jUsNEi)**

---

## 📁 Estructura del projecte
* **`AppBanc.java`**: Classe principal (`main`) que coordina la lògica de negoci, la classificació dels moviments, la generació dels codis ID (`TR`, `PG`, `RE`) i el control d'excepcions.
* **`FileManager.java`**: Capa de gestió del sistema de fitxers i directoris amb NIO.2 (comprovació d'`Entrada`, creació de `Processats` i cerca amb `DirectoryStream`).
* **`RepositoriContingut.java`**: Capa d'accés al contingut dels fitxers en format UTF-8 i serialització binària a `altres.dat` utilitzant blocs `try-with-resources`.
* **`Statements.java`**: Classe model (POJO) que implementa `Serializable` per emmagatzemar els moviments de la categoria *Altres*.