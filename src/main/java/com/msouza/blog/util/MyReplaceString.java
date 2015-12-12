package com.msouza.blog.util;

import java.text.Normalizer;

public class MyReplaceString {
	
	public static String formatarPermaLink(String value){
		
		// Persistência com JPA ! -- persistencia_com_jpa
		
		String link = value.trim();
		
		link = link.toLowerCase();
		
		link = Normalizer.normalize(link, Normalizer.Form.NFD);
		
		link = link.replaceAll("\\s", "_");
		
		link = link.replaceAll("\\_+", "_");
		
		link = link.replaceAll("\\w", "");

		return link;
	}

}
