package model;

import java.util.List;

public class ChineseCharacter {
	
	private String id = "";
	private String tag = "";
	private String type = "";
	private String structure = "";
	private String keyRadical = "";
	private String strokeCount = "";
	private String strokeOrder = "";
	
	private List<String> subRadicals;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ChineseCharacter(String id, String tag, String type, String structure, String keyRadical, String strokeCount,
			String strokeOrder, List<String> subRadicals) {
		super();
		this.id = id;
		this.tag = tag;
		this.type = type;
		this.structure = structure;
		this.keyRadical = keyRadical;
		this.strokeCount = strokeCount;
		this.strokeOrder = strokeOrder;
		this.subRadicals = subRadicals;
	}
	

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getKeyRadical() {
		return keyRadical;
	}

	public void setKeyRadical(String keyRadical) {
		this.keyRadical = keyRadical;
	}

	public String getStrokeCount() {
		return strokeCount;
	}

	public void setStrokeCount(String strokeCount) {
		this.strokeCount = strokeCount;
	}

	public String getStrokeOrder() {
		return strokeOrder;
	}

	public void setStrokeOrder(String strokeOrder) {
		this.strokeOrder = strokeOrder;
	}

	public List<String> getSubRadicals() {
		return subRadicals;
	}

	public void setSubRadicals(List<String> subRadicals) {
		this.subRadicals = subRadicals;
	}

	
	
	
	
	
	
	

}
