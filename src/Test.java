import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;


public class Test {
    int bordX = 700;
    int boardY = 700;
    JFrame frame = new JFrame();
    JLabel textlabel = new JLabel();
    JPanel textpanle = new JPanel();
    JPanel gamepanle = new JPanel();
    JButton buttons[][] = new JButton[4][4];
    String shape[][] = new String[4][4];

    ImageIcon playIcon = new ImageIcon("1.jpg");

    String array[][] = new String[4][4];
    LinkedList<String> list = new LinkedList<>();

    private int firstRow = -1, firstCol = -1;

    private JButton firt = null;
    private void setAllButtonsEnabled(boolean enabled) {
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
//                if (buttons[r][c].isEnabled()) { // only re-enable buttons not matched yet
//                    buttons[r][c].setEnabled(enabled);
//                }
            }
        }
    }
    private int elapsedTime = 0; // Time in seconds
    private Timer gameTimer; // Timer to update the elapsed time
    private JLabel timerLabel; // Label to display the elapsed time



    public Test() {
        Random rand = new Random();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("4");
        list.add("4");
        list.add("5");
        list.add("5");
        list.add("6");
        list.add("6");
        list.add("7");
        list.add("7");
        list.add("8");
        list.add("8");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                array[i][j] = list.remove(rand.nextInt(0, list.size()));
            }
        }
        frame.setVisible(true);
        frame.setSize(bordX, boardY);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("memory");
        textlabel.setOpaque(true);

        textpanle.setLayout(new BorderLayout());
        textpanle.add(textlabel);
//        frame.add(textpanle);
//        gamepanle.add(buttons);
        frame.add(textpanle, BorderLayout.NORTH);
        gamepanle.setLayout(new GridLayout(4, 4));
        gamepanle.setBackground(Color.darkGray);
        frame.add(gamepanle);


        JButton second = new JButton();
        second.setText(" ");
        int sI = 0;
        int sJ = 0;
        int time = 0;


        Stack<JButton> stack = new Stack<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton(playIcon);
                buttons[i][j] = button;
                gamepanle.add(button);

                button.setBackground(Color.darkGray);
                button.setForeground(Color.white);
                button.setFont(new Font("Arial", Font.BOLD, 120));

                int row = i;
                int col = j;

                button.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 button.setText(array[row][col]); // show value

                                                 if (firt == null) {
                                                     firt = button;
                                                     firstRow = row;
                                                     firstCol = col;
                                                 } else {
                                                     if (button == firt) return; // ignore same button

                                                     if (array[row][col].equals(array[firstRow][firstCol])) {
                                                         // Match found
                                                         button.setEnabled(false);
                                                         firt.setEnabled(false);
                                                         firt = null; // reset for next turn
                                                     } else {
                                                         // Not a match – disable all buttons temporarily
                                                         setAllButtonsEnabled(false);

                                                         Timer timer = new Timer(750, new ActionListener() {
                                                             public void actionPerformed(ActionEvent evt) {
                                                                 button.setText("");
                                                                 firt.setText("");
                                                                 firt = null; // reset for next turn

                                                                 setAllButtonsEnabled(true); // re-enable buttons

                                                                 ((Timer) evt.getSource()).stop();
                                                             }


                                                         });

                                                         timer.setRepeats(false);
                                                         timer.start();
                                                     }
                                                 }

                                             }

                                         });

            }

// Only add panel and show frame ONCE, after all buttons are added
        frame.add(gamepanle);
        frame.setVisible(true);


    }
    }
}