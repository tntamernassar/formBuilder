package TypesHandler;

public class TypeColor extends Type {

	public TypeColor(int id, String labelName) {
		super(id, labelName);
		result = "#0FA13B";
	}

	@Override
	public String getHTML(String property) {
		String result = "";

		result = result + "<div class=\"color_form_element\"><br />";

		result = result + "<div class=\"auto_label_name_header\">" + labelName + "</div><br />";

		result = result + "<div class=\"auto_color_type\">Choose a color : <input onchange=\"answer(" + id
				+ ")\" name=\"" + id + "\" type=\"color\" value=\"#0FA13B\" " + property + "></input></div>";

		result = result + "<img src=\"Images/delete.png\"   class=\"color_delete_auto_added_form\" onClick=\"Delete("
				+ id + ")\" ></img>";

		result = result + "</div> <br></br> ";

		return result;
	}

	@Override
	public int getType() {
		return 1;
	}

	@Override
	public String getDisplay() {
		return "<input type=\"color\" value=\"" + result + "\" disabled></input>";
	}
}
