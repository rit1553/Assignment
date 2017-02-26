package Assignment;

import static Assignment.User.gifArray;
import static java.lang.Math.*;
import java.sql.*;
import static jdk.nashorn.internal.objects.Global.Infinity;

import java.io.*;
import java.util.*;

import com.csvreader.CsvWriter;

public class Q2_User {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		couple_generator c_g = new couple_generator();
		c_g.create();
		User.input();
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		Collections.sort(User.gifArray, (gift a, gift b) -> {
			if(a.value<b.value){return 1;}else if(a.value>b.value){return -1;}else if(a.price<b.price){return -1;}else if(a.price>b.price){return 1;} else {return 0;}
		});

		String outputFile = "C:\\Users\\Anurag\\Desktop\\Q2_TimeStamp.csv";
		boolean alreadyExists = new File(outputFile).exists();
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput.write("Couples Data:");
			csvOutput.endRecord();

			if (!alreadyExists) {
				csvOutput.write("TimeStamp");
				csvOutput.write("C_id");
				csvOutput.endRecord();
			}

			for (int it = 0; it < User.cArray.size(); it++) {
				csvOutput.write("Time :" + ts.toString());
				csvOutput.write(Integer.toString(User.cArray.get(it).couple_id));
				csvOutput.endRecord();
			}

			csvOutput.write("Exchanged Gifts Data:");
			csvOutput.endRecord();

			for (int itr_a = 0; itr_a < User.cArray.size(); itr_a++) {
				if (User.cArray.get(itr_a).b.type.equals("Miser")) {
					int mtc = User.cArray.get(itr_a).g.mtCost;
					int itr_b = 0, itr_c = 0;
					while (itr_b < mtc) {
						itr_b += User.gifArray.get(itr_c).price;
						User.cArray.get(itr_a).giftBasket.add(gifArray.get(itr_c));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(User.cArray.get(itr_a).couple_id));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gifArray.get(itr_c).gift_id));
						csvOutput.endRecord();
						itr_c++;
					}
				} else if (User.cArray.get(itr_a).b.type.equals("Generous")) {
					int mtc = User.cArray.get(itr_a).b.budget;
					int itr_b = 0, itr_c = 0;
					while (itr_b < mtc) {
						itr_b += User.gifArray.get(itr_c).price;
						User.cArray.get(itr_a).giftBasket.add(gifArray.get(itr_c));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(User.cArray.get(itr_a).couple_id));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gifArray.get(itr_c).gift_id));
						csvOutput.endRecord();
						itr_c++;
					}
				} else {
					int mtc = User.cArray.get(itr_a).b.budget;
					int itr_b = 0, itr_c = 0;
					while (itr_b < mtc) {
						itr_b += User.gifArray.get(itr_c).price;
						User.cArray.get(itr_a).giftBasket.add(gifArray.get(itr_c));
						csvOutput.write("Time :" + ts.toString());
						csvOutput.write("Couples: " + Integer.toString(User.cArray.get(itr_a).couple_id));
						csvOutput.write("Exchanged Gift :" + Integer.toString(gifArray.get(itr_c).gift_id));
						csvOutput.endRecord();
						itr_c++;
					}
					int itr_d = User.cArray.get(itr_a).b.budget - itr_b;
					for (itr_b = 0; itr_b < gifArray.size(); itr_b++) {
						if (User.gifArray.get(itr_b).type.equals("Luxury") && itr_d >= gifArray.get(itr_b).price) {
							User.cArray.get(itr_a).giftBasket.add(gifArray.get(itr_c));
							csvOutput.write("Time :" + ts.toString());
							csvOutput.write("Couples: " + Integer.toString(User.cArray.get(itr_a).couple_id));
							csvOutput.write("Exchanged Gift :" + Integer.toString(gifArray.get(itr_c).gift_id));
							csvOutput.endRecord();
						}
					}
				}
			}

			for (int itr_a = 0; itr_a < User.cArray.size(); itr_a++) {
				double h_g = 0, h_b = 0;
				int itr_c = 0, itr_d = 0;
				for (int itr_b = 0; itr_b < User.cArray.get(itr_a).giftBasket.size(); itr_b++) {
					itr_c += User.cArray.get(itr_a).giftBasket.get(itr_b).price;
					itr_d += User.cArray.get(itr_a).giftBasket.get(itr_b).value;
				}
				int mtc = User.cArray.get(itr_a).g.mtCost;
				if (User.cArray.get(itr_a).g.type.equals("Choosy")) {
					h_g += Math.abs(Math.log10(itr_c - mtc + (itr_d * 2)));
				} else if (User.cArray.get(itr_a).g.type.equals("Desperate")) {
					int val = itr_c - mtc ;
					while (Math.exp(val) == Infinity) {
						val -= 500;
					}
					h_g += Math.abs(Math.exp((val)));
				} else {
					h_g += Math.abs(itr_c - mtc + itr_d);
				}
				if (User.cArray.get(itr_a).b.type.equals("Miser")) {
					h_b += Math.abs(User.cArray.get(itr_a).b.budget - itr_c);
				} else if (User.cArray.get(itr_a).b.type.equals("Generous")) {
					h_b = h_g;
				} else {
					h_b = User.cArray.get(itr_a).g.iLevel;
				}

				User.cArray.get(itr_a).b.happiness = h_b;
				User.cArray.get(itr_a).g.happiness = h_g;
				User.cArray.get(itr_a).happiness = h_b + h_g;
				User.cArray.get(itr_a).compatibility = User.cArray.get(itr_a).b.budget - mtc
						+ Math.abs(User.cArray.get(itr_a).g.iLevel
								- User.cArray.get(itr_a).b.iLevel)
						+ Math.abs(User.cArray.get(itr_a).g.attractVal
								- User.cArray.get(itr_a).b.attractVal);
			}

			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String outputFile1 = "C:\\Users\\Anurag\\Desktop\\Q2_log.csv";
		boolean alreadyExists1 = new File(outputFile1).exists();

		Collections.sort(User.cArray, (couple a, couple b) -> {
			if(a.happiness<b.happiness){return 1;}else if(a.happiness>b.happiness){return -1;}else{return 0;}
		});

		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile1, true), ',');
			if (!alreadyExists1) {
				csvOutput.write("Exchanged Gifts Data:");
				csvOutput.endRecord();
				for (int itr_a = 0; itr_a < User.cArray.size(); itr_a++) {
					int itr_c = 0, itr_d = 0;
					for (int itr_b = 0; itr_b < User.cArray.get(itr_a).giftBasket.size(); itr_b++) {
						itr_c += User.cArray.get(itr_a).giftBasket.get(itr_b).price;
						itr_d += User.cArray.get(itr_a).giftBasket.get(itr_b).value;
					}
					csvOutput.write("Couples :" + Integer.toString(User.cArray.get(itr_a).couple_id));
					csvOutput.write(
							"Exchanged  Gifts:" + Integer.toString(User.cArray.get(itr_a).giftBasket.size()));
					csvOutput.write("Price :" + Integer.toString(itr_c));
					csvOutput.write("Value :" + Integer.toString(itr_d));
					csvOutput.endRecord();
				}

				csvOutput.write(Integer.toString(User.cArray.size()) + " are most happy.");
				csvOutput.endRecord();

				csvOutput.write("TimeStamp");
				csvOutput.write("Couple_id");
				csvOutput.write("Happiness");
				csvOutput.endRecord();

				for (int itr_a = 0; itr_a < User.cArray.size(); itr_a++) {
					csvOutput.write("Time :" + ts.toString());
					csvOutput.write(Integer.toString(User.cArray.get(itr_a).couple_id));
					csvOutput.write("Happiness : " + Double.toString(User.cArray.get(itr_a).happiness));
					csvOutput.endRecord();
				}

				Collections.sort(User.cArray, (couple a, couple b) -> {
					if(a.compatibility<b.compatibility){return 1;}else if(a.compatibility>b.compatibility){return -1;}else{return 0;}
				});

				csvOutput.write(Integer.toString(User.cArray.size()) + " are most compatible.");
				csvOutput.endRecord();

				csvOutput.write("TimeStamp");
				csvOutput.write("Couple_id");
				csvOutput.write("Compatibility");
				csvOutput.endRecord();

				for (int itr_a = 0; itr_a < User.cArray.size(); itr_a++) {
					csvOutput.write("Time :" + ts.toString());
					csvOutput.write(Integer.toString(User.cArray.get(itr_a).couple_id));
					csvOutput.write("Compatibility :" + Double.toString(User.cArray.get(itr_a).compatibility));
					csvOutput.endRecord();
				}
				csvOutput.close();

			}

			csvOutput.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
