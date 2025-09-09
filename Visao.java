package aulas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Visao extends JFrame {
	// Propriedades da classe
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtTexto = new JTextArea();
	private JScrollPane jspTexto = new JScrollPane(txtTexto);
	
	private JTextField txtResumo = new JTextField();
	
	private JButton btnCalcular = new JButton("Calcular");
	
	// Método principal de execução da janela
	public static void main(String[] args) {
		new Visao().setVisible(true);
	}
	
	// Método construtor da classe
	public Visao() {
		// Configuração da janela
		setTitle("Cálculo do Resumo Unidirecional SHA-256");
		setSize(500, 380);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//Coniuração da área de texto
		jspTexto.setBounds(10, 10, 465, 250);
		add(jspTexto);
		txtTexto.setLineWrap(true);
		
		// Configuração da caixa de resumo
		txtResumo.setBounds(10, 270, 465, 20);
		add(txtResumo);
		
		//Configuração do botão calcular
		btnCalcular.setBounds(200, 300, 100, 30);
		add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtResumo.setText(SHA256.calcular(txtTexto.getText()));
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,  erro);
				}
			}
		});
	}
}
