package MicroservicioCompartido.Model;

import java.util.Map;

public enum ClassType {
    CALISTENIA(15000),
    YOGA(12000),
    MMA(18000),
    ACROBACIA(20000);

    private final int price;

    ClassType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    // También puedes incluir un método para mostrar precios en un map (opcional)
    public static Map<String, Integer> getAllPrices() {
        return Map.of(
            CALISTENIA.name(), CALISTENIA.getPrice(),
            YOGA.name(), YOGA.getPrice(),
            MMA.name(), MMA.getPrice(),
            ACROBACIA.name(), ACROBACIA.getPrice()
        );
    }
}
