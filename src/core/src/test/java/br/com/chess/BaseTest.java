package br.com.chess;

import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.chess.repositories.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class BaseTest {

    @Autowired
    protected WebTestClient testClient;
    
    @Autowired
    private PerfilRepository perfilRepository;



    public WebTestClient getTestClient() {
        return testClient;
    }

    public void setTestClient(WebTestClient testClient) {
        this.testClient = testClient;
    }

    /**
     * Gera um código UUID randômico
     * @return
     */
    protected String uuid() {
        return UUID.randomUUID().toString();
    }
    
	protected File criarCsvTeste() {
		
		File arquivo = new File(System.getProperty("java.io.tmpdir") + "/planilha-" + uuid() + ".csv");
		
		String arquivoStr = "";
		arquivoStr += "Nome;";
		arquivoStr += "Sobrenome;";
		arquivoStr += "E-mail;";
		arquivoStr += "Matricula;";
		arquivoStr += "Data de nascimento \n";
		
		int numLinhas = 1 + (int) (Math.random() * 100);
		for (int i = 1; i <= numLinhas; i++) {
			arquivoStr += "Nome " + uuid() + ";";
			arquivoStr += "Sobrenome " + uuid() + ";";
			arquivoStr += uuid() + "@teste.com;";
			arquivoStr += uuid() + ";";
			arquivoStr += "01/01/2020 \n";
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))){
			writer.write(arquivoStr);
			
		} catch (IOException ex) {
			throw new RuntimeException("Erro criando arquivo de teste", ex);
		}
		
		
		return arquivo;
	}
    
	protected Date addDias(Date data, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}
	
	/**
	 * Retorna a lista de datas
	 * 0 - data de lançamento
	 * 1 - data de inicio
	 * 2 - data de fim
	 * 3 - data do feedback
	 * 
	 * 4 - data de finalização do conteúdo da campanha
	 */
	public String[] criarDatasComHora(boolean jaLancou, int diasAposLancamento, int diasCampanha) {
		String[] result = new String[5];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date agora = new Date();
		
		Date dataLancamento = jaLancou ? addDias(agora, -3) : addDias(agora, 3);
		Date dataInicio = addDias(dataLancamento, diasAposLancamento);
		Date dataFim = addDias(dataInicio, diasCampanha - 1);
		Date dataFeedback = addDias(dataFim, -1);
		Date dataFimDoConteudo = addDias(dataFim, - 1);
		
		result[0] = dateFormat.format(dataLancamento) + " 00:00:00";
		result[1] = dateFormat.format(dataInicio)  + " 00:00:00";
		result[2] = dateFormat.format(dataFim) + " 23:00:00";
		result[3] = dateFormat.format(dataFeedback) + " 00:00:00";
		result[4] = dateFormat.format(dataFimDoConteudo) + " 23:00:00";
		
		return result;
	}

	
	/**
	 * Retorna a lista de datas
	 * 0 - data de lançamento
	 * 1 - data de inicio
	 * 2 - data de fim
	 * 3 - data do feedback
	 * 
	 * 4 - data de finalização do conteúdo da campanha
	 */

	public String[] criarDatas(boolean jaLancou, int diasAposLancamento, int diasCampanha) {
		String[] result = new String[5];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date agora = new Date();
		
		Date dataLancamento = jaLancou ? addDias(agora, -3) : addDias(agora, 3);
		Date dataInicio = addDias(dataLancamento, diasAposLancamento);
		Date dataFim = addDias(dataInicio, diasCampanha - 1);
		Date dataFeedback = addDias(dataFim, -1);
		Date dataFimDoConteudo = addDias(dataFim, - 1);
		
		result[0] = dateFormat.format(dataLancamento);
		result[1] = dateFormat.format(dataInicio);
		result[2] = dateFormat.format(dataFim);
		result[3] = dateFormat.format(dataFeedback);
		result[4] = dateFormat.format(dataFimDoConteudo);
		
		return result;
	}
	
	/**
	 * Retorna a lista de datas
	 * 0 - data de lançamento
	 * 1 - data de inicio
	 * 2 - data de fim
	 * 3 - data do feedback
	 * Se a campanha for do tipo Interesses:
	 * 4 - data do inicio da seleção de interesses
	 * 5 - data do fim da seleção de interesses
	 * 
	 * 6 - data do fim do conteudo de uma campanha
	 */
	public String[] criarDatasEventoInteresse(boolean jaLancou, int diasAposLancamento, int diasCampanha) {
		String[] result = new String[7];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date agora = new Date();
		
		Date dataLancamento = jaLancou ? addDias(agora, -3) : addDias(agora, 3);
		Date dataInicio = addDias(dataLancamento, diasAposLancamento);
		Date dataFim = addDias(dataInicio, diasCampanha - 1);
		Date dataFeedback = addDias(dataFim, -1);
		Date dataPreCampanhaInicio = addDias(dataLancamento, + 1);
		Date dataPreCampanhaFim = addDias(dataInicio, - 1);
		Date dataFimDoConteudo = addDias(dataFim, - 1);
		
		result[0] = dateFormat.format(dataLancamento);
		result[1] = dateFormat.format(dataInicio);
		result[2] = dateFormat.format(dataFim);
		result[3] = dateFormat.format(dataFeedback);
		result[4] = dateFormat.format(dataPreCampanhaInicio);
		result[5] = dateFormat.format(dataPreCampanhaFim);
		result[6] = dateFormat.format(dataFimDoConteudo);
		
		
		return result;
	}
}
