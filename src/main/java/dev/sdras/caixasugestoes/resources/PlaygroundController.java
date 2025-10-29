package dev.sdras.caixasugestoes.resources;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playground")
public class PlaygroundController {

    @GetMapping("/hello/{nome}")
    public String hello(@PathVariable String nome) {
        return "Hello " + nome;
    }

    @GetMapping("/calculadora/somar")
    public String calculadoraSomar(@PathParam("a") Integer a,
                                   @PathParam("b") Integer b) {
        Integer result = a + b;

        return String.valueOf(result);
    }
}
