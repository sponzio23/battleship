package src.main.java.com.sponzio.battleship.unused;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomRulesPanel extends Frame implements  ActionListener {
    static String promptString = "Would you like to read the rules?";
    static String yesRulesString = "Yes, print the rules";
    static String noRulesString = "No, do not print the rules";
    static boolean rulesDone = false;
    static boolean printRules;

    public static void main(String[] args) {
        CustomRulesPanel panel1 = new CustomRulesPanel("Rules");
        panel1.setVisible(true);
    }

    public CustomRulesPanel(String title) {
        super(title);

        Button yesRules = new Button(yesRulesString);
        yesRules.setActionCommand(yesRulesString);
        yesRules.addActionListener(this);

        Button noRules = new Button(noRulesString);
        noRules.setActionCommand(noRulesString);
        noRules.addActionListener(this);

        JLabel prompt = new JLabel(promptString);

        setLayout(new BorderLayout());
        add(prompt, BorderLayout.PAGE_START);
        add(yesRules, BorderLayout.WEST);
        add(noRules, BorderLayout.EAST);

        //pack();
        setSize(250, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(yesRulesString)){
            printRules = true;
            rulesDone = true;
            dispose();
        }
        else if(e.getActionCommand().equals(noRulesString)){
            printRules = false;
            rulesDone = true;
            dispose();
        }
    }
}