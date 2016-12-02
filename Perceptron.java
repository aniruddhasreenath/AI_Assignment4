import org.omg.CORBA.IMP_LIMIT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by sreenath on 28/11/2016.
 */
public class Perceptron {

    public Double output;

    public ArrayList<Double> input;

    public final int SIZE_OF_INPUT = 784;

    public Double[] weights;

    public Integer representingDigit;


    public Perceptron(ArrayList<Double> in, int digit){
        input = new ArrayList<Double>(in);
        weights = new Double[SIZE_OF_INPUT];
        representingDigit = digit;

        output = 0.0;

        //initialise all the weights to 0, this implies that the w_c associated with these weights is also 0
        Arrays.fill(weights, 0);

    }
    public Perceptron(int digit){
        input = new ArrayList<Double>();
        weights = new Double[SIZE_OF_INPUT];
        representingDigit = digit;

        output = 0.0;

        //initialise all the weights to 0, this implies that the w_c associated with these weights is also 0
        for(int i = 0; i < weights.length; i++){

            weights[i] = 0.0;
        }
    }

    public void passInput(ArrayList<Double> in){
        input = in;

    }

    public void calculateOutput(){

        if(input.isEmpty()){
            System.out.println("Inputs have not been passed");
        }
        else{
            for(int i = 0; i < input.size(); i++){

                output = output + (input.get(i) * weights[i]);

            }
        }



    }

    public void updateWeights(boolean increase){

        Double[] scaledInputVals = alphaDotInput();

        if(increase){

            //increase
            for(int i = 0; i < weights.length; i++){

                weights[i] = weights[i] +  scaledInputVals[i];

            }

        }
        else{

            //decrease
            for(int i = 0; i < weights.length; i++){

                weights[i] = weights[i] -  scaledInputVals[i];

            }
        }



    }

    private Double[] alphaDotInput(){

        ArrayList<Double> newInput = new ArrayList<Double>();

        for (int i = 0; i < input.size(); i++){
            newInput.add(input.get(i)*NeuralNetwork.alpha);
        }

        Double[] in = new Double[newInput.size()];
        in = newInput.toArray(in);

        return in;


    }

    public void updateBias(){

        //TODO BIAS IS NOT BEING CONSIDERED

    }

    public String toString(){
        String out = "";

        out = "\n"+"Digit: " + representingDigit.toString() + " Output: " + output + "\n";
        out = out + "Weights: " + "\n";
        for(int i = 0; i < weights.length; i++){
            out = out + weights[0] + " ";
        }
        out = out + "\n";
        out = out + "Inputs: " + "\n";
        for(int i = 0; i < input.size(); i++){
            out = out + input.get(i) + " ";
        }
        out = out + "\n";

        return out;

    }
}
