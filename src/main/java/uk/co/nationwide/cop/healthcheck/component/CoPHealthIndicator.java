package uk.co.nationwide.cop.healthcheck.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CoPHealthIndicator implements HealthIndicator {
	
	@Autowired
	HttpServletRequest request;
	@Value("${cop.healthcheck.property}")
	private String healthcheckProperty;
	@Value("${info.app.name}")
	private String apiName;
	
    @Override
    public Health health() {
    	
    	Map<String, String> details = new HashMap<String, String>();
    	
    	Enumeration<String> parameterNames = request.getParameterNames();

    	while (parameterNames.hasMoreElements()) {
    	    String key = parameterNames.nextElement();
    	    String val = request.getParameter(key);
    	  if(null!= val) {
    		  details.put(key, val);
    	  }
    	}
    	final SimpleDateFormat f = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        details.put("Apiname", apiName);
    	details.put("dateTime", String.valueOf(System.currentTimeMillis()));
    	details.put("dateTimeUTC", f.format(new Date()));
    	details.put("cop.healthcheck.property", healthcheckProperty);
        return Health.up().withDetails(details).build();       
    }
     
 
}