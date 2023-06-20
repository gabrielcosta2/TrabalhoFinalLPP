package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/main/imagens/image4.png")));
		setTitle("SkyWing - Gest\u00E3o de Encomendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GestaoEncomendas frame = new GestaoEncomendas();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnIniciar.setBackground(new Color(255, 255, 255));
		btnIniciar.setBounds(230, 400, 133, 34);
		btnIniciar.setFocusPainted(false);
		btnIniciar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnIniciar.setMinimumSize(new Dimension(141, 23));
		btnIniciar.setMaximumSize(new Dimension(141, 23));
		btnIniciar.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(btnIniciar);

		JLabel Background = new JLabel("");
		Background.setHorizontalAlignment(SwingConstants.CENTER);
		Background.setIcon(new ImageIcon(Home.class.getResource("/main/imagens/LOGO.png")));
		Background.setBounds(0, 0, 584, 461);
		contentPane.add(Background);
	}
}
