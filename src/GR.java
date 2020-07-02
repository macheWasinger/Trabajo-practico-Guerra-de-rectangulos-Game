import java.util.Random;

import org.w3c.dom.css.Rect;

public class GR {

	private Tablero tablero;
	private String winner;
	private Boolean primerosRect_jugadores;
	private Boolean siguientesRect_jugadores;
	private Boolean terminoElJuego;
	private Rectangulo primerosRec;
	private Rectangulo siguientesRec;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private int turnoDelJugador;
	private int turnosPerdidos;
	private int cantidadJugadores;
	private int idTurnos;
	private int leTocaAlJugador;
	private int[] ultimaPosi = new int[200];
	
	private int[] vectorRectAreas;
	private int indiceVector;
	private int acumArea_RecVector;
	private int[] vector_TOTALareasAcumuladas;
	private boolean isManual;
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GR(int ancho, int alto, int cantidadJugadores) {
		this.tablero = new Tablero(ancho, alto, cantidadJugadores);
		this.cantidadJugadores = cantidadJugadores;
		this.vectorRectAreas = new int[this.cantidadJugadores];
		this.indiceVector = 0;
		this.vector_TOTALareasAcumuladas = new int[this.cantidadJugadores];
		this.acumArea_RecVector = 0;
		this.idTurnos = 1;
		this.turnoDelJugador = 0;
		this.turnosPerdidos = 0;
		this.winner = "";
		this.primerosRect_jugadores = true;
		this.siguientesRect_jugadores = false;
		this.terminoElJuego = false;
	}
	
	public void enableManual() {  /*Habilitamos el isManual y se activa dentro del TRY*/
		this.isManual = true;
	}

	public int tirarDado1() {
		Random r1 = new Random();
		int tiro1 = r1.nextInt(5) + 1;
		return tiro1;
	}
	
	public int tirarDado2() {
		Random r2 = new Random();
		int tiro2 = r2.nextInt(5) + 1;
		return tiro2;
	}
	
	public String jugar() { /** MODO AUTOMATICO*/
		int dado1 = tirarDado1(); /* EL PRIMER DADO ES PARA OBTENER UN VALOR RANDOM DEL ALTO DEL RECTANGULO. */
		int dado2 = tirarDado2(); /* EL SEGUNDO DADO ES PARA OBTENER UN VALOR RANDOM DEL ANCHO DEL RECTANGULO. */
		if(dado2 <= 5) { /**Pusimos esa condicion para que los rectangulos de los jugadores no puedan tener un ancho mayor a 5
		                  para que, si les toca un ancho de tamaño 6, no puedan colocar su rectangulo y pierdan un turno.  */
			return jugar(dado1, dado2); 
		}else {
			return "";
		}
	}

	public String jugar(int altoRect, int anchoRect) { /** MODO MANUAL*/
		leTocaAlJugador = idTurnos;
		
		if (altoRect >= 1 && altoRect <= 6 && anchoRect >= 1 && anchoRect <= 6) {
			try {
				if (this.primerosRect_jugadores) { //AGREGAR LOS PRIMEROS RECTANGULOS DE CADA JUGADOR
					System.out.println("Turno del " + idTurnos + "° jugador: " + "\n" + "alto: " + altoRect + ", ancho: " + anchoRect + "\n");	
					primerosRec = tablero.dibujarRectangulo (altoRect, anchoRect, idTurnos);
					ultimaPosi[idTurnos] = primerosRec.alto()   + primerosRec.coorden().getY();
					tablero.agregarRect(primerosRec, idTurnos);
					this.vectorRectAreas[this.indiceVector] = this.vectorRectAreas[this.indiceVector] + primerosRec.area();
					turnosPerdidos = 0;		
				}
				
				if (this.siguientesRect_jugadores) { //AGREGA LOS SIGUIENTES RECTANGULOS DE CADA JUGADOR
					System.out.println("Turno del " + idTurnos + "° jugador: " + "\n" + "alto: " + altoRect + ", ancho: " + anchoRect + "\n");
					siguientesRec = tablero.dibujarRect_Abajo (altoRect, anchoRect, idTurnos, ultimaPosi[idTurnos]);
					ultimaPosi[idTurnos] = siguientesRec.alto()   + siguientesRec.coorden().getY();
					
					tablero.agregarRect(siguientesRec, idTurnos);
					this.vectorRectAreas[this.indiceVector] = this.vectorRectAreas[this.indiceVector] + siguientesRec.area();
					turnosPerdidos = 0;
				}
				
				if (indiceVector == cantidadJugadores - 1) {  /* indiceVector es un contador y, si es igual a cantidadJugadores - 1, 
				 											quiere decir que el area del rectangulo de cada jugador, se guardo dentro del vectorRectAreas.
				 											Y si no es igual, quiere decir que hay lugar disponible dentro del vector. Por lo que entra 
				 											en el ELSE y se guarda el area acumulada de cada jugador.
				 											A la variable cantidadJugadores, le resto 1 para que se pueda comparar con el indiceVector. 
				 											Ya que, se inicializa en 0 y se encarga de recorrer cada indice del vectorRectAreas */	
					
					this.acumArea_RecVector = this.vectorRectAreas[this.indiceVector];
					this.vectorRectAreas[this.indiceVector] = this.acumArea_RecVector;
					System.out.println("EL AREA ACUMULADA DEL " + idTurnos + "° JUGADOR ES: " + this.vectorRectAreas[this.indiceVector] + "\n");
					indiceVector = 0;
				}
				
				else {
					this.acumArea_RecVector = this.vectorRectAreas[this.indiceVector];
					this.vectorRectAreas[this.indiceVector] = this.acumArea_RecVector;
					System.out.println("EL AREA ACUMULADA DEL " + idTurnos + "° JUGADOR ES: " + this.vectorRectAreas[this.indiceVector] + "\n");
					indiceVector++;
				}
				
				if (idTurnos == cantidadJugadores) { 
					System.out.println("/////////////////...NUEVA RONDA.../////////////////" + "\n");
					this.primerosRect_jugadores = false;
					this.acumArea_RecVector = 0;
					idTurnos = 1;
					this.siguientesRect_jugadores = true;
				}
				
				else {
					idTurnos++;
				}
				
				
				if(this.isManual) {  /* Con "isManual" forzamos a turnosPerdidos a que sea 2 (como si fuese que hubieron 2 turnos perdidos) 
				 						para se cumple la condicion "if (turnosPerdidos == 2)" asi finaliza el juego y devuelve un ganador
				 						para el modo manual sin que hayan habido 2 turnos perdidos.  */
					turnosPerdidos = 2;
				}
			}
			
			catch (RuntimeException e) {
				
				turnosPerdidos++;
				System.out.println("EL JUGADOR " + idTurnos + "°, HA PERDIDO " + turnosPerdidos + " TURNO/S");
				System.out.println(ultimaPosi[idTurnos]);
				System.out.println("El rectangulo con las medidas " + altoRect + " de alto y " + anchoRect + " de ancho " 
						+ "\n no se puede agregar en el tablero porque no hay mas casilleros vacios. \n");
			}
			
			if (turnosPerdidos == 2) {
				this.terminoElJuego = true;
				this.winner = decimeElGanador();
			}
		}
		return winner;
	}
	
	public int area (int jugador) {
		int ret = 0;
		if (jugador == 1) {
			ret = this.vectorRectAreas[0];	
		}
		
		if (jugador == 2) {
			ret = this.vectorRectAreas[1];
		}
		return ret;
	}	
	
	public Rectangulo ultimoRectangulo() {
		return tablero.ultimoRectDelJugador();
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(tablero.toString() + "\n");
		ret.append("El ultimo rectangulo agregado al tablero" + ultimoRectangulo() + "\n");
		ret.append(winner);
		
		return ret.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		
		if (obj == null) {
			return false;			
		}
		
		if (this.getClass() != obj.getClass()) {
			return false;			
		}
		
		GR other = (GR) obj;
		if (winner == null) {
			if (other.winner != null) {
				return false;					
			}
		} 
		
		else if (!winner.equals(other.winner)) {
			return false;			
		}
		
		if (tablero == null) {
			if (other.tablero != null) {
				return false;				
			}	
		} 
		
		else if (!tablero.equals(other.tablero)) {
			return false;			
		}

		if (this.turnoDelJugador != other.turnoDelJugador) {
			return false;			
		}
		
		if (this.turnosPerdidos != other.turnosPerdidos) {
			return false;			
		}
		
		return true;
	}
	
	private String decimeElGanador() {
		if (this.terminoElJuego) {
			return this.quienGano();
		}
		return "";
	}
	
	
	private String quienGano() {
		int mayorArea = 0;
		int mayorIndice = 1;
		for (int i = 0; i < this.vectorRectAreas.length; i++) {
			if(this.vectorRectAreas[i] > mayorArea) {
				mayorIndice = i;
				mayorArea = this.vectorRectAreas[i];
			}
		}
		return "Ha ganado el " + (mayorIndice + 1) + "° jugador con un area de " + this.vectorRectAreas[mayorIndice];
	}
}
