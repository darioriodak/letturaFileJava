package entity;

import java.util.ArrayList;

import org.json.JSONArray;

public class ListaDistro extends ArrayList<Distro> {
	
	private static final long serialVersionUID = 1L;

	public String toJSONString() {
		return new JSONArray(this).toString();
	}
	
}
