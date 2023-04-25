package pkgaeropuerto.app;

import pkgaeropuerto.modelo.Aeropuerto;

public class AppAeropuerto {

	public static void main(String[] args) {
		Aeropuerto aeropuerto = new Aeropuerto();

		aeropuerto.leerFicheroCursos();
		System.out.println("\nLista de vuelos ordenados alfabeticamente\n");
		aeropuerto.imprimirAeropuerto();
		System.out.println("\nVuelos ordenados por plaza de la compañia Vueling\n");
		aeropuerto.regularPorPlazas("Vueling");
		System.out.println("\nLista de vuelos con plazas libres\n");
		aeropuerto.imprimirListaVuelos(aeropuerto.plazasLibres());
		System.out.println("\nEstadistica de vuelos a Madrid\n");
		aeropuerto.estadisticaDestino("MAD");
		System.out.println("\nSe han borrado " + aeropuerto.borrarVuelosEmpresa("98735424") + " vuelos");
	}

}
