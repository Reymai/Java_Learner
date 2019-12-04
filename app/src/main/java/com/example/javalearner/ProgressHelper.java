package com.example.javalearner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProgressHelper {

	private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

	public static int levelCounter(int experience) {
		int level = 0;
		if (experience < 100){
			level = 1;
		} else if (experience < 150){
			level = 2;
		} else if (experience < 230){
			level = 3;
		} else if (experience < 350){
			level = 4;
		} else if (experience < 530){
			level = 5;
		} else if (experience < 800){
			level = 6;
		} else if (experience < 1200){
			level = 7;
		} else if (experience < 1800){
			level = 8;
		} else if (experience < 2700){
			level = 9;
		} else if (experience > 2700){
			level = 10;
		}
		return level;
	}

	public static int getMaxExperience(int level){
		int experience = 0;

		if (level == 1){
			experience = 100;
		} else if (level == 2){
			experience = 150;
		} else if (level == 3){
			experience = 230;
		} else if (level == 4){
			experience = 350;
		} else if (level == 5){
			experience = 530;
		} else if (level == 6){
			experience = 800;
		} else if (level == 7){
			experience = 1200;
		} else if (level == 8){
			experience = 1800;
		} else if (level >= 9){
			experience = 2700;
		}
		return experience;
	}

}
