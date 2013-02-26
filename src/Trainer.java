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

    public void train(Neuron n, double learnrate) {

        Double err = 99999.0;
        int i = 0;
        while (err > 0.0001) {
            if (i == data.size()) {
                i = 0;
            }
            Example e = data.get(i);
            err = Math.abs(n.train(e.getAttributes(), e.getTarget(), learnrate));
            System.out.println(err);
            i++;
        }

        System.out.println("Weights Learned: " + n.getWeights());
        n.save();
    }

    public void test(Neuron n) {
        int correct = 0, total = 0;

        for (Example e : data) {
            int expected = (int) Math.round(e.getTarget());
            int actual = (int) Math.round(n.activate(e.getAttributes()));
            System.out.println("Expected:" + expected + " Actual:" + actual);
            if (expected == actual) {
                correct++;
            }
            total++;
        }

        System.out.println("Got " + correct + "/" + total + " examples correct. (" + (double) correct / (double) total + "%)");

    }

}
