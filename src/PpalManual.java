public class PpalManual {

	public static void main(String[] args) {
		GR gr2 = new GR(60, 40, 8);
		gr2.enableManual(); /** Habilitamos el isManual para poder devolver un ganador sin que hayan habido turnos perdidos. 
		 					   Esta mejor definido al final del TRY de la clase GR. */
		
							/*Dependiendo de la cantidad de jugadores, hay que modificar el alto y el ancho. Ya que, si no se modifican, no se cumple
						     el segundo FOR del metodo "dibujarRectangulo" y del metodo "dibujarRect_Abajo". Por lo que afecta
						     el funcionamiento del mismo y solo agrega los rectangulos de una cierta cantidad de jugadores. */
		
		String ganador;
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		ganador = gr2.jugar();
		
		
		System.out.println(gr2);
	}
}
