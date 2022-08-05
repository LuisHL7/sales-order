package test.luis.huapaya.TechnicalTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.luis.huapaya.TechnicalTest.models.SalesOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TechnicalTestApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TechnicalTestApplication.class, args);


		ResponseBody body = apiConsumer("https://api.holded.com/api/invoicing/v1/documents/salesorder").body();
		String string = body.string();
		System.out.println(string);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<SalesOrder> salesOrder = objectMapper.readValue(string, new TypeReference<>(){});
		System.out.println(salesOrder);



	}

	private static Response apiConsumer(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("Accept", "application/json")
				.addHeader("key", "953f868762ab5245be2138a520378b38")
				.build();

		return client.newCall(request).execute();
	}


}
