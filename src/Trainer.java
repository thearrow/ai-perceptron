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

        double error = 99999999.0;
        int i = 0, iterations = 0;
        while (error > 0.01) {
            if (i == data.size()) {
                i = 0;
            }
            Example e = data.get(i);
            n.train(e.getAttributes(), e.getTarget(), learnrate);
            error = this.test(n, false);
            System.out.println(error);
            i++;
            iterations++;
        }

        System.out.println("Weights Learned: " + n.getWeights() + " In " + iterations + " iterations.");
        n.save();
    }

    public double test(Neuron n, boolean output) {
        int correct = 0, total = 0;

        for (Example e : data) {
            int expected = (int) Math.round(e.getTarget());
            int actual = (int) Math.round(n.activate(e.getAttributes()));
            if (output)
                System.out.println("Expected:" + expected + " Actual:" + actual);
            if (expected == actual) {
                correct++;
            }
            total++;
        }

        if (output)
            System.out.println("Got " + correct + "/" + total + " examples correct. (" + (double) correct / (double) total * 100 + "%)");
        return 1.0 - ((double) correct / (double) total);
    }

}
