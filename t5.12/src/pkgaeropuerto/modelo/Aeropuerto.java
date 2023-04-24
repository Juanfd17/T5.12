package pkgaeropuerto.modelo;

import java.util.*;

public class Aeropuerto {

	private Map<String, Set<Vuelo>> vuelos;

	public Aeropuerto() {
		vuelos = new TreeMap<>();
	}

	/**
	 * Añade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
	 * no estuviese ya introducido, si la aerolinea no existiese se añade tanto la
	 * aerolinea como el vuelo.
	 */
	public void addVuelo(String aerolinea, Vuelo vuelo) {
		Set<Vuelo> vueleosKey = vuelos.get(aerolinea);
		if (vueleosKey == null){
			vueleosKey = new TreeSet<>(vuelo);
		}

		vueleosKey.add(vuelo);
		vuelos.put(aerolinea, vueleosKey);
	}

	/**
	 * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
	 * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
	 */
	public void ordenAerolineasAlfabetico() {
		Set<String> aerolineas = vuelos.keySet();
		for (String aerolinea: aerolineas) {
			System.out.println(aerolinea);
			System.out.println("========\n");
			Set<Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			for (Vuelo vuelo: vuelosAerolinea) {
				System.out.println(vuelo);
			}
		}
	}

	/**
	 * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
	 * visualizaran de mayor a menor segun el numero de plazas
	 *
	 * @param aerolinea
	 *            Aerolinea de la que se imprimiran los vuelos regulares
	 */
	public void regularPorPlazas(String aerolinea) {
		Set<Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
		Set<Regular> vuelosRegulares = new TreeSet<>();
		for (Vuelo vuelo: vuelosAerolinea) {
			if (vuelo instanceof Regular){
				vuelosRegulares.add((Regular) vuelo);
			}
		}

		for (Vuelo vuelo: vuelosRegulares) {
			System.out.println(vuelo);
		}
	}

	/**
	 * Devuelve una lista con vuelos regulares con plazas libres
	 *
	 * @return aerolina Aerolina del avion charter con más capacidad
	 */
	public List<Vuelo> plazasLibres() {
		Set<String> aerolineas = vuelos.keySet();
		List<Vuelo> vuelosLibres = new ArrayList<>();
		for (String aerolinea: aerolineas) {
			Set<Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			for (Vuelo vuelo: vuelosAerolinea) {
				if (vuelo instanceof Regular){
					if (((Regular) vuelo).getPlazasLibres() > 0){
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
			Set<Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			int nVuelos = 0;
			for (Vuelo vuelo: vuelosAerolinea) {
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
			Set<Vuelo> vuelosAerolinea = vuelos.get(aerolinea);
			Iterator<Vuelo> iterador = vuelosAerolinea.iterator();
			while (iterador.hasNext()) {
				Vuelo vuelo = iterador.next();
				if (vuelo instanceof Charter && ((Charter) vuelo).getNif().equals(nifEmpresa)) {
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
	public void imprimirListaVuelos(List<Vuelo> listaVuelos) {
		for (Vuelo vuelo: listaVuelos) {
			System.out.println(vuelo);
		}
	}

	/**
	 * Represetación textual del mapa tal y como se muestra en el enunciado
	 */
	public String toString() {
		return null;
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
