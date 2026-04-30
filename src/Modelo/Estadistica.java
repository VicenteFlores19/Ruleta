package Modelo;

import java.util.List;

public class Estadistica {
	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias; // Ajustado a double para los decimales
	private int rachaMaxima;
	private TipoDeApuesta tipoMasJugado; // Ajustado al Enum

	public Estadistica() {
		this.totalJugadas = 0;
		this.victorias = 0;
		this.porcentajeVictorias = 0.0;
		this.rachaMaxima = 0;
		this.tipoMasJugado = null;
	}

	// --- GETTERS ---
	public int getTotalJugadas() { return totalJugadas; }
	public int getVictorias() { return victorias; }
	public double getPorcentajeVictorias() { return porcentajeVictorias; }
	public int getRachaMaxima() { return rachaMaxima; }
	public TipoDeApuesta getTipoMasJugado() { return tipoMasJugado; }

	/**
	 * Calcula todas las estadísticas basándose en el historial del usuario.
	 */
	public void calcular(List<Resultado> historial) {
		if (historial == null || historial.isEmpty()) {
			return; // Si no ha jugado, no calculamos nada
		}

		this.totalJugadas = historial.size();
		this.victorias = 0;
		int rachaActual = 0;
		this.rachaMaxima = 0;

		// Contadores para descubrir cuál es la apuesta más frecuente
		int contRojo = 0, contNegro = 0, contPar = 0, contImpar = 0;

		for (Resultado r : historial) {
			// 1. Calcular Victorias y Racha
			if (r.getGanancia() > 0) {
				this.victorias++;
				rachaActual++;
				if (rachaActual > this.rachaMaxima) {
					this.rachaMaxima = rachaActual; // Guardamos la racha más alta
				}
			} else {
				rachaActual = 0; // Si pierde, se corta la racha
			}

			// 2. Contar qué tipo de apuesta hizo
			switch (r.getTipoApuesta()) {
				case ROJO -> contRojo++;
				case NEGRO -> contNegro++;
				case PAR -> contPar++;
				case IMPAR -> contImpar++;
			}
		}

		// 3. Calcular Porcentaje (regla de 3 simple)
		this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100.0;

		// 4. Determinar la apuesta favorita (la que tenga el contador más alto)
		int max = Math.max(Math.max(contRojo, contNegro), Math.max(contPar, contImpar));
		if (max == contRojo) this.tipoMasJugado = TipoDeApuesta.ROJO;
		else if (max == contNegro) this.tipoMasJugado = TipoDeApuesta.NEGRO;
		else if (max == contPar) this.tipoMasJugado = TipoDeApuesta.PAR;
		else this.tipoMasJugado = TipoDeApuesta.IMPAR;
	}

	// sadjaskldja
}