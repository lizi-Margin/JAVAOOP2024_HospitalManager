import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ButtonGrid().setVisible(true);
            }
        });
    }
     
}


class ButtonGrid extends JFrame {


    private JButton btn6;
    private JButton btn10;
    private JButton btn15;
    private JButton btn8;
    public ButtonGrid() {
        setTitle("Button Grid");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton[] buttons = new JButton[19];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
        }

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Place buttons in the grid
        placeButton(buttons[0], 5, 0, 3, 1, gbc);//  1
        placeButton(buttons[1], 5, 1, 1, 1, gbc); //  2
        placeButton(buttons[2], 6, 1, 1, 1, gbc); //  3
        placeButton(buttons[3], 7, 1, 1, 1, gbc); //  4
        placeButton(buttons[4], 5, 2, 3, 1, gbc); //  5
        placeButton(buttons[5], 5, 3, 3, 1, gbc); //  6
        placeButton(buttons[6], 5, 4, 1, 1, gbc); //  7
        placeButton(buttons[7], 6, 4, 1, 1, gbc); //  8
        placeButton(buttons[8], 7, 4, 1, 1, gbc); //  9
        placeButton(buttons[9], 5, 5, 3, 1, gbc); //  10
        placeButton(buttons[10], 5, 6, 3, 1, gbc); //  11
        placeButton(buttons[11], 5, 7, 3, 1, gbc); //  12
        placeButton(buttons[12], 0, 8, 1, 4, gbc); //  13
        placeButton(buttons[13], 1, 8, 1, 2, gbc); //  14
        placeButton(buttons[14], 2, 8, 1, 2, gbc); //  15
        placeButton(buttons[15], 3, 8, 1, 2, gbc); //  16
        placeButton(buttons[16], 4, 8, 1, 2, gbc); //  17
        placeButton(buttons[17], 5, 8, 3, 1, gbc); //  18
        placeButton(buttons[18], 5, 9, 3, 1, gbc); //  19

        btn6 = buttons[5];
        btn10 = buttons[9];
        btn15 = buttons[14];
        btn8=buttons[7];

        buttons[4].addActionListener(new ActionListener() { // 5
            @Override
            public void actionPerformed(ActionEvent e) {
                btn6.setText("");
            }
        });

        buttons[6].addActionListener(new ActionListener() { // 7
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                btn8.add(textField);
                btn8.revalidate();
            }
        });

        buttons[8].addActionListener(new ActionListener() { // 9
            @Override
            public void actionPerformed(ActionEvent e) {
                btn10.setEnabled(!btn10.isEnabled());
            }
        });


        ActionListener colorListener = new ActionListener() { //10 11
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttons[9]) { // 10
                    
                    btn15.setBackground(Color.RED);
                    btn15.setForeground(Color.GREEN);
                }

                if (e.getSource() == buttons[10]) { // 11

                    btn15.setBackground(Color.BLUE);
                    btn15.setForeground(Color.RED);
                    
                }
            }
        };

        buttons[9].addActionListener(colorListener); //  10
        buttons[10].addActionListener(colorListener); // 11
    }

    private void placeButton(JButton button, int gridx, int gridy, int gridwidth, int gridheight, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        add(button, gbc);
    }


}
