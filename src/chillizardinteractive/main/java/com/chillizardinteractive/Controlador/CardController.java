package src.chillizardinteractive.main.java.com.chillizardinteractive.Controlador;

import org.springframework.web.bind.annotation.*;

import src.chillizardinteractive.Modelo.deck.Deck;
import src.chillizardinteractive.Modelo.deck.DeckGenerator;

@RestController
@RequestMapping("/api")
public class CardController {

    @PostMapping("/deck")
    public Deck createDeck(@RequestBody String filePath) {
        return DeckGenerator.generateDeck(filePath);
    }

    @GetMapping("/deck/{playerId}")
    public Deck getDeck(@PathVariable String playerId) {
        // Lógica para obtener el mazo del jugador
        return new Deck(); // Placeholder
    }

    @PostMapping("/startGame")
    public String startGame(@RequestBody GameRequest gameRequest) {
        // Lógica para iniciar una partida entre dos jugadores
        return "Partida iniciada entre " + gameRequest.getPlayer1() + " y " + gameRequest.getPlayer2();
    }
}