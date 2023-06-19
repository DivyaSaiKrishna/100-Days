package com.Days.Java8API.Service;

import com.Days.Java8API.Model.LamdaModel;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class APIService {

    //Sort list of string by using Lamda functions
    //asc small to big , desc big to small
    public List<String> sortStringList(LamdaModel lamdaModel){
        List<String> inputModel = lamdaModel.getLamdaList();
        String sortStyle = lamdaModel.getSortStyle();
        List<String> returnString =new ArrayList<>();
        if(sortStyle.equals("asc")){
            inputModel.sort((a,b)->a.compareTo(b));
        }if(sortStyle.equals("desc")){
            inputModel.sort((a,b)->b.compareTo(a));
        }
        return inputModel;
    }

    public Object squareRoot(double number){
        Function<Double, Object> factorial = n -> {
            long  result = 1;
            for(int i=1;i<=number ;i++){
                    result*=i;
            }
            return result;
        };
        Double ans = Double.valueOf(number);
        Object factResult = factorial.apply(ans);
        return factResult;
    }

    public Double averageList(List<Integer> integerList){
        return integerList.stream().collect(Collectors.averagingInt(n->n*1));
    }



}
