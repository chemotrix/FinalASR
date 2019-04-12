package asr.proyectoFinal.services;

import java.io.*;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetVoiceOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class Text2Speech {
	public static InputStream toSpeech(String text, Voice voice, String format) throws IOException {
		InputStream in = null;
		System.out.println("DentroFinal");
		IamOptions options = new IamOptions.Builder().apiKey("U67NKpgcFqf42ouUUcElJ5fVmWMgKemN44YSa3lPCKfr").build();

		TextToSpeech textToSpeech = new TextToSpeech(options);

		textToSpeech.setEndPoint("https://gateway-lon.watsonplatform.net/text-to-speech/api");

		try {

			if (voice.getName() == null) {
				GetVoiceOptions getVoiceOptions = new GetVoiceOptions.Builder().voice("es-ES_LauraVoice").build();

				Voice vo = textToSpeech.getVoice(getVoiceOptions).execute();
				System.out.println(vo);
				
				voice = vo;
			}
			
			SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder().text(text).accept(format)
					.voice(voice.getName()).build();

			InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute();

			in = WaveUtils.reWriteWaveHeader(inputStream);

			System.out.println("DentroFinal");
			// Invoke a Text to Speech method
		} catch (NotFoundException e) {

			// Handle Not Found (404) exception
		} catch (RequestTooLargeException e) {

			// Handle Request Too Large (413) exception
		} catch (ServiceResponseException e) {

			// Base class for all exceptions caused by error responses from the service
			System.out.println("Service returned status code " + e.getStatusCode() + ": " + e.getMessage());
		}
		return in;
	}
}