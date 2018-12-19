import java.io.*;
import java.util.*;

public class Lab1 {

        private static List<Character> letter = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        public static void main(String[] args) throws Exception {
            ArrayList<Character> book = readfile("book.txt");
            ArrayList<Character> chapter = readfile("chapter.txt");
            ArrayList<Character> cryptchapter = encrypt(chapter);
            Map<Integer, Character> book_frq = frequency(book);
            Map<Integer, Character> chapter_frq = frequency(cryptchapter);
            for(int i = 0; i < cryptchapter.size();  i++) {
                for (int j = 0; j < 26; j++) {
                    if (cryptchapter.get(i) == book_frq.get(j)) {
                        cryptchapter.set(i, chapter_frq.get(j));
                        break;
                    }
                }
            }
            File file = new File("DecryptChapter1.txt");
            file.createNewFile();
            FileWriter writerbuffer = new FileWriter(file);
            //BufferedWriter wrbuf = new BufferedWriter(new FileWriter(new File("DecryptChapter1.txt")));
            for(int i = 0; i < cryptchapter.size(); i++) {
                writerbuffer.write(cryptchapter.get(i));
            }
            writerbuffer.close();


            Map<Integer, String> book_big = frequency_big(book);
            Map<Integer, String> chapter_big = frequency_big(cryptchapter);
            for(int i = 0; i < cryptchapter.size() - 1; i++){
                String s = String.valueOf(cryptchapter.get(i).toString() + cryptchapter.get(i + 1).toString());
                for(int j = 0; j < 20; j++){
                    if(s.equals(book_big.get(j))){
                        char[] mas = chapter_big.get(j).toCharArray();
                        cryptchapter.set(i, mas[0]);
                        cryptchapter.set(i + 1, mas[1]);
                        break;
                    }
                }
            }
            File file1 = new File("DecryptChapter2.txt");
            file1.createNewFile();
            FileWriter writerbuffer2 = new FileWriter(file1);
            //BufferedWriter wrbuf1 = new BufferedWriter(new FileWriter(new File("DecryptChapter2.txt")));
            for(int i = 0; i < cryptchapter.size(); i++) {
                writerbuffer2.write(cryptchapter.get(i));
            }
            writerbuffer2.close();
        }


        public static ArrayList<Character> readfile(String filename) throws FileNotFoundException, IOException {
            ArrayList<Character> file = new ArrayList<>();
            FileReader reader = new FileReader(filename);
            int c;
            while((c = reader.read()) != -1){
                file.add(Character.toLowerCase((char) c));
            }
            reader.close();
            return file;
        }

        public static Map<Integer, Character> frequency(ArrayList<Character> file){
            Map<Integer, Character> map = new HashMap();
            ArrayList<Integer> val = new ArrayList<>();
            for(int i = 0; i  <26; i++){
                val.add(Collections.frequency(file, letter.get(i)));
            }

            for(int i = 0; i < 26; i++){
                int temp = val.get(i);
                char c = letter.get(i);
                for(int j = 0; j<26; j++){
                    if(val.get(j) > temp){
                        temp = val.get(j);
                        c = letter.get(j);
                    }
                }

                map.put(i, c);
                val.set(letter.indexOf(c), -1);
            }
            return map;
        }



        public static Map<Integer, String> frequency_big(ArrayList<Character> file) {
            ArrayList<String> bigramms = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    String bigramma = String.valueOf(letter.get(i).toString() + letter.get(j).toString());
                    bigramms.add(bigramma);
                }
            }
            Map<Integer, String> big_map = new HashMap();
            int[] value = new int[676];
            for(int i = 0; i < 676; i++){
                value[i] = 0;
            }
            for(int i = 0; i < file.size() - 1; i++){
                String buff = String.valueOf(file.get(i).toString() + file.get(i + 1).toString());
                for(int j = 0; j < 676; j++){
                    if(buff.equals(bigramms.get(j))){
                        value[j]++;
                        break;
                    }
                }
            }
            for(int i = 0; i < 26; i++){
                int temp = value[i];
                String c = bigramms.get(i);
                int st = 0;
                for(int j = 0; j < 676; j++){
                    if(value[j]> temp){
                        temp = value[j];
                        c = bigramms.get(j);
                        st = j;
                    }
                }
                big_map.put(i, c);
                value[st] = 0;
            }
            return big_map;
        }


        public static ArrayList<Character> encrypt(ArrayList<Character> file) throws Exception {
            ArrayList<Character> newFile = (ArrayList<Character>) file.clone();
            for (int i = 0; i < file.size(); i++) {
                for (int j = 0; j < 26; j++) {
                    if (file.get(i) == letter.get(j)) {
                        newFile.set(i, letter.get(25 - j));
                    }
                }
            }
            File file2 = new File("CryptChapter.txt");
            file2.createNewFile();
            FileWriter writer = new FileWriter(file2);
            for(int i = 0; i < newFile.size();i++) {
                file.set(i, newFile.get(i));
                writer.write(newFile.get(i));
            }
            writer.close();
            return file;
        }

    }


