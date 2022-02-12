package br.com.chess.cammel.processor;

import br.com.chess.exceptions.ServiceError;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ErroProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		throw new ServiceError("Parametro não identificado!");
	}

}
