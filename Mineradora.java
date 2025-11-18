package aulas;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Mineradora {
	private String calcularHash(String texto) throws Exception{
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		byte[] vetor = objHash.digest(texto.getBytes("UTF-8"));
		String retorno = "";
		
		for(int i = 0; i < vetor.length; i++){
			String letra = Integer.toHexString(0xFF & vetor[i]);
			if (letra.length() == 1) {
				letra = ("0" + letra);
			}
			retorno += letra;
		}
		
		return retorno;
	}
	
	public void minerar(int tamanho) throws Exception{
		String referencia = "0";
		for (int i = 0; i < tamanho; i++) {
			referencia += "0";
		}
		
		BigInteger nonce = BigInteger.ZERO;
		while(true) {
			String hash = calcularHash(calcularHash(nonce.toString()));
			if (hash.startsWith(referencia)) {
				System.out.println("GAME OVER PORRA !!!!!");
				System.out.println("FIQUEIMILLIONÁRIO !!!!!");
				System.out.println(nonce + " " + hash);
				System.exit(0);
			}
			nonce = nonce.add(BigInteger.ONE);
		}
	}
}
