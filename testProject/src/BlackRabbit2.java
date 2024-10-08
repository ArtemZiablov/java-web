import java.util.HashMap;
import java.util.Map;

public class BlackRabbit2 {
    public Map<Integer, Integer> findCashPayment(int amount) {
        // Доступные монеты
        int[] coins = {100, 50, 20, 10, 5, 2, 1};
        // Результат с количеством каждой монеты
        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 0);
        result.put(2, 0);
        result.put(5, 0);
        result.put(10, 0);
        result.put(20, 0);
        result.put(50, 0);
        result.put(100, 0);

        for (int coin : coins) {
            if (amount >= coin) {
                result.put(coin, amount / coin);
                amount %= coin;
            }
        }

        return result;
    }

    /**
     * Оптимизированная версия решения с изменённым набором монет: 1, 5, 8, 10, 20.
     * Вычисляет минимальное количество монет для получения суммы.
     */
    public Map<Integer, Integer> findCashPaymentOptimized(int amount) {
        int[] coins = {1, 5, 8, 10, 20};
        int[] minCoins = new int[amount + 1];
        int[] coinUsed = new int[amount + 1];

        // Инициализация массива минимальных монет
        for (int i = 1; i <= amount; i++) {
            minCoins[i] = Integer.MAX_VALUE;
        }
        minCoins[0] = 0; // Нулевая сумма требует 0 монет

        // Заполнение таблицы минимальных монет
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && minCoins[i - coin] + 1 < minCoins[i]) {
                    minCoins[i] = minCoins[i - coin] + 1;
                    coinUsed[i] = coin;
                }
            }
        }

        // Восстановление результата
        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 0);
        result.put(5, 0);
        result.put(8, 0);
        result.put(10, 0);
        result.put(20, 0);

        while (amount > 0) {
            int coin = coinUsed[amount];
            result.put(coin, result.get(coin) + 1);
            amount -= coin;
        }

        return result;
    }

    public static void main(String[] args) {
        BlackRabbit2 blackRabbit = new BlackRabbit2();
        System.out.println(blackRabbit.findCashPaymentOptimized(24));
    }
}