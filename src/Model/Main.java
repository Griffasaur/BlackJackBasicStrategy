package Model;

//import Controller.DisplayWindow;
import Model.Gameplay;
import Enums.Action;
import Enums.DealerUpcard;
import Enums.HandType;

import java.util.*;


public class Main {

    private static final Scanner input = new Scanner(System.in);

    //static DisplayWindow displayWindow = new DisplayWindow();


    public static Map<HandType, Map<DealerUpcard, Action>> loadData() {
        BasicStrategy basicStrategy = new BasicStrategy();
        String filePath = "C:\\Users\\evan0\\Workspace\\BlackJackBasicStrategy\\blackjackDataMap - Sheet2.csv";
        return basicStrategy.getBasicStrategyMapFromCSV(filePath);
    }

    public static void main(String[] args) {

        Map<HandType, Map<DealerUpcard, Action>> basicStrategyData = loadData();

        Gameplay.run(basicStrategyData);

        //displayWindow.window(basicStrategyData);

    }
}