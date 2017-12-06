package defaultmethode.resolution;

public class Cprime extends D implements B, A {

	/**
	 * La Règle 1 indique qu’une déclaration de méthode dans la classe est
	 * prioritaire. Mais D n’override pas hello; il implémente l’interface A.
	 * Par conséquent, il a une méthode par défaut à partir de l’interface A. La
	 * Règle 2 indique que s’il n’y a pas de méthodes dans la classe ou la
	 * superclasse, alors la méthode par défaut située dans l’interface la plus
	 * spécifique est sélectionnée. Le compilateur a donc le choix entre la
	 * méthode hello de l’interface A et la méthode hello de l’interface B.
	 * Parce que B est plus spécifique, le programme affichera à nouveau « Hello
	 * from B »
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		new Cprime().hello();

	}
}
