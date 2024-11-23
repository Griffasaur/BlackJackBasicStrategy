package Model;

import Controller.ConsoleController;
import Enums.Action;
import Enums.DealerUpcard;
import Enums.HandType;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Model.Card;

public class BasicStrategy {

    private static final Scanner input = new Scanner(System.in);

    public Map<HandType, Map<DealerUpcard, Action>> getBasicStrategyMapFromCSV(String filePath) {
        Map<HandType, Map<DealerUpcard, Action>> basicStrategyMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                HandType handType = HandType.valueOf(values[0].toUpperCase());
                DealerUpcard dealerUpcard = DealerUpcard.valueOf(values[1].toUpperCase());
                Action action = Action.valueOf(values[2].toUpperCase());

                basicStrategyMap.putIfAbsent((handType), new HashMap<>());
                basicStrategyMap.get(handType).put(dealerUpcard, action);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basicStrategyMap;

    }

    public static DealerUpcard determineDealerUpcard() {
        DealerUpcard dealerUpcard = null;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("What is the dealer's face up card? ");
                String dealerCardInput = input.next().toUpperCase();
                dealerUpcard = mapUserInputToDealerUpcard(dealerCardInput);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid card.");
            }
        }
        return dealerUpcard;
    }

    public static HandType determineHandType(List<Card> hand) {
        HandType handType;
        int total = 0;
        boolean hasAce = false;
        boolean isPair = hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue();

        for (Card card : hand) {
            int value = card.getValue();
            total += value;

            if (value == 1 || value == 11) {
                hasAce = true;
            }
        }

        if ((isPair && hand.get(0).getValue() == 1) ||
            (isPair && hand.get(0).getValue() == 11)) {
            handType = HandType.PAIR_ACE;
        } else if (hasAce && total + 10 <= 21) {
                handType = HandType.valueOf("SOFT_" + (total + 10));
            } else {
                handType = total > 21 ? HandType.BUST : HandType.valueOf("HARD_" + total);
            }
        return handType;
    }

/*
    public static HandType updatedHandType(HandType handType, List<Card> hand) {
        String handTypeString = handType.toString();
        String[] parts = handTypeString.split("_");
        int total = Integer.parseInt(parts[1]);

        total += nextCard;

        HandType newHandType;

        if (total > 21) {
            newHandType = HandType.BUST;
        } else if (total <= 21) {
            newHandType = HandType.valueOf("HARD_" + total);
        } else {
            newHandType = HandType.valueOf("SOFT_" + total);
        }
        return newHandType;
    }
*/
    public static Action determineAction(HandType handType, DealerUpcard dealerUpcard, Map<HandType, Map<DealerUpcard, Action>> basicStrategy) {

        Map<DealerUpcard, Action> dealerUpcardActionMap = basicStrategy.get(handType);
        if (dealerUpcardActionMap == null) {
            System.out.println("No strategy found for hand type: " + handType);
            return Action.UNKNOWN;
        }

        Action action = dealerUpcardActionMap.get(dealerUpcard);

        if (action == null) {
            System.out.println("No strategy found for dealer card: " + dealerUpcard);
            return Action.UNKNOWN;
        }
        return action;
    }

    public static int convertCardValue(String cardInput) {
        return switch (cardInput.toUpperCase()) {
            case "A" -> 1;
            case "K", "Q", "J" -> 10;
            default -> Integer.parseInt(cardInput);
        };
    }
    public static DealerUpcard mapUserInputToDealerUpcard(String input) {
        return switch (input.toUpperCase()) {
            case "2" -> DealerUpcard.TWO;
            case "3" -> DealerUpcard.THREE;
            case "4" -> DealerUpcard.FOUR;
            case "5" -> DealerUpcard.FIVE;
            case "6" -> DealerUpcard.SIX;
            case "7" -> DealerUpcard.SEVEN;
            case "8" -> DealerUpcard.EIGHT;
            case "9" -> DealerUpcard.NINE;
            case "10", "J", "Q", "K" -> DealerUpcard.TEN;
            case "A" -> DealerUpcard.ACE;
            default -> throw new IllegalArgumentException("Invalid card input: " + input);
        };
    }

    public static String actionToString(Action action) {
        String actionString = null;
        switch (action) {
            case HIT -> actionString = "Hit";
            case SPLIT -> actionString = "Split";
            case STAND -> actionString = "Stand";
            case NO_SPLIT -> actionString = "Don't Split";
            case DOUBLE_DOWN -> actionString = "Double Down";
            case SURRENDER -> actionString = "Surrender if available. Otherwise, Stand";
            case UNKNOWN -> actionString = "Unknown combination has been presented.";
            case BUST -> actionString = "BUST! Your cards now equal over 21.";
        }
        return actionString;
    }

    public static List<Card> drawFirstCards() {
        List<Card> hand = new ArrayList<>();

        String cardInput1 = ConsoleController.promptForInput("What is your first card? ").toUpperCase();
        int card1 = convertCardValue(cardInput1);
        Card firstCard = new Card(card1, cardInput1);
        hand.add(firstCard);

        String cardInput2 = ConsoleController.promptForInput("What is your second card? ").toUpperCase();
        int card2 = convertCardValue(cardInput2);
        Card secondCard = new Card(card2, cardInput2);
        hand.add(secondCard);

        return hand;

    }

    public static Card drawNewCard() {
        String cardInput = ConsoleController.promptForInput("What is the next card drawn? ").toUpperCase();
        int card = convertCardValue(cardInput);
        Card newCard = new Card(card, cardInput);;

        return newCard;
    }
    public static HandType hit(List<Card> hand) {
        hand.add(drawNewCard());
        return determineHandType(hand);
    }

}
