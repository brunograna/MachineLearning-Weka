package playtennis;

import playtennis.enums.ClimaEnum;
import playtennis.enums.TemperaturaEnum;
import playtennis.enums.UmidadeEnum;
import playtennis.enums.VentoEnum;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class TennisTest {

    public static final String DATASETPATH = "./src/playtennis-dataset/dataset.arff";
    public static final String MODElPATH = "./src/playtennis-dataset/model.bin";

    public static void main(String[] args) throws Exception {

        TennisModelGenerator modelGenerator = new TennisModelGenerator();

        Instances dataset = modelGenerator.loadDataset(DATASETPATH);

        //divide dataset to train 80% and to test 20%
        int trainSize = (int) (Math.round(dataset.numInstances())*0.85);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));

        //Normalize dataset
        Filter filter = new Normalize();

        filter.setInputFormat(dataset);
        Instances dataSetNormalized = Filter.useFilter(dataset, filter);

        //Separating the 2 datasets one for training and other for testing
        Instances trainDataSet = new Instances(dataSetNormalized, 0, trainSize);
        Instances testDataSet = new Instances(dataSetNormalized, trainSize, testSize);

        //build classifier with train dataset
        MultilayerPerceptron ann = (MultilayerPerceptron) modelGenerator.buildClassifier(trainDataSet);

        //Evaluate classifier with test dataset
        String evalSummary = modelGenerator.evaluateModel(ann, trainDataSet, testDataSet);
        System.out.println("Evaluation: "+evalSummary);

        //Save model
        modelGenerator.saveModel(ann, MODElPATH);

        //Classifiy a single instance
        TennisModelClassifier cls = new TennisModelClassifier();
        String jogar = cls.classify(Filter.useFilter(cls.createInstance(ClimaEnum.Nublado,TemperaturaEnum.Fria, UmidadeEnum.Alta, VentoEnum.Fraco),filter), MODElPATH);
        System.out.println("Com estas condicoes do tempo irei jogar tennis ? "+ jogar);






    }
}
