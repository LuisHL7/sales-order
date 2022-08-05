package test.luis.huapaya.TechnicalTest.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ConsumerApi {
    public static Response apiConsumer(String url) throws IOException {
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
