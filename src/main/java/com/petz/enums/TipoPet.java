package com.petz.enums;

public enum TipoPet {
	
	GATO(1, "GATO"),
	CACHORRO(2, "CACHORRO"),
	PEIXE(3, "PEIXE"),
	PASSAROS(4, "PÁSSAROS"),
	OUTROSPETZ(5,"OUTROS PETZ");
	
	private int cod;
	private String descricao;

	private TipoPet(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPet toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoPet x : TipoPet.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


}
