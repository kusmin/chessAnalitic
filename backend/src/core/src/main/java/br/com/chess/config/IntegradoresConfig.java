package br.com.chess.config;

import br.com.chess.log.LoggingInterceptor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class IntegradoresConfig {

	@Value("${lichess.host}")
	private String urlIntegradorLichess;

	@Value("${chess.com.host}")
	private String urlIntegradorChessCom;

	public IntegradoresConfig() {
		// construtor padrao
	}


	@Bean
	@Qualifier("CHESSCOM")
	public RestTemplate restTemplateChessCom(RestTemplateBuilder builder) {

		return builder.additionalInterceptors(new LoggingInterceptor())
				.uriTemplateHandler(new DefaultUriBuilderFactory(urlIntegradorChessCom))
				.build();
	}

	@Bean
	@Qualifier("LICHESS")
	public RestTemplate restTemplateLichess(RestTemplateBuilder builder) {

		return builder.additionalInterceptors(new LoggingInterceptor())
				.uriTemplateHandler(new DefaultUriBuilderFactory(urlIntegradorLichess))
				.build();
	}
}
