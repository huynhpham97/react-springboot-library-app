package com.luv2code.srpingbootlibrary.utils;

import java.util.Base64;
import java.util.Map;
import java.util.HashMap;

public class ExtractJWT {

	public static String payloadJWTExtraction(String token, String extraction) {

		token.replace("Bearer", "");

		// Split JWT parts and decode payload
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));

		//split payload and put it for map
		String[] entries = payload.split(",");
		Map<String, String> map = new HashMap<String, String>();

		// find sub(email) using for
		for (String entry : entries) {
			String[] keyValue = entry.split(":");
			if (keyValue[0].equals(extraction)) {
				int remove = 1;
				if (keyValue[1].endsWith("}")) {
					remove = 2;
				}

				keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
				keyValue[1] = keyValue[1].substring(1);

				map.put(keyValue[0], keyValue[1]);
			}
		}

		if (map.containsKey(extraction)) {
			return map.get(extraction);
		}
		return null;
	}
}
