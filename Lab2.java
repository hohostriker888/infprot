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
		System.out.println ("�������1 ������ P = ");
		P = in.nextInt();
		writer.write("�������1 ������ ������ P, ������ " + P + "\n\r");
		System.out.println ("�������1 ������ A = ");
		A = in.nextInt();
		writer.write("\n"+"�������1 ������ ������ A, ������ " + A + "\n\r");
		
		x1 = (int) (Math.random()*10);
		x2 = (int) (Math.random()*10);
		
		int Y1 = (int) (Math.pow(A,x1)%P);
		int Y2 = (int) (Math.pow(A,x2)%P);
		
		writer.write("\n" + "�������1 �������� Y1, ������ " + Y1 + "\n\r");
		writer.write("\n" + "�������2 �������� Y2, ������ " + Y2 + "\n\r");
		writer.write("\n" + "�������� ���������� �������\n\r");
		int Key1 = (int ) (Math.pow(Y2,x1)%P);
		int Key2 = (int ) (Math.pow(Y1,x2)%P);
		
		
		if (Key1 == Key2) {
			writer.write("\n" + "�������1 �������� ���� k1, ������ " + Key1 + "\n\r");
			writer.write("\n" + "�������1 �������� ���� k2, ������ " + Key2 + "\n\r");
		}
		writer.flush();
		writer.close();
}
}
