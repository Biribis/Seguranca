package aulas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private static KeyPair gerarChavesAssimetricas() throws Exception{
		KeyPairGenerator objGerador = KeyPairGenerator.getInstance("RSA");
		objGerador.initialize(2048);
		return objGerador.generateKeyPair();
	}
	
	private static String encriptarRSA(KeyPair chavesAssimetricas, byte[] chaveSimetrica) throws Exception{
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.ENCRYPT_MODE, chavesAssimetricas.getPublic());
		byte[] temp = objCifra.doFinal(chaveSimetrica);
		return Base64.getEncoder().encodeToString(temp);
	}
	
	private static byte[] decriptarRSA(KeyPair chavesAssimetricas, String criptogramaDaChaveSimetrica) throws Exception{
		Cipher objCifra = Cipher.getInstance("RSA");
		objCifra.init(Cipher.DECRYPT_MODE, chavesAssimetricas.getPrivate());
		return objCifra.doFinal(Base64.getDecoder().decode(criptogramaDaChaveSimetrica));
	}
	
	private static String encriptarAES(String texto, byte[] chaveSimetrica) throws Exception{
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec objIv = new IvParameterSpec("0123456789012345".getBytes());
		Key objChave = new SecretKeySpec(chaveSimetrica, "AES");
		objCifra.init(Cipher.ENCRYPT_MODE, objChave, objIv);
		byte[] temp = objCifra.doFinal(texto.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(temp);
	}
	
	private static String decriptarAES(String criptograma, byte[] chaveSimetrica) throws Exception{
		Cipher objCifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec objIv = new IvParameterSpec("0123456789012345".getBytes());
		Key objChave = new SecretKeySpec(chaveSimetrica, "AES");
		objCifra.init(Cipher.DECRYPT_MODE, objChave, objIv);
		byte[] temp = objCifra.doFinal(Base64.getDecoder().decode(criptograma));
		return new String(temp, "UTF-8");
	}
	
	private static byte[] calcularHash(byte[] chaveSimetrica) throws Exception {
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		return objHash.digest(chaveSimetrica);
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
			KeyPair chavesAssimetricas = gerarChavesAssimetricas();
			
			System.out.println("Digite um texto: ");
			String texto = leitor.readLine();
			
			byte[] senha = new byte[100];
			for(int i = 0; i < senha.length; i++) {
				senha[i] = ((byte) (256 * Math.random()));
			}
			
			System.out.println(encriptarRSA(chavesAssimetricas, senha));
			
			System.out.println(encriptarAES(texto, calcularHash(senha)));
			
			System.out.print("Digite o criptograma da chave: ");
			senha = decriptarRSA(chavesAssimetricas, leitor.readLine());
			
			System.out.print("Digite o criptorama do texto: ");
			System.out.println(decriptarAES(leitor.readLine(), calcularHash(senha)));
		} catch(Exception erro) {
			System.out.println(erro);
		}
	}
}
