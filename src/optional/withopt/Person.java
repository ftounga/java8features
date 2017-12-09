package optional.withopt;

import java.util.Optional;

import optional.Car;

public class Person {
private Optional<Car> car;

public Optional<Car> getCar() {
	return car;
}

public void setCar(Optional<Car> car) {
	this.car = car;
}

}
