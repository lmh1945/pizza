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
	  //setLayout���� ��ġ����� GridLayout���� ����
      setLayout(new GridLayout(4, 1));
      
      //JRadioButton�� ����ؼ� ���� ��ư�� �־���
      combo = new JRadioButton("�޺�");
      combo.addActionListener(this);
      potato = new JRadioButton("��������");
      potato.addActionListener(this);
      bulgogi = new JRadioButton("�Ұ��");
      bulgogi.addActionListener(this);
      chick = new JRadioButton("�߰���");
      chick.addActionListener(this);
      
      //�� ������ư�� �׷����� ������
      bg = new ButtonGroup();
      bg.add(combo);
      bg.add(potato);
      bg.add(bulgogi);
      bg.add(chick);
      
      //�гο� setBorder�� ������ ������ �������
      setBorder(BorderFactory.createTitledBorder("1. ����"));
      
      //�гο� ������ ������ư�� �־���
      add(combo);
      add(potato);
      add(bulgogi);
      add(chick);
      
   }

   public void actionPerformed(ActionEvent e) {		//������ ��ư�� ������ �� �����̸��� ������ �ʱ�ȭ
      if (e.getSource() == combo) { 
         setPizzaName("�޺�");
         setPizzaPrice(15000);
      }
      
      else if (e.getSource() == potato) {
         setPizzaName("��������");
         setPizzaPrice(14000);
      }
      
      else if (e.getSource() == bulgogi) {
         setPizzaName("�Ұ��");
         setPizzaPrice(16000);
      }
      
      else if (e.getSource() == chick) {
         setPizzaName("�߰���");
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