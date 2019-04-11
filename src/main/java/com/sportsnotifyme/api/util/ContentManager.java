package com.sportsnotifyme.api.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContentManager {

	public static String transfer(String content) {
		//JSONObject json = new JSONObject();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n");
	    sb.append("\"title\": \"The Basics - Networking\",\n");
	    sb.append("\"description\": \"Your app fetched this from a remote endpoint!\",\n");
	    sb.append("\"games\": ");
	    //content = content.replaceAll("%20", " ");
	    content = "&nba_s_delay=120&nba_s_stamp=0411084323&nba_s_left1=Indiana 135   Atlanta 134 (0:00 IN 4TH)&nba_s_right1_1=T. Leaf 28pts, 2ast, 10reb&nba_s_right1_2=J. Collins 20pts, 6ast, 25reb&nba_s_right1_3=K. Huerter 5-6 three pointers&nba_s_right1_count=3&nba_s_url1=http://www.espn.com/nba/boxscore?gameId=401071893&nba_s_left2=Miami 94   ^Brooklyn 113 (FINAL)&nba_s_right2_1=D. Wade 25pts, 10ast, 11reb&nba_s_right2_2=D. Russell 21pts, 5ast, 7reb&nba_s_right2_3=D. Russell 7-11 three pointers&nba_s_right2_count=3&nba_s_url2=http://www.espn.com/nba/boxscore?gameId=401071894&nba_s_left3=^Orlando 122   Charlotte 114 (FINAL)&nba_s_right3_1=T. Ross 35pts, 2ast, 6reb&nba_s_right3_2=K. Walker 43pts, 5ast, 2reb&nba_s_right3_3=T. Ross 6-10 three pointers&nba_s_right3_count=3&nba_s_url3=http://www.espn.com/nba/boxscore?gameId=401071895&nba_s_left4=Detroit 115   New York 89 (END OF 4TH)&nba_s_right4_1=A. Drummond 20pts, 3ast, 18reb&nba_s_right4_2=K. Allen 13pts, 8ast, 5reb&nba_s_right4_count=2&nba_s_url4=http://www.espn.com/nba/boxscore?gameId=401071896&nba_s_left5=Chicago 109   ^Philadelphia 125 (FINAL)&nba_s_right5_1=W. Lemon Jr. 20pts, 4ast, 6reb&nba_s_right5_2=B. Marjanovic 18pts, 6ast, 8reb&nba_s_right5_count=2&nba_s_url5=http://www.espn.com/nba/boxscore?gameId=401071897&nba_s_left6=Golden State 117   ^Memphis 132 (FINAL)&nba_s_right6_1=K. Durant 21pts, 6ast, 0reb&nba_s_right6_2=D. Wright 13pts, 11ast, 11reb&nba_s_right6_3=J. Carter 8-12 three pointers&nba_s_right6_count=3&nba_s_url6=http://www.espn.com/nba/boxscore?gameId=401071898&nba_s_left7=^Oklahoma City 127   Milwaukee 116 (FINAL)&nba_s_right7_1=R. Westbrook 15pts, 17ast, 11reb&nba_s_right7_2=T. Frazier 29pts, 13ast, 6reb&nba_s_right7_3=D. Schroder 8-15 three pointers&nba_s_right7_count=3&nba_s_url7=http://www.espn.com/nba/boxscore?gameId=401071899&nba_s_left8=Dallas 94   ^San Antonio 105 (FINAL)&nba_s_right8_1=D. Nowitzki 20pts, 1ast, 10reb&nba_s_right8_2=L. Aldridge 34pts, 1ast, 16reb&nba_s_right8_count=2&nba_s_url8=http://www.espn.com/nba/boxscore?gameId=401071900&nba_s_left9=Minnesota 95   ^Denver 99 (FINAL)&nba_s_right9_1=A. Wiggins 25pts, 4ast, 5reb&nba_s_right9_2=N. Jokic 29pts, 2ast, 14reb&nba_s_right9_3=C. Reynolds 5-7 three pointers&nba_s_right9_count=3&nba_s_url9=http://www.espn.com/nba/boxscore?gameId=401071901&nba_s_left10=Utah 137   ^LA 143 (FINAL - OT)&nba_s_right10_1=G. Allen 40pts, 4ast, 7reb&nba_s_right10_2=M. Harrell 24pts, 5ast, 7reb&nba_s_right10_3=G. Allen 5-13 three pointers&nba_s_right10_count=3&nba_s_url10=http://www.espn.com/nba/boxscore?gameId=401071902&nba_s_left11=Sacramento 131   ^Portland 136 (FINAL)&nba_s_right11_1=D. Fox 17pts, 9ast, 1reb&nba_s_right11_2=A. Simons 37pts, 9ast, 6reb&nba_s_right11_3=A. Simons 7-11 three pointers&nba_s_right11_count=3&nba_s_url11=http://www.espn.com/nba/boxscore?gameId=401071903&nba_s_count=11&nba_s_loaded=true\r\n";
	    content = content.replaceAll("\\^", "");
	    Pattern pattern = Pattern.compile("(nba_s_left.*?=(.*?)\\((.*?)\\).*?&nba_s_right.*?nba_s_url.*?=(.*?gameId=(.*?)&))");
	    Matcher matcher = pattern.matcher(content);
	    JSONArray jsonArray = new JSONArray();
	    while (matcher.find()) {
	    	JSONObject jsonObject = new JSONObject();
	    	String title = matcher.group(2);
	    	if(title.toLowerCase().contains(" at ")) {
	    		String[] tokens = Arrays.asList(title.split(" at ")).stream().filter(str -> !str.isEmpty()).collect(Collectors.toList()).toArray(new String[0]);
	    		jsonObject.put("team1", tokens[0].replaceAll("[-.:?,+]+(?!\\w)", ""));
	    		jsonObject.put("team2", tokens[1].replaceAll("[-.:?,+]+(?!\\w)", ""));
	    		jsonObject.put("live", false);
	    	}
	    	else {
	    		Pattern tPattern = Pattern.compile("(.*?)([0-9].*?)([^0-9].*?)([0-9].*?[^0-9])");
	    	    Matcher tMatcher = tPattern.matcher(title);
	    	    if(tMatcher.find()) {
	    	    	jsonObject.put("team1", tMatcher.group(1).trim());
		    		jsonObject.put("team2", tMatcher.group(3).trim());
		    		jsonObject.put("team1_score", tMatcher.group(2).trim());
		    		jsonObject.put("team2_score", tMatcher.group(4).trim());
	    	    }
	    		
	    		jsonObject.put("live", true);
	    	}
	    	jsonObject.put("title", title);
	    	jsonObject.put("time", matcher.group(3));
	    	jsonObject.put("url", matcher.group(4));
	    	jsonObject.put("gameid", matcher.group(5));
			jsonArray.put(jsonObject);
	    }
	  
	    
		
		/*
		 * String games[] = content.split("&nba_s_left"); for(int i = 0; i <
		 * games.length; i++) { String gameInfo =
		 * games[i].split("&nba_s_right")[0].replaceAll("%20", " ");
		 * if(gameInfo.contains("delay")) { continue; }
		 * 
		 * 
		 * }
		 */
		
		sb.append(jsonArray.toString());
		sb.append("}");
		return sb.toString();

	}

}
