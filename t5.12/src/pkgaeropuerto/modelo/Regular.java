package pkgaeropuerto.modelo;

public class Regular extends Vuelo implements Comparable<Regular>{
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
    public int compareTo(Regular o) {
        int posicion = Integer.compare(o.plazasLibres, this.getPlazasLibres());

        if (posicion == 0){
            posicion = super.getDestino().compareTo(o.getDestino());
        }

        if (posicion == 0){
            posicion = super.getModelo().compareTo(o.getModelo());
        }

        return posicion;
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
