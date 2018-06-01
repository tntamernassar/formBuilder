
public class InputText extends Input {

	
	public InputText(String fieldName) {
		super(fieldName,Input.TEXT_FIELD);
	}

	@Override
	public String getHTML() {
		
		String result="";
		
		result ="<input type=\"text\" width=\"50px\">";
		
		return result;
	}
	
	
	
	
	
}
