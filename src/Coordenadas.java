public class Coordenadas<X, Y> {

	private int x, y;

	public Coordenadas(int posX, int posY) {
		this.x = posX;
		this.y = posY;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override  /** Colocamos una condicion para que si el usuario ingresa un numero negativo,
		no funcione el juego y se de cuenta que tienen que ser numeros positivos. */

	
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
		
		Coordenadas<X, Y> other = (Coordenadas<X, Y>) obj;
		if (this.x != other.getX()) {
			return false;			
		}
		
		if (this.y != other.getY()) {
			return false;
		}
		
		return true;
	}
}
