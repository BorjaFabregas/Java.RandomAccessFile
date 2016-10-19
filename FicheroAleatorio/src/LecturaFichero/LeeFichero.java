package LecturaFichero;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Borja Fabregas
 */
public class LeeFichero {

    public static void main(String[] args) {

        int pos = 0;
        final byte LONGITUD = 47;

        boolean finFichero = false;
        short numEmple;
        String apellidos;
        char[] cadApe = new char[20];
        byte deptNo;
        float salario;

        try {
            RandomAccessFile raf = new RandomAccessFile("REmple.dat", "rw");

            do {
                //Lee numEmple
                numEmple = raf.readShort();

                //Lee Apellidos
                for (int i = 0; i < 20; i++) {
                    cadApe[i] = raf.readChar();
                }
                apellidos = new String(cadApe);
                apellidos=apellidos.trim();
                
                /*if (apellidos.indexOf('\u0000') != -1) {
                    apellidos = apellidos.substring(0, apellidos.indexOf('\u0000'));
                }*/

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
                    pos = pos + LONGITUD;
                    raf.seek(pos);
                }

            } while (!finFichero);

        } catch (EOFException eoef) {
            finFichero = true;
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }catch(IOException ex){
                        ex.printStackTrace();
        }

    }

}
