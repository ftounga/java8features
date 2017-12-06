package defaultmethode.resolution;

public class C implements A, B {

	/**
	 * La règle 2 indique que la méthode avec l’interface par défaut la plus
	 * spécifique est sélectionnée. Parce que B est plus spécifique que A, le
	 * hello() de B est sélectionné. Par conséquent, le programme affichera «
	 * Hello from B. »
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		new C().hello();
	}
}
