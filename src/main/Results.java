package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class Results extends JFrame {
    private JPanel contentPane;
    private String nickName;
    private String score;
    public Map<Integer, String> map1 = new HashMap<Integer, String>();

    public Results(Map<Integer, String> e) {
        this.map1 = e;
        mainFrame();
    }

    public void mainFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 210);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout());

        JTextArea textAreaName = new JTextArea("NAME", 1, 10);
        JTextArea textAreaScore = new JTextArea("SCORE", 1, 10);
        textAreaName.setBackground(Color.LIGHT_GRAY);
        textAreaScore.setBackground(Color.LIGHT_GRAY);
        textAreaName.setLineWrap(true);
        textAreaScore.setLineWrap(true);
        textAreaName.setEditable(false);
        textAreaScore.setEditable(false);
        contentPane.add(textAreaName);
        contentPane.add(textAreaScore);

        for (Map.Entry<Integer, String> e : map1.entrySet()) {
            nickName = e.getValue();
            score = String.valueOf(e.getKey());

            textAreaName = new JTextArea(nickName, 1, 10);
            textAreaScore = new JTextArea(score, 1, 10);
            textAreaName.setLineWrap(true);
            textAreaScore.setLineWrap(true);
            textAreaName.setEditable(false);
            textAreaScore.setEditable(false);
            contentPane.add(textAreaName);
            contentPane.add(textAreaScore);
        }


        JButton btnNewButton = new JButton("Ok");
        btnNewButton.setBounds(100, 210, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnNewButton);
    }
}