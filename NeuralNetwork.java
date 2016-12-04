import java.util.ArrayList;

/**
 * Created by sreenath on 30/11/2016.
 */
public class NeuralNetwork {

    public static double alpha;

    public final int MAX_EPOCH = 1000;

    public  ArrayList<Perceptron> networks;

    public ArrayList<ArrayList<Double>> inputs;

    public ArrayList<ArrayList<Double>> testInputs;

    public ArrayList<Image> trainingImages;

    public ArrayList<Image> testImages;

    public ArrayList<Integer> results;

    int numberofCorrectMatches;

    public NeuralNetwork(){

        alpha = 1;
        networks = new ArrayList<Perceptron>();
        inputs = new ArrayList<ArrayList<Double>>();
        testInputs = new ArrayList<ArrayList<Double>>();
        trainingImages = new ArrayList<Image>(Setup.trainingData);
        testImages = new ArrayList<Image>(Setup.testData);
        results = new ArrayList<Integer>();
        numberofCorrectMatches = 0;

        for (int i = 0; i < 10; i ++){
            Perceptron newPerc = new Perceptron(i);
            networks.add(newPerc);
        }

        getInput();
        learn();
        test();
    }

    public void getInput(){

        for(int i = 0; i < trainingImages.size(); i++){

            inputs.add(Setup.trainingData.get(i).inputFormat);

        }

        for(int i = 0; i < Setup.testData.size(); i++){

            testInputs.add(Setup.testData.get(i).inputFormat);

        }

    }

    public void decayAlpha(double t){

        alpha =  (1000/(1000 + t));

    }

    public void learn(){
        System.out.println("Training...");
        System.out.println();
        int count = 0;
        for (int epoch = 0; epoch < MAX_EPOCH; epoch++){

            System.out.println("EPOCH: " + epoch + " Learning rate: " + alpha);
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


                    //decrease the weights of the class that was incorrectly classifed
                    networks.get(predictedOutput).updateWeights(false);


                    //increase the weights of the class that it should have predicted
                    networks.get(trainingImages.get(i).tureLabel).updateWeights(true);

                }
                else{
                    count = count +1;
                    results.add(predictedOutput);
                }
            }

            decayAlpha(epoch);

            //if the error is 0 stop learning
            if((5000 - count) == 0){
                System.out.println();
                System.out.println("Converged at EPOCH: " + epoch);
                break;
            }
            count = 0;
        }
    }


    public void test(){

        System.out.println();
        System.out.println("Testing...");

        for(int i = 0; i < testInputs.size(); i++){

            ArrayList<Double> outputs = new ArrayList<Double>();

            for(int j = 0; j < networks.size(); j ++){

                //stores all the outputs of the 10 perceptrons
                networks.get(j).passInput(testInputs.get(i));
                networks.get(j).calculateOutput();

                outputs.add(networks.get(j).output);

            }

            int predictedOutput = selectMaxOutput(outputs);

            if(predictedOutput == Setup.testlabels.get(i)){

                numberofCorrectMatches = numberofCorrectMatches + 1;

            }


        }

        System.out.println();
        testPrint();
    }

    public int selectMaxOutput(ArrayList<Double> outs){

        double max = -Double.MAX_VALUE;
        int perc = 0;

        for(int i = 0; i < outs.size(); i++){

            if(max < outs.get(i)){
                max = outs.get(i);
                perc = i;
            }
        }
        return perc;
    }

    public void testPrint(){

        /*for(int i = 0; i < networks.size(); i++){

            System.out.println(networks.get(i));

        }*/

        System.out.println("Accuracy: "+ ((double) numberofCorrectMatches/ (double)testImages.size())*100 + "%");


    }



}
