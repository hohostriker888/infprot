import java.math.BigInteger;
import java.util.Scanner;


public class ClientSRP {
    public static void main(String[] args){
        //int q = 1019;
        BigInteger N =  BigInteger.valueOf(2039);
        BigInteger k = BigInteger.valueOf(3);
        Scanner in = new Scanner(System.in);
        ServerSRP serverSRP = new ServerSRP();
        serverSRP.k = BigInteger.valueOf(3);
        serverSRP.N = BigInteger.valueOf(2039);
        serverSRP.I = "useadmin";
        //
        serverSRP.p = "p66w0r3";
        System.out.println("Введите случайную строку");
        serverSRP.s = in.nextLine();
        serverSRP.x = BigInteger.valueOf(hashFunc(serverSRP.p, serverSRP.s));
        System.out.println("Сервер вычислил x = " + serverSRP.x);
        serverSRP.g = calculate_g(serverSRP.x, N);
        BigInteger g = serverSRP.g;
        System.out.println("Сервер вычислил g = " + serverSRP.g);
        serverSRP.v = serverSRP.g.modPow(serverSRP.x, serverSRP.N);
        System.out.println("Сервер хранит пару I, s, v: " + serverSRP.I + ", " + serverSRP.s + ", "  + serverSRP.v);
        double a1 = Math.random() * 10000 ;
        BigInteger a = BigInteger.valueOf((long)a1);
        System.out.println("a = " + a);
        BigInteger A = g.modPow(a, N);
        System.out.println("Клиент вычислил A = " + A);
        System.out.println("Введите логин");
        String I = in.nextLine();
        serverSRP.I1 = I;
        serverSRP.A = A;
        System.out.println("Сервер принял I, A");
        if (serverSRP.A == BigInteger.valueOf(0)) {
            System.out.println("A = " + serverSRP.A);
            System.exit(-1);
        }
        serverSRP.b1 = (long) Math.random() * 10000;
        serverSRP.b = BigInteger.valueOf(serverSRP.b1);
        serverSRP.B = serverSRP.k.multiply(serverSRP.v).add(serverSRP.g.modPow(serverSRP.b, serverSRP.N));
        System.out.println("Сервер вычислил B = " + serverSRP.B);
        BigInteger B = serverSRP.B;
        String s = serverSRP.s;
        System.out.println("Сервер отослал клиенту s, B");
        BigInteger u = BigInteger.valueOf(hashFunc(String.valueOf(A), String.valueOf(B)));
        serverSRP.u = BigInteger.valueOf(hashFunc(String.valueOf(serverSRP.A), String.valueOf(serverSRP.B)));
        System.out.println("Клиент и сервер вычислили u = " + u + " ; " + serverSRP.u);
        if (u == BigInteger.valueOf(0) || serverSRP.u == BigInteger.valueOf(0)) {
            System.out.println("u = " + u);
            System.exit(-1);
        }

        System.out.println("Введите пароль");
        String p = in.nextLine();
        serverSRP.p1 = p;
        BigInteger x = BigInteger.valueOf(hashFunc(serverSRP.s, p));
        BigInteger S = (B.subtract((k.multiply(g.modPow(x, N))))).modPow(a.add(u.multiply(x)), N);
        System.out.println("Клиент вычислил ключ сессии S = " + S);
        BigInteger K = BigInteger.valueOf(hashFunc(String.valueOf(S)));
        System.out.println("Клиент вычислил ключ шифрования K = " + K);

        serverSRP.S = A.multiply(serverSRP.v.pow(serverSRP.u.intValue())).pow(serverSRP.b.intValue());
        System.out.println("Сервер вычислил ключ сесcии S = " + serverSRP.S);
        serverSRP.K = BigInteger.valueOf(hashFunc(String.valueOf(serverSRP.S)));
        System.out.println("Сервер вычислил ключ шифрования K = " + serverSRP.K);
        BigInteger M = calculateM(N, g, I, s, A, B, K);
        System.out.println("Клиент вычислил свой M = " + M);
        serverSRP.M = calculateM(serverSRP.N, serverSRP.g, serverSRP.I, serverSRP.s, serverSRP.A, serverSRP.B, serverSRP.K);
        System.out.println("Сервер вычислил свой M = " + serverSRP.M);
        BigInteger R;

        if(M.equals(serverSRP.M)) {
            serverSRP.R = BigInteger.valueOf(hashFunc(String.valueOf(serverSRP.A), String.valueOf(serverSRP.M), String.valueOf(serverSRP.K)));
            R = serverSRP.R;
            System.out.println("Сервер отправил клиенту R = " + R);
            BigInteger R1 = BigInteger.valueOf(hashFunc(String.valueOf(A), String.valueOf(M), String.valueOf(K)));
            if (R.equals(R1)) System.out.println("Ok.");
        }



    }

    public static long hashFunc(String... args) {
        long res = 0;
        for (String arg : args) {
            for (char string : arg.toCharArray()) {
                res += string;
            }
        }
        return res;
    }

    public static BigInteger calculate_g(BigInteger x, BigInteger N){
        BigInteger g = BigInteger.valueOf(1);
        while (g.modPow(x, N) != BigInteger.valueOf(0)){
            g = g.add(BigInteger.valueOf(1));
            System.out.println(g);
            if (g.modPow(x, N).equals(x)) break;
        }
        return g;
    }

    public static BigInteger calculateM(BigInteger N, BigInteger g, String I, String s, BigInteger A, BigInteger B, BigInteger K){
        BigInteger M = BigInteger.valueOf(hashFunc(String.valueOf(BigInteger.valueOf(hashFunc(String.valueOf(N))).xor(BigInteger.valueOf(hashFunc(String.valueOf(g))))), I, s, String.valueOf(A), String.valueOf(B), String.valueOf(K)));
        return M;
    }



}

class ServerSRP{
    BigInteger k;
    BigInteger N;
    BigInteger x;
    String I;
    String I1;
    String p;
    String p1;
    String s;
    BigInteger A;
    BigInteger g;
    long b1;
    BigInteger v;
    BigInteger b;
    BigInteger B;
    BigInteger u;
    BigInteger S;
    BigInteger K;
    BigInteger M;
    BigInteger R;

}
