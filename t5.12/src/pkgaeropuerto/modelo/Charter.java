package pkgaeropuerto.modelo;

public class Charter extends Vuelo {
    private String nif;

    public Charter(String destino, String modelo, int nPlazas, String nif) {
        super(destino, modelo, nPlazas);
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vuelo Charter\n");
        sb.append("------------\n\n");
        sb.append(super.toString());
        sb.append("Nif empresa: ").append(nif).append("\n");
        return sb.toString();
    }
}
