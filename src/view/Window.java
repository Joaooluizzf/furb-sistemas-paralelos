package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.QueueManager;
import java.awt.event.KeyAdapter;

public class Window extends JFrame {

	public static void main(String[] args) {
		Window window = new Window();
		EventQueue.invokeLater(() -> window.setVisible(true));
	}

	JButton btOpenConnection;
	JTextField tfPlayerName;
	JLabel lPlayerName;

	QueueManager ctrl = new QueueManager();

	Window() {
		lPlayerName = new JLabel("Player name:");

		tfPlayerName = new JTextField();
		tfPlayerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					openConnection();
				}
			}
		});
		btOpenConnection = new JButton("Nova conexão");
		btOpenConnection.addActionListener(l -> openConnection());

		lPlayerName.setBounds(10, 10, 90, 30);
		tfPlayerName.setBounds(100, 10, 90, 30);
		btOpenConnection.setBounds(210, 10, 200, 30);

		getContentPane().add(lPlayerName);
		getContentPane().add(tfPlayerName);
		getContentPane().add(btOpenConnection);

		setTitle("Wait and notify");
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(425, 100);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void openConnection() {
		ctrl.addPlayer(tfPlayerName.getText());
	}

}
