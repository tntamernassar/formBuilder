import java.util.LinkedList;

public class HTMLTable {
	
	private LinkedList<Rowable> rows;
	private String[] header;
	private int width;
	
	public HTMLTable(int cols,int width) {
		this.rows = new LinkedList<Rowable>();
		this.width = width;
		this.header = new String[cols];
	}
	
	public void addRow(Rowable row) {
		rows.add(row);
	}
	
	@Override
	public String toString() {
		return createTable();
	}
	
	public void buildHeader(String ... args) {
		
		if(args.length != header.length)
			throw new RuntimeException("wrong Table header arguments given");
		
		int i=0;
		for(String s : args) {
			header[i] = s;
			i++;
		}
		
	}
	
	private String createHeader() {
		String result = "<tr class=\"t_header\">";
		
		for(int i=0;i<header.length;i++)
			result = result + "<th class=\"header_cell\">"+header[i]+"</th>";
		
		
		return result+"</tr>";
	}
	
	private String createTable() {
		String result="<table class=\"table\" >";
		result = result + createHeader();
		for(Rowable r : rows)
			result = result + r.generateRow();
		return result+"</table>";
	}
	

}
