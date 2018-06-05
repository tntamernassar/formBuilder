package TypesHandler;

public class TypeText extends Type {

	public TypeText(int id, String labelName) {
		super(id, labelName);
	}

	@Override
	public String getHTML(String property) {

		String result = "";

		result = result + "<div class=\"text_form_element\"><br />";

		result = result + "<div class=\"auto_label_name_header\">" + labelName + "</div><br />";

		result = result + "<input onchange=\"answer(" + id + ")\" name=\"" + id
				+ "\" type=\"text\" placeHolder=\"Enter your Answer\" class=\"auto_text_type\" " + property
				+ "></input>";

		result = result + "<img src=\"Images/delete.png\" class=\"delete_auto_added_form\" onClick=\"Delete(" + id
				+ ")\" ></img>";

		result = result + "</div> <br></br>";

		return result;
	}

	@Override
	public int getType() {
		return 0;
	}
}
