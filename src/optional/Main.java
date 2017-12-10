package optional;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Insurance axa = new Insurance();
		Insurance groupama = new Insurance();
		groupama.setName("groupama");
		Car car = new Car();
		optional.withopt.Car optCar = new optional.withopt.Car();
		optCar.setInsurance(Optional.of(groupama));
		car.setInsurance(axa);
		Person francky = new Person();
		optional.withopt.Person raoul = new optional.withopt.Person();
		raoul.setCar(Optional.of(optCar));
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
		
		Optional<optional.withopt.Person> optPerson = Optional.of(raoul);
		/**
		 * La méthode  flat map permet de convertir: Optional<Optional<T>> en Optional<T>
		 */
		Optional<String> insuranceName = optPerson.flatMap(optional.withopt.Person::getCar).flatMap(optional.withopt.Car::getInsurance).map(Insurance::getName);
		System.out.println("Le nom de l'assurance de raoul est:" + insuranceName.get());
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
