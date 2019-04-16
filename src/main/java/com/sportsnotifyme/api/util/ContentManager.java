package com.sportsnotifyme.api.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContentManager {

	public static String transfer(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"title\": \"SportsNotifyMe Live NBA Updates\",\n");
		sb.append("\"description\": \"Your app fetched this from a remote endpoint!\",\n");
		sb.append("\"games\": ");
		content = content.replaceAll("%20", " ");
		content = content.replaceAll("\\^", "");
		Pattern pattern = Pattern
				.compile("(nba_s_left.*?=(.*?)\\((.*?)\\).*?&nba_s_right.*?nba_s_url.*?=(.*?gameId=(.*?)&))");
		Matcher matcher = pattern.matcher(content);
		JSONArray jsonArray = new JSONArray();
		while (matcher.find()) {
			JSONObject jsonObject = new JSONObject();
			String title = matcher.group(2);
			if (title.toLowerCase().contains(" at ")) {
				String[] tokens = Arrays.asList(title.split(" at ")).stream().filter(str -> !str.isEmpty())
						.collect(Collectors.toList()).toArray(new String[0]);
				jsonObject.put("away", tokens[0].replaceAll("[-.:?,+]+(?!\\w)", ""));
				jsonObject.put("home", tokens[1].replaceAll("[-.:?,+]+(?!\\w)", ""));
				jsonObject.put("live", false);
			} else {
				Pattern tPattern = Pattern.compile("(.*?)([0-9].*?)([^0-9].*?)([0-9].*?[^0-9])");
				Matcher tMatcher = tPattern.matcher(title);
				if (tMatcher.find()) {
					jsonObject.put("away", tMatcher.group(1).trim());
					jsonObject.put("home", tMatcher.group(3).trim());
					jsonObject.put("away_score", tMatcher.group(2).trim());
					jsonObject.put("home_score", tMatcher.group(4).trim());
				}

				jsonObject.put("live", true);
			}
			jsonObject.put("time", matcher.group(3));
			jsonArray.put(jsonObject);
		}
		sb.append(jsonArray.toString());
		sb.append("}");
		return sb.toString();

	}

}
