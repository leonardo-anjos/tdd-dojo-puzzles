package com.dojopuzzles.tdd.contadordeburacos;

import java.util.HashMap;
import java.util.Map;

public class VerificadorDeBuraco {

	private Map<Character, Integer> letras;

	public VerificadorDeBuraco() {
		this.letras = new HashMap<Character, Integer>();
		this.letras.put('a', 1);
		this.letras.put('b', 1);
		this.letras.put('d', 1);
		this.letras.put('e', 1);
		this.letras.put('g', 1);
		this.letras.put('o', 1);
		this.letras.put('p', 1);
		this.letras.put('q', 1);
		this.letras.put('A', 1);
		this.letras.put('B', 2);
		this.letras.put('D', 1);
		this.letras.put('O', 1);
		this.letras.put('P', 1);
		this.letras.put('Q', 1);
		this.letras.put('R', 1);
	}

	public int quantidadeDeBuracos(String palavra) {
		int cont = 0;
		for (Character l : palavra.toCharArray()) {
			if (letras.containsKey(l))
				cont += letras.get(l);
		}
		return cont;
	}

}
