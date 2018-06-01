package TypesHandler;

public class TypeDate extends Type {

	public TypeDate(int id, String labelName) {
		super(id, labelName);
	}

	@Override
	public String getHTML(String property) {
		String result="";
		
		result = result + "<div class=\"color_form_element\"><br />";
				
		result = result + "<div class=\"auto_label_name_header\">"+labelName+"</div><br />";
		
		result = result + "<div class=\"auto_color_type\">Choose Date : <input onChange=\"answer("+id+")\"name=\""+id+"\" type=\"date\" "+property+"></input></div>";
		
		result = result + "<img src=\"Images/delete.png\" class=\"color_delete_auto_added_form\" onClick=\"Delete("+id+")\" ></img>";
		
		result = result + "</div> <br></br> ";
		
		return result;
	}
	
	@Override
	public int getType() {
		return 2;
	}
}
