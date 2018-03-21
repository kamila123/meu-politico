package com.meupolitico.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.meupolitico.entity.IPolitico;
import com.meupolitico.entity.Politico;
import com.meupolitico.entity.ProcessoJudicial;
import com.meupolitico.util.PoliticoUtility;

@RestController
public class ProcessosRestController {

	@Autowired
	private IPolitico iPolitico;

	@ResponseBody
	@RequestMapping(value = "/BuscaMG", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	private void buscaProcessosMinasGerais(String nomePolitico) throws MalformedURLException, IOException {

		// final String link =
		// "http://www4.tjmg.jus.br/juridico/sf/proc_resultado_nome.jsp?tipoPesquisa=2&txtProcesso=&comrCodigo=24"
		// + "&nomePessoa=" + "Fernando%20Damata%20Pimentel"
		// +
		// "&tipoPessoa=X&naturezaProcesso=0&situacaoParte=X&codigoOAB=&tipoOAB=N&ufOAB=MG&numero=20&select=1&tipoConsulta=1&natureza=0&ativoBaixado=X";
		nomePolitico = "Fernando%20Damata%20Pimentel";
		final String link = "http://www4.tjmg.jus.br/juridico/sf/proc_resultado_nome.jsp?tipoPesquisa=2&txtProcesso=&comrCodigo="
				+ 24 + "&nomePessoa=" + nomePolitico
				+ "&tipoPessoa=X&naturezaProcesso=1&situacaoParte=X&codigoOAB=&tipoOAB=N&ufOAB=MG&numero=20&select=1&tipoConsulta=1&natureza=0&ativoBaixado=X";

		final String linkTJM_SP = "https://esaj.tjsp.jus.br/cpopg/search.do?conversationId=&dadosConsulta.localPesquisa.cdLocal=-1&cbPesquisa=NMPARTE&dadosConsulta.tipoNuProcesso=UNIFICADO&dadosConsulta.valorConsulta=Alberto+Goldman&uuidCaptcha=";
		try {
			Document html = Jsoup.parse(new URL(link).openStream(), StandardCharsets.ISO_8859_1.name(), link);

			Elements form = html.select("table.tabela_formulario");

			ArrayList<String> numerosChaveProcessos = new ArrayList<String>();

			for (Element element : form.get(0).getAllElements()) {
				numerosChaveProcessos.add(element.getAllElements().get(3).text().substring(2));
			}

			final String allProcessos = "http://www4.tjmg.jus.br/juridico/sf/proc_resultado2.jsp?pessCodigo="
					+ numerosChaveProcessos.get(0) + "&situacaoParte=X&naturezaProcesso=0" + "&comrCodigo=" + 24
					+ "&numero=" + 20;

			// html = Jsoup.parse(new URL(link).openStream(),
			// StandardCharsets.ISO_8859_1.name() , link);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/CargaSP", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	private Politico cargaProcessosSaoPaulo(String nomePolitico) throws MalformedURLException, IOException {

		nomePolitico = "Alberto+Goldman";
		final String linkTJ_SP = "https://esaj.tjsp.jus.br/cpopg/search.do?conversationId=&dadosConsulta.localPesquisa.cdLocal=-1&cbPesquisa=NMPARTE&dadosConsulta.tipoNuProcesso=UNIFICADO&dadosConsulta.valorConsulta="
				+ nomePolitico + "&uuidCaptcha=";

		Document html = Jsoup.connect(linkTJ_SP).validateTLSCertificates(false).post();

		ArrayList<String> linkProcessos = new ArrayList<String>();

		Elements elements = html.select("div#listagemDeProcessos");

		Elements elementsProcessos = elements.get(0).getElementsByClass("nuProcesso");

		for (int i = 0; i < elementsProcessos.size(); i++) {
			linkProcessos.add("https://esaj.tjsp.jus.br" + elementsProcessos.get(i).getAllElements().attr("href"));
		}

		Politico politico = new Politico();
		politico.setNome(nomePolitico);

		ArrayList<ProcessoJudicial> listaDeProcessos = new ArrayList<ProcessoJudicial>();
		for (String processos : linkProcessos) {
			// listaDeProcessos.add(PoliticoUtility.getDados(processos));
			Document html_processo;
			ProcessoJudicial processoJudicial = new ProcessoJudicial();
			try {
				// Precisa permanecer dentro do mesmo metodo
				html_processo = Jsoup.connect(processos).validateTLSCertificates(false).post();
				Element detalhesProcesso = html_processo.getElementsByClass("secaoFormBody").get(1);
				String descricaoProcesso = detalhesProcesso.getElementsByClass("secaoFormBody").get(0)
						.getElementsByTag("tr").get(6).getElementsByTag("td").get(1).text();
				String numeroProcesso = detalhesProcesso.getElementsByClass("secaoFormBody").get(0)
						.getElementsByTag("tr").get(1).getElementsByTag("td").get(0).text();
				processoJudicial.setDescricao(descricaoProcesso);
				// processoJudicial.setNumero(numeroProcesso);
				PoliticoUtility.getStatusProcesso(numeroProcesso, processoJudicial);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listaDeProcessos.add(processoJudicial);
		}
		politico.setProcessosJudiciais(listaDeProcessos);
		iPolitico.save(politico);
		return politico;

	}

	@ResponseBody
	@RequestMapping(value = "/BuscaSP", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	private Politico buscaProcessosSaoPaulo(String nomePolitico) throws MalformedURLException, IOException {
		List<Politico> listPolitico = (List<Politico>) iPolitico.findAll();

		return listPolitico.get(0);
	}

	@ResponseBody
	@RequestMapping(value = "/BuscaRJ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	private void buscaProcessosRioDeJaneiro() throws MalformedURLException, IOException, InterruptedException {

		final WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);

		final HtmlPage page = webClient
				.getPage("http://www4.tjrj.jus.br/ConsultaUnificada/consulta.do#tabs-nome-indice1");

		HtmlInput htmlInputNomeParte = (HtmlInput) page.getElementById("nomeParte");
		htmlInputNomeParte.setValueAttribute("Jair Messias Bolsonaro");

		HtmlInput htmlInputAnoInicio = (HtmlInput) page.getElementByName("anoInicio");
		htmlInputAnoInicio.setValueAttribute("30/11/1900");

		// HtmlSelect htmlSelectOrigem = (HtmlSelect)
		// page.getElementByName("origem");
		// htmlSelectOrigem.setSelectedAttribute("2", true);

		HtmlButtonInput htmlButtonInput = (HtmlButtonInput) page.getElementById("pesquisa");
		HtmlPage htmlPage = htmlButtonInput.click();
		webClient.waitForBackgroundJavaScript(7000);
		htmlPage.asText();
	}
}
