import java.io.*;
import java.util.ArrayList;

public class Neuron implements Serializable {

    private ArrayList<Double> inputs, weights;

    public Neuron() {
        inputs = new ArrayList<Double>();
        weights = new ArrayList<Double>();

        //add bias term
        inputs.add(-1.0);
        weights.add(0.5);
    }

    public Neuron(String fileName) {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
            Neuron n = new Neuron();
            n = (Neuron) reader.readObject();
            this.inputs = n.getInputs();
            this.weights = n.getWeights();

            reader.close();
        } catch (IOException e) {
            System.out.println("error: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("error: " + e);
        }
    }

    public ArrayList<Double> getInputs() {
        return inputs;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public double activate(double[] inputs) {
        this.setInputs(inputs);
        double input = 0.0, output = 0.0;

        //calculate sum of weighted inputs
        for (int i = 0; i < this.inputs.size(); i++) {
            input += this.inputs.get(i) * weights.get(i);
        }

        //calculate logistic function
        output = 1.0 / (1.0 + Math.exp(-input));
        return output;
    }

    public void setInputs(double[] inputs) {
        for (double in : inputs) {
            this.inputs.add(in);
            //initialize weight for input
            this.weights.add(0.5);
        }
    }

    public void save() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("neuron.dat"));
            writer.writeObject(this);
            writer.close();
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

}