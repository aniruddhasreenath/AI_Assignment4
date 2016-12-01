import java.util.ArrayList;

/**
 * Created by sreenath on 30/11/2016.
 */
public class NeuralNetwork {

    public static double alpha;

    public final int MAX_EPOCH = 1000;

    public  ArrayList<Perceptron> networks;

    public ArrayList<ArrayList<Double>> inputs;

    public NeuralNetwork(){

        alpha = 1;
        networks = new ArrayList<Perceptron>();
        inputs = new ArrayList<ArrayList<Double>>();

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


        for(int i = 0; i < inputs.size(); i++){

            for(int j = 0; j < networks.size(); j ++){

                networks.get(j).passInput(inputs.get(i));
                networks.get(j).calculateOutput();

                //TODO add the update rule logic here

                if(networks.get(j).output)


            }

        }

        testPrint();

    }

    public void testPrint(){

        for(int i = 0; i < networks.size(); i++){

            System.out.println(networks.get(i));

        }


    }

}
