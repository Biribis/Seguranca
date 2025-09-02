package aulas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	private static String encriptar(String texto, String chave) throws Exception{
		String criptograma = "";
		
		Cipher objCifra = Cipher.getInstance("DES");// se for TripleDES pode usar, ou DESede, é a mesma coisa, tem que mudar em todos, nesse caso a chave tem 24 letras
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
		objCifra.init(Cipher.ENCRYPT_MODE, objChave);
		byte[] temp = objCifra.doFinal(texto.getBytes("UTF-8"));
		criptograma = Base64.getEncoder().encodeToString(temp);
	
		return criptograma;
	}
	
	private static String decriptar(String criptograma, String chave) throws Exception{	
		Cipher objCifra = Cipher.getInstance("DES");
		SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DES");
		objCifra.init(Cipher.DECRYPT_MODE, objChave);
		byte[] temp = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
		
		return new String(temp, "UTF-8");
	}
	
	public static void main(String[] args) {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
		String texto = "";
		String chave = "";
		String criptograma = "";
		
		try {
			System.out.print("Digite o texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite a chave: ");
			chave = leitor.readLine();
			
			criptograma = encriptar(texto, chave);
			System.out.println(criptograma);
			System.out.println(decriptar(criptograma, chave));
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}
}
