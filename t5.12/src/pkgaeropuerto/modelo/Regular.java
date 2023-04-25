package pkgaeropuerto.modelo;

public class Regular extends Vuelo{
    private int plazasLibres;

    public Regular(String destino, String modelo, int nPlazas, int plazasLibres, int precio) {
        super(destino, modelo, nPlazas, precio);
        this.plazasLibres = plazasLibres;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    public int precioFinal (){
        return (int) ((super.getPrecio() * 1.1) + (5 * getPlazasLibres()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vuelo Regular\n");
        sb.append("------------\n\n");
        sb.append(super.toString());
        sb.append("Precio billete: ").append(precioFinal()).append("\n");
        sb.append("Plazas Libres: ").append(plazasLibres).append("\n");
        return sb.toString();
    }
}
