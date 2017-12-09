package optional;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Insurance axa = new Insurance();
		Car car = new Car();
		car.equals(axa);
		Person francky = new Person();
		francky.setCar(car);
		
		Optional<Insurance> axaOptional= Optional.of(axa);
		Optional<String> name = axaOptional.map(Insurance::getName);
		try {
			francky.getCar().getInsurance().getName().length();
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("Le nom de l'assurance de la voiture de francky est null d'où la NPE");
		}
		System.out.println("La methode défensive retourne: " + getCarInsuranceNameWithDefensiveCheck(francky) );
		if(!name.isPresent()){
			System.out.println(" La méthode getInsuranceName retourne une Optional Empty");
		}
	}

	public static String getCarInsuranceName(Person person){
		return person.getCar().getInsurance().getName();
	}
	
	public static String getCarInsuranceNameWithDefensiveCheck(Person person){
		if(person != null && person.getCar() !=null && person.getCar().getInsurance() != null){
			return person.getCar().getInsurance().getName();
		}
		return "Unknown";
	}
}
