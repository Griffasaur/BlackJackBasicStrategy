//package Controller;
//
//import Enums.Action;
//import Enums.DealerUpcard;
//import Enums.HandType;
//import Model.Gameplay;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DisplayWindow implements ActionListener {
//
//    Map<HandType, Map<DealerUpcard, Action>> basicStrategyData = new HashMap<>();
//
//    public void window (Map<HandType, Map<DealerUpcard, Action>> basicStrategyData) {
//        JFrame frame = new JFrame();
//
//        JTextField dealerTextField = new JTextField();
//        JLabel dealerLabel = new JLabel("Dealer's Card: ");
//
//        JButton submitButton = new JButton("Submit");
//        JTextField playerCard1 = new JTextField();
//        JTextField playerCard2 = new JTextField();
//        JLabel playerLabel1 = new JLabel("Your first card: ");
//        JLabel playerLabel2 = new JLabel("Your second card: ");
//
//        JLabel hitLabel = new JLabel("Next card drawn: ");
//        JLabel splitLabel1 = new JLabel("Next card drawn for first hand: ");
//        JLabel splitLabel2 = new JLabel("Next card drawn for second hand: ");
//
//
//        JLabel messageLabel = new JLabel();
//
//
//        dealerLabel.setBounds(100, 350, 125, 25);
//        playerLabel1.setBounds(100, 425, 125, 25);
//        playerLabel2.setBounds(100, 450, 125, 25);
//
//        dealerTextField.setBounds(225, 352, 50, 20);
//        playerCard1.setBounds(225, 427, 50, 20);
//        playerCard2.setBounds(225, 452, 50, 20);
//
//        frame.add(dealerLabel);
//        frame.add(playerLabel1);
//        frame.add(playerLabel2);
//
//        frame.add(dealerTextField);
//        frame.add(playerCard1);
//        frame.add(playerCard2);
//
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(480, 960);
//        frame.setLayout(null);
//
//
//        Gameplay.run(basicStrategyData);
//        frame.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
