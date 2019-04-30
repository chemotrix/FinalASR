package asr.proyectoFinal.services;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import asr.proyectoFinal.dao.CloudantTweetStore;

import asr.proyectoFinal.dominio.Tweet;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class ScrapTweets {

	private static HttpURLConnection con;
	private static CloudantTweetStore store = new CloudantTweetStore();

	public static Map<String, String> get_tweets(String NombreUsuario) {

		String url = "https://eu-gb.functions.cloud.ibm.com/api/v1/namespaces/alu.comillas.edu_dev/actions/get_tweets?blocking=true&result=true";
		String usernameColonPassword = "3d9fda08-e508-42b8-bb24-efc75878c68e:FEtIy9LWQkvGe4CUYtw79ZUMnBnZLpQNaFQk5dqo69vQwNWJdhbNsOhUPE2KCSzf";
		String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());

		Map<String, String> nombreMap = new HashMap<String, String>();

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setDoOutput(true);

			con.addRequestProperty("Authorization", basicAuthPayload);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

			String pet = "{\"user\":\"" + NombreUsuario + "\"}";

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
			nombreMap = convertToMap(nombreMap, content.toString(), NombreUsuario);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nombreMap;
	}

	private static Map<String, String> convertToMap(Map<String, String> nombreMap, String content,
			String NombreUsuario) {
		// TODO Auto-generated method stub
		int num = 0;

		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
		System.out.println(content.toString());
		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
		String alltweets = "";
		try {
			JSONObject obj = new JSONObject(content.toString());
			Iterator<String> keys = obj.keys();
			num = 0;
			while (keys.hasNext() && num < 10) {
				String idUser = (String) keys.next();
				String tweet = obj.getString(idUser);
				String pic = picTwit(tweet);
				alltweets = alltweets + tweet;
				tweet = formatTweet(tweet);

				String tone = TAnalysis.get_sentiment(tweet);
				// System.out.println(tone);
				// System.out.println(idUser);
				// System.out.println(tweet);
				// System.out.println(pic);
				tone = formatTone(tone);

				nombreMap.put("id" + num, idUser);
				nombreMap.put("username" + num, NombreUsuario);
				nombreMap.put("tweet" + num, tweet);
				nombreMap.put("pic" + num, pic);
				nombreMap.put("tone" + num, tone);
				storeTweet(idUser, tweet, pic, tone, NombreUsuario);
				num++;
			}

		} catch (Exception e) {
			System.out.println("DOCUMENT_TONE NOT FOUND FOR THIS TWEET");
		}
		
		String insi = "";
		
		if (nombreMap.isEmpty()) {
			nombreMap = null;
		} else {
			insi = PInsights.get_pinsights(alltweets);
			nombreMap.put("insi", insi);
		}

		return nombreMap;
	}

	private static void storeTweet(String idUser, String tweet, String pic, String tone, String username) {
		Tweet t = new Tweet(idUser, tweet, pic, tone, username);

		if (tweet != null && tweet != "") {
			if (store.getDB() != null) {
				store.persist(t);
			}
		}

	}

	private static String formatTone(String tone) {
		JSONObject obj = new JSONObject(tone);
		String result = "";
		float score_def = 0;
		String tonename_def = null;
		try {
			JSONObject obj_documenttone = obj.getJSONObject("document_tone");
			JSONArray arr_documenttone = obj_documenttone.getJSONArray("tones");

			for (int i = 0; i < arr_documenttone.length(); i++) {

				float score = arr_documenttone.getJSONObject(i).getFloat("score");
				String tone_name = arr_documenttone.getJSONObject(i).getString("tone_name");
				System.out.println("\n\n\tScore: " + score);
				System.out.println("\tTone_name: " + tone_name);
				if (score > score_def) {
					score_def = score;
					tonename_def = tone_name;
				}
			}

		} catch (Exception e) {
			System.out.println("DOCUMENT_TONE NOT FOUND FOR THIS TWEET");
		}

		try {
			JSONObject obj_sentencetone = obj.getJSONObject("sentences_tone");
			JSONArray arr_sentencetone = obj_sentencetone.getJSONArray("tones");

			for (int i = 0; i < arr_sentencetone.length(); i++) {

				float score = arr_sentencetone.getJSONObject(i).getFloat("score");
				String tone_name = arr_sentencetone.getJSONObject(i).getString("tone_name");
				System.out.println("\n\n\tScore: " + score);
				System.out.println("\tTone_name: " + tone_name);
				if (score > score_def) {
					score_def = score;
					tonename_def = tone_name;
				}
			}

		} catch (Exception e) {
			System.out.println("SENTENCE_TONE NOT FOUND FOR THIS TWEET");
		}
		if (score_def > 0) {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			String s = df.format(score_def * 100);
			// result = tonename_def + ", with score " + s + "%";
			result = tonename_def + " - " + s + "%";
		}

		return result;
	}

	private static String formatTweet(String tweet) {
		String regex = "(((pic).)+(([0-9a-z_-]+\\.)+(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mn|mn|mo|mp|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|nom|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ra|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw|arpa)(:[0-9]+)?((\\/([~0-9a-zA-Z\\#\\+\\%@\\.\\/_-]+))?(\\?[0-9a-zA-Z\\+\\%@\\/&\\[\\];=_-]+)?)?))\\b";
		tweet = tweet.replaceAll("\\\\n", " ");

		tweet = tweet.replaceAll("\\n", " ");

		tweet = tweet.replaceAll("\n", " ");

		tweet = tweet.replaceAll(regex, " ");

		return tweet;
	}

	private static String picTwit(String tweet) {
		String pic = " ";
		int index = tweet.indexOf("pic.twitter.com/", 0);
		if (index != -1) {
			pic = tweet.substring(index, index + 26);
		}
		System.out.println(pic);
		return pic;
	}

}
