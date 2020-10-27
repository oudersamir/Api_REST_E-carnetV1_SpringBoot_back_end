package fsr.iao.formation.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

/*
 * génératrice ID
 */


@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHANUM = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String generateStringId(int length) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < length; i++) {
			string.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
		}

		return new String(string);
	}
}
