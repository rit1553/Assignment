package Assignment;

import java.io.*;
import java.util.ArrayList;

public class couple_generator {

	public void create() {

		ArrayList<girl> gArray = User.gArray;
		ArrayList<boy> bArray = User.bArray;
		ArrayList<couple> cArray = User.cArray;

		for (int a = 0; a < gArray.size(); a++) {
			for (int b = 0; b < bArray.size(); b++) {
				if (bArray.get(b).minAttractVal <= gArray.get(a).attractVal
						&& bArray.get(b).budget > gArray.get(a).mtCost
						&& bArray.get(b).isCom == 0) {
					bArray.get(b).isCom = 1;
					gArray.get(a).isCom = 1;
					couple c = new couple(bArray.get(b), gArray.get(a));
					cArray.add(c);
					break;
				}
			}
		}
	}

}
