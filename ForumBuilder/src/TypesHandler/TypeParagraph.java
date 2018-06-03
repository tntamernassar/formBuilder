package TypesHandler;

public class TypeParagraph extends Type{

	public TypeParagraph(int id, String labelName) {
		super(id, labelName);
		
	}

	@Override
	public String getHTML(String property) {
		String result="";
		
		result = result + "<div class=\"text_form_element\"><br />";
				
		result = result + "<div class=\"auto_label_name_header\">"+labelName+"</div><br />";
		
		result = result + "<textarea onchange=\"answer("+id+")\" name=\""+id+"\" id =\""+id+"\" placeHolder=\"Enter your Answer\" class=\"auto_text_type\" "+property+"></textarea>";
		
		result = result + "<img src=\"Images/delete.png\" class=\"delete_auto_added_form\" onClick=\"Delete("+id+")\" ></img>";
		
		result = result + "</div> <br></br>";
		
		return result;
	}

	@Override
	public int getType() {
		return 6;
	}

}
