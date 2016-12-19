package pizza;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class PizzaPanel extends JPanel implements ActionListener {
   private String pizzaName = "";
   private int pizzaPrice = 0;
   private JRadioButton combo, potato, bulgogi, chick;
   private ButtonGroup bg;
   
   public PizzaPanel() {
	 
      setLayout(new GridLayout(4, 1));

      combo = new JRadioButton("콤보");
      combo.addActionListener(this);
      potato = new JRadioButton("포테이토");
      potato.addActionListener(this);
      bulgogi = new JRadioButton("불고기");
      bulgogi.addActionListener(this);
      chick = new JRadioButton("닭갈비");
      chick.addActionListener(this);
      
      bg = new ButtonGroup();
      bg.add(combo);
      bg.add(potato);
      bg.add(bulgogi);
      bg.add(chick);
      
      setBorder(BorderFactory.createTitledBorder("1. 피자"));
      
      add(combo);
      add(potato);
      add(bulgogi);
      add(chick);
      
   }

   public void actionPerformed(ActionEvent e) {		
      if (e.getSource() == combo) { 
         setPizzaName("콤보");
         setPizzaPrice(15000);
      }
      
      else if (e.getSource() == potato) {
         setPizzaName("포테이토");
         setPizzaPrice(14000);
      }
      
      else if (e.getSource() == bulgogi) {
         setPizzaName("불고기");
         setPizzaPrice(16000);
      }
      
      else if (e.getSource() == chick) {
         setPizzaName("닭갈비");
         setPizzaPrice(15000);
      }
      
   }
   
   public String getPizzaName() {
      return pizzaName;
   }
   
   public void setPizzaName(String pizzaName) {
      this.pizzaName = pizzaName;
   }

   public int getPizzaPrice() {
      return pizzaPrice;
   }

   public void setPizzaPrice(int pizzaPrice) {
      this.pizzaPrice = pizzaPrice;
   }

}
