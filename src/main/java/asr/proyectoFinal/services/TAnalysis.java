package asr.proyectoFinal.services;

import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TAnalysis {

	public static String get_sentiment(String text) {
		String s = null;
		try {
			text = Traductor.translate(text, "es", "en", false);
			System.out.println("************************************TRANSALTE************************************");
			System.out.println(text);
			System.out.println("************************************TRANSLATE************************************");
			String usernameColonPassword = "apikey:7Qv8UueUtTNZTLkHbyHhmoM6ZOujC_DLCDBqX5fnAZtM";
			String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
			BufferedReader httpResponseReader = null;

			try {
				URL serverUrl = new URL(
						"https://gateway-lon.watsonplatform.net/tone-analyzer/api/v3/tone?version=2017-09-21&text="+ URLEncoder.encode(text, "UTF-8"));
				HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.addRequestProperty("Authorization", basicAuthPayload);
				//urlConnection.addRequestProperty("Accept-Language", "es");
				httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String lineRead;
				while ((lineRead = httpResponseReader.readLine()) != null) {
					System.out.println(lineRead);
					s = lineRead;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

}
