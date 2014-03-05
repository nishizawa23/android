package com.example.androidapplicationcontext;

import android.app.Application;

public class TestForApplication extends Application {

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
