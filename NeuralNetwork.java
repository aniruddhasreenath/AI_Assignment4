import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * Created by sreenath on 30/11/2016.
 */
public class NeuralNetwork {

    public static double alpha;

    public final int MAX_EPOCH = 100;

    public  ArrayList<Perceptron> networks;

    public ArrayList<ArrayList<Double>> inputs;

    public ArrayList<ArrayList<Double>> testInputs;

    public ArrayList<Image> trainingImages;

    public ArrayList<Image> testImages;

    public ArrayList<Integer> results;

    public NeuralNetwork(){

        alpha = 1;
        networks = new ArrayList<Perceptron>();
        inputs = new ArrayList<ArrayList<Double>>();
        testInputs = new ArrayList<ArrayList<Double>>();
        trainingImages = new ArrayList<Image>(Setup.trainingData);
        testImages = new ArrayList<Image>(Setup.testData);
        results = new ArrayList<Integer>();

        for (int i = 0; i < 10; i ++){
            Perceptron newPerc = new Perceptron(i);
            networks.add(newPerc);
        }
        getInput();
        learn();
        test();
    }

    public void getInput(){

        for(int i = 0; i < Setup.trainingData.size(); i++){

            inputs.add(Setup.trainingData.get(i).inputFormat);

        }

        for(int i = 0; i < Setup.testData.size(); i++){

            testInputs.add(Setup.testData.get(i).inputFormat);

        }

    }

    public void decayAlpha(double t){

        alpha =  (500/(500 + t));

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

                int predictedOutput = selectMaxOutput(outputs);

                //if it predicts incorrectly then modify weights
                if(predictedOutput != trainingImages.get(i).tureLabel){

                    //increase weights of perceptron that predicted incorrectly
                    networks.get(predictedOutput).updateWeights(false);
                    //System.out.println("Selected output: "+selectMaxOutput(outputs) + " true output: " + trainingImages.get(i).tureLabel + " EPOCH: " + epoch);


                    //reduce the weights of what it should have predicted
                    networks.get(trainingImages.get(i).tureLabel).updateWeights(true);
                }
                else{
                    //System.out.println("Selected output: "+selectMaxOutput(outputs) + " true output: " + trainingImages.get(i).tureLabel + " EPOCH: " + epoch);


                    results.add(predictedOutput);

                }

            }

            //decayAlpha(epoch);


        }


        //testPrint();
    }

    public void test(){

        for(int i = 0; i < testInputs.size(); i++){

            ArrayList<Double> outputs = new ArrayList<Double>();

            for(int j = 0; j < networks.size(); j ++){

                //stores all the outputs of the 10 perceptrons
                networks.get(j).passInput(testInputs.get(i));
                networks.get(j).calculateOutput();

                outputs.add(networks.get(j).output);

            }

            int predictedOutput = selectMaxOutput(outputs);

            System.out.println("Predicted: " + predictedOutput + " Expected: " + Setup.testlabels.get(i));


        }

        testPrint();



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

            //System.out.print(" " + outs.get(i) + " ");
            if(max == outs.get(i)){
                perc = i;
            }
        }
       // System.out.println();

        return perc;

    }

    public void testPrint(){

        for(int i = 0; i < networks.size(); i++){

            System.out.println(networks.get(i));

        }


       //System.out.println("Accuracy: " + results.size());


        /*for(int i = 0; i < trainingImages.size(); i++){

            System.out.println(trainingImages.get(i).tureLabel);

        }*/

    }

}
