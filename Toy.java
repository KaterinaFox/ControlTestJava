public class Toy {
    // id игрушки,
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // текстовое название
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // частота выпадения игрушки (вес в % от 100)
    private int dropFrequency;
    public int getDropFrequency() {
        return dropFrequency;
    }
    public void setDropFrequency(int dropFrequency) {
        this.dropFrequency = dropFrequency;
    }

    public Toy(int id, String name, int dropFrequency) {
        this.id = id;
        this.name = name;
        this.dropFrequency = dropFrequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Toy))
            return false;
        Toy toy = (Toy) obj;
        return toy.id == this.id && toy.name.equals(this.name);
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("id:")
            .append(String.valueOf(id))
            .append("\tname:")
            .append(name);
        return buff.toString();
    }
}
