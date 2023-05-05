import java.io.IOException;

// Необходимо написать программу – розыгрыша игрушек в магазине детских товаров
public class FinalTask02 {

    public static void main(String[] args) throws IOException {
        // Инициализация/наполнение игрового автомата
        ToyPrank toyPrank = new ToyPrank();
        // Номера розыгрышей
        int number = 1;
        while (toyPrank.hasToys()) {
            // 10 раз разыгрываем призы
            System.out.println("raffle a prize " + String.valueOf(number++) + " tour with " + String.valueOf(toyPrank.count()) + " toys");
            for(int i=0; i<10; i++)
                toyPrank.choosingPrize();
            // Выбираем призы
            while (toyPrank.hasPrizes()) {
                Toy toy = toyPrank.receivePrize();
                if (toy != null) {
                    System.out.println("Receive " + toy.toString());
                }
            }
        }
        System.out.println("No more toys");
    }
}
