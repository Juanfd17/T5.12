package pkgaeropuerto.pkgaeropuerto.modelo;

import pkgaeropuerto.modelo.Charter;
import pkgaeropuerto.modelo.ComparadorPorPlazas;
import pkgaeropuerto.modelo.Regular;
import pkgaeropuerto.modelo.Vuelo;

import java.util.*;

public class Aeropuerto {

	private Map<String, Set<pkgaeropuerto.modelo.Vuelo>> vuelos;

	public Aeropuerto() {
		vuelos = new TreeMap<>();
	}

	/**
	 * Añade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
	 * no estuviese ya introducido, si la aerolinea no existiese se añade tanto la
	 * aerolinea como el vuelo.
	 */
	public void addVuelo(String aerolinea, pkgaeropuerto.modelo.Vuelo vuelo) {
		Set<pkgaeropuerto.modelo.Vuelo> vueleosKey = vuelos.get(aerolinea);
		if (vueleosKey == null){
			vueleosKey = new TreeSet<>(pkgaeropuerto.modelo.Vuelo::compareTo);
		}

		vueleosKey.add(vuelo);
		vuelos.put(aerolinea, vueleosKey);
	}

	/**
	 * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
	 * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
	 */
	public void imprimirAeropuerto() {
		System.out.println(toString());
	}

	/**
	 * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
	 * visualizaran de mayor a menor segun el numero de plazas
	 *
	 * @param aerolinea
	 *            Aerolinea de la que se imprimiran los vuelos regulares
	 */
	public void regularPorPlazas(String aerolinea) {
		Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
		Set<pkgaeropuerto.modelo.Regular> vuelosRegulares = new TreeSet<>(new ComparadorPorPlazas());
		for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
			if (vuelo instanceof pkgaeropuerto.modelo.Regular){
				vuelosRegulares.add((pkgaeropuerto.modelo.Regular) vuelo);
			}
		}

		for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosRegulares) {
			System.out.println(vuelo);
		}
	}

	/**
	 * Devuelve una lista con vuelos regulares con plazas libres
	 *
	 * @return aerolina Aerolina del avion charter con más capacidad
	 */
	public List<pkgaeropuerto.modelo.Vuelo> plazasLibres() {
		Set<String> aerolineas = vuelos.keySet();
		List<pkgaeropuerto.modelo.Vuelo> vuelosLibres = new ArrayList<>();
		for (String aerolinea: aerolineas) {
			Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
				if (vuelo instanceof pkgaeropuerto.modelo.Regular){
					if (((pkgaeropuerto.modelo.Regular) vuelo).getPlazasLibres() > 0){
						vuelosLibres.add(vuelo);
					}
				}
			}
		}

		return vuelosLibres;
	}

	/**
	 * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
	 * por parametro, ver resultados de ejecucion
	 *
	 * @param destino
	 *            Destino del que se debe sacar la estadistica
	 */
	public void estadisticaDestino(String destino) {
		System.out.println("Estadistica de los vuelos con destino a MAD\n");
		Set<String> aerolineas = vuelos.keySet();
		for (String aerolinea: aerolineas) {
			Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			int nVuelos = 0;
			for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
				if (vuelo.getDestino().equals(destino)){
					nVuelos++;
				}
			}
			System.out.println(nVuelos + " de cada " + vuelosAerolinea.size() + " de ka aerolinea " + aerolinea + " vuelan a " + destino + "\n");
		}
	}

	/**
	 * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
	 * borrados, utiliza un conjunto de entradas
	 *
	 * @param nifEmpresa
	 * @return numero de vuelos borrados
	 */
	public int borrarVuelosEmpresa(String nifEmpresa) {
		int vuelosBorrados = 0;
		Set<String> aerolineas = vuelos.keySet();
		for (String aerolinea : aerolineas) {
			Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			Iterator<pkgaeropuerto.modelo.Vuelo> iterador = vuelosAerolinea.iterator();
			while (iterador.hasNext()) {
				pkgaeropuerto.modelo.Vuelo vuelo = iterador.next();
				if (vuelo instanceof pkgaeropuerto.modelo.Charter && ((pkgaeropuerto.modelo.Charter) vuelo).getNif().equals(nifEmpresa)) {
					iterador.remove();
					vuelosBorrados++;
				}
			}
		}

		return vuelosBorrados;
	}

	/**
	 * Imprime la lista de vuelos pasada por parametro
	 *
	 * @param listaVuelos
	 */
	public void imprimirListaVuelos(List<pkgaeropuerto.modelo.Vuelo> listaVuelos) {
		for (pkgaeropuerto.modelo.Vuelo vuelo: listaVuelos) {
			System.out.println(vuelo);
		}
	}

	public void imprimirPasajerosPorAerolinea(String aerolinea){
		Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
		int pasageros = 0;
		for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
			if (vuelo instanceof pkgaeropuerto.modelo.Regular){
				pasageros += vuelo.getnPlazas() - ((pkgaeropuerto.modelo.Regular) vuelo).getPlazasLibres();
			} else {
				pasageros += vuelo.getnPlazas();
			}
		}

		System.out.println("La aerolina " + aerolinea + " ha desplazado a " + pasageros + " pasageros");
	}

	public void imprimirVuelosMasPasajerosQueMedia(){
		Set<String> aerolineas = vuelos.keySet();
		for (String aerolinea: aerolineas) {
			Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			double media = 0;
			for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
				media += vuelo.getnPlazas();
			}

			media = media / vuelosAerolinea.size();

			System.out.println("La media de plazas de los vuelos de " + aerolinea + " es de " + media);
			System.out.println("Los Vuelos de " + aerolinea + " con mas plaxas que la media son:");
			for (pkgaeropuerto.modelo.Vuelo vuelo: vuelosAerolinea) {
				if (vuelo.getnPlazas() >= media){
					System.out.println(vuelo);
				}
			}
		}
	}

	/**
	 * Represetación textual del mapa tal y como se muestra en el enunciado
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<String> aerolineas = vuelos.keySet();
		for (String aerolinea: aerolineas) {
			sb.append(aerolinea);
			sb.append("\n========\n");
			Set<pkgaeropuerto.modelo.Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			for (Vuelo vuelo: vuelosAerolinea) {
				sb.append(vuelo).append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Rellena el mapa haciendo uso de un fichero de texto
	 */
	public void leerFicheroCursos() {
		Scanner entrada = null;
		try {
			entrada = new Scanner(this.getClass().getResourceAsStream("../../aviones.txt"));
			while (entrada.hasNextLine()) {
				String linea = entrada.nextLine();
				int pos = linea.indexOf(":");
				String aerolinea = linea.substring(0, pos);
				String[] vuelo = linea.substring(pos + 1).split(":");
				String destino = vuelo[1];
				String avion = vuelo[2];
				int plazas = Integer.parseInt(vuelo[3].trim());
				if (vuelo[0].equals("R")) {
					int plazasLibres = Integer.parseInt(vuelo[4].trim());
					this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres));
				} else {
					String nifEmpresa = vuelo[4];
					this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
				}
			}

		} finally {
			try {
				entrada.close();
			} catch (NullPointerException e) {
				System.out.println("Error en IO , no se ha creado el fichero");
			}
		}

	}

}
