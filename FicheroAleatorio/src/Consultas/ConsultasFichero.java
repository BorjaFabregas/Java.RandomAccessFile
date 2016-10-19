package Consultas;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Fabregas
 */
public class ConsultasFichero {

    public static void main(String[] args) {

        int pos = 0;
        final byte LONGITUD = 47;

        Scanner sc = new Scanner(System.in);

        boolean finFichero = false;
        short numEmple;
        String apellidos;
        char[] cadApe = new char[20];
        byte deptNo;
        float salario;
        char continuar;

        try {
            RandomAccessFile raf = new RandomAccessFile("REmple.dat", "rw");

            do {

                System.out.print("Introduce Nº Empleado: ");
                pos = sc.nextInt();

                if (pos > 0) {
                    raf.seek((pos-1) * LONGITUD);
                } else if (pos == 0) {
                    raf.seek(pos-1);
                }

                //Lee numEmple
                numEmple = raf.readShort();
                //Lee Apellidos
                for (int i = 0; i < 20; i++) {
                    cadApe[i] = raf.readChar();
                }
                apellidos = new String(cadApe);
                if (apellidos.indexOf('\u0000') != -1) {
                    apellidos = apellidos.substring(0, apellidos.indexOf('\u0000'));
                }
                //Lee deptNo
                deptNo = raf.readByte();
                //Lee Salario
                salario = raf.readFloat();

                if (numEmple != 0) {
                    System.out.println("NUMERO: " + numEmple
                            + "\tAPELLIDOS: " + apellidos
                            + "\tDEPARTAMENTO: " + deptNo
                            + "\tSALARIO: " + salario);
                } else {
                    System.out.println("No existe el Empleado Nº: " + pos);
                }

                sc.nextLine();
                System.out.print("¿Continuar?");
                continuar = sc.nextLine().charAt(0);

            } while (continuar != 'N' && continuar != 'n');

        } catch (EOFException eoef) {
            finFichero = true;
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
