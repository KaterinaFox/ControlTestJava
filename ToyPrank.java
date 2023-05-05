import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
public class ToyPrank {
    private ToyList toyList = new ToyList();
    // Призовые игрушки хранятся в список\массив
    private LinkedList<Toy> prizeToys = new LinkedList<Toy>();

    public static final String[] toysNames = {
            "Ласковый Мишутка",
            "Божья коровка",
            "Оранжевый бегемотик",
            "Веселый жираф",
            "Чижик-пыжик",
            "Чип и Дейл",
            "Пиноккио",
            "Барби"
    };

    // Инициализация/наполнение игрового автомата
    public ToyPrank() {
        int randomToyCount = ThreadLocalRandom.current().nextInt(toysNames.length/2, toysNames.length);
        for(int i=0; i < randomToyCount; i++) {
            int randomToyIndex = ThreadLocalRandom.current().nextInt(0, toysNames.length);
            String randomToyName = toysNames[randomToyIndex];
            Toy toy = toyList.findByName(randomToyName);
            if (toy == null) {
                // Такой игрушки не добавляли
                int randomToyAmount = ThreadLocalRandom.current().nextInt(1, 10);
                int randomToyDropFrequency = ThreadLocalRandom.current().nextInt(1, 100);
                toy = new Toy(i, randomToyName, randomToyDropFrequency);
                toyList.add(toy, randomToyAmount);
            } else {
                // Такая игрушка уже есть
                int randomToyAmount = ThreadLocalRandom.current().nextInt(1, 10);
                toyList.add(toy, randomToyAmount);
            }
        }
    }

    // Кол-во игрушки в призовом наборе
    private int countPrizeToy(Toy toy) {
        int count = 0;
        for(int i=0; i < prizeToys.size(); i++) {
            if (prizeToys.get(i).equals(toy))
                count++;
        }
        return count;
    }

    // Выбор призовой игрушки
    public boolean choosingPrize() {
        int maxDropFrequency = 0;
        Toy find_toy = null;
        for (Map.Entry<Toy, Integer> entry : toyList.getToys().entrySet()) {
            Toy toy = entry.getKey();
            int amount = entry.getValue();
            if (toy.getDropFrequency() > maxDropFrequency) {
                // Проверить что игрушка уже есть в массиве prizeToys
                int count = countPrizeToy(toy);
                int randomToyIndex = ThreadLocalRandom.current().nextInt(0, count+1);
                // Выбираем как кандидат на приз (с учетом того, что нужное кол-во игрушек есть)
                if (randomToyIndex == 0 && amount > count) {
                    find_toy = toy;
                    maxDropFrequency = toy.getDropFrequency()/(count+1);
                }
            }
        }
        if (find_toy != null) {
            prizeToys.add(find_toy);
            return true;
        } else
            return false;
    }

    // Получение призовой игрушки.
    // Удаляем из списка\массива первую игрушку и сдвигаем массив. А эту игрушку записываем в текстовый файл.
    public Toy receivePrize() throws IOException {
        if (prizeToys.size() == 0)
            return null;
        Toy toy = prizeToys.removeFirst();
        /////////////////////////////////////
        File file = new File("toysPrize.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(toy.toString()+"\n");
        } finally {
            fileWriter.close();
        }
        /////////////////////////////////////
        toyList.delete(toy);
        return toy;
    }

    // Есть призы
    public boolean hasPrizes() {
        return prizeToys.size() > 0;
    }

    // Есть игрушки
    public boolean hasToys() {
        return toyList.count() > 0;
    }

    // Общее кол-во игрушек
    public int count() {
        int count = 0;
        for (Map.Entry<Toy, Integer> entry : toyList.getToys().entrySet())
            count += entry.getValue();
        return count;
    }
}
