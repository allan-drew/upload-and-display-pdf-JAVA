package dev.aadc.model;


public class Contratos {

    private Integer idContrato;
    private String contratoFileName;

    public Contratos(Integer idContrato, String contratoFileName) {
        this.idContrato = idContrato;
        this.contratoFileName = contratoFileName;
    }

    public Contratos(String contratoFileName) {
        this(null, contratoFileName);
    }


    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContratoFileName() {
        return contratoFileName;
    }

    public void setContratoFileName(String contratoFileName) {
        this.contratoFileName = contratoFileName;
    }

    @Override
    public String toString() {
        return "Contratos{" +
                "idContrato=" + idContrato +
                ", contratoFileName='" + contratoFileName + '\'' +
                '}';
    }


}
