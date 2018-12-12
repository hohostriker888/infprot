package lab2;

import java.io.*;
import java.util.Scanner;

public class Lab2 {
	
	public static void main(String[] args) throws IOException {
		int A;
		int P;
		int x1, x2;
		
		Scanner in = new Scanner (System.in);
		File file = new File("log.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		System.out.println ("Абонент1 выбрал P = ");
		P = in.nextInt();
		writer.write("Абонент1 выбрал выбрал P, равное " + P + "\n\r");
		System.out.println ("Абонент1 выбрал A = ");
		A = in.nextInt();
		writer.write("\n"+"Абонент1 выбрал выбрал A, равное " + A + "\n\r");
		
		x1 = (int) (Math.random()*10);
		x2 = (int) (Math.random()*10);
		
		int Y1 = (int) (Math.pow(A,x1)%P);
		int Y2 = (int) (Math.pow(A,x2)%P);
		
		writer.write("\n" + "Абонент1 вычислил Y1, равный " + Y1 + "\n\r");
		writer.write("\n" + "Абонент2 вычислил Y2, равный " + Y2 + "\n\r");
		writer.write("\n" + "Абоненты обмнеялись ключами\n\r");
		int Key1 = (int ) (Math.pow(Y2,x1)%P);
		int Key2 = (int ) (Math.pow(Y1,x2)%P);
		
		
		if (Key1 == Key2) {
			writer.write("\n" + "Абонент1 вычислил ключ k1, равный " + Key1 + "\n\r");
			writer.write("\n" + "Абонент1 вычислил ключ k2, равный " + Key2 + "\n\r");
		}
		writer.flush();
		writer.close();
}
}
