import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import provaLettura.Candidato;

public class prova {

	public static void main(String[] args) {
		FileReader in = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			in = new FileReader("/home/dario/Scaricati/esito.txt");
			br = new BufferedReader(in);
			sb = new StringBuilder();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		HashMap<String,Integer> graduatoria = new HashMap<>();
	    try {
	    	String line;
	    	while ((line = br.readLine()) != null) {
	    		if(line.trim().isEmpty()) {
	    			String[] temp = sb.toString().split(" ");
	    			String cand = temp[0];
	    			int voto = Integer.parseInt(temp[1]);
	    			if(!graduatoria.containsKey(cand)) {
	    				graduatoria.put(cand, voto);
	    			}
	    			else {
	    				int tempvoto = graduatoria.get(cand);
	    				graduatoria.replace(cand,voto+tempvoto);
	    			}
	    			
	    			
	    			sb.setLength(0);	
	    		}
	    		else {
	    			sb.append(line);
	    		}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	 
	    ArrayList<Candidato> risultato = new ArrayList<>();
	    for (Entry<String, Integer> entry : graduatoria.entrySet()) {
	        String nome = entry.getKey();
	        Integer voto = entry.getValue();
	        Candidato c = new Candidato(nome,voto);
	        risultato.add(c);
	    }
	    
	    risultato.sort(Comparator.comparing(Candidato::getVoto).reversed());
	    
	    for (Candidato i : risultato) {
	    	System.out.println(i);
	    }
	    
	
	}
	
}


