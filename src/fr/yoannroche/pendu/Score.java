package fr.yoannroche.pendu;

import java.io.Serializable;

public class Score implements Serializable {
	
	protected static int point ;
	protected String nom ;
	public Score() {
		
	}
	public Score(int point , String nom) {
		Score.point = point ;
		this.nom = nom ;
	}
	
	public int getPoint() {
		return point ;
	}

	
	
	
	public int getScore() {
		return point ;
	}
	public String getNom () {
		return nom ;
	}

}
