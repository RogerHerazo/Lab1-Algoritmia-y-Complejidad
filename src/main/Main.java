/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.math.BigInteger;
import java.util.Random;
import Vista.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roger H
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static char[] LETRAS = new char[26];
    static ArrayList<String[]> DATA;
    static HashMap<String, String> KEYS;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Ventana v = new Ventana();
        v.setLocationRelativeTo(null);
        v.setVisible(true);

        LETRAS[0] = 'a';
        LETRAS[1] = 'b';
        LETRAS[2] = 'c';
        LETRAS[3] = 'd';
        LETRAS[4] = 'e';
        LETRAS[5] = 'f';
        LETRAS[6] = 'g';
        LETRAS[7] = 'h';
        LETRAS[8] = 'i';
        LETRAS[9] = 'j';
        LETRAS[10] = 'k';
        LETRAS[11] = 'l';
        LETRAS[12] = 'm';
        LETRAS[13] = 'n';
        LETRAS[14] = 'o';
        LETRAS[15] = 'p';
        LETRAS[16] = 'q';
        LETRAS[17] = 'r';
        LETRAS[18] = 's';
        LETRAS[19] = 't';
        LETRAS[20] = 'u';
        LETRAS[21] = 'v';
        LETRAS[22] = 'w';
        LETRAS[23] = 'x';
        LETRAS[24] = 'y';
        LETRAS[25] = 'z';

    }
    public static void initArray() {
        DATA = new ArrayList();
        KEYS  = new HashMap<>();
    }

    public static ArrayList<String[]> getData() {
        return DATA;
    }

    public static String generarRegistro(int nc, int lc) {
        String[] data = new String[nc + 1];
        String registro = randomKey(lc);
        data[0] = registro;
        for (int i = 0; i < nc; i++) {
            if (i % 2 == 0) {
                String number = randomBigInteger(lc);
                registro = registro + ";" + number;
                data[i + 1] = number;

            } else {
                String str = randomString(lc);
                registro = registro + ";" + str;
                data[i + 1] = str;

            }
        }
        DATA.add(data);
        return registro;
    }

    private static String randomKey(int lenght_of_digits) {
        Random rnd = new Random();
        BigInteger max_value = (new BigInteger("10").pow(lenght_of_digits)).subtract(BigInteger.ONE);
        BigInteger min_value = new BigInteger("10").pow(lenght_of_digits - 1);
        BigInteger n = max_value;
        int sw = 0;
        String str = "";
        while (sw == 0) {
            str = "";
            BigInteger r;
            do {
                r = new BigInteger(n.bitLength(), rnd);
            } while (r.compareTo(n) >= 0);
            str = r + "";
            if (str.length() < lenght_of_digits) {
                str = r.add(min_value) + "";
            }
            str = str.substring(0, lenght_of_digits);

            if (KEYS.containsKey(str)) {
                sw = 0;
            } else {
                sw = 1;
            }
        }

        //System.out.println(str);
        KEYS.put(str, str);
        return str;
    }

    private static String randomBigInteger(int lenght_of_digits) {
        Random rnd = new Random();
        BigInteger max_value = (new BigInteger("10").pow(lenght_of_digits)).subtract(BigInteger.ONE);
        BigInteger min_value = new BigInteger("10").pow(lenght_of_digits - 1);
        BigInteger n = max_value;
        String str = "";
        BigInteger r;
        do {
            r = new BigInteger(n.bitLength(), rnd);
        } while (r.compareTo(n) >= 0);
        str = r + "";
        if (str.length() < lenght_of_digits) {
            str = r.add(min_value) + "";
        }
        str = str.substring(0, lenght_of_digits);
        return str;
    }

    private static String randomString(int lenght_of_string) {
        String lletras = randomBigInteger(lenght_of_string);
        String str = "";
        for (int i = 0; i < lletras.length(); i++) {
            str = str + LETRAS[Integer.parseInt(lletras.charAt(i) + "")];
        }
        return str;
    }

    public static void sort(int col) {
        Collections.sort(DATA, (String[] o1, String[] o2) -> o1[col].compareTo(o2[col]));
//        for (int i = 0; i < DATA.size(); i++) {
//            String[] str = DATA.get(i);
//            for (int j = 0; j < str.length; j++) {
//                System.out.print(str[j] + "--");
//            }
//            System.out.println("");
//        }
    }

    public static DefaultTableModel updateModel(DefaultTableModel tbl_model) {
//        if (tbl_model.getColumnCount() == DATA.get(0).length && tbl_model.getRowCount() == DATA.size()) {
//            for (int i = 0; i < DATA.size(); i++) {
//                String[] str2 = DATA.get(i);
//                for (int j = 0; j < str2.length; j++) {
//                    tbl_model.setValueAt(str2[j], i, j);
//                }
//            }
//        } else {
            DefaultTableModel tbl_model2 =  new DefaultTableModel();

            for (int i = 0; i < DATA.get(0).length; i++) {
                tbl_model2.addColumn(i);
            }
            for (int i = 0; i < DATA.size(); i++) {
                String[] str2 = DATA.get(i);
                tbl_model2.addRow(str2);
            }
//        }
        return tbl_model2;
    }

    public static String promedio(String columna) {
        int col = Integer.parseInt(columna);
        BigInteger resultado = BigInteger.ONE;
        if (col % 2 == 1) {
            for (int i = 0; i < DATA.size(); i++) {
                resultado = resultado.add(new BigInteger(DATA.get(i)[col]));
            }
        }else{
            return "Columna no valida";
        }
        return "El promedio de la columna es: "+resultado.divide(new BigInteger(DATA.size()+""));
    }

    public static String maxmin(String columna) {
        int col = Integer.parseInt(columna);        
        if (col % 2 == 1) {
            sort(col);
            BigInteger max = new BigInteger(DATA.get(0)[col]);
            BigInteger min = new BigInteger(DATA.get(DATA.size()-1)[col]);
            sort(0);
            return "Maximo : "+ max+ "\n"+"Minimo : "+min;
        }else{
             return "Columna no valida";
        }
        
    }

    public static DefaultTableModel busqueda(String columna, String valor, DefaultTableModel tbl_model) {
        int col = Integer.parseInt(columna);
        System.out.println(tbl_model.getColumnCount());
        System.out.println(tbl_model.getRowCount());
        int i = 0;
        int j = 0;
        while(tbl_model.getRowCount() > 1){
            if (!DATA.get(i)[col].equalsIgnoreCase(valor)) {
                tbl_model.removeRow(j);               
            }else{
                j++;
            }
            i++;
        }
        return tbl_model;
    }

    
}
