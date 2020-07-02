import java.util.ArrayList;

public class Tablero {

	private int[][] tablero;
	private int cantJugad;
	private int contadorTurnos;
	private Conjunto<Rectangulo> rectJugador;
	private Rectangulo ultimoRectangulo;
	private Rectangulo siguienteRectangulo;
	private Coordenadas nuevasCoordenadas;
	private ArrayList lista;

	public Tablero(int ancho, int alto, int cantJugad) {
		this.cantJugad = cantJugad;
		this.contadorTurnos = 1;
		this.rectJugador = new Conjunto <Rectangulo>();
		
		if ((ancho > 1 && alto > 0) || (ancho > 0 && alto > 1)) {
			this.tablero = new int[alto][ancho];
			for (int f = 0; f < this.altoTab(); f++) {
				for (int c = 0; c < this.anchoTab(); c++) {
					this.tablero[f][c] = 0; // el tablero comienza con ceros en todas sus posiciones
				}
			}
			
			for (int j = 0; j < cantJugad; j++) {
				Lista();
				insertar(rectJugador);
			}	
		}
	}
	
	public void Lista(){
 		lista = new ArrayList();
	}
	
	public void insertar(Conjunto rect){
 		this.lista.add(rect);
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int f = 0; f < this.altoTab(); f++) {
			for (int c = 0; c < this.anchoTab(); c++) {
				ret.append(" " + this.tablero[f][c] + " ");
			}
			ret.append("\n");
		}
		return ret.toString();
	}

	public void agregarRect (Rectangulo rect, int identificador) {
		for (int c = rect.coorden().getY(); c < rect.coorden().getY() + rect.alto(); c++) {
			for (int f = rect.coorden().getX(); f < rect.coorden().getX() + rect.ancho(); f++) {
				this.tablero[c][f] = identificador;
			}
		}				
		identificador++;
	
		if (identificador == contadorTurnos) {
			lista.add(identificador, rectJugador);
		}
		
		if (contadorTurnos == cantJugad) {
			contadorTurnos = 0;
			identificador = 0;
		}
		
		contadorTurnos++;
		this.ultimoRectangulo = rect;
	}

	
	/** antes de dibujar el rectangulo, verifico si hay lugar disponible para agregarlo*/
	public Rectangulo dibujarRectangulo(int altoTablero, int anchoTablero, int identificador) {
		for (int c = 0; c < this.altoTab(); c++) {
			for (int f = 6*(identificador-1); f < this.anchoTab(); f++) {	/* Le multiplico 6 al identificador para que el rectangulo de cada jugador
				   															  comience a dibujarse a una distancia de 6 lugares de su rival.
																			  O sea que cada jugador va a tener una columna de 6 lugares para ir agregando
																			  los rectangulos uno abajo del otro.  */
				if (puedeAgregarseEnTablero(f, c, altoTablero, anchoTablero)) {
					
					Coordenadas<Integer, Integer> nuevasCoord = new Coordenadas<Integer, Integer>(f, c);
					siguienteRectangulo = new Rectangulo(altoTablero, anchoTablero, nuevasCoord);
					
					Coordenadas<Integer, Integer> coord = new Coordenadas<Integer, Integer>(f, c);
					return new Rectangulo(altoTablero, anchoTablero, coord);
				}
			}
		}
		throw new RuntimeException("No se puede dibujar el rectangulo porque no hay mas espacios disponibles.");
	}
	
	
	
	public Rectangulo dibujarRect_Abajo(int altoTablero, int anchoTablero, int identificador, int desde) {
		for (int c = desde; c < this.altoTab(); c++) {
			for (int f = 6*(identificador-1); f < this.anchoTab(); f++) {  /* Le multiplico 6 al identificador para que el rectangulo de cada jugador
			 															   comience a dibujarse a una distancia de 6 lugares de su rival.
			 															   O sea que cada jugador va a tener una columna de 6 lugares para ir agregando
			 															   los rectangulos uno abajo del otro.  */
				if (puedeAgregarseEnTablero(f, c, altoTablero, anchoTablero)) {
					Coordenadas<Integer, Integer> coord = new Coordenadas<Integer, Integer>(f, c);
					return new Rectangulo(altoTablero, anchoTablero, coord);
				}
			}
		}
		throw new RuntimeException("No se puede dibujar el rectangulo porque no hay mas espacios disponibles.");
	}
	
	public int altoTab() {
		return this.tablero.length;
	}

	public int anchoTab() {
		return this.tablero[0].length;
	}
	
	/** Compruebo si se puede agregar un rectangulo dentro del tablero sin superponerse con otro que haya sido agregado */
	public boolean puedeAgregarseEnTablero(int posX, int posY, int altoTablero, int anchoTablero) {
		boolean ret = true;
		ret = ret && ((posY + altoTablero) <= this.altoTab()) && ((posX + anchoTablero) 
				<= this.anchoTab()) && !seSuperponenLosRectangulos(posX, posY, altoTablero, anchoTablero);
		return ret;
	}
	
	
	/** Compruebo que no hayan rectangulos de ningun en donde intento agregar uno nuevo */
	public boolean seSuperponenLosRectangulos(int posX, int posY, int altoTablero, int anchoTablero) {
		boolean ret = false;
		for (int f = posY; f < posY + altoTablero; f++) {
			for (int c = posX; c < posX + anchoTablero; c++) {
				ret = ret || this.tablero[f][c] != 0;
			}
		}
		return ret;
	}
	

	
	public Conjunto <Rectangulo> rectJugador() {
		return this.rectJugador;
	}
	
	public Rectangulo siguienteRectangulo() {
		return this.siguienteRectangulo;
	}

	public Rectangulo ultimoRectDelJugador() {
		return this.ultimoRectangulo;
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
		
		Tablero other = (Tablero) obj;
		if (this.rectJugador == null) {
			if (other.rectJugador() != null) {
				return false;				
			}
		} 
		
		else if (!this.rectJugador.equals(other.rectJugador())) {
			return false;			
		}
		
		if (this.siguienteRectangulo == null) {
			if (other.siguienteRectangulo() != null) {
				return false;				
			}	
		} 
		
		else if (!this.siguienteRectangulo.equals(other.siguienteRectangulo())) {
			return false;			
		}
		
		if (this.altoTab() != other.altoTab() || this.anchoTab() != other.anchoTab()) {
			return false;			
		}
		
		else if (this.altoTab() == other.altoTab() && this.anchoTab() == other.anchoTab()) {
			boolean ret = true;
			for (int f = 0; f < this.altoTab(); f++) {
				for (int c = 0; c < this.anchoTab(); c++) {
					ret = ret && this.tablero[f][c] == other.tablero[f][c];
				}
			}
			return ret;
		}
		if (this.ultimoRectangulo == null) {
			if (other.ultimoRectDelJugador() != null) {
				return false;				
			}
		} 
		
		else if (!this.ultimoRectangulo.equals(other.ultimoRectDelJugador())) {
			return false;			
		}
		return true;
	}
}
