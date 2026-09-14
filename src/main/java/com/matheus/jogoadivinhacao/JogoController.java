package com.matheus.jogoadivinhacao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class JogoController {

    private int numeroSecreto = new Random().nextInt(10) + 1;
    private int tentativas = 0;

    @GetMapping("/")
    public String abrirJogo() {
        return "index";
    }

    @PostMapping("/tentar")
    public String tentar(@RequestParam int numero, Model model) {

        tentativas++;

        if (numero == numeroSecreto) {

            model.addAttribute(
                    "mensagem",
                    "Parabéns! Você acertou!"
            );

            model.addAttribute("numeroSecreto", numeroSecreto);

        } else if (numero < numeroSecreto) {

            model.addAttribute(
                    "mensagem",
                    "Putz, você errou cabra! O número secreto é maior 😂"
            );

        } else {

            model.addAttribute(
                    "mensagem",
                    "Putz, você errou cabra! O número secreto é menor 😂"
            );
        }

        model.addAttribute("tentativas", tentativas);

        return "index";
    }

    @PostMapping("/reiniciar")
    public String reiniciar() {

        numeroSecreto = new Random().nextInt(10) + 1;
        tentativas = 0;

        return "redirect:/";
    }
}