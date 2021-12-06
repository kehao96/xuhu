package com.xuhu.onlinechargingsystem.mlAlgorithm;


import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class LRRegression {


    public static LinearRegression doLinearRegression(String filePath) throws Exception {

        DataSource trainingData = new DataSource(filePath);
        Instances instances = trainingData.getDataSet();
        instances.setClassIndex(instances.numAttributes()-1);

        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(instances);




        // prediction will use lr.classifyInstance(Instance instance)



        // calc R

//        double R = calcR(predictValues,realValues);
        return lr;
    }

    public static List<Double> getRealElectricity(String city, String filepath) throws IOException {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filepath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = null;
        br = new BufferedReader(streamReader);

        String line = "";
        List<Double> electricityList = new LinkedList<>();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if(city.equals(data[0])){
                electricityList.add(Double.valueOf(data[3]));
            }
        }
        return electricityList;
    }


    public static List<Double> getPredictElectricity(String city) throws Exception {

        List<Double> predictList = new LinkedList<>();
        DataSource trainingData = new DataSource("train.arff");
        Instances instances = trainingData.getDataSet();
        instances.setClassIndex(instances.numAttributes()-1);

        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(instances);

        for (int i=1; i<=12; i++) {
            Instance predictInstance = new SparseInstance(3);
            predictInstance.setDataset(instances);
            predictInstance.setValue(0,city);
            predictInstance.setValue(1,2017);
            predictInstance.setValue(2,i);

            double predictValue = lr.classifyInstance(predictInstance);
            predictList.add(predictValue);
        }
        return predictList;
    }


    public static Map<Integer, Double> getRealData(String city) throws IOException {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("test.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = null;
        br = new BufferedReader(streamReader);

        String line = "";
        Map<Integer,Double> electricityMap = new HashMap<>();
        int counter = 1;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if(city.equals(data[0])){
                electricityMap.put(counter,Double.valueOf(data[3]));
                counter++;
            }

        }
        return electricityMap;
    }

    public static Map<Integer, Double> getPredictData(String city) throws Exception {
        Map<Integer,Double> predictMap = new HashMap<>();

        DataSource trainingData = new DataSource("train.arff");
        Instances instances = trainingData.getDataSet();
        instances.setClassIndex(instances.numAttributes()-1);

        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(instances);

        for (int i=1; i<=12; i++) {
            Instance predictInstance = new SparseInstance(3);
            predictInstance.setDataset(instances);
            predictInstance.setValue(0,city);
            predictInstance.setValue(1,2017);
            predictInstance.setValue(2,i);

            double predictValue = lr.classifyInstance(predictInstance);
            predictMap.put(i,predictValue);
        }

        return predictMap;
    }
    public static double calcR(String city){
        List<Double> realList = null;
        List<Double> predictList = null;
        try {
            predictList = getPredictElectricity(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            realList = getRealElectricity(city,"test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        double sum = 0;

        double averageValue;
        for (Double value: realList) {
            sum += value;
        }
        averageValue = sum/realList.size();

        double numerator = 0;
        for (int i = 0; i < predictList.size(); i++) {
            double difference = realList.get(i)-predictList.get(i);
            numerator += Math.pow(difference,2);
        }

        double denominator = 0;
        for (Double value: realList) {
            double difference = averageValue-value;
            denominator += Math.pow(difference,2);
        }

        double R_square = Math.abs(1- numerator/denominator);
        return Math.sqrt(R_square);
    }
}
