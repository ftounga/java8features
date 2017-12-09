package optional.withopt;

import java.util.Optional;

import optional.Insurance;

public class Car {

	private Optional<Insurance> insurance;

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Optional<Insurance> insurance) {
		this.insurance = insurance;
	}
	
}
