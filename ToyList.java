import java.util.HashMap;
import java.util.Map;
public class ToyList {

    private HashMap<Toy, Integer> toys = new HashMap<Toy, Integer>();
    public HashMap<Toy, Integer> getToys() {
        return toys;
    }

    // Метод добавление новых игрушек
    public int add(Toy toy, int amount) {
        int count;
        if (!toys.containsKey(toy)) {
            toys.put(toy, amount);
            count = 1;
        } else {
            count = toys.get(toy) + amount;
            toys.put(toy, count);
        }
        return count;
    }
    public int add(Toy toy) {
        return this.add(toy, 1);
    }
    // Метод удаления игрушек
    public int delete(Toy toy) {
        int count = toys.get(toy) - 1;
        if (count > 0)
            toys.put(toy, count);
        else
            toys.remove(toy);
        return count;
    }
    // Изменения веса (частоты выпадения игрушки)
    public boolean changeDropFrequency(int toyId, int dropFrequency) {
        Toy find_toy = null;
        for (Map.Entry<Toy, Integer> entry : toys.entrySet()) {
            Toy toy = entry.getKey();
            if (toy.getId() == toyId) {
                find_toy = toy;
                break;
            }
        }
        if (find_toy != null) {
            Integer count = toys.remove(find_toy);
            find_toy.setDropFrequency(dropFrequency);
            toys.put(find_toy, count);
            return true;
        } else
            return false;
    }
    // Кол-во видов игрушек
    public int count() {
        return toys.size();
    }
    // Поиск игрушки по имени
    public Toy findByName(String name) {
        for (Map.Entry<Toy, Integer> entry : toys.entrySet()) {
            Toy toy = entry.getKey();
            if (toy.getName().equals(name))
                return toy;
        }
        return null;
    }
}
