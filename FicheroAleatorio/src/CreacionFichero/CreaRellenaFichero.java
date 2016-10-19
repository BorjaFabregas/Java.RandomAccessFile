package CreacionFichero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Borja Fabregas
 */
public class CreaRellenaFichero {

    public static void main(String[] args) {

        short numEmple;
        String apellidos = "";
        char continuar;
        byte deptNo;
        float salario;

        final byte LONGITUD = 47;
        long pos = 0;

        File f = new File("REmple.dat");
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();

        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(pos);
            do {

                //Numero Empleado
                System.out.print("Introduce Nº de Empleado: ");
                numEmple = sc.nextShort();
                pos = numEmple - 1;
                raf.seek(pos*LONGITUD);
                

                //Nombre y apellidos
                sc.nextLine();
                System.out.print("Introduzca nombre y apellidos: ");
                apellidos = sc.nextLine();
                sb = new StringBuffer(apellidos);
                sb.setLength(20);
                apellidos = sb.toString();
                

                //Departamento
                System.out.print("Introduzca Departamento: ");
                deptNo = sc.nextByte();
                

                //Salario
                System.out.print("Introduzca Salario: ");
                salario = sc.nextFloat();
                
                
                raf.writeShort(numEmple);
                raf.writeChars(apellidos);
                raf.writeByte(deptNo);
                raf.writeFloat(salario);

                sc.nextLine();
                System.out.print("¿Continuar?");
                continuar = sc.nextLine().charAt(0);
            } while (continuar != 'N' && continuar != 'n');
            raf.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("No encontrado");
        } catch (IOException e) {
            System.out.println("Error I/O");
        }

    }

}
