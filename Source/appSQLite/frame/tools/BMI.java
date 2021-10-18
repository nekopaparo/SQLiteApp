package frame.tools;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BMI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI frame = new BMI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	double height;
	double weight;
	boolean heightError = false;
	boolean weightError = false;

	public BMI() {
		setTitle("BMI");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("BMI計算");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 404, 48);
		panel.add(lblNewLabel);

		JLabel Label_Height = new JLabel("身高:");
		Label_Height.setHorizontalAlignment(SwingConstants.CENTER);
		Label_Height.setFont(new Font("新細明體", Font.PLAIN, 30));
		Label_Height.setBounds(10, 69, 100, 40);
		panel.add(Label_Height);

		JLabel LabelHeightError = new JLabel("");
		LabelHeightError.setForeground(Color.RED);
		LabelHeightError.setBounds(120, 107, 169, 20);
		panel.add(LabelHeightError);
		
		JTextField textHeight = new JTextField();
		textHeight.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String text = textHeight.getText();
				try {
					height = Double.parseDouble(text);
					heightError = false;
					LabelHeightError.setText("");
				} catch (NumberFormatException hError) {
					heightError = true;
					if (text.isBlank())
						LabelHeightError.setText("");
					else
						LabelHeightError.setText("身高格式錯誤");
				}
			}
		});
		textHeight.setFont(new Font("新細明體", Font.PLAIN, 20));
		textHeight.setBounds(120, 69, 263, 40);
		panel.add(textHeight);

		JLabel Label_Weight = new JLabel("體重:");
		Label_Weight.setFont(new Font("新細明體", Font.PLAIN, 30));
		Label_Weight.setHorizontalAlignment(SwingConstants.CENTER);
		Label_Weight.setBounds(10, 147, 100, 40);
		panel.add(Label_Weight);

		JLabel LabelWeightError = new JLabel("");
		LabelWeightError.setForeground(Color.RED);
		LabelWeightError.setBounds(120, 187, 169, 20);
		panel.add(LabelWeightError);

		JTextField textWeight = new JTextField();
		textWeight.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String text = textWeight.getText();
				try {
					weight = Double.parseDouble(text);
					weightError = false;
					LabelWeightError.setText("");
				} catch (NumberFormatException wError) {
					weightError = true;
					if (text.isBlank())
						LabelWeightError.setText("");
					else
						LabelWeightError.setText("體重格式錯誤");
				}
			}
		});
		textWeight.setFont(new Font("新細明體", Font.PLAIN, 20));
		textWeight.setBounds(120, 147, 263, 40);
		panel.add(textWeight);

		JLabel Label_Result = new JLabel("");
		Label_Result.setHorizontalAlignment(SwingConstants.CENTER);
		Label_Result.setFont(new Font("新細明體", Font.PLAIN, 25));
		Label_Result.setBounds(33, 207, 248, 38);
		panel.add(Label_Result);

		JButton btnNewButton = new JButton("送出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Label_Result.setForeground(Color.RED);
				if (!weightError && !heightError) {
					String result = BMIResult();
					try {
						double range = Double.parseDouble(result);
						// 留小數點後1位
						result = String.format("%.1f", range) + "\s";
						if (range < 18.5)
							result += ("過輕");
						else if (range < 24) {
							Label_Result.setForeground(Color.green);
							result += ("正常");
						} else if (range < 27)
							result += ("過重");
						else if (range < 30)
							result += ("輕度肥胖");
						else if (range < 35)
							result += ("中度肥胖");
						else
							result += ("重度肥胖");
					} catch (NumberFormatException resultError) {
						result = "Error:" + result;
					}
					Label_Result.setText(result);
				} else
					Label_Result.setText("請輸入數字");
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnNewButton.setBounds(326, 212, 88, 33);
		panel.add(btnNewButton);

	}

	public String BMIResult() {
		if (height < 500 && height > 0) { // 不超過500cm
			if (height > 5)
				height /= 100;
		} else
			return "身高有問題";
		if (weight > 0 && weight < 800) // 不超過800kg
			return Double.toString(weight / height / height);
		else
			return "體重有問題";
	}
}
