package fr.yoannroche.pendu;

import java.io.Serializable;

public class Score extends Jeu implements Serializable {
	
	protected int point ;
	protected String nom ;
	public Score() {
		
	}
	public Score(int point , String nom) {
		this.point = point ;
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
