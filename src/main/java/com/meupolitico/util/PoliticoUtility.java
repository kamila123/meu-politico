package com.meupolitico.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.meupolitico.entity.ProcessoJudicial;

public class PoliticoUtility {

	public static ProcessoJudicial getDados(String processo) {
		Document html_processo;
		ProcessoJudicial processoJudicial = new ProcessoJudicial();
		try {
			html_processo = Jsoup.connect(processo).validateTLSCertificates(false).post();
			Element detalhesProcesso = html_processo.getElementsByClass("secaoFormBody").get(1);
			processoJudicial.setDescricao(detalhesProcesso.getElementsByClass("secaoFormBody").get(0)
					.getElementsByTag("tr").get(6).getElementsByTag("td").get(1).text());
			processoJudicial.setNumero(detalhesProcesso.getElementsByClass("secaoFormBody").get(0)
					.getElementsByTag("tr").get(1).getElementsByTag("td").get(0).text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// processoJudicial.setPolitico(politico);
		return processoJudicial;
	}

	public static ProcessoJudicial getStatusProcesso(String processo, ProcessoJudicial processoJudicial) {
		boolean hasStatus = false;
		for (int i = 0; i < processo.toCharArray().length; i++) {
			if (Character.isLetter(processo.toCharArray()[i])) {
				processoJudicial.setStatus(processo.substring(i, processo.toCharArray().length));
				processoJudicial.setNumero(processo.substring(0, i));
				hasStatus = true;
				break;
			}
		}

		if (!hasStatus) {
			processoJudicial.setNumero(processo);
		}
		return processoJudicial;
	}
}
