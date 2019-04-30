package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class PInsights {

	

	public static String get_pinsights(String text) {
		String profile = "";
		try {

			PersonalityInsights service = new PersonalityInsights("2017-10-13");
			IamOptions options = new IamOptions.Builder().apiKey("SAAn-fVYFPx5TbeeI81h-JZoABauYvEJFU97ml9qyRC1")
					.build();
			service.setEndPoint("https://gateway-lon.watsonplatform.net/personality-insights/api");
			service.setIamCredentials(options);

			// Demo content from Moby Dick by Hermann Melville (Chapter 1)

			ProfileOptions profileOptions = new ProfileOptions.Builder().text(text).build();

			profile = service.profile(profileOptions).execute().getPersonality().toString();
			System.out.println("ASDKDLAKSMDLAKMSDLMALSKMDALKMDLM");
			System.out.println(profile);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;
	}

}
