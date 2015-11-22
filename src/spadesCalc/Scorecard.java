package spadesCalc;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scorecard extends JFrame{
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public Scorecard(final int importedNumberOfPlayers, final String[] importedPlayerNames, final int importedDealer, final int[][][] importedData, final int importedRound, final int importedTotalTricks) {
                int greatestScore = 0;
                for(int count = 0;count<importedNumberOfPlayers;count++){
                        if(importedData[importedRound][count][0] == importedData[importedRound][count][1]){
                                if(importedRound==0){
                                        importedData[importedRound][count][2] = 10;
                                }else{
                                        importedData[importedRound][count][2] = importedData[importedRound-1][count][2]+10;
                                }
                        }else{
                                if(importedRound==0){
                                        importedData[importedRound][count][2] = importedData[importedRound][count][1];
                                }else{
                                        importedData[importedRound][count][2] = importedData[importedRound-1][count][2]+importedData[importedRound][count][1];
                                }
                        }
                        if(importedData[importedRound][count][2]>greatestScore){
                                greatestScore = importedData[importedRound][count][2];
                                System.out.println(greatestScore);
                        }
                }
                int[] sortedData = new int[importedNumberOfPlayers];
                int currentPlayer = 0;
                for(int count  = greatestScore; count >=0; count --){
                        for(int count2 = 0; count2 < importedNumberOfPlayers; count2++){
                                if(importedData[importedRound][count2][2]==count){
                                        sortedData[currentPlayer]=count2;
                                        currentPlayer++;
                                }
                        }
                }
                
                setLayout(new GridLayout(1,importedNumberOfPlayers+1,10,10));
                
                setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setResizable(true);
                setTitle("Spades Calculator");
                
                JPanel nextGame = new JPanel();
                nextGame.setLayout(new GridLayout(2, 1));
                JButton plusOne = new JButton("+1");
                plusOne.setFont(new Font("Serif", Font.PLAIN, 50));
                plusOne.addMouseListener(new MouseListener() {
                        
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
                                int dealer = importedDealer;
                                setVisible(false);
                                if(importedDealer+1==importedNumberOfPlayers){
                                        dealer = 0;
                                }else{
                                        dealer++;
                                }
                                
                                Bidding bidding = new Bidding(importedNumberOfPlayers,importedPlayerNames,dealer,importedData,importedRound+1,importedTotalTricks+1);
                                bidding.setVisible(true);
                                dispose();
                                
                        }
                });
                nextGame.add(plusOne);
                
                if(importedTotalTricks!=1){
                        JButton minusOne = new JButton("-1");
                        minusOne.addMouseListener(new MouseListener() {
                                
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
                                        int dealer = importedDealer;
                                        setVisible(false);
                                        if(importedDealer+1==importedNumberOfPlayers){
                                                dealer = 0;
                                        }else{
                                                dealer++;
                                        }
                                        
                                        Bidding bidding = new Bidding(importedNumberOfPlayers,importedPlayerNames,dealer,importedData,importedRound+1,importedTotalTricks-1);
                                        bidding.setVisible(true);
                                        dispose();
                                        
                                }
                        });
                        minusOne.setFont(new Font("Serif", Font.PLAIN, 50));
                        nextGame.add(minusOne);
                }
                
                add(nextGame);
                for(int count = 0; count < importedNumberOfPlayers; count++){
                        JPanel playerPanel = new JPanel();
                        if(importedRound < 5){
                                playerPanel.setLayout(new GridLayout(8,1));
                        }else{
                                playerPanel.setLayout(new GridLayout(importedRound+3,1));
                        }
                        JPanel enclosingPanel = new JPanel();
                        JLabel label = new JLabel(importedPlayerNames[sortedData[count]]);
                        label.setFont(new Font("Serif", Font.PLAIN, 50));
                        enclosingPanel.add(label);
                        playerPanel.add(enclosingPanel);
                        
                        
                        JPanel enclosingPanel2 = new JPanel();
                        JLabel label2 = new JLabel(importedData[importedRound][sortedData[count]][2]+"");
                        label2.setFont(new Font("Serif", Font.PLAIN, 50));
                        enclosingPanel2.add(label2);
                        playerPanel.add(enclosingPanel2);
                        for(int count2 = 0; count2 <= importedRound; count2++){
                                JPanel roundPanel = new JPanel();
                                if(importedData[count2][sortedData[count]][1]==importedData[count2][sortedData[count]][0]){
                                        roundPanel.setBackground(Color.GREEN);
                                }else{
                                        roundPanel.setBackground(Color.RED);
                                }
                                roundPanel.setLayout(new GridLayout(1, 2));
                                JLabel pointsDisplay = new JLabel(importedData[count2][sortedData[count]][2]+"");
                                pointsDisplay.setFont(new Font("Serif", Font.PLAIN, 50));
                                roundPanel.add(pointsDisplay);
                                JLabel trickDisplay = new JLabel(importedData[count2][sortedData[count]][1]+"/"+importedData[count2][sortedData[count]][0]);
                                trickDisplay.setFont(new Font("Serif", Font.PLAIN, 40));
                                roundPanel.add(trickDisplay);
                                playerPanel.add(roundPanel);
                        }
                        add(playerPanel);
                }
        }
}