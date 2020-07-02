public class PpalAutomatico {
	
	public static void main(String[] args) {
		GR gr = new GR(36, 30, 6); /*Dependiendo de la cantidad de jugadores, hay que modificar el alto y el ancho. Ya que, si no se modifican, no se cumple
		 						     el segundo FOR del metodo "dibujarRectangulo" y del metodo "dibujarRect_Abajo". Por lo que afecta
		 						     el funcionamiento del mismo y solo agrega los rectangulos de una cierta cantidad de jugadores. */
		String ganador = gr.jugar();
		while (ganador == "") {
			ganador = gr.jugar();
		}
		
		System.out.println(gr);
	}
}
