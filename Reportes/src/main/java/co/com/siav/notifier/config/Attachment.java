package co.com.siav.notifier.config;

public class Attachment {
	
	public Attachment(){}
	
	public Attachment(String filename, byte[] file){
		this.file = file;
		this.filename = filename;
	}
	
	private byte[] file;
	private String filename;
	
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

}