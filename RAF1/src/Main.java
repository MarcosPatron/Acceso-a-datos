import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class Main {
    public static void main(String[] args) {

        try{
            RandomAccessFile raf = new RandomAccessFile("resources/numeros.dat", "rw");

            for(int i = 0; i < 30; i++){
                raf.writeInt((int) (Math.random()*101));
            }

            while(raf.getFilePointer() < raf.length()){

                int numero = raf.readInt();
                System.out.print(numero + ", ");
            }
            System.out.println();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            RandomAccessFile raf = new RandomAccessFile("resources/numeros.dat", "rw");

            while(raf.getFilePointer() < raf.length()){

                int numero = raf.readInt();
                System.out.print(numero + ", ");
            }
            System.out.println();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}