package Model;

import Controller.ConsoleController;
import Enums.Action;
import Enums.DealerUpcard;
import Enums.HandType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gameplay {

    public Gameplay() {
    }

    public static void run (Map<HandType, Map<DealerUpcard, Action>> basicStrategyData) {

        boolean keepPlaying = ConsoleController.printMainMenu();

        List<Card> hand;


        while (keepPlaying) {

            ConsoleController.printDivider();
            DealerUpcard dealerUpcard = BasicStrategy.determineDealerUpcard();

            ConsoleController.printDivider();
            hand = (BasicStrategy.drawFirstCards());
            HandType handType = BasicStrategy.determineHandType(hand);

            Action action = BasicStrategy.determineAction(handType, dealerUpcard, basicStrategyData);

            while (action == Action.HIT || action == Action.SPLIT) {
                ConsoleController.printDivider();
                System.out.println("Recommended action: " + BasicStrategy.actionToString(action));

                if (action == Action.SPLIT) {
                    action = Action.UNKNOWN;
                    List<Card> hand1 = new ArrayList<>();
                    hand1.add(hand.getFirst());
                    hand1.add(BasicStrategy.drawNewCard());

                    List<Card> hand2 = new ArrayList<>();
                    hand2.add(hand.get(1));
                    hand2.add(BasicStrategy.drawNewCard());

                    HandType handType1 = BasicStrategy.determineHandType(hand1);
                    Action splitAction1 = BasicStrategy.determineAction(handType1, dealerUpcard, basicStrategyData);
                    ConsoleController.printDivider();
                    System.out.println("Recommended action for first hand: " + BasicStrategy.actionToString(splitAction1));

                    while (splitAction1 == Action.HIT) {
                        splitAction1 = BasicStrategy.determineAction(BasicStrategy.hit(hand1), dealerUpcard, basicStrategyData);
                        ConsoleController.printDivider();
                        System.out.println("Recommended action for first hand: " + BasicStrategy.actionToString(splitAction1));
                    }

                    HandType handType2 = BasicStrategy.determineHandType(hand2);
                    Action splitAction2 = BasicStrategy.determineAction(handType2, dealerUpcard, basicStrategyData);
                    ConsoleController.printDivider();
                    System.out.println("Recommended action for second hand: " + BasicStrategy.actionToString(splitAction2));

                    while (splitAction2 == Action.HIT) {
                        splitAction2 = BasicStrategy.determineAction(BasicStrategy.hit(hand2), dealerUpcard, basicStrategyData);
                        ConsoleController.printDivider();
                        System.out.println("Recommended action for second hand: " + BasicStrategy.actionToString(splitAction2));
                    }
                    break;

                } else {
                    handType = BasicStrategy.hit(hand);

                    if (handType == HandType.BUST) {
                        action = Action.BUST;
                    } else {
                        action = BasicStrategy.determineAction(handType, dealerUpcard, basicStrategyData);
                    }
                }
            }

            if (action != Action.UNKNOWN) {
                ConsoleController.printDivider();
                System.out.println("Recommended action: " + BasicStrategy.actionToString(action));
            }
            ConsoleController.printDivider();
            String choice = ConsoleController.promptForInput("Would you like to play another hand? (Y/N) ");

            if (!choice.equalsIgnoreCase("y")) {
                keepPlaying = false;
                ConsoleController.printMainMenu();
            }
        }
    }
}
