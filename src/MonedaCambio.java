import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MonedaCambio {
    private Map<String, Double> rates;
    private String apiKey;

    public MonedaCambio(String apiKey) {
        this.apiKey = apiKey;
        rates = new HashMap<>();
        updateRates();
    }

    public double getRate(String currency) {
        return rates.getOrDefault(currency, 0.0);
    }

    private void updateRates() {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
        try {
            String response = HttpClientHelper.get(url);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            JsonObject ratesJson = jsonObject.getAsJsonObject("conversion_rates");

            for (Map.Entry<String, JsonElement> entry : ratesJson.entrySet()) {
                rates.put(entry.getKey(), entry.getValue().getAsDouble());
            }

            System.out.println("Tasas de cambio cargadas:");
            for (Map.Entry<String, Double> entry : rates.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar las tasas de cambio: " + e.getMessage());
        }
    }
}
