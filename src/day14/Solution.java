package day14;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static final String[] INPUT = {"Vixen can fly 19 km/s for 7 seconds, but then must rest for 124 seconds.","Rudolph can fly 3 km/s for 15 seconds, but then must rest for 28 seconds.","Donner can fly 19 km/s for 9 seconds, but then must rest for 164 seconds.","Blitzen can fly 19 km/s for 9 seconds, but then must rest for 158 seconds.","Comet can fly 13 km/s for 7 seconds, but then must rest for 82 seconds.","Cupid can fly 25 km/s for 6 seconds, but then must rest for 145 seconds.","Dasher can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.","Dancer can fly 3 km/s for 16 seconds, but then must rest for 37 seconds.","Prancer can fly 25 km/s for 6 seconds, but then must rest for 143 seconds."};
	public static int TIME = 2503;

	public static void main(String[] args){
		ArrayList<Reindeer> deers = new ArrayList<>();
		TreeMap<String, Integer> scores = new TreeMap<>();
		for(String s: INPUT) {
			Reindeer r = new Reindeer(s);
			deers.add(r);
			scores.put(r.getName(), 0);
		}

		int max = 0;
		for(Reindeer r: deers){
			int m = r.getDistance(TIME);
			if(m > max)
				max = m;
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + max + "\n");

		for(int t = 1; t <= TIME; t++){
			int maxD = 0;
			TreeSet<String> topScores = new TreeSet<>();
			for(Reindeer r: deers){
				int s = r.getDistance(t);
				if(s > maxD){
					maxD = s;
					topScores = new TreeSet<>();
					topScores.add(r.getName());
				}
				else if(s == maxD)
					topScores.add(r.getName());
			}

			for(String n: topScores)
				scores.put(n, scores.get(n)+1);
		}

		max = 0;
		for(String s: scores.keySet()){
			if(scores.get(s) > max)
				max = scores.get(s);
		}

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + max);
	}
}

class Reindeer{

	private String name;
	private int speed;
	private int speedTime;
	private int restTime;
	private int cycle;

	public Reindeer(String s){
		Pattern pat = Pattern.compile("([A-Za-z]+) can fly ([0-9]+) km/s for ([0-9]+) seconds, but then must rest for ([0-9]+) seconds.");
		Matcher m = pat.matcher(s);
		m.find();
		this.name = m.group(1);
		this.speed = Integer.parseInt(m.group(2));
		this.speedTime = Integer.parseInt(m.group(3));
		this.restTime = Integer.parseInt(m.group(4));
		this.cycle = this.speedTime+this.restTime;
	}

	public int getDistance(int time){
		int result = 0;

		int cycles = time/this.cycle;
		result += cycles*(this.speed*this.speedTime);
		if(time%this.cycle >= this.speedTime)
			result += this.speed*this.speedTime;
		else
			result += (time%this.cycle)*this.speed;

		return result;
	}

	public String getName() {
		return name;
	}
}
