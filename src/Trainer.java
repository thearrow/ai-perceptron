import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trainer {

    ArrayList<Example> data;

    public Trainer(String filePath) {

        data = new ArrayList<Example>();

        //read in data from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            //skip first line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                Example e = new Example(line);
                data.add(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e);
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

    public void train(Neuron n) {

        //implement training

        n.save();
    }

    public void test(Neuron n) {

    }

}
