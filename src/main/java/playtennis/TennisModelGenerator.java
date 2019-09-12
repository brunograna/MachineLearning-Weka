package playtennis;

import sample.ModelGenerator;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TennisModelGenerator {

    public Instances loadDataset(String path){
        Instances dataset = null;
        try{
            dataset = DataSource.read(path);
            if (dataset.classIndex() == -1){
                dataset.setClassIndex(dataset.numAttributes()-1);
            }
        } catch (Exception e) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
        return dataset;
    }

    public Classifier buildClassifier(Instances traindataset){
        MultilayerPerceptron m = new MultilayerPerceptron();

        try {
            m.buildClassifier(traindataset);
        } catch (Exception e) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }

    public String evaluateModel(Classifier model, Instances traindataset, Instances testdataset){
        Evaluation eval = null;

        try {
            // Evaluate classifier with test dataset
            eval = new Evaluation(traindataset);
            eval.evaluateModel(model, testdataset);
        } catch (Exception e) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
        return eval.toSummaryString("",true);
    }

    public void saveModel(Classifier model, String modelPath){
        try {
            SerializationHelper.write(modelPath, model);
        } catch (Exception e) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
