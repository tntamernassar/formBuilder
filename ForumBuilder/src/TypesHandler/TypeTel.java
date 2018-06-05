package TypesHandler;

public class TypeTel extends Type {

	public TypeTel(int id, String labelName) {
		super(id, labelName);
	}

	@Override
	public String getHTML(String property) {
		String result = "";

		result = result + "<div class=\"text_form_element\"><br />";

		result = result + "<div class=\"auto_label_name_header\">" + labelName + "</div><br />";

		result = result + "<input onchange=\"answer(" + id + ")\" name=\"" + id
				+ "\" type=\"tel\" placeHolder=\"051-2345678\" class=\"auto_tel_type\" " + property + "></input>";

		result = result + "<img src=\"Images/delete.png\" class=\"delete_auto_added_form\" onClick=\"Delete(" + id
				+ ")\" ></img>";

		result = result + "</div> <br></br>";

		return result;
	}

	@Override
	public int getType() {
		return 4;
	}
}
