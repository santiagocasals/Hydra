package me.acru.utils;

public class IntegerUtils {
	
    public static int getRandomInteger(int max, int min){
        return ((int) (Math.random()*(max - min))) + min;
    }
    
    

}
