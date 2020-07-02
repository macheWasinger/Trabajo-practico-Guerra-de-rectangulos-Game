import java.util.ArrayList;
import java.util.Iterator;

public class Conjunto<T extends Comparable<T>> {

	private ArrayList<T> datos;
	private T max;
	private T min;

	public Conjunto() {
		this.datos = new ArrayList<T>();
	}

	public int tamanio() {
		return datos.size();
	}
	
	public void agrega(T elemento) {
		if (!contiene(elemento)) {
			if (tamanio() == 0) {
				max = elemento;
				min = elemento;
			} 
			
			else {
				if (elemento.compareTo(max) == 1) { /** Si se cumple que compareTo() es mayor, va a devolver 1*/
					max = elemento;
				}
				if (elemento.compareTo(min) == -1) { /** Si se cumple que compareTo() es mayor, va a devolver 1 */
					min = elemento;
				}
			}
			datos.add(elemento);
		}
	}

	public T getIesimo(int indice) { /** Me da el elemento "i" de la coleccion */
		return datos.get(indice);
	}
	
	public void eliminarPorIndice(int indice) {
		datos.remove(indice);
	}

	public void eliminarPorElemento(T elem) {
		for (int i = 0; i < tamanio(); i++) {
			if (getIesimo(i).equals(elem)) {
				datos.remove(i);
			}
		}
	}
	
	public boolean contiene(T elemento) {
		boolean ret = true;
		ret = ret && datos.contains(elemento);
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		Iterator <T> it = this.datos.iterator();
		while (it.hasNext()) {
			string.append (it.next().toString());
		}
		string.deleteCharAt (string.length() - 1);
		string.deleteCharAt (string.length() - 1);
		return string.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = true;
		
		if (obj == null) {
			ret = false;
		}
		
		if (this.getClass() != obj.getClass()) {
			ret = false;
		}
		
		if (ret) {
			Conjunto<T> other = (Conjunto<T>) obj;
			
			if (this.tamanio() != other.tamanio()) {
				ret = false;
			}
			
			for (int i = 0; i < this.tamanio(); i++) {
				ret = ret && this.getIesimo(i).equals(other.getIesimo(i));
			}
		}
		
		return ret;
	}
}
