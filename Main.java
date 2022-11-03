import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static int checkINT(String s) {

        try {
            Integer.parseInt(s);
            return 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    } // function to check if values are an integer

    public static boolean checkIDENTIFIER(String str)
    {

        if (!((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') || (str.charAt(0)>= 'A' && str.charAt(0) <= 'Z')))
            return false; // checking to see if the first character of the possible variables is valid

        for (int i = 1; i < str.length(); i++)
        {
            if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= '0' && str.charAt(i) <= '9')))
                return false; // parse the string to see if it respects the variable syntax (no special characters)
        }

        return true; // if the input string is a valid variable
    }

    public static boolean checkRESWORD(String s) {
        return s.equals("if") || s.equals("else") || s.equals("while") || s.equals("for") || s.equals("void") || s.equals("class") || s.equals("return") || s.equals("array") || s.equals("bool") || s.equals("char") || s.equals("int") || s.equals("string") || s.equals("do") || s.equals("cin>>") || s.equals("cout<<") || s.equals("main");
        // checks to see if the input word is a reserved word
    }

    public static boolean checkSEPARATOR(String s) {
        return s.equals(";") || s.equals(",") || s.equals("(") || s.equals(")") || s.equals("{") || s.equals("}") || s.equals("[") || s.equals("]");
        // checks to see if the input is a separator
    }

    public static boolean checkOPERATOR(String s) {
        return s.equals(":==") || s.equals(":=") || s.equals(":+") || s.equals(":-") || s.equals(":*") || s.equals(":/") || s.equals(":<") || s.equals(":=<") || s.equals(":>") || s.equals(":=>") || s.equals(":!=") || s.equals(":&") || s.equals("|") || s.equals(":%");
        // checks to see if input is an operator
    }

    public static void main(String[] args) {


        Map<String, Integer> symbolMap = new HashMap<String, Integer>(); //only identifiers and constants
        int n = 0;

        Map<String, List<Integer>> PIFMap = new HashMap<String, List<Integer>>();

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("identifier", 0);
        map.put("constant", 1);
        map.put("if", 2);
        map.put("else", 3);
        map.put("while", 4);
        map.put("for", 5);
        map.put("void", 6);
        map.put("class", 7);
        map.put("return", 8);
        map.put("do", 9);
        map.put("array", 10);
        map.put("bool", 11);
        map.put("char", 12);
        map.put("int", 13);
        map.put("string", 14);
        map.put(";", 15);
        map.put(",", 16);
        map.put("(", 17);
        map.put(")", 18);
        map.put("{", 19);
        map.put("}", 20);
        map.put("]", 21);
        map.put("[", 22);
        map.put(":+", 23);
        map.put(":-", 24);
        map.put(":*", 25);
        map.put(":/", 26);
        map.put(":<", 27);
        map.put(":=<", 28);
        map.put(":>", 29);
        map.put(":=>", 30);
        map.put(":==", 31);
        map.put(":=", 32);
        map.put(":!=", 33);
        map.put(":&", 34);
        map.put(":|", 35);
        map.put(":%", 36);
        map.put("main", 37);
        map.put("cin>>", 38);
        map.put("cout<<", 39);



        try {
            File file = new File("C:\\Users\\toshiba\\Desktop\\Scoala\\AN 3\\LFTC\\laboratory2\\code.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {

                String[] splitted = line.split(" ");

                for (String s : splitted) {
                    if (checkINT(s) == 1) {
                        if (!(map.containsKey(s))) {
                            if(!(symbolMap.containsKey(s))) {
                                symbolMap.put(s, n);
                                n++;
                            }
                        }
                    } else if (checkIDENTIFIER(s) && checkRESWORD(s)==false) {
                        if (!(map.containsKey(s))) {
                            if (!(symbolMap.containsKey(s))) {
                                symbolMap.put(s, n);
                                n++;
                            }
                        }
                    }
                    if (checkRESWORD(s) || checkOPERATOR(s) || checkSEPARATOR(s) || checkIDENTIFIER(s) || checkSEPARATOR(s)) {
                        List<Integer> maps = new ArrayList<>();
                        maps.add(symbolMap.get(s) == null ? -1 : symbolMap.get(s));
                        if((checkIDENTIFIER(s)) && checkRESWORD(s)==false){
                            maps.add(0);
                        } else if ((checkINT(s) == 1)){
                            maps.add(1);
                        } else {
                            maps.add(map.get(s));
                        }
                        PIFMap.put(s, maps);
                    } else {
                        PIFMap.put(s, Collections.singletonList(0));
                    }
                }
            }
            fr.close();
            System.out.println("Symbol Table: ");
            for(Map.Entry<String, Integer> entry : symbolMap.entrySet())
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            System.out.println(sb.toString());

            System.out.println("Program Internal Form: ");
            for(Map.Entry<String, List<Integer>> entry : PIFMap.entrySet())
                System.out.println("Key = " + entry.getKey() +
                        ", [SymbolTable value, TokenTable value] = " + entry.getValue());
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

	// write your code here
    }
}
