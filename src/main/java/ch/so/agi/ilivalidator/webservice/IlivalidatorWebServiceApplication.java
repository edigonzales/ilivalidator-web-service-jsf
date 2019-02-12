package ch.so.agi.ilivalidator.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IlivalidatorWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IlivalidatorWebServiceApplication.class, args);
	}

    @Bean
    public ServletContextInitializer servletContextInitializer() {
            return servletContext -> {
//                    servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
//                    servletContext.setInitParameter("primefaces.THEME", "omega");
//                    servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
//                    servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
//                    servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
//                    servletContext.setInitParameter("primefaces.UPLOADER", "commons");
            };
    }
}

