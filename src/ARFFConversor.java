import java.lang.*;
import java.util.*;
import java.io.*;

/**
* Clase-programa que lee los datos de un fichero CSV y los añade al fichero WEKA .arff
*/
public class ARFFConversor {

    public void fromCSVtoARFF(String csvFile, String arffFile, boolean append) {
        BufferedReader csvReader = null;
        BufferedWriter arffWriter = null;
        try {
            csvReader = new BufferedReader(new FileReader(csvFile));
            arffWriter = new BufferedWriter(new FileWriter(arffFile, append));
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                    arffWriter.write(values[i]);
                    if (i != values.length - 1)
                        arffWriter.write(", ");
                }
                arffWriter.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvReader != null)
                    csvReader.close();
                if (arffWriter != null)
                    arffWriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ARFFConversor conversor = new ARFFConversor();
        conversor.fromCSVtoARFF(args[0], args[1], true);
    }
}
