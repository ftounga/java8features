package defaultmethode.resolution;

public class C implements A, B {

	/**
	 * La r�gle 2 indique que la m�thode avec l�interface par d�faut la plus
	 * sp�cifique est s�lectionn�e. Parce que B est plus sp�cifique que A, le
	 * hello() de B est s�lectionn�. Par cons�quent, le programme affichera �
	 * Hello from B. �
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		new C().hello();
	}
}
