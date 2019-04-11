package asr.proyectoFinal.services;

import java.io.*;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class Text2Speech {
	public static int toSpeech(String palabra) throws IOException {
		IamOptions options = new IamOptions.Builder()
				.apiKey("U67NKpgcFqf42ouUUcElJ5fVmWMgKemN44YSa3lPCKfr")
				.build();

		TextToSpeech textToSpeech = new TextToSpeech(options);

		textToSpeech.setEndPoint("https://gateway-lon.watsonplatform.net/text-to-speech/api");

		try {
			SynthesizeOptions synthesizeOptions = new SynthesizeOptions
					.Builder()
					.text("Hola Bartolome Ucero")
					.accept("audio/wav")
					.voice("es-ES_EnriqueVoice")
					.build();

			InputStream inputStream = textToSpeech
					.synthesize(synthesizeOptions)
					.execute();
			
			InputStream in = WaveUtils
					.reWriteWaveHeader(inputStream);

			OutputStream out = new FileOutputStream("hello_world.wav");
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.close();
			in.close();
			inputStream.close();

			// Invoke a Text to Speech method
		} catch (NotFoundException e) {

			// Handle Not Found (404) exception
		} catch (RequestTooLargeException e) {

			// Handle Request Too Large (413) exception
		} catch (ServiceResponseException e) {

			// Base class for all exceptions caused by error responses from the service
			System.out.println("Service returned status code " + e.getStatusCode() + ": " + e.getMessage());
		}
		return 0;
	}
}