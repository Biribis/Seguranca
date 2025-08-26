package criptografiaSimetrica;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class CriptografiaVigenere {
	public static void main(String[] args) {
		// Declaração de variáveis
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(System.in));
		
		String mensagem = "";
		String senha = "";
		String cifra = "";
		int opcao = 0;
		
		// Entrada de dados
		try {
			System.out.print("Digite a mensagem: ");
			mensagem = leitor.readLine();
			
			System.out.print("Digite a senha: ");
			senha = leitor.readLine();
			
			System.out.print("Digite <1> para encriptar ou" +
								" <2> para decriptar: ");
			opcao = Integer.parseInt(leitor.readLine());
			
		} catch (Exception erro) {
			System.out.println(erro);
		}
		
		// Processamento
		if (opcao == 1) {
			cifra = encriptar(mensagem, senha);
		} else if (opcao == 2) {
			cifra = decriptar(mensagem, senha);
		}
		
		// Saída de dados
		System.out.println("Resultado: " + cifra);
	}
	
	private static String encriptar(String mensagem, String senha) {
		String cifra = "";
		
		for (int i = 0; i < mensagem.length(); i++) {
			int letraMensagem = mensagem.charAt(i);
			int letraSenha = senha.charAt(i % senha.length());
			int letraCifra = (letraMensagem ^ letraSenha);
			
			// Protação contra caracteres não imprimíveis -esc- -backspace-
			String temp = Integer.toHexString(0xff & letraCifra);
			if(temp.length()==1) temp = "0" + temp;
			
			cifra += temp;
			
		}
		
		return cifra;
	}
	
	private static String decriptar(String cifra, String senha) {
		String mensagem = "";
		
		for (int i = 0; i < cifra.length(); i+=2) {
			int letraCifra = Integer.parseInt(
							 cifra.substring(i, i + 2), 16);
			int letraSeha = senha.charAt((i/2) % senha.length());
			int letraMensagem = (letraCifra ^ letraSeha);
			
			mensagem += ((char) letraMensagem);
		}
		
		return mensagem;
	}
	
}
