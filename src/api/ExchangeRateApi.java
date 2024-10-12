package api;

import com.google.gson.Gson;
import model.Currency;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApi {

    public Currency convertValue(String base, String target, double amount) {
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/8693850da986303f655de6e6/pair/"
                + base + "/" + target + "/" + amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Currency.class);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível obter o endereço");
        }

    }

}
