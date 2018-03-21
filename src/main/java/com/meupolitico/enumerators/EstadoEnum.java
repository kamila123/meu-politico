package com.meupolitico.enumerators;

public enum EstadoEnum {
	RIO_DE_JANEIRO("Rio de Janeiro"), MINAS_GERAIS("Minas Gerais"), SAO_PAULO("São Paulo"), Pernambuco("Pernambuco");
	private String desc;

	private EstadoEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
