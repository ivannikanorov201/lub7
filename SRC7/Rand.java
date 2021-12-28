import java.io.*;
import java.util.Scanner;

public class Rand {
    private static final int MAX_STRING_LENGTH = 20;

    private static String ReadString(Scanner sc) throws IllegalArgumentException {
        String out = sc.nextLine();
        if (out.length() > MAX_STRING_LENGTH)
            throw new IllegalArgumentException();
        return out;
    }

    private static String ReadFromFileString(RandomAccessFile rf) throws IOException {
        String str = rf.readUTF();
        for (int i = 0; i < MAX_STRING_LENGTH - str.length(); i++)
            rf.readByte();
        return str;
    }
    private static void WriteToFileString(RandomAccessFile rf, String str) throws IOException, IllegalArgumentException {
        if (str.length() > MAX_STRING_LENGTH)
            throw new IllegalArgumentException();
        rf.seek(rf.length());
        rf.writeUTF(str);
        for (int i = 0; i < MAX_STRING_LENGTH - str.length(); i++)
            rf.writeByte(0);
    }

    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\java\\txt.txt");
        if (!file1.createNewFile()) {
            file1.delete();
            file1.createNewFile();
        }
        RandomAccessFile raf1 = new RandomAccessFile(file1, "rw");
        Scanner sc = new Scanner(System.in, "cp1251");
        System.out.println("Введите количество авто => ");
        int count = sc.nextInt();
        sc.nextLine();
        try {
            for (int i = 0; i < count; i++) {
                System.out.println("Введите информацию об авто" + (i + 1) + ": ");
                System.out.print("Модель => ");
                WriteToFileString(raf1, ReadString(sc));
                System.out.print("Регистрационный номер => ");
                WriteToFileString(raf1, ReadString(sc));
                System.out.print("Год выпуска => ");
                raf1.writeInt(sc.nextInt());
                sc.nextLine();
                System.out.print("Пробег => ");
                raf1.writeInt(sc.nextInt());
                sc.nextLine();
                System.out.print("Цена => ");
                raf1.writeInt(sc.nextInt());
                sc.nextLine();
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error");
            raf1.close();
            return;
        }
        raf1.close();

        raf1 = new RandomAccessFile(file1, "r");
        File file2 = new File("C:\\java\\txt2.txt");
        if (!file2.createNewFile()) {
            file2.delete();
            file2.createNewFile();
        }
        RandomAccessFile raf2 = new RandomAccessFile(file2, "rw");
        try {
            while (true) {
                String mod = ReadFromFileString(raf1);
                String reg_nom = ReadFromFileString(raf1);
                int year = raf1.readInt();
                int prob = raf1.readInt();
                int price = raf1.readInt();
                PrintStream ps = new PrintStream(System.out, true, "cp1251");
                ps.println(mod + " " + reg_nom + " " + year + " " + prob + " " + price);
                if (year > 2009) {
                    WriteToFileString(raf2, mod);
                    WriteToFileString(raf2, reg_nom);
                    raf2.writeInt(year);
                    raf2.writeInt(prob);
                    raf2.writeInt(price);
                }
            }
        } catch (EOFException e) {

        } catch (IllegalArgumentException e) {
            System.out.println("Error");
            raf2.close();
            raf1.close();
            return;
        }
        raf2.close();
        raf1.close();
    }
}
