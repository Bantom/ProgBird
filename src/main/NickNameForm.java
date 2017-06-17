package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NickNameForm extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private String nickName;
    private int score;

    public NickNameForm(int score) {
        this.score = score;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 90);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        textField = new JTextField();
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setBounds(10, 11, 170, 23);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.setBounds(190, 10, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nickName = textField.getText();
                new dbconnect(nickName, score);
                dispose();

            }
        });
        contentPane.add(btnNewButton);
    }
}
