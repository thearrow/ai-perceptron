import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Neuron implements Serializable {

    private ArrayList<Double> weights;
    private boolean weightsInitialized = false;
    private double biasTerm = 1.0;

    public Neuron() {
        weights = new ArrayList<Double>();
    }

    public Neuron(String fileName) {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
            Neuron n = (Neuron) reader.readObject();
            this.weights = n.getWeights();

            reader.close();
        } catch (IOException e) {
            System.out.println("error: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("error: " + e);
        }
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public Double getWeightSum() {
        double sum = 0.0;
        for (Double w : this.weights) {
            sum += w;
        }
        return sum;
    }

    public double activate(ArrayList<Double> inputs) {
        if (!weightsInitialized) {
            //initialize weights (including extra bias term weight)
            for (int i = 0; i <= inputs.size(); i++) {
                this.weights.add(0.0);
            }
            weightsInitialized = true;
        }
        double input = 0.0, output;

        //calculate sum of weighted inputs
        for (int i = 0; i < inputs.size(); i++) {
            input += inputs.get(i) * weights.get(i);
        }
        //plus bias * last weight
        input += biasTerm * weights.get(inputs.size());

        //calculate logistic function
        output = 1.0 / (1.0 + Math.exp(-input));

        return output;
    }

    public Double train(ArrayList<Double> inputs, Double target, Double rate) {
        double out = this.activate(inputs);
        double error = target - out;

        //update weights
        for (int i = 0; i < inputs.size(); i++) {
            Double w = weights.get(i);
            w += rate * error * inputs.get(i);
            weights.set(i, w);
        }
        //plus bias term
        double biasWeight = weights.get(inputs.size()) + (rate * out * error * biasTerm);
        weights.set(inputs.size(), biasWeight);

        return error;
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