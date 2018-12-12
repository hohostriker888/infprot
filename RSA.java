import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RSA {
    public static void main(String args[]) throws IOException {

        Abonent Bob = new Abonent();
        Abonent Alice = new Abonent();
        File file = new File("log.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        int p = 3;
        int q = 11;
        writer.write("Выбраны p и q, равные: " + p + " и " + q + "\n\r");

        int n = p * q;
        writer.write("\nВычислено N: " + n + "\n\r");
        int f = (p-1)*(q-1);
        writer.write("\nВычислена функия Эйлера, равная: " + f + "\n\r");

        int e = 7;
        writer.write("\nПолучен открытый ключ e: " + e + "\n\r");
        //int l = 0;
        //int k = 3;
        //System.out.println(k);
        int d = closeKey(e, f);
        writer.write("\nПолучен закрытый ключ d: " + d + "\n\r");
        int m = 11;
        Bob.M = m;
        writer.write("\nВведено исходное сообщение: " + Bob.M + "\n\r");
        Bob.C = (int) Math.pow(Bob.M, e) % n;
        writer.write("\nБоб зашифровал исходное сообщение: " + Bob.C + "\n\r");
        Alice.M = (int) Math.pow(Bob.C, d) % n;
        writer.write("\nАлиса расшифровала сообщение: " + Alice.M + "\n\r");
        writer.flush();
        writer.close();

    }

    private static int closeKey(int e, int f){
        int k = 1;
        while ((float)((k * f + 1) % e) != 0 ) {
            k++;
        }
        int d = (k * f + 1) / e;

        return d;
    }
}


class Abonent{
    int M;
    int C;
}


