package Assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	public static ArrayList<girl> gArray= new ArrayList<>();
	public static ArrayList<boy> bArray = new ArrayList<>();
	public static ArrayList<couple> cArray = new ArrayList<>();

	public static ArrayList<gift> gifArray = new ArrayList<>();

	public static void input() throws IOException {

		String csvFile = "C:\\Users\\Anurag\\Desktop\\girl.csv";// csv for girl
		String line = "";
		String cvsSplitby = ",";

		int name, attractiveness, maintenanceCost, intelligenceLevel;
		String type;
		girl g;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				name = Integer.parseInt(line.split(",")[0]);
				attractiveness = Integer.parseInt(line.split(",")[1]);
				maintenanceCost = Integer.parseInt(line.split(",")[2]);
				intelligenceLevel = Integer.parseInt(line.split(",")[3]);
				type = (line.split(",")[4]);

				g = new girl(name, attractiveness, maintenanceCost, intelligenceLevel, type);
				gArray.add(g);
			}
		} catch (IOException eg) {
			eg.printStackTrace();
		}

		csvFile = "C:\\Users\\Anurag\\Desktop\\boy.csv";// csv for boy
		line = "";
		cvsSplitby = ",";

		int budget, minAttractivenessReq;
		boy b;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				name = Integer.parseInt(line.split(",")[0]);
				attractiveness = Integer.parseInt(line.split(",")[1]);
				budget = Integer.parseInt(line.split(",")[2]);
				intelligenceLevel = Integer.parseInt(line.split(",")[3]);
				minAttractivenessReq = Integer.parseInt(line.split(",")[4]);
				type = (line.split(",")[5]);

				b = new boy(name, attractiveness, budget, intelligenceLevel, minAttractivenessReq, type);
				bArray.add(b);
			}
		} catch (IOException eb) {
			eb.printStackTrace();
		}

		csvFile = "C:\\Users\\Anurag\\Desktop\\gift.csv";// csv for gift
		line = "";
		cvsSplitby = ",";

		int price, value, id;
		gift gif;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				price = Integer.parseInt(line.split(",")[0]);
				value = Integer.parseInt(line.split(",")[1]);
				id = Integer.parseInt(line.split(",")[2]);
				type = (line.split(",")[3]);

				gif = new gift(price, value, id, type);
				gifArray.add(gif);
			}
		} catch (IOException egif) {
			egif.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		int opt;
		while (true) {
			System.out.println(
					"Assignment:\n1: Choose \"1\" for Answer 1\n2: Choose \"2\" for Answer 2\n3: Choose \"3\" for Exiting the Program");
			opt = s.nextInt();
			switch (opt) {
			case 1: Q1_User.main(args); break;
			case 2: Q2_User.main(args); break;
			default: System.exit(0);
			}
		}
	}
}
