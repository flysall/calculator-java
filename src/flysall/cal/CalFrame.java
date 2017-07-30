package flysall.cal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 界面对象
 * 
 * @author yangenxiong yangenxiong2009@gmail.com
 * @author Kelvin Mak kelvin.mak125@gmail.com
 * @version  1.0
 */
public class CalFrame extends JFrame{
	private JTextField textField = null;
	private String[] mOp = {"MC", "MR", "MS", "M+"};
	private String[] rOp = {"Back", "CE", "C"};
	private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*",
			"%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
	private JButton button = null;
	private CalService service = new CalService();
	private ActionListener actionListener = null;
	private final int PRE_WIDTH = 360;
	private final int PRE_HEIGHT = 250;
	
	/**
	 * default constructor
	 */
	public CalFrame(){
		super();
		initialize();
	}
	
	/**
	 * initrialize the UI
	 * @return void
	 */
	private void initialize(){
		this.setTitle("计算器");
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 1));
		panel.add(getTextField(), BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(PRE_WIDTH, PRE_HEIGHT));
		JButton[] mButton = getMButton();
		// 新建一个panel，用于放置按钮
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		for(JButton b : mButton){
			panel1.add(b);
		}
		JButton[] rButton = getRButton();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,5));
		// 新建一个panel，用于放置按钮
		JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayout(1,3,3,3));
		for(JButton b : rButton){
			panel21.add(b);
		}
		JButton[] nButton = getNButton();
		// 新建一个panel，用于放置按钮
		JPanel panel22 = new JPanel();
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		for(JButton b : nButton){
			panel22.add(b);
		}
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}
	
	/**
	 * 这个方法用来获取监听器
	 * 
	 * @return ActionListener
	 */
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener(){
				/**
				 * 实现接口中的actionPerformed方法
				 * 
				 * @param e
				 *            ActionEvent
				 * @return void
				 */
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					String result = null;
					try{
						result = service.callMethod(cmd,  textField.getText());
					} catch (Exception el) {
						System.out.println(el.getMessage());
					}
					// 处理button的标记
					if (cmd.indexOf("MC") == 0) {		
						button.setText("M");
					}
					// 设置计算结果
					if(result !=  null) {
						textField.setText(result);
					}
				}
			};
		}
		return actionListener;
	}
	
	/**
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButton() {
		if (button == null) {
			button = new JButton();
		}
		return button;
	}
	
	/**
	 * 这个方法初始化输入框
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField("0");
			textField.setEditable(false);
			textField.setBackground(Color.white);
		}
		return textField;
	}
	/**
	 * 此方法获得计算器的存储操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getMButton() {
		JButton[] result = new JButton[mOp.length + 1];
		result[0] = getButton();
		for (int i = 0; i < this.mOp.length; i++) {
			JButton b = new JButton(this.mOp[i]);
			b.addActionListener(getActionListener());
			b.setBackground(Color.red);
			result[i+1] = b;
		}
		return result;
	}
	
	/**
	 * 此方法获得计算器的结果操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getRButton() {
		JButton[] result = new JButton[rOp.length];
		for(int i = 0; i < rOp.length; i++){
			JButton b = new JButton(this.rOp[i]);
			b.addActionListener(getActionListener());
			b.setBackground(Color.red);
			result[i] = b;
		}
		return result;
	}
	
	/**
	 * 此方法获得计算器的其它操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getNButton() {
		String[] redButton = {"/", "*", "-", "+", "=" };
		JButton[] result = new JButton[nOp.length];
		for(int i = 0; i < this.nOp.length; i++) {
			JButton b = new JButton(this.nOp[i]);
			b.addActionListener(getActionListener());
			Arrays.sort(redButton);
			if(Arrays.binarySearch(redButton,  nOp[i]) >= 0) {
				b.setForeground(Color.red);
			} 
			else {
				b.setForeground(Color.blue);
			}
			result[i] = b;
		}
		return result;
	}
}


















