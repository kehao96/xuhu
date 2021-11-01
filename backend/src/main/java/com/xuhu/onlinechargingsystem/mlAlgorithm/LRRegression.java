package com.xuhu.onlinechargingsystem.mlAlgorithm;


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;




public class LRRegression {

    private LRRegression(){}

    public static LinearRegression doLinearRegression(String filePath) throws Exception {
        DataSource trainingData = new DataSource(filePath);
        Instances instances = trainingData.getDataSet();
        instances.setClassIndex(instances.numAttributes()-1);

        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(instances);

        // prediction will use lr.classifyInstance(Instance instance)
        return lr;

    }
}
