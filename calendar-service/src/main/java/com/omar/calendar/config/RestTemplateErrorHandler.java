package com.omar.calendar.config;

import java.io.IOException;
import java.util.Scanner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * 
 * A RestTemplate ErrorHandler custom implementation
 * Useful to collect error stack across RestTemplate calls
 * 
 * @author Omar.Gaye
 * Created 03/07/2017
 *
 */
@Slf4j
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {
	
	String detailledMessage = "";
	HttpStatus status = null;

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		Scanner scanner = new Scanner(response.getBody());
		scanner.useDelimiter("\\Z");
		
		String fullBodyResponse = "";
		if (scanner.hasNext()){
			fullBodyResponse = scanner.next();
		}
		
		log.error("handleError: {}", fullBodyResponse);
		setDetailledMessage(fullBodyResponse);
		HttpStatus status = response.getStatusCode();
		setStatus(status);
		
		try{
			scanner.close();
		}catch (Exception e){
		}
	}

}
