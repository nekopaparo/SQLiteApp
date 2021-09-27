package frame.tools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;

public class Slots extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// 圖片位置，預設8張圖
	static ImageIcon[] imagePath = new ImageIcon[8];
	// slot1.2.3的目前圖片
	static int[] labelNowImage = { 0, 0, 0 };
	// 圖片1.2.3滑動時間，放這邊是為了手動停止用
	static int[] labelImageRunTime = { 0, 0, 0 };
	/*
	// 彈跳視窗用
	JFrame message = new JFrame();
	*/
	// 避免遊戲重複執行用
	boolean startGame = false;
	// 餘額
	int balance = 100;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Slots frame = new Slots();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Slots() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 687, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 匯入圖片位置
		for (int i = 0; i < imagePath.length; i++) {
			imagePath[i] = changeSize(new ImageIcon(Slots.class.getResource("/images/" + i + ".jpg")));
		}

		JPanel slotHome = new JPanel();
		slotHome.setBounds(152, 84, 348, 125);
		contentPane.add(slotHome);
		slotHome.setLayout(new GridLayout(0, 3, 0, 0));

		// 預設slot1的顯示圖片
		JLabel slot_1 = new JLabel("");
		slot_1.setHorizontalAlignment(SwingConstants.CENTER);
		slot_1.setIcon(imagePath[labelNowImage[0]]);
		slotHome.add(slot_1);
		// 預設slot2的顯示圖片
		JLabel slot_2 = new JLabel("");
		slot_2.setHorizontalAlignment(SwingConstants.CENTER);
		slot_2.setIcon(imagePath[labelNowImage[1]]);
		slotHome.add(slot_2);
		// 預設slot3的顯示圖片
		JLabel slot_3 = new JLabel("");
		slot_3.setHorizontalAlignment(SwingConstants.CENTER);
		slot_3.setIcon(imagePath[labelNowImage[2]]);
		slotHome.add(slot_3);

		JPanel stopButtonHome = new JPanel();
		stopButtonHome.setBounds(152, 212, 348, 60);
		contentPane.add(stopButtonHome);
		stopButtonHome.setLayout(new GridLayout(0, 3, 0, 0));

		// slot1的停止按鍵
		JButton stopSlot_1 = new JButton("停止");
		stopSlot_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelImageRunTime[0] = 0;
			}
		});
		stopButtonHome.add(stopSlot_1);
		// slot2的停止按鍵
		JButton stopSlot_2 = new JButton("停止");
		stopSlot_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelImageRunTime[1] = 0;
			}
		});
		stopButtonHome.add(stopSlot_2);
		// slot3的停止按鍵
		JButton stopSlot_3 = new JButton("停止");
		stopSlot_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelImageRunTime[2] = 0;
			}
		});
		stopButtonHome.add(stopSlot_3);

		JPanel amountAndBetHome = new JPanel();
		amountAndBetHome.setBounds(152, 309, 348, 31);
		contentPane.add(amountAndBetHome);
		amountAndBetHome.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel amountHome = new JPanel();
		amountAndBetHome.add(amountHome);
		amountHome.setLayout(new GridLayout(0, 2, 0, 0));

		// 餘額title
		JLabel labelBalance = new JLabel("目前餘額:");
		labelBalance.setHorizontalAlignment(SwingConstants.CENTER);
		amountHome.add(labelBalance);
		// 顯示目前餘額
		JLabel labelNowBalance = new JLabel(Integer.toString(balance));
		amountHome.add(labelNowBalance);

		JPanel betHome = new JPanel();
		amountAndBetHome.add(betHome);

		// 下注title
		JLabel labelBet = new JLabel("下注");
		labelBet.setHorizontalAlignment(SwingConstants.CENTER);
		betHome.add(labelBet);
		// 下注輸入
		JTextField amountOfBet = new JTextField();
		amountOfBet.setText("1");
		betHome.add(amountOfBet);
		amountOfBet.setColumns(10);

		// 紀錄運作中slot數量且呼叫彈跳視窗用，不會顯示在視窗中
		TextField nowSlotsRun = new TextField();
		nowSlotsRun.setText("0");
		nowSlotsRun.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				// 運作中slot為0時
				if (nowSlotsRun.getText().equals("0")) {
					// 三個圖片相同時，下注*圖片編號*2
					if (labelNowImage[0] == labelNowImage[1] && labelNowImage[1] == labelNowImage[2]) {
						int get = Integer.parseInt(amountOfBet.getText()) * labelNowImage[0] * 2;
						balance += get;
						// 顯示訊息
						JOptionPane.showMessageDialog(contentPane, "恭喜中大獎: 獲得" + Integer.toString(get));
						labelNowBalance.setText(Integer.toString(balance));
					}
					// 兩個圖片相同時，下注*圖片編號
					else if (labelNowImage[0] == labelNowImage[1] || labelNowImage[1] == labelNowImage[2]
							|| labelNowImage[2] == labelNowImage[0]) {
						int get;
						if (labelNowImage[1] == labelNowImage[2]) //slot2.3相同
							get = Integer.parseInt(amountOfBet.getText()) * labelNowImage[1];
						else //slot1.2相同或slot3.1相同
							get = Integer.parseInt(amountOfBet.getText()) * labelNowImage[0];
						
						balance += get;
						// 顯示訊息
						JOptionPane.showMessageDialog(contentPane, "恭喜中二獎: 獲得" + Integer.toString(get));
						labelNowBalance.setText(Integer.toString(balance));
					} else
						JOptionPane.showMessageDialog(contentPane, "沒中獎");
					amountOfBet.setEnabled(true); //開啟下注輸入
				}
			}
		});
		contentPane.add(nowSlotsRun);

		// 用於GameStart分辨slot編號
		JLabel[] slot = { slot_1, slot_2, slot_3 };
		// 開始遊戲
		JButton gameStart = new JButton("開始");
		gameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//沒有slot在執行，開啟遊戲開關
				if (nowSlotsRun.getText().equals("0"))
					startGame = true;
				//開始遊戲
				if (startGame) {
					try {
						if (balance >= Integer.parseInt(amountOfBet.getText())) {
							startGame = false; //關掉遊戲開關
							nowSlotsRun.setText(Integer.toString(slot.length)); // slot運作N台
							//slot開始運作
							for (int i = 0; i < slot.length; i++) {
								Thread game = new GameStart(slot[i], i, nowSlotsRun);
								game.start();
							}
							// 下注消費
							balance -= Integer.parseInt(amountOfBet.getText());
							labelNowBalance.setText(Integer.toString(balance));
							amountOfBet.setEnabled(false); //關閉下注輸入
						} else {
							JOptionPane.showMessageDialog(contentPane, "餘額不足，請盡速儲值");
						}
					}
					// 下注不是數字時
					catch (NumberFormatException betError) {
						JOptionPane.showMessageDialog(contentPane, "BETERROR: 請輸入數字");
					}

				}

			}
		});
		gameStart.setBounds(283, 356, 87, 23);
		contentPane.add(gameStart);

		// 儲值，按一次餘額+100
		JButton storedValue = new JButton("儲值");
		storedValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balance += 100;
				labelNowBalance.setText(Integer.toString(balance));
			}
		});
		storedValue.setBounds(574, 22, 87, 23);
		contentPane.add(storedValue);
	}

	// 設定圖片大小
	public static ImageIcon changeSize(ImageIcon image) {
		image.setImage(image.getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE));
		return image;
	}
}

class GameStart extends Thread {
	JLabel slot;
	int slotNumber;
	TextField nowSlotsRun;
	int image;

	// 亂數幾秒圖片換一次(0.05-0.25秒)
	int sleepTime = ((int) (Math.random() * 5) + 1) * 50;

	GameStart(JLabel slot, int slotNumber, TextField nowSlotsRun) {
		// 取得圖片變動權
		this.slot = slot;
		// 取得label編號
		this.slotNumber = slotNumber;
		// Slot編號
		this.nowSlotsRun = nowSlotsRun;
		// 圖片轉動時間亂數變動(10-20秒)
		Slots.labelImageRunTime[slotNumber] = ((int) (Math.random() * 11) + 10) * 1000;
		// 取得目前圖片編號
		image = getLabelImage(slotNumber);
	}

	public void run() {
		imageSlide();
	}

	// 拉霸效果
	public void imageSlide() {
		showImage();// 顯示圖片
		timeRun(); // 計算經過時間
	}

	// 顯示圖片
	public void showImage() {
		slot.setIcon(Slots.imagePath[image]);
	}

	// 計算經過時間
	public void timeRun() {
		try {
			sleep(sleepTime); // 暫停時間
			if (Slots.labelImageRunTime[slotNumber] > 0) {
				// 時間消耗
				Slots.labelImageRunTime[slotNumber] -= sleepTime;
				image++; // 更換圖片
				if (image == 8)
					image = 0; // 超過圖片範圍重頭開始
				imageSlide(); // 圖片繼續轉動
			} else {
				setLabelImage(slotNumber, image); // 結束並紀錄目前圖片編號
				// 關掉slot
				nowSlotsRun.setText(Integer.toString(Integer.parseInt(nowSlotsRun.getText()) - 1));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 取得目前圖片編號
	public int getLabelImage(int slotNumber) {
		return Slots.labelNowImage[slotNumber];
	}

	// 更新成目前圖片編號
	public void setLabelImage(int slotNumber, int image) {
		Slots.labelNowImage[slotNumber] = image;
	}
}