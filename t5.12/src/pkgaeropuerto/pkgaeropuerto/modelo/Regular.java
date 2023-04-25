package pkgaeropuerto.pkgaeropuerto.modelo;

import pkgaeropuerto.modelo.Vuelo;

public class Regular extends Vuelo {
    private int plazasLibres;

    public Regular(String destino, String modelo, int nPlazas, int plazasLibres) {
        super(destino, modelo, nPlazas);
        this.plazasLibres = plazasLibres;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }




    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vuelo Regular\n");
        sb.append("------------\n\n");
        sb.append(super.toString());
        sb.append("Plazas Libres: ").append(plazasLibres).append("\n");
        return sb.toString();
    }
}
