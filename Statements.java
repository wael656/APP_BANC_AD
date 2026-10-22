import java.io.Serializable;

public class Statements implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipusMoviment;
    private String producte;
    private String dataInici;
    private String dataFi;
    private String descripcio;
    private String importMoviment;
    private String comissio;
    private String divisa;
    private String estat;
    private String saldo;

    public Statements(String tipusMoviment, String producte, String dataInici, String dataFi,
        String descripcio, String importMoviment, String comissio,
        String divisa, String estat, String saldo) {
        this.tipusMoviment = tipusMoviment;
        this.producte = producte;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.descripcio = descripcio;
        this.importMoviment = importMoviment;
        this.comissio = comissio;
        this.divisa = divisa;
        this.estat = estat;
        this.saldo = saldo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTipusMoviment() {
        return tipusMoviment;
    }

    public void setTipusMoviment(String tipusMoviment) {
        this.tipusMoviment = tipusMoviment;
    }

    public String getProducte() {
        return producte;
    }

    public void setProducte(String producte) {
        this.producte = producte;
    }

    public String getDataInici() {
        return dataInici;
    }

    public void setDataInici(String dataInici) {
        this.dataInici = dataInici;
    }

    public String getDataFi() {
        return dataFi;
    }

    public void setDataFi(String dataFi) {
        this.dataFi = dataFi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getImportMoviment() {
        return importMoviment;
    }

    public void setImportMoviment(String importMoviment) {
        this.importMoviment = importMoviment;
    }

    public String getComissio() {
        return comissio;
    }

    public void setComissio(String comissio) {
        this.comissio = comissio;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

}