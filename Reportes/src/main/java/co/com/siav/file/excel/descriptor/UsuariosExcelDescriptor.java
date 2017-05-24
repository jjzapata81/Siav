package co.com.siav.file.excel.descriptor;

import co.com.siav.file.excel.IExcelDescriptor;

public enum UsuariosExcelDescriptor implements IExcelDescriptor{
	
	CEDULA("CEDULA","getCedula"),
	NOMBRES("NOMBRES","getNombres"),
	APELLIDOS("APELLIDOS","getApellidos"),
	TELEFONO("TELEFONO","getTelefono"),
	CELULAR("CELULAR","getCelular"),
	EMAIL("EMAIL","getEmail"),
	INSTALACION("INSTALACION","getInstalacion"),
	CUENTAS_VENCIDAS("CUENTAS VENCIDAS","getCuentasVencidas"),
	RAMAL("RAMAL","getRamal"),
	MEDIDOR("MEDIDOR","getMedidor")
	;
	
	private String header;
	
	private String attribute;
	
	private UsuariosExcelDescriptor(String header, String attribute){
		this.header = header;
		this.attribute = attribute;
	}

	@Override
	public String getColumnHeader() {
		return header;
	}

	@Override
	public String getColumnDataMapper() {
		return attribute;
	}

	@Override
	public int getColumnIndex() {
		return ordinal();
	}

}
