import  java.util.Scanner

public class Car implements Serializable{
    String model, reg_nom;
    int year, prob, price;


    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", reg_nom ' " + reg_nom + '\'' +
                ", year=" + year +
                ", prob=" + prob +
                ", price=" + price +
                '}';
    }
}

