package criptografiaSimetrica;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LinearFeedbackShiftRegister {
	private static void inicializarRegistrador(int[] registrador) throws Exception {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String chave = "";
		
		System.out.print("Digite uma chave de 4 letras: ");
		chave = leitor.readLine();
		
		for (int i = 0; i < 4; i++) {
			int letra = chave.charAt(i);
			String binario = Integer.toBinaryString(letra);
			int tamanho = binario.length();
			for (int j = 0; j < (8 - tamanho); j++) {
				binario = "0" + binario;
			}
			for (int j = 0; j < 8; j++) {
				registrador[(i * 8) + j] = Integer.parseInt(binario.substring(j, j + 1));
			}
		}
	}
	private static int rotacionar(int[] registrador, int tipo) {
		int retorno = 0;
		int xor = 0;
		
		if(tipo == 0) {
			xor = registrador[31] ^ registrador[6] ^registrador[4] ^ registrador[2] ^ registrador[1] ^ registrador[0];
		} else {
			xor = registrador[31] ^ registrador[6] ^ registrador[5] ^ registrador[1];
		}
		
		retorno = registrador[0];
		
		for (int i = 0; i < 31; i++) {
			registrador[i] = registrador[i + 1];
		}
		registrador[31] = xor;
		
		return retorno;
	}
	
	public static void main(String[] args) {
		try {
			int[] cabeca = new int[32];
			int[] gerador0 = new int[32];
			int[] gerador1 = new int[32];
			
			inicializarRegistrador(cabeca);
			inicializarRegistrador(gerador0);
			inicializarRegistrador(gerador1);
			
			String acumulador = "";
			for (int i = 1; i < 800000001; i++) {
				int bit0 = 0;
				int bit1 = 0;
				
				if (rotacionar(cabeca, 0) == 0) {
					bit0 = rotacionar(gerador0, 0);
					bit1 = gerador1[0];
				} else {
					bit0 = gerador0[0];
					bit1 = rotacionar(gerador1, 1);
				}
				
				acumulador += (bit0 ^ bit1);
				if ((i % 8) == 0) {
					System.out.print((char) (Integer.parseInt(acumulador, 2)));
					acumulador = "";
				}
			}
			System.out.println("Done!");
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}

}
