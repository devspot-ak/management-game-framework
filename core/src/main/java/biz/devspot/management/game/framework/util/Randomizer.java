package biz.devspot.management.game.framework.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Randomizer {
    
	public Object getRandomListEntry(List list) {
		int n = getRandomInteger(0, list.size() - 1);
		return list.get(n);
	}

	public Object getRandomArrayEntry(Object[] array) {
		return array[getRandomInteger(0, array.length - 1)];
	}
    
	public int getRandomInteger(int nmin, int nmax) {
		return (int) (Math.round((Math.random() * ((nmax + 1) - nmin)) - 0.5) + nmin);
	}

	public long getRandomLong(long nmin, long nmax) {
		return (long) (Math.round(Math.random() * (nmax - nmin)) + nmin);
	}

	public int getRandomWeightedInteger(int nmin, int nmax, int target) {
		int lowerLimit = nmin;
		int upperLimit = nmax;
		if ((target - nmin) > (nmax - target)) {
			upperLimit = target + (target - nmin);
		} else {
			lowerLimit = target - (nmax - target);
		}
		int candidates = 3;
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < candidates; i++) {
			int value = getRandomInteger(lowerLimit, upperLimit);
			if (value < nmin) {
				value = nmin;
			} else if (value > nmax) {
				value = nmax;
			}
			if (Math.abs(value - target) < Math.abs(result - target)) {
				result = value;
			}
		}
		return result;
	}

	public boolean getRandomWeightedBoolean(int trueWeight) {
		return getRandomWeightedBoolean(trueWeight, 100 - trueWeight);
	}
    
	public  boolean getRandomWeightedBoolean(int trueWeight, int falseWeight) {
		int n = getRandomInteger(1, trueWeight + falseWeight);
		return n <= trueWeight;
	}

	public Object getRandomWeightedObject(Map<Object, Integer> objects) {
		List objectList = new ArrayList();
		for (Object object : objects.keySet()) {
			int weight = objects.get(object);
			for (int i = 0; i < weight; i++) {
				objectList.add(object);
			}
		}
		return getRandomListEntry(objectList);
	}

	public static List shuffleList(List source) {
		Collections.shuffle(source);
        return source;
	}

	public static Object[] shuffleArray(Object[] source) {
		Collections.shuffle(Arrays.asList(source));
		return source;
	}

	public int getRandomIntegerInPercentageRange(int target, int percentage) {
		float range = target * ((float) percentage / 100);
		int lowerLimit = (int) (target - range);
		int upperLimit = (int) (target + range);
		return getRandomInteger(lowerLimit, upperLimit);
	}
	
	public int getRandomIntegerInPercentageRange(int target, int percentage, int min, int max) {
		float range = target * ((float) percentage / 100);
		int lowerLimit = (int) (target - range);
		if(lowerLimit < min){
			lowerLimit = min;
		}
		int upperLimit = (int) (target + range);
		if(upperLimit > max){
			upperLimit = max;
		}
		return getRandomInteger(lowerLimit, upperLimit);
	}
}
