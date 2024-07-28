package domaca_1;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) throws Exception {		
		long t0 = System.currentTimeMillis();
		
		
		// VNESI POTI DATOTEK
		String vhodnaDatoteka = "C:\\Users\\Emina\\Desktop\\icons\\domace\\psa2_1\\domaca_1\\src\\domaca_1\\nalogaA.txt";
		String izhodnaDatoteka = "C:\\Users\\Emina\\Desktop\\icons\\domace\\psa2_1\\domaca_1\\src\\domaca_1\\rezultatiA_java.txt";
		String zakodiraneResitve = "C:\\Users\\Emina\\Desktop\\icons\\domace\\psa2_1\\domaca_1\\src\\domaca_1\\resitveA.pub";
		String izhodnaIzPythona = "C:\\Users\\Emina\\Desktop\\icons\\domace\\psa2_1\\Dn1\\src\\Dn1\\rezultati_python.txt";
		// VASA RESITEV
		Resitev.resi(vhodnaDatoteka, izhodnaDatoteka, izhodnaIzPythona);
		
		
		long t1 = System.currentTimeMillis();
		
		PreveriResitev.preveri(t0, t1, izhodnaDatoteka, zakodiraneResitve);
	}

}
