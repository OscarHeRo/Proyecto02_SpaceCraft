package com.chillizardinteractive.vista;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.card.WeaponCard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CardHtmlGenerator {

    public static void generateCardHtml(Card card, String outputPath) {
        try {
            String template = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/cards.html")));
            template = template.replace("{{cardName}}", card.getName());
            template = template.replace("{{cardDescription}}", card.getDescription());
            template = template.replace("{{cardCost}}", String.valueOf(card.getNenCost()));
            if (card instanceof MinionCard) {
                MinionCard minion = (MinionCard) card;
                template = template.replace("{{cardAttack}}", "Ataque: " + minion.getAttack());
                template = template.replace("{{cardDefense}}", "Defensa: " + minion.getDefense());
            } else if (card instanceof WeaponCard) {
                WeaponCard weapon = (WeaponCard) card;
                template = template.replace("{{cardAttack}}", "Ataque: " + weapon.getAttack());
                template = template.replace("{{cardDefense}}", "");
            } else if (card instanceof SpellCard) {
                template = template.replace("{{cardAttack}}", "");
                template = template.replace("{{cardDefense}}", "");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write(template);
                System.out.println("Archivo HTML generado: " + outputPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}