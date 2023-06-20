package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

interface Entregavel {
	void entregar();
}

abstract class Encomenda {
	protected int numero;
	protected double peso;

	public Encomenda(int numero, double peso) {
		this.numero = numero;
		this.peso = peso;
	}

	public Encomenda() {
		this.numero = 0;
		this.peso = 0.0;
	}

	public Encomenda(Encomenda outra) {
		this.numero = outra.numero;
		this.peso = outra.peso;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public abstract double calcularPreco();

	public void print() {
		System.out.println("Número: " + numero);
		System.out.println("Peso: " + peso);
	}

	@Override
	public String toString() {
		return "\n Encomenda Numero " + numero + "\n Peso: " + peso + "kg";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Encomenda other = (Encomenda) obj;
		return numero == other.numero && peso == other.peso;
	}
}

class EncomendaNormal extends Encomenda {
	private double preco;

	public EncomendaNormal(int numero, double peso, double preco) {
		super(numero, peso);
		this.preco = preco;
	}

	public EncomendaNormal() {
		super();
		this.preco = 0.0;
	}

	public EncomendaNormal(EncomendaNormal outra) {
		super(outra);
		this.preco = outra.preco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public double calcularPreco() {
		return preco;
	}

	@Override
	public void print() {
		super.print();
		System.out.println("Preço: " + preco + "€");
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Normal";
	}
}

class EncomendaExpressa extends Encomenda implements Entregavel {
	private double preco;

	public EncomendaExpressa(int numero, double peso, double preco) {
		super(numero, peso);
		this.preco = preco;
	}

	public EncomendaExpressa() {
		super();
		this.preco = 0.0;
	}

	public EncomendaExpressa(EncomendaExpressa outra) {
		super(outra);
		this.preco = outra.preco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public double calcularPreco() {
		return preco;
	}

	@Override
	public void print() {
		super.print();
		System.out.println("\n Preço: " + preco + "€");
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Expressa";
	}

	@Override
	public void entregar() {
		System.out.println("Entrega Expressa Realizada.");
	}
}

class EncomendaPrioritaria extends EncomendaExpressa {
	private boolean entregaRapida;

	public EncomendaPrioritaria(int numero, double peso, double preco, boolean entregaRapida) {
		super(numero, peso, preco);
		this.entregaRapida = entregaRapida;
	}

	public EncomendaPrioritaria() {
		super();
		this.entregaRapida = false;
	}

	public EncomendaPrioritaria(EncomendaPrioritaria outra) {
		super(outra);
		this.entregaRapida = outra.entregaRapida;
	}

	public boolean isEntregaRapida() {
		return entregaRapida;
	}

	public void setEntregaRapida(boolean entregaRapida) {
		this.entregaRapida = entregaRapida;
	}

	@Override
	public void print() {
		super.print();
	}

	@Override
	public String toString() {
		return super.toString() + " Prioritaria";
	}
}

public class GestaoEncomendas {
	private JFrame frame;
	private JTextArea textArea;
	private ArrayList<Encomenda> encomendas;

	public GestaoEncomendas() {
		frame = new JFrame("SkyWing - Gestão de Encomendas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/main/imagens/image4.png")));

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton adicionarButton = new JButton("Adicionar Encomenda");
		adicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarEncomenda();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(adicionarButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		frame.add(panel);

		encomendas = new ArrayList<>();
	}

	public void exibirEncomendas() {
		StringBuilder sb = new StringBuilder();
		for (Encomenda encomenda : encomendas) {
			sb.append(encomenda.toString()).append("\n Preço: ").append(encomenda.calcularPreco()).append("€\n");
		}
		textArea.setText(sb.toString());
	}

	public void adicionarEncomenda() {
		String tipoEncomenda = JOptionPane.showInputDialog(null,
				"Insira o Tipo de Encomenda (Normal / Expressa / Prioritaria)", "Tipo de Encomenda",
				JOptionPane.INFORMATION_MESSAGE);

		if (tipoEncomenda != null) {
			tipoEncomenda = tipoEncomenda.toLowerCase();
			if (tipoEncomenda.equals("normal")) {
				EncomendaNormal encomendaNormal = criarEncomendaNormal();
				if (encomendaNormal != null) {
					if (!verificarNumeroEncomendaExistente(encomendaNormal.getNumero())) {
						encomendas.add(encomendaNormal);
						JOptionPane.showMessageDialog(frame, "Encomenda Normal Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE, null);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Ja existe uma Encomenda com o mesmo Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			} else if (tipoEncomenda.equals("expressa")) {
				EncomendaExpressa encomendaExpressa = criarEncomendaExpressa();
				if (encomendaExpressa != null) {
					if (!verificarNumeroEncomendaExistente(encomendaExpressa.getNumero())) {
						encomendas.add(encomendaExpressa);
						JOptionPane.showMessageDialog(frame, "Encomenda Expressa Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE, null);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Ja existe uma Encomenda com o mesmo Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			} else if (tipoEncomenda.equals("prioritaria")) {
				EncomendaPrioritaria encomendaPrioritaria = criarEncomendaPrioritaria();
				if (encomendaPrioritaria != null) {
					if (!verificarNumeroEncomendaExistente(encomendaPrioritaria.getNumero())) {
						encomendas.add(encomendaPrioritaria);
						JOptionPane.showMessageDialog(frame, "Encomenda Prioritaria Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE, null);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Ja existe uma Encomenda com o mesmo Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			} else {
				JOptionPane.showMessageDialog(frame, "ERRO - Tipo de Encomenda Invalido", "ERRO",
						JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}

	public boolean verificarNumeroEncomendaExistente(int numero) {
		for (Encomenda encomenda : encomendas) {
			if (encomenda.getNumero() == numero) {
				return true;
			}
		}
		return false;
	}

	public EncomendaNormal criarEncomendaNormal() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o numero da encomenda:",
					"Numero da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preco da encomenda:",
					"Preco da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaNormal(numero, peso, preco);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Invalidos", "ERRO", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
	}

	public EncomendaExpressa criarEncomendaExpressa() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o numero da encomenda:",
					"Numero da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preco da encomenda:",
					"Preco da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaExpressa(numero, peso, preco);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Inválidos", "ERRO", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
	}

	public EncomendaPrioritaria criarEncomendaPrioritaria() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o numero da encomenda:",
					"Numero da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preco da encomenda:",
					"Preco da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaPrioritaria(numero, peso, preco, false);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Invalidos", "ERRO", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
	}

	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GestaoEncomendas gestaoEncomendas = new GestaoEncomendas();
		gestaoEncomendas.show();
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

	public void setLocationRelativeTo(Object object) {
		// TODO Auto-generated method stub

	}
}
