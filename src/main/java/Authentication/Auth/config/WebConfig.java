package Authentication.Auth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for your frontend application
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Allow only the frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (cookies, etc.)
    }
}
