import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.event.*;
import java.util.HashMap;

public class VendingMachine {
    private JFrame frame;
    private JPanel panel;
    private JTextField pinField;
    private JButton insertPinButton;
    private JButton coffeeButton;
    private JButton teaButton;
    private JButton waterButton;
    private JButton snackButton;
    private JButton checkBalanceButton;
    private JLabel balanceLabel;
    private JButton  cashButton;
    private JTextComponent amountField;

    private HashMap<String, Integer> keychains; // Memorizza i pin delle chiavette e i loro saldi

    public VendingMachine() {
        keychains = new HashMap<>();
        keychains.put("1234", 20); // Esempio di chiavetta con pin "1234" e saldo 20 euro
        keychains.put("1111", 50); //Saldo 50 euro

        frame = new JFrame("Vending Machine");
        panel = new JPanel();
        pinField = new JTextField(10);
        insertPinButton = new JButton("Inserisci Pin");
        coffeeButton = new JButton("Caffè - 2 euro");
        teaButton = new JButton("Tè - 3 euro");
        waterButton = new JButton("Acqua - 1 euro");
        snackButton = new JButton("Merendina - 3 euro");
        checkBalanceButton = new JButton("Verifica Saldo");
        balanceLabel = new JLabel();
        cashButton = new JButton("Aggiundi soldi $");
        amountField = new JTextField(5);

        panel.add(pinField);
        panel.add(insertPinButton);
        panel.add(coffeeButton);
        panel.add(teaButton);
        panel.add(waterButton);
        panel.add(snackButton);
        panel.add(checkBalanceButton);
        panel.add(balanceLabel);
        panel.add(cashButton);
        panel.add(amountField);
        insertPinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
                if (keychains.containsKey(pin)) {
                    enableButtons();
                    balanceLabel.setText("Saldo: " + keychains.get(pin) + " euro");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pin non valido");
                }
            }
        });

        coffeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePurchase(2);
            }
        });

        teaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePurchase(3);
            }
        });

        waterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePurchase(1);
            }
        });

        snackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePurchase(3);
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
                if (keychains.containsKey(pin)) {
                    JOptionPane.showMessageDialog(frame, "Saldo attuale: " + keychains.get(pin) + " euro");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pin non valido");
                }
            }
        });
        
        
       cashButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
				int amount = Integer.parseInt(amountField.getText());
                if (keychains.containsKey(pin)) {
                    int balance = keychains.get(pin);
                    keychains.put(pin, balance + amount);
                    balanceLabel.setText("Saldo: " + keychains.get(pin) + " euro");
                    JOptionPane.showMessageDialog(frame, "Denaro caricato con successo!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pin non valido");
                }
            }
        });
       
       
       

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        disableButtons();
    }

    private void makePurchase(int cost) {
        String pin = pinField.getText();
        if (keychains.containsKey(pin)) {
            int balance = keychains.get(pin);
            if (balance >= cost) {
                keychains.put(pin, balance - cost);
                JOptionPane.showMessageDialog(frame, "Acquisto effettuato!");
                balanceLabel.setText("Saldo: " + keychains.get(pin) + " euro");
            } else {
                JOptionPane.showMessageDialog(frame, "Saldo insufficiente");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Pin non valido");
        }
    }

    private void disableButtons() {
        coffeeButton.setEnabled(false);
        teaButton.setEnabled(false);
        waterButton.setEnabled(false);
        snackButton.setEnabled(false);
        checkBalanceButton.setEnabled(false);
        cashButton.setEnabled(false);
    }

    private void enableButtons() {
        coffeeButton.setEnabled(true);
        teaButton.setEnabled(true);
        waterButton.setEnabled(true);
        snackButton.setEnabled(true);
        checkBalanceButton.setEnabled(true);
        cashButton.setEnabled(true);
    }

    public static void main(String[] args) {
        new VendingMachine();
    }
}
