package asr.proyectoFinal.services;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScrapTweets {

	private static HttpURLConnection con;

	public static Map<String, String> get_tweets(String NombreUsuario) {

		String url = "https://eu-gb.functions.cloud.ibm.com/api/v1/namespaces/alu.comillas.edu_dev/actions/get_tweets?blocking=true&result=true";
		String usernameColonPassword = "3d9fda08-e508-42b8-bb24-efc75878c68e:FEtIy9LWQkvGe4CUYtw79ZUMnBnZLpQNaFQk5dqo69vQwNWJdhbNsOhUPE2KCSzf";
		String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());

		Map<String, String> nombreMap = new HashMap<String, String>();
		nombreMap.put("hola", "quetal");

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setDoOutput(true);

			con.addRequestProperty("Authorization", basicAuthPayload);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

			String pet = "{\"user\":\""+NombreUsuario+"\"}";

			wr.write(pet);
			wr.flush();
			StringBuilder content;

			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuilder();

				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}

			System.out.println(content.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nombreMap;
	}

}