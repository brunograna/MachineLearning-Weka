package playtennis;

import playtennis.enums.ClimaEnum;
import playtennis.enums.TemperaturaEnum;
import playtennis.enums.UmidadeEnum;
import playtennis.enums.VentoEnum;
import sample.ModelClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TennisModelClassifier {

    private Attribute clima;
    private Attribute temperatura;
    private Attribute umidade;
    private Attribute vento;

    private ArrayList attributes;
    private List<String> jogar;
    private Instances dataRaw;

    public TennisModelClassifier(){
        attributes = new ArrayList();

        List<String> climaList = new ArrayList<String>();
        climaList.add(ClimaEnum.Chuva.getKey());
        climaList.add(ClimaEnum.Ensolarado.getKey());
        climaList.add(ClimaEnum.Nublado.getKey());
        clima = new Attribute("clima", climaList);

        List<String> tempList = new ArrayList<String>();
        tempList.add(TemperaturaEnum.Quente.getKey());
        tempList.add(TemperaturaEnum.Moderada.getKey());
        tempList.add(TemperaturaEnum.Fria.getKey());
        temperatura = new Attribute("temperatura", tempList);

        List<String> umidList = new ArrayList<String>();
        umidList.add(UmidadeEnum.Alta.getKey());
        umidList.add(UmidadeEnum.Normal.getKey());
        umidade = new Attribute("umidade", umidList);

        List<String> ventoList = new ArrayList<String>();
        ventoList.add(VentoEnum.Forte.getKey());
        ventoList.add(VentoEnum.Fraco.getKey());
        vento = new Attribute("vento", ventoList);

        jogar = new ArrayList();
        jogar.add("Sim");
        jogar.add("Nao");

        attributes.add(clima);
        attributes.add(temperatura);
        attributes.add(umidade);
        attributes.add(vento);

        attributes.add(new Attribute("jogar", jogar));
        dataRaw = new Instances("TestInstances",attributes,0);
        dataRaw.setClassIndex(dataRaw.numAttributes()-1);
    }

    public Instances createInstance(ClimaEnum clima, TemperaturaEnum temperatura,
                                    UmidadeEnum umidade, VentoEnum vento ){

        dataRaw.clear();
        double[] instanceValue = new double[]{clima.getValue(), temperatura.getValue(), umidade.getValue(), vento.getValue(), 0};
        dataRaw.add(new DenseInstance(1.0, instanceValue));
        return dataRaw;
    }

    public String classify(Instances insts, String path){
        String result = "Not classified yet!!";
        Classifier cls = null;
        try{
            cls = (MultilayerPerceptron) SerializationHelper.read(path);
            result = jogar.get((int) cls.classifyInstance(insts.firstInstance()));
        } catch (Exception e) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public Instances getInstance(){
        return dataRaw;
    }
}
