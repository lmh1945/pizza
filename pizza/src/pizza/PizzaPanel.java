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
	  //setLayout으로 배치방법을 GridLayout으로 결정
      setLayout(new GridLayout(4, 1));
      
      //JRadioButton을 사용해서 각각 버튼을 넣어줌
      combo = new JRadioButton("콤보");
      combo.addActionListener(this);
      potato = new JRadioButton("포테이토");
      potato.addActionListener(this);
      bulgogi = new JRadioButton("불고기");
      bulgogi.addActionListener(this);
      chick = new JRadioButton("닭갈비");
      chick.addActionListener(this);
      
      //각 라디오버튼을 그룹으로 묶어줌
      bg = new ButtonGroup();
      bg.add(combo);
      bg.add(potato);
      bg.add(bulgogi);
      bg.add(chick);
      
      //패널에 setBorder로 정려된 라인을 만들어줌
      setBorder(BorderFactory.createTitledBorder("1. 피자"));
      
      //패널에 피자의 종류버튼을 넣어줌
      add(combo);
      add(potato);
      add(bulgogi);
      add(chick);
      
   }

   public void actionPerformed(ActionEvent e) {		//각각의 버튼을 선택할 시 피자이름과 가격을 초기화
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