package dev.sdras.caixasugestoes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

@Component
public class EndpointLogger implements CommandLineRunner {

    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointLogger(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void run(String... args) {

        System.out.println("\n========== ROTAS DISPONÍVEIS (Spring Boot 3.5.7) ==========\n");

        handlerMapping.getHandlerMethods().forEach((info, method) -> {

            var httpMethods = info.getMethodsCondition().getMethods();
            var patterns = info.getPathPatternsCondition().getPatterns();

            httpMethods.forEach(httpMethod -> {
                patterns.forEach(pattern -> {
                    System.out.println(httpMethod + "   " + pattern.getPatternString());
                });
            });
        });

        System.out.println("\n==========================================================\n");
    }
}