package pkgaeropuerto.modelo;

public class Charter extends Vuelo {
    private String nif;

    public Charter(String destino, String modelo, int nPlazas, String nif, int precio) {
        super(destino, modelo, nPlazas, precio);
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int precioFinal(){
        int precio = (int) (getPrecio() * 1.25);
        if (getnPlazas() < 200){
            precio += 50;
        }

        return precio;
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
