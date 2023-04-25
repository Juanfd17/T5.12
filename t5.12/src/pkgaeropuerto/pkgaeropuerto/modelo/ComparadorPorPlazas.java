package pkgaeropuerto.pkgaeropuerto.modelo;

import pkgaeropuerto.modelo.Regular;

import java.util.Comparator;

public class ComparadorPorPlazas implements Comparator<pkgaeropuerto.modelo.Regular> {
    public int compare(pkgaeropuerto.modelo.Regular o1, Regular o2){
        return o2.getPlazasLibres() - o1.getPlazasLibres();
    }
}
