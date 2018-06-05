import java.util.LinkedList;

public class HTMLTable {
	
	private LinkedList<Rowable> rows;
	private String[] header;
	
	public HTMLTable(int cols) {
		this.rows = new LinkedList<Rowable>();
		this.header = new String[cols];
	}
	
	public void addRow(Rowable row) {
		rows.add(row);
	}
	
	@Override
	public String toString() {
		return createTable();
	}
	
	/*
	 * @params : 'args' -> header arguments 
	 * @do : initialize 'header'
	 * */
	public void buildHeader(String ... args) {
		if(args.length != header.length)
			throw new RuntimeException("wrong Table header arguments given");
		int i=0;
		for(String s : args) {
			header[i] = s;
			i++;
		}
	}
	
	/*
	 * @return : 'header' HTML structure
	 * */
	private String createHeader() {
		String result = "<tr class=\"t_header\">";
		for(int i=0;i<header.length;i++)
			result = result + "<th class=\"header_cell\">&nbsp;"+header[i]+"&nbsp;</th>";
		return result+"</tr>";
	}
	
	/*
	 * @return : table HTML structure combined with the rows
	 * */
	private String createTable() {
		String result="<table class=\"table\" >";
		result = result + createHeader();
		for(Rowable r : rows)
			result = result + r.generateRow();
		return result+"</table>";
	}
	

}
