package defaultmethode;

public interface Rotable {

	void setRotationAngle(int angleInDegrees);
	int getRotationAngle();
	default void rotateBy(int angleInDegrees){
		setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
	}
}
