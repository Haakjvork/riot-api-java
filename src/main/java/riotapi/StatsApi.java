package main.java.riotapi;

/*
 * Copyright 2014 Taylor Caldwell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import constant.Region;
import constant.Season;
import dto.Stats.PlayerStatsSummaryList;
import dto.Stats.RankedStats;

final class StatsApi {

	private static final String VERSION = "/v1.3/";

	public static PlayerStatsSummaryList getPlayerStatsSummary(Region region, Season season, String key, long summonerId) throws RiotApiException {
		String url = region.getEndpoint() + VERSION + "stats/by-summoner/" + summonerId + "/summary?";
		if (season != null) {
			url += "season=" + season + "&";
		}
		url += "api_key=" + key;

		PlayerStatsSummaryList summaryList = null;
		try {
			summaryList = new Gson().fromJson(Request.execute(url), PlayerStatsSummaryList.class);
		} catch (JsonSyntaxException e) {
			throw new RiotApiException(RiotApiException.PARSE_FAILURE);
		}
		if (summaryList == null) {
			throw new RiotApiException(RiotApiException.PARSE_FAILURE);
		}

		return summaryList;
	}

	public static RankedStats getRankedStats(Region region, Season season, String key, long summonerId) throws RiotApiException {
		String url = region.getEndpoint() + VERSION + "stats/by-summoner/" + summonerId + "/ranked?";
		if (season != null) {
			url += "season=" + season + "&";
		}
		url += "api_key=" + key;

		RankedStats rankedStats = null;
		try {
			rankedStats = new Gson().fromJson(Request.execute(url), RankedStats.class);
		} catch (JsonSyntaxException e) {
			throw new RiotApiException(RiotApiException.PARSE_FAILURE);
		}
		if (rankedStats == null) {
			throw new RiotApiException(RiotApiException.PARSE_FAILURE);
		}

		return rankedStats;
	}
}