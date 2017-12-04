package defaultmethode;

public interface Resizable {
	int getWidth();
	int getHeight();
	void setWidth(int width);
	void setAbsoluteSize(int width, int heigth);
	
	default void setRelativeSize(int wFactor, int hFactor){
		setAbsoluteSize(getWidth() / wFactor, getHeight()/hFactor);
	}
}
