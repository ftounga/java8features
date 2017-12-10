package optional.withopt;

import java.util.Optional;

public class Person {
private Optional<optional.withopt.Car> car;

public Optional<optional.withopt.Car> getCar() {
	return car;
}

public void setCar(Optional<optional.withopt.Car> car) {
	this.car = car;
}

}
