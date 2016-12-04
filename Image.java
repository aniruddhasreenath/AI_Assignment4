import java.util.ArrayList;

/**
 * Created by Sreenath on 6/11/2016.
 */
public class Image implements Comparable<Image> {

    public int tureLabel;
    public char[][] image;
    public ArrayList<Double> inputFormat;

    public Image(int lab, char[][] img){
        tureLabel = lab;
        image = img;

        inputFormat = new ArrayList<Double>();
        inputFormat = convertImageToInput(this);
    }

    public int compareTo(Image a){
        if(a.tureLabel == this.tureLabel){
            return 1;
        }
        return 0;
    }

    public ArrayList<Double> convertImageToInput(Image data){

        ArrayList<Double> input = new ArrayList<Double>();

        for(int i = 0; i < data.image.length; i ++){

            for(int j = 0; j < data.image.length; j++){

                //if a hash is found then consider it as 1 set all other symbols to 0
                if(data.image[i][j] == ' '){

                    input.add(0.0);


                }
                else if(data.image[i][j] == '#'){

                    input.add(1.0);
                }
                else if(data.image[i][j] == '+'){

                    input.add(1.0);

                }

            }
        }

        input.add(1.0);

        return input;
    }

    public String toString(){
        String imag = "";
        if (image.length == 0){
            System.out.println("DOES NOT HAVE AN IMAGE");
        }
        else{
            imag = imag + "Actual Image:" + "\n" +"\n";

            for(int i = 0; i < image.length; i++){
                imag = imag + "\n";
                for (int j = 0; j < image.length; j++){
                    imag = imag + Character.toString(image[i][j]);
                }
            }

            imag = imag + "\n";
            imag = imag + "Input format for Perceptron:" + "\n" +"\n";

            int index = 0;
            for(int i = 0; i < inputFormat.size(); i ++){

                imag = imag + inputFormat.get(i).toString();

                index = index +1;

                if (index == 28){
                    imag = imag + "\n";
                    index = 0;
                }
            }


        }
        return imag;
    }
}
