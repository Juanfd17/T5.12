package pkgaeropuerto.modelo;

import java.util.Comparator;

public class ComparadorPorPlazas implements Comparator<Regular> {
    public int compare(Regular o1, Regular o2){
        return o2.getPlazasLibres() - o1.getPlazasLibres();
    }
}
