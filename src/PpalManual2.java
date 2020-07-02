public class PpalManual2 {
	public static void main(String[] args) {
		GR gr = new GR(31, 30, 3);
		
		gr.enableManual();  /** Habilitamos el isManual para poder devolver un ganador sin que hayan habido turnos perdidos. 
		   					Esta mejor definido al final del TRY de la clase GR. */
		
							/*Dependiendo de la cantidad de jugadores, hay que modificar el alto y el ancho. Ya que, si no se modifican, no se cumple
						     el segundo FOR del metodo "dibujarRectangulo" y del metodo "dibujarRect_Abajo". Por lo que afecta
						     el funcionamiento del mismo y solo agrega los rectangulos de una cierta cantidad de jugadores. */
		gr.jugar(4, 2);
		System.out.println(gr.toString());
		
		gr.jugar(5, 5);
		System.out.println(gr.toString());
		
		gr.jugar(2, 3);
		System.out.println(gr.toString());
		
		gr.jugar(6, 6);
		System.out.println(gr.toString());
		
		gr.jugar(4, 1);
		System.out.println(gr.toString());
		
		gr.jugar(5, 4);
		System.out.println(gr.toString());
		
		gr.jugar(6, 5);
		System.out.println(gr.toString());
		
		gr.jugar(1, 3);
		System.out.println(gr.toString());
		
		gr.jugar(5, 5);
		System.out.println(gr.toString());
		
		gr.jugar(2, 3);
		System.out.println(gr.toString());
		
		gr.jugar(1, 6);
		System.out.println(gr.toString());
		
		gr.jugar(6, 1);
		System.out.println(gr.toString());
		
		gr.jugar(6, 5);
		System.out.println(gr.toString());
		
		gr.jugar(5, 3);
		System.out.println(gr.toString());
		
		gr.jugar(4, 6);
		System.out.println(gr.toString());
		
		gr.jugar(6, 6);
		System.out.println(gr.toString());
	}
}
