package defaultmethode.resolution;

public class Cprime extends D implements B, A {

	/**
	 * La R�gle 1 indique qu�une d�claration de m�thode dans la classe est
	 * prioritaire. Mais D n�override pas hello; il impl�mente l�interface A.
	 * Par cons�quent, il a une m�thode par d�faut � partir de l�interface A. La
	 * R�gle 2 indique que s�il n�y a pas de m�thodes dans la classe ou la
	 * superclasse, alors la m�thode par d�faut situ�e dans l�interface la plus
	 * sp�cifique est s�lectionn�e. Le compilateur a donc le choix entre la
	 * m�thode hello de l�interface A et la m�thode hello de l�interface B.
	 * Parce que B est plus sp�cifique, le programme affichera � nouveau � Hello
	 * from B �
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		new Cprime().hello();

	}
}
