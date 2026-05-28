package modelo;

import java.util.List;

public class Estadistica {
	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;

	// 1. Cambiado de TipoDeApuesta a String
	private String tipoMasJugado;

	public Estadistica() {
		this.totalJugadas = 0;
		this.victorias = 0;
		this.porcentajeVictorias = 0.0;
		this.rachaMaxima = 0;
		this.tipoMasJugado = "Ninguna"; // Valor por defecto
	}

	// --- GETTERS ---
	public int getTotalJugadas() { return totalJugadas; }
	public int getVictorias() { return victorias; }
	public double getPorcentajeVictorias() { return porcentajeVictorias; }
	public int getRachaMaxima() { return rachaMaxima; }

	// 2. Retorna String
	public String getTipoMasJugado() { return tipoMasJugado; }

	public void calcular(List<Resultado> historial) {
		if (historial == null || historial.isEmpty()) {
			return;
		}

		this.totalJugadas = historial.size();
		this.victorias = 0;
		int rachaActual = 0;
		this.rachaMaxima = 0;

		int contRojo = 0, contNegro = 0, contPar = 0, contImpar = 0;

		for (Resultado r : historial) {
			if (r.getGanancia() > 0) {
				this.victorias++;
				rachaActual++;
				if (rachaActual > this.rachaMaxima) {
					this.rachaMaxima = rachaActual;
				}
			} else {
				rachaActual = 0;
			}

			// 3. Evaluamos el String devuelto por Resultado
			switch (r.getTipoApuesta().toUpperCase()) {
				case "ROJO" -> contRojo++;
				case "NEGRO" -> contNegro++;
				case "PAR" -> contPar++;
				case "IMPAR" -> contImpar++;
			}
		}

		this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100.0;

		// 4. Asignamos la apuesta favorita como Texto
		int max = Math.max(Math.max(contRojo, contNegro), Math.max(contPar, contImpar));
		if (max == 0) this.tipoMasJugado = "Ninguna";
		else if (max == contRojo) this.tipoMasJugado = "ROJO";
		else if (max == contNegro) this.tipoMasJugado = "NEGRO";
		else if (max == contPar) this.tipoMasJugado = "PAR";
		else this.tipoMasJugado = "IMPAR";
	}
}