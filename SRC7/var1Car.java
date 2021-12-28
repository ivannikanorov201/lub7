import java.util.Scanner;
import java.io.*;

public class var1Car {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("count");
        int count = sc.nextInt();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileOutputStream fos2 = null;
        ObjectOutputStream oos2 = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileInputStream fis2 = null;
        ObjectInputStream ois2 = null;
        try {
            File f = new File("C:\\java\\CarIn.txt");
            f.createNewFile();
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            sc.nextLine();
            for (int i = 0; i < count; i++) {
                Car car = new Car();
                System.out.println("model = ");
                car.model = sc.nextLine();
                System.out.println("reg_nom = ");
                car.reg_nom = sc.nextLine();
                System.out.println("year = ");
                car.year = sc.nextInt();
                System.out.println("prob = ");
                car.prob = sc.nextInt();
                System.out.println("price = ");
                car.price = sc.nextInt();
                sc.nextLine();
                oos.writeObject(car);
            }
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Car carWrite = null;

            File f2 = new File("C:\\java\\CarOut.txt");
            fos2 = new FileOutputStream(f2);
            oos2 = new ObjectOutputStream(fos2);
            int k = 0;
            for (int i = 0; i < count; i++) {
                carWrite = (Car)ois.readObject();
                if(carWrite.year>2009){
                    oos2.writeObject(carWrite);
                    System.out.println(carWrite);
                    k++;
                }
            }
            fis2 = new FileInputStream(f2);
            ois2 = new ObjectInputStream(fis2);
            Car cars = null;
            if(k>0){
                for (int i = 0; i < k; i++) {
                    cars = (Car) ois2.readObject();
                    System.out.println(cars);
                }
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            oos.flush();
            oos.close();
            oos2.flush();
            oos2.close();
            fos.close();
            ois.close();
            fos2.close();
            fis.close();
            ois2.close();
            fis2.close();
        }
    }
}
