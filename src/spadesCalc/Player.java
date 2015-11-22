package spadesCalc;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JPanel{
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public int count;
        public JLabel trickCounter;
        public int tricksTaken = 0;     
        Player(final int[][][] data, final int round, final int countA, String[] playerNames, final Game game){ 
                count = countA;
                setLayout(new GridLayout(3, 1));
                 JLabel name = new JLabel(playerNames[count]);
                 name.setFont(new Font("Serif", Font.PLAIN, 100));
                 add(name);
                 trickCounter = new JLabel(data[round][count][1]+"/"+data[round][count][0]);
                 trickCounter.setFont(new Font("Serif", Font.PLAIN, 100));
                 add(trickCounter);
                 JButton addTrick = new JButton("+");
                 addTrick.setFont(new Font("Serif", Font.PLAIN, 150));
                 add(addTrick);
                 if(tricksTaken == data[round][countA][0]){
                         setBackground(Color.green);
                 }else if(tricksTaken > data[round][countA][0]){
                         setBackground(Color.red);
                 }
                 addTrick.addMouseListener(new MouseListener() {
                        
                        @Override
                        public void mouseReleased(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseExited(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseEntered(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseClicked(MouseEvent arg0) {
                                tricksTaken ++;
                                trickCounter.setText(tricksTaken+"/"+data[round][countA][0]);
                                game.trickTaken(countA, round);
                                if(tricksTaken == data[round][countA][0]){
                                         setBackground(Color.green);
                                 }else{
                                         setBackground(Color.red);
                                 }
                        }
                });
        }
}