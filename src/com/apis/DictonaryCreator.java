package com.apis;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.service.Constants;
import com.service.SearchResultData;

public class DictonaryCreator {
	static HashMap<String, Integer> hm = new HashMap<String, Integer>();
	public static TST<ArrayList<SearchResultData>> tstObject= new TST<ArrayList<SearchResultData>>();
	static long words =0;
	static long pgcount=0;
	
	public static void main(String args[])
	{
		//generateTST();
		generateDict();
	}
	
	public static TST<ArrayList<SearchResultData>> getGeneratedTST()
	{
		if(tstObject.size()<1)
		{
			System.out.println("Generating TST for the first time");
			generateTST();
			System.out.println("TST generation completed");
		}		 
		 return tstObject;
	}
	
	public static void generateDict() {
		try {
		File directory = new File(Constants.WEB_PAGES_PATH);
		Stack<File> s = new Stack<File>();
		int fileCount=0;
		for( File file : directory.listFiles()) {
			s.push(file);
			while(!s.empty()) {
				File f = s.pop();
				if(f.isDirectory()) {
					for(File ff : f.listFiles())
						s.push(ff);
				}
				else {
						
						  //if(fileCount++>1000) { break; }
						 
					if(f.getName().contains("_converted"))
					{
						//putInMap(f);
						ArrayList<String> keys  = putInMap(f);
						
						/*String pagePath=f.getAbsolutePath().replaceAll("_converted.txt", ".html");
						pagePath =pagePath.substring(pagePath.indexOf("\\"+Constants.WEB_PAGES_BEGIN_FOLDER_NAME));
						String pageName= f.getName().replaceAll("_converted.txt", ".html");
						processPageDataInTST(pageName,pagePath,keys);*/
					}				
					
				}
			}
		}
		Object[] arry =  hm.keySet().toArray();
		Arrays.sort(arry);
		FileWriter fw = new FileWriter(new File("C:\\Users\\vivek\\Desktop\\WebSearchEngine\\src\\dic_webistes.txt"));
		for(Object ss : arry) {
			words++;
			fw.write(ss.toString()+"\n");
			//System.out.println(ss.toString());
		}
		fw.close();
		System.out.println(words);
		System.out.println(pgcount);
		
		/*FileWriter fileWrit= new FileWriter(new File("C:\\Users\\vivek\\Desktop\\TST_DATA.txt"));
		 for (String key : tstObject.keys()) {
	           // StdOut.println(key + " " + tstObject.get(key));
	            fileWrit.write(key + " " + tstObject.get(key)+"\n");
	        }
	        fileWrit.close();
	        */
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void generateTST() {
		try {
		File directory = new File(Constants.WEB_PAGES_PATH);
		Stack<File> s = new Stack<File>();
		int fileCount=0;
		for( File file : directory.listFiles()) {
			s.push(file);
			while(!s.empty()) {
				File f = s.pop();
				if(f.isDirectory()) {
					for(File ff : f.listFiles())
						s.push(ff);
				}
				else {
						
						  //if(fileCount++>3000) { break; }
						 
					if(f.getName().contains("_converted"))
					{
						//putInMap(f);
						ArrayList<String> keys  = putInMap(f);
						
						String pagePath=f.getAbsolutePath().replaceAll("_converted.txt", ".html");
						pagePath =pagePath.substring(pagePath.indexOf("\\"+Constants.WEB_PAGES_BEGIN_FOLDER_NAME));
						String pageName= f.getName().replaceAll("_converted.txt", ".html");
						processPageDataInTST(pageName,pagePath,keys);
					}				
					
				}
			}
		}
			/*
			 * Object[] arry = hm.keySet().toArray(); Arrays.sort(arry); FileWriter fw = new
			 * FileWriter(new
			 * File("C:\\Users\\vivek\\Desktop\\WebSearchEngine\\src\\dic.txt")); for(Object
			 * ss : arry) { words++; fw.write(ss.toString()+"\n");
			 * //System.out.println(ss.toString()); } fw.close();
			 */
		System.out.println(words);
		System.out.println(pgcount);
		
		FileWriter fileWrit= new FileWriter(new File("C:\\Users\\vivek\\Desktop\\TST_DATA_new.txt"));
		 for (String key : tstObject.keys()) {
	           // StdOut.println(key + " " + tstObject.get(key));
	            fileWrit.write(key + " " + tstObject.get(key)+"\n");
	        }
		 fileWrit.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * private static void putInMap(File f) throws Exception { pgcount++; Scanner in
	 * = new Scanner(f); HashMap<String, Integer> hashMap= new HashMap<String,
	 * Integer>(); String page=""; while(in.hasNext()) { page+="\n"+in.nextLine(); }
	 * 
	 * //String page = in.readAll(); Pattern p = Pattern.compile("\\w+"); Matcher m
	 * = p.matcher(page); while(m.find()) {
	 * 
	 * Integer i=hm.get(m.group()); if(i==null) { hm.put(m.group(), 1); } else {
	 * i=i+1; hm.put(m.group(), i); }
	 * 
	 * 
	 * Integer j=hashMap.get(m.group()); if(j==null) { hashMap.put(m.group(), 1); }
	 * else { j=j+1; hashMap.put(m.group(), j); } //hm.put(m.group(), 1); //words++;
	 * }
	 * 
	 * for(String keyStr:hashMap.keySet()) { Integer i=hashMap.get(keyStr);
	 * ArrayList<SearchResultData> list= null;
	 * 
	 * list=tstObject.get(keyStr); if(list==null) { list= new
	 * ArrayList<SearchResultData>(); list.add(new
	 * SearchResultData(f.getAbsolutePath().replaceAll("_converted.txt", ".html"),
	 * keyStr, i, null)); tstObject.put(keyStr, list); } else { list.add(new
	 * SearchResultData(f.getAbsolutePath().replaceAll("_converted.txt", ".html"),
	 * keyStr, i, null)); tstObject.put(keyStr, list); } }
	 * 
	 * 
	 * in.close(); }
	 */
	
	private static ArrayList<String> putInMap(File f) throws Exception {
		pgcount++;
		System.out.println(pgcount);
		Scanner in = new Scanner(f);
		ArrayList<String> keys = new ArrayList<String>();
		String page="";
		while(in.hasNext())
		{
			page+="\n"+in.nextLine();
		}
		
		//String page = in.readAll();
		Pattern p = Pattern.compile("[^\\d\\W]+");
		Matcher m = p.matcher(page);
		while(m.find()) {
			keys.add(m.group());
			Integer i=hm.get(m.group());
			
			if(i==null)
			{	
								
				hm.put(m.group(),1);
			}
			else
			{	i++;
				hm.put(m.group(), i);
			}
			
		}
			
		in.close();
		return keys;
	}
	
	 public static void processPageDataInTST(String pageName,String pagePath, ArrayList<String> keys) 
	    {
	        
	        ArrayList<SearchResultData> list= null;
	        SearchResultData dataObj;
	        for (int i = 0; i < keys.size(); i++) {
	        	list=tstObject.get(keys.get(i));
	        	if(list==null)
	        	{
	        		list= new ArrayList<SearchResultData>();
	        		list.add(new SearchResultData(pageName, keys.get(i), 1, null,pagePath));
	        	}
	        	else
	        	{
	        		boolean found=false;
	        		for(int j=0;j<list.size();j++)
	        		{
	        			dataObj =list.get(j);
	        			
	        			if(pageName.equals(dataObj.getPageName()))
	        			{
	        				int wordCount= dataObj.getWordCount();
	        				dataObj.setWordCount(wordCount+1);
	        				found=true;
	                		
	        			}
	        			
	        		}
	        		
	        		if(!found)
	        		{
	        			list.add(new SearchResultData(pageName, keys.get(i), 1, null,pagePath));
	        		}
	        	}
	        	
	        	
	        	tstObject.put(keys.get(i), list);
	        }

			/*
			 * 
			 * // print value of a key String key = "shells";
			 * StdOut.println("key = shells, value = "+ tstObject.get(key));
			 */
	    }
}
