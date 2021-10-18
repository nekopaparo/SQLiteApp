package frame.tools;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class Caclulator extends JFrame {

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> numberData = new ArrayList<String>();
	String formula = "0";
	String ans = "";
	boolean start = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caclulator frame = new Caclulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Caclulator() {
		setTitle("簡易計算機");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(39, 42, 360, 60);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel Label_Main = new JLabel("0");
		Label_Main.setFont(new Font("Calibri", Font.PLAIN, 20));
		Label_Main.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(Label_Main, BorderLayout.CENTER);

		JLabel Label_Secondary = new JLabel();
		Label_Secondary.setFont(new Font("Calibri", Font.PLAIN, 15));
		Label_Secondary.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(Label_Secondary, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 112, 360, 140);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));

		JButton button_Del = new JButton("←");
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int JLabelTextLength = formula.length();
				if (formula.matches(".*\\s[+-×÷]\\s")) // 最後面是+-×÷
					formula = formula.substring(0, JLabelTextLength - 3);
				else if (formula.matches("[0-9]") || formula.matches("[0-9.]*") ) // 只剩個位數
					formula = "0";
				else
					formula = formula.substring(0, JLabelTextLength - 1);
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Del);

		JButton button_Percent = new JButton("%");
		button_Percent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('%');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Percent);

		JButton btnHachamachama = new JButton("Hachama");
		panel_1.add(btnHachamachama);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('7');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});

		JButton button_AC = new JButton("AC");
		button_AC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ans = "";
				formula = "0";
				numberData.clear();
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_AC);
		panel_1.add(button_7);

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('8');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_8);

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('9');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_9);

		JButton button_Plus = new JButton("+");
		button_Plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('+');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Plus);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('4');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_4);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('5');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_5);

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('6');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_6);

		JButton button_Minus = new JButton("-");
		button_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('-');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Minus);

		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('1');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_1);

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('2');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_2);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('3');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_3);

		JButton button_Multiply = new JButton("×");
		button_Multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('×');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Multiply);

		JButton button_Point = new JButton(".");
		button_Point.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('.');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_Point);

		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('0');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);
			}
		});
		panel_1.add(button_0);

		JButton button_Equals = new JButton("=");
		button_Equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result();
				Label_Main.setText(ans);
				Label_Secondary.setText(formula + " = ");
				formula = ans;
				ans = "Ans : " + ans;
				start = false;
			}
		});
		panel_1.add(button_Equals);

		JButton button_Divided = new JButton("÷");
		button_Divided.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input('÷');
				Label_Main.setText(formula);
				Label_Secondary.setText(ans);

			}
		});
		panel_1.add(button_Divided);
	}

	// 輸入
	public void input(char number) {
		switch (number) {
		case '.':
			if (formula.matches("(([0-9]+[.][0-9]+|[0-9]+%?)(\\s[+-×÷]\\s))*"))
				break;
			if (!formula.matches("(([0-9]+[.][0-9]+|[0-9]+%?)(\\s[+-×÷]\\s))*[0-9]*"))
				break;
		case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
			if ((formula.equals("0") || !start) && number != '.')
				formula = "";
			if (formula.matches(".*[0-9]+%"))
				formula += "\s×\s";
			formula += number;
			break;
		case '%':
			if (!formula.matches(".*[0-9]+") || formula.matches(".*[0-9]+%"))
				break;
			formula += number;
			break;
		case '+', '-', '×', '÷':
			if (formula.matches(".*\\s[+-×÷]\\s"))
				formula = formula.substring(0, formula.length() - 3);
			formula += '\s';
			formula += number;
			formula += '\s';
		}
		if (!start)
			start = true;
	}

	// 顯示結果
	public void result() {
		// 將數字和符號切割出來
		String[] tempArray = formula.split("\s");
		String temp;
		for (int i = 0; i < tempArray.length; i++) {
			temp = tempArray[i];
			// 換算%
			if (temp.matches("[0-9.]+%")) {
				temp = temp.substring(0, temp.length() - 1);
				temp = Double.toString(Double.parseDouble(temp) / 100);
			}
			numberData.add(temp);
		}
		// 先算×÷
		for (int i = 1; i < numberData.size();) {
			if (numberData.get(i).matches("[×÷]")) {
				numberData.set(i - 1, calculate(Double.parseDouble(numberData.get(i - 1)), numberData.get(i),
						Double.parseDouble(numberData.get(i + 1))));
				numberData.remove(i);
				numberData.remove(i);
				continue;
			}
			i += 2;
		}
		// 加總
		while (numberData.size() > 2) {
			numberData.set(0, calculate(Double.parseDouble(numberData.get(0)), numberData.get(1),
					Double.parseDouble(numberData.get(2))));
			numberData.remove(1);
			numberData.remove(1);
		}
		ans = numberData.get(0);
		numberData.clear();
	}

	// 計算加減乘除
	public String calculate(double number1, String doWhat, double number2) {
		switch (doWhat) {
		case "+":
			number1 += number2;
			break;
		case "-":
			number1 -= number2;
			break;
		case "×":
			number1 *= number2;
			break;
		case "÷":
			number1 /= number2;
			break;
		}
		return Double.toString(number1);
	}
}
