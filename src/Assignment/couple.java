package Assignment;

import java.io.*;
import java.util.ArrayList;

public class couple {

	boy b;
	girl g;
	int couple_id;
	gift gif;
	double happiness;
	double compatibility;
	ArrayList<gift> giftBasket = new ArrayList<>();

	public couple(boy b, girl g) {
		super();
		this.b = b;
		this.g = g;
		this.couple_id = b.name * 10000 + g.name;
		this.happiness = 0;
		this.compatibility = 0;
	}

}
