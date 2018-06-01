
abstract public class Input {
	
	public static int TEXT_FIELD;

	private String fieldName;
	private int Attribute;
	
	public Input(String fieldName,int Attribute) {
		this.fieldName = fieldName;
		this.Attribute = Attribute;
	}
	
	
	abstract public String getHTML();

	
}
