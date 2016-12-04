import java.util.ArrayList;
/**
 * Created by sreenath on 28/11/2016.
 */
public class Perceptron {

    public Double output;

    public ArrayList<Double> input;

    public final int SIZE_OF_INPUT = 785;

    public Double[] weights;

    public Integer representingDigit;

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

        double outp = 0.0;
        if(input.isEmpty()){
            System.out.println("Inputs have not been passed");
        }
        else{
            for(int i = 0; i < input.size(); i++){

                outp = outp + (input.get(i) * weights[i]);

            }
        }

        output = outp;

    }

    public void updateWeights(boolean increase){

        Double[] scaledInputVals = alphaDotInput();

        if(increase){

            //increase
            for(int i = 0; i < weights.length; i++){

                weights[i] = weights[i] +  scaledInputVals[i];
                //System.out.println("Increasing Weight["+i+"] is "+weights[i]);

            }

        }
        else{

            //decrease
            for(int i = 0; i < weights.length; i++){

                weights[i] = weights[i] -  scaledInputVals[i];
                //System.out.println("Decreasing Weight["+i+"] is "+weights[i]);

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

    public String toString(){
        String out = "";

        out = "\n"+"Digit: " + representingDigit.toString() + "\n";
        out = out + "Weights: " + "\n";
        int count = 0;
        for(int i = 0; i < weights.length; i++){
            out = out + " " + weights[i] + " ";
            count = count + 1;

            if(count == 30){

                out = out + "\n";
                count = 0;
            }
        }
        out = out + "\n";
        /*out = out + "Inputs: " + "\n";
        for(int i = 0; i < input.size(); i++){
            out = out + input.get(i) + " ";
        }*/
        out = out + "\n";

        return out;

    }
}
