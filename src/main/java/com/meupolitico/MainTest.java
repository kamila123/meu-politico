package com.meupolitico;

public class MainTest {
	public static void main(String[] args) {

		String processo = "0104367-13.9600.8.26.0090 (583.90.9600.1043676) Suspenso";

		for (int i = 0; i < processo.toCharArray().length; i++) {
			if (Character.isLetter(processo.toCharArray()[i])) {
				processo = processo.substring(i, processo.toCharArray().length);
				break;
			}
		}
		System.out.println(processo);
	}
}
