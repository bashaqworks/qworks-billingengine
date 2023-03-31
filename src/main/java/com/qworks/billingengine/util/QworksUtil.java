package com.qworks.billingengine.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class QworksUtil {

	public static Set<String> getSheetProductIds(Map<String, Object> sheetInfo) {
		Set<String> prList = new HashSet<>();
		Object lines = sheetInfo.get("lineItems");
		List<Map<String, Object>> lineItems = JsonUtils.convert(lines, List.class);
		for (Map<String, Object> map : lineItems) {
			prList.add(map.get("productId").toString());
		}
		return prList;
	}

	public static Set<String> getProductIds(List<Map<String, Object>> prdList) {
		Set<String> prList = new HashSet<>();
		for (Map<String, Object> map : prdList) {
			prList.add(map.get("id").toString());
		}
		return prList;
	}

	public static Optional<String> getValueFromMap(Map<String, Object> action, String key) {
		return action.containsKey(key) ? Optional.of(String.valueOf(action.get(key))) : Optional.empty();
	}
	
	
	
//	public static void createRuleExecutionStatus(PublishMessage publishMessage, Map<String, Map<String, Object>> dbRule) {
//		List<Object> actions = new ArrayList<>();
//		actions.add(publishMessage.getAction());
//		Map<String, List<String>> intents = new HashMap<>();
//		intents.put(publishMessage.getActionIntent(), publishMessage.getProductIds());
//		Map<String, Object> rule = new HashMap<>();
//		rule.put("ruleId", publishMessage.getRuleId());
//		rule.put("ruleName", publishMessage.getRuleName());
//		rule.put("ruleActions", actions);
//		rule.put("actionIntents", intents);
//		if(publishMessage.getActionSize()==1) {
//			rule.put("ruleExecutionStatus", RuleStatus.COMPLETED.name());
//		}
//		else {
//			rule.put("ruleExecutionStatus", RuleStatus.INPROGRESS.name());
//		}
//		
//		dbRule.put(publishMessage.getRuleId(), rule);
//	}
//
//	public static void updateExecutionStatus(PublishMessage publishMessage, Map<String, Map<String, Object>> dbRule,
//			Map<String, Object> rule) {
//		List<Map<String, Object>> ruleActions = JsonUtils.convert(rule.get("ruleActions"), List.class);
//		//Update previous transaction status
//		List<Map<String, Object>> updatedActions  =  new ArrayList<>();
//		for (Map<String, Object> map : ruleActions) {
//			map.put("status", RuleStatus.EXECUTED.name());
//			updatedActions.add(map);
//		}
//		updatedActions.add(publishMessage.getAction());
//		Map<String, Set<String>> intents = JsonUtils.convert(rule.get("actionIntents"), Map.class);
//		if (intents.containsKey(publishMessage.getActionIntent().toUpperCase())) {
//			List<String> intent = (List<String>) intents.get(publishMessage.getActionIntent().toUpperCase());
//			intent.addAll(new HashSet<String> (publishMessage.getProductIds()));
//			intents.put(publishMessage.getActionIntent(), new HashSet<String> (intent));
//		} else {
//			intents.put(publishMessage.getActionIntent(), new HashSet<String> (publishMessage.getProductIds()));
//		}
//		rule.put("ruleActions", updatedActions);
//		rule.put("actionIntents", intents);
//		if(publishMessage.getActionSize()==ruleActions.size()) {
//			rule.put("ruleExecutionStatus", RuleStatus.COMPLETED.name());
//		}
//		else {
//			rule.put("ruleExecutionStatus", RuleStatus.INPROGRESS.name());
//		}
//		dbRule.put(publishMessage.getRuleId(), rule);
//	}

	/**
	 * @param prdList
	 * @return
	 */
	public static Map<String, Map<String, String>> getProductIdAndName(List<Map<String, Object>> prdList) {
		Map<String, Map<String, String>> prdMap = new HashMap<>();
		for (Map<String, Object> map : prdList) {
			Map<String, String> prd = new HashMap<>();
			prd.put("productId",  map.get("id").toString());
			prd.put("productName", map.get("productName").toString());
			prdMap.put(map.get("id").toString(), prd);
		}
		return prdMap;
	}
}
