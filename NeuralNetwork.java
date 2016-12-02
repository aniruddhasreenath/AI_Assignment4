import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * Created by sreenath on 30/11/2016.
 */
public class NeuralNetwork {

    public static double alpha;

    public final int MAX_EPOCH = 1000;

    public  ArrayList<Perceptron> networks;

    public ArrayList<ArrayList<Double>> inputs;

    public ArrayList<Image> trainingImages;

    public ArrayList<Integer> results;

    public NeuralNetwork(){

        alpha = 1;
        networks = new ArrayList<Perceptron>();
        inputs = new ArrayList<ArrayList<Double>>();
        trainingImages = new ArrayList<Image>(Setup.trainingData);
        results = new ArrayList<Integer>();

        for (int i = 0; i < 10; i ++){
            Perceptron newPerc = new Perceptron(i);
            networks.add(newPerc);
        }

        getInput();
        learn();
    }

    public void getInput(){

        for(int i = 0; i < Setup.trainingData.size(); i++){

            inputs.add(Setup.trainingData.get(i).inputFormat);

        }

    }

    public void decayAlpha(int t){

        alpha = (double) (1000/(1000 + t));

    }

    public void learn(){

        for (int epoch = 0; epoch < MAX_EPOCH; epoch++){
            for(int i = 0; i < inputs.size(); i++){
                ArrayList<Double> outputs = new ArrayList<Double>();
                for(int j = 0; j < networks.size(); j ++){

                    //stores all the outputs of the 10 perceptrons


                    networks.get(j).passInput(inputs.get(i));
                    networks.get(j).calculateOutput();

                    outputs.add(networks.get(j).output);

                }

                //if it predicts incorrectly then modify weights
                if(selectMaxOutput(outputs) != trainingImages.get(i).tureLabel){

                    //increase weights of perceptron that predicted incorrectly
                    networks.get(selectMaxOutput(outputs)).updateWeights(true);

                    //reduce the weights of what it should have predicted
                    networks.get(trainingImages.get(i).tureLabel).updateWeights(false);
                }
                else{

                    results.add(selectMaxOutput(outputs));
                    System.out.println(selectMaxOutput(outputs));

                }

            }

            decayAlpha(epoch);

            //testPrint();
        }



    }

    public int selectMaxOutput(ArrayList<Double> outs){

        double max = 0;
        int perc = 0;

        for(int i = 0; i < outs.size(); i++){

            if(max < outs.get(i)){
                max = outs.get(i);
            }
        }

        for(int i = 0; i < outs.size(); i++){

            if(max == outs.get(i)){
                perc = i;
            }
        }

        return perc;

    }

    public void testPrint(){

        for(int i = 0; i < networks.size(); i++){

            System.out.println(networks.get(i));

        }



        /*for(int i = 0; i < trainingImages.size(); i++){

            System.out.println(trainingImages.get(i).tureLabel);

        }*/

    }

}
