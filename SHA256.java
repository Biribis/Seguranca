package aulas;

import java.security.MessageDigest;

public class SHA256 {
	public static String calcular(String texto) throws Exception {
		String retorno = "";
		
		MessageDigest objHash = MessageDigest.getInstance("SHA-256");
		byte[] temp = objHash.digest(texto.getBytes("UTF-8"));
		
		for(int i = 0; i < temp.length; i++) {
			String letra = Integer.toHexString(0xFF & temp[i]);
			if (letra.length() == 1) letra = "0" + letra;
			retorno += letra;
		}
		
		return retorno;
	}
}
