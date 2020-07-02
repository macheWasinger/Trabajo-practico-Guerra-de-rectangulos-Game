import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class Testing {
	private GR gr1;
	private GR gr2;
	private GR gr3;
	private int[] vectorRect;

	@Before
	public void setUp() {
		gr1 = new GR(16, 13, 2);
		gr2 = new GR(12, 10, 2);
		gr3 = new GR(16, 13, 2);
	}

	@Test
	public void testEquals1() {
		assertTrue(gr1.equals(gr3)); /** Para que se cumpla, gr1 debe ser igual a gr3 y diferente a gr2*/
		assertTrue(!gr1.equals(gr2));
	}

	@Test
	public void testEquals2() {
		gr1.jugar(3, 1);
		gr2.jugar(2, 4);
		gr3.jugar(3, 1);
		assertTrue(gr1.equals(gr3)); /** Para que se cumpla, gr1 debe ser igual a gr3 y diferente a gr2*/
		assertTrue(!gr1.equals(gr2)); 
	}

	@Test
	public void testArea() {
	gr1.jugar();
	Rectangulo h1 = gr1.ultimoRectangulo();
	gr1.jugar();
	Rectangulo h2 = gr1.ultimoRectangulo();	
	
	int suma = gr1.area(1) + gr1.area(2); //COLOCAMOS LOS PRINTS SOLAMENTE PARA IMPRIMIR LOS VALORES EN LA CONSOLA Y CORROBORAR QUE SON IGUALES.
	System.out.println("gr1.area(1) : " + gr1.area(1) + "   gr1.area(2) : " + gr1.area(2) + " = " + suma + "\n");
	
	int sumaH = h1.area() + h2.area();
	System.out.println("h1.area() : " + h1.area() + "   h2.area() : " + h2.area() + " = " + sumaH + "\n");
	
	assertEquals(gr1.area(1) + gr1.area(2), h1.area() + h2.area());
	}
}
