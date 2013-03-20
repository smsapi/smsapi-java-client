package pl.smsapi.message;

import java.util.HashMap;

public final class Template {

	private String name, param1, param2, param3, param4;
	private HashMap<String, String> params;

	public Template(HashMap<String, String> params, String name) {
		this.name = name;
		this.params = params;
		params.put("template", name);
		params.put("message", "");
	}

	public String getParam1() {
		return param1;
	}

	public Template setParam1(String param1) {
		this.param1 = param1;
		params.put("param1", param1);
		return this;
	}

	public String getParam2() {
		return param2;
	}

	public Template setParam2(String param2) {
		this.param2 = param2;
		params.put("param2", param2);
		return this;
	}

	public String getParam3() {
		return param3;
	}

	public Template setParam3(String param3) {
		this.param3 = param3;
		params.put("param3", param3);
		return this;
	}

	public String getParam4() {
		return param4;
	}

	public Template setParam4(String param4) {
		this.param4 = param4;
		params.put("param4", param4);
		return this;
	}

	public String getQuery() {
		return "template=" + name + "&param1=" + param1 + "&param2=" + param2 + "&param3=" + param3 + "&param4=" + param4;
	}
}
