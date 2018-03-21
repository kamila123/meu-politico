package com.meupolitico.enumerators;

public enum StatusProcessoEnum {

	RECURSO("Em grau de recurso"), SUSPENSO("Suspenso");

	private String desc;

	private StatusProcessoEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
