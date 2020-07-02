public class Rectangulo implements Comparable<Rectangulo> {

	private Coordenadas<Integer, Integer> coord;  /** Contiene las coordenadas X e Y de los rectangulos */
	private int alto;
	private int ancho;

	public Rectangulo(int altoRect, int anchoRect, Coordenadas <Integer, Integer> coord) {
		if (coord.getX() >= 0 || coord.getY() >= 0) { /** Colocamos una condicion para que si el usuario ingresa un numero negativo,
		 												no funcione el juego y se de cuenta que tienen que ser numeros positivos. */
		
			this.alto = altoRect;
			this.ancho = anchoRect;
			this.coord = coord;
		} 		
	}

	public int area() {
		return this.ancho * this.alto;
	}

	public int ancho() {
		return this.ancho;
	}

	public int alto() {
		return this.alto;
	}

	public Coordenadas<Integer, Integer> coorden() {
		return coord;
	}
	
	
	public Coordenadas<Integer, Integer> getCoord() {
		return coord;
	}

	public void setCoord(Coordenadas<Integer, Integer> coord) {
		this.coord = coord;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	@Override  /** Heredo el toString para poder modificarlo y utilizarlo a mi manera */
	public String toString() {
		return " tiene las medidas: \n" + alto + " de alto y " + ancho + " de ancho. ";
	}

	@Override  /** Heredo el equals para poder modificarlo y utilizarlo a mi manera */
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
		
		Rectangulo other = (Rectangulo) obj;
		
		if (this.alto != other.alto()) {
			return false;			
		}
		
		if (this.ancho != other.ancho()) {
			return false;			
		}
		
		if (this.coord == null) {
			if (other.coorden() != null) {
				return false;				
			}
		} 
		
		else if (!this.coord.equals(other.coorden())) {
			return false;			
		}
		
		return true;
	}

	@Override
	public int compareTo(Rectangulo other) {
		if (this.area() > other.area()) {
			return 1;
		}
		
		if (this.area() < other.area()) {
			return -1;
		}
		return 0;
	}
}

