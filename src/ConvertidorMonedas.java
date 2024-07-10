public class ConvertidorMonedas {
    private MonedaCambio rates;

    public ConvertidorMonedas(MonedaCambio rates) {
        this.rates = rates;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double fromRate = rates.getRate(fromCurrency);
        double toRate = rates.getRate(toCurrency);

        if (fromRate == 0.0 || toRate == 0.0) {
            System.err.println("Error: una de las tasas de cambio no está disponible.");
            return Double.NaN;
        }

        return amount * toRate / fromRate;
    }
}
