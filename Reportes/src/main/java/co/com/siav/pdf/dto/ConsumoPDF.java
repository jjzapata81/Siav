package co.com.siav.pdf.dto;

public class ConsumoPDF {
	
	private String ms;
	private int con;
	
	public ConsumoPDF() {
	}

	
	public ConsumoPDF(String valor) {
		String[] arg = valor.split("-");
		con = Integer.parseInt(arg[0]);
		ms = arg[1];
	}

	public String getMs() {
		return ms;
	}
	
	public void setMs(String ms) {
		this.ms = ms;
	}
	
	public int getCon() {
		return con;
	}
	
	public void setCon(int con) {
		this.con = con;
	}

}
