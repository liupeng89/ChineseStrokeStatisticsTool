package model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.layout.BorderWidths;

public class QueryCharFromXML {
	
	public static ChineseCharacter queryNodeList(NodeList nodeList, String condition) throws ParserConfigurationException, SAXException, IOException {
		ChineseCharacter chObj = null;
		
		// find the char basd on the TAG
		Element resultElement = null;
		for (int i=0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String tag = element.getAttribute("TAG");
					
			if (tag.equals(condition)) {
				resultElement = (Element) nodeList.item(i);
				break;
			}
		}
				
		if(resultElement == null) {
			return chObj;
		}
				
		// ID
		String id = resultElement.getAttribute("ID");
		// TAG
		String tag = resultElement.getAttribute("TAG");
							
		// type
		String type = "";
		if (resultElement.getElementsByTagName("TYPE") != null && resultElement.getElementsByTagName("TYPE").item(0) != null) {
			type = resultElement.getElementsByTagName("TYPE").item(0).getTextContent();
		}
							
		// structure
		String structure = "";
		if (resultElement.getElementsByTagName("STRUCTURE") != null && resultElement.getElementsByTagName("STRUCTURE").item(0) != null) {
			structure = resultElement.getElementsByTagName("STRUCTURE").item(0).getTextContent();
		}
							
		// key radical
		String keyRadical = "";
		if(resultElement.getElementsByTagName("KEY_RADICAL") != null && resultElement.getElementsByTagName("KEY_RADICAL").item(0) != null) {
			keyRadical = resultElement.getElementsByTagName("KEY_RADICAL").item(0).getTextContent();
		}
							
		// STROKE_COUNT
		String strokeCount = "";
		if (resultElement.getElementsByTagName("STROKE_COUNT") != null && resultElement.getElementsByTagName("STROKE_COUNT").item(0) != null) {
			strokeCount = resultElement.getElementsByTagName("STROKE_COUNT").item(0).getTextContent();
		}
				
		// Stroke order
		String strokeOrder = "";
		if (resultElement.getElementsByTagName("STROKE_ORDER") != null && resultElement.getElementsByTagName("STROKE_ORDER").item(0) != null) {
			strokeOrder = resultElement.getElementsByTagName("STROKE_ORDER").item(0).getTextContent();
		}
		
		// Sub radicals
		List<String> subRadicals = new ArrayList<>();
		if (resultElement.getElementsByTagName("SUB_RADICAL") != null) {
			NodeList sub_elem_nodes = resultElement.getElementsByTagName("SUB_RADICAL");
			for (int j=0; j< sub_elem_nodes.getLength(); j++) {
				Element sub_elem = (Element) sub_elem_nodes.item(j);				
				String sub_tag = sub_elem.getAttribute("TAG");
				subRadicals.add(sub_tag);
			}
		}
							
		// Generate the chinese character objects
		chObj = new ChineseCharacter(id, tag, type, structure, keyRadical, strokeCount, strokeOrder, subRadicals);
				
		return chObj;
	}
	
	public static ChineseCharacter query(String condition) throws ParserConfigurationException, SAXException, IOException {
		ChineseCharacter chObj = null;
	
		File xmlFile = new File("/Users/liupeng/Documents/Data/Characters/radical_add_stroke_position_similar_structure_add_stroke_order.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("RADICAL");
		
		// find the char basd on the TAG
		Element resultElement = null;
		for (int i=0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String tag = element.getAttribute("TAG");
			
			if (tag.equals(condition)) {
				System.out.println(tag);
				resultElement = (Element) nodeList.item(i);
				break;
			}
		}
		
		if(resultElement == null) {
			return chObj;
		}
		
		// ID
		String id = resultElement.getAttribute("ID");
		// TAG
		String tag = resultElement.getAttribute("TAG");
					
		// type
		String type = "";
		if (resultElement.getElementsByTagName("TYPE") != null && resultElement.getElementsByTagName("TYPE").item(0) != null) {
			type = resultElement.getElementsByTagName("TYPE").item(0).getTextContent();
		}
					
		// structure
		String structure = "";
		if (resultElement.getElementsByTagName("STRUCTURE") != null && resultElement.getElementsByTagName("STRUCTURE").item(0) != null) {
			structure = resultElement.getElementsByTagName("STRUCTURE").item(0).getTextContent();
		}
					
		// key radical
		String keyRadical = "";
		if(resultElement.getElementsByTagName("KEY_RADICAL") != null && resultElement.getElementsByTagName("KEY_RADICAL").item(0) != null) {
			keyRadical = resultElement.getElementsByTagName("KEY_RADICAL").item(0).getTextContent();
		}
					
		// STROKE_COUNT
		String strokeCount = "";
		if (resultElement.getElementsByTagName("STROKE_COUNT") != null && resultElement.getElementsByTagName("STROKE_COUNT").item(0) != null) {
			strokeCount = resultElement.getElementsByTagName("STROKE_COUNT").item(0).getTextContent();
		}
		
		// Stroke order
		String strokeOrder = "";
		if (resultElement.getElementsByTagName("STROKE_ORDER") != null && resultElement.getElementsByTagName("STROKE_ORDER").item(0) != null) {
			strokeOrder = resultElement.getElementsByTagName("STROKE_ORDER").item(0).getTextContent();
		}
		
		// Sub radicals
		List<String> subRadicals = new ArrayList<>();
		if (resultElement.getElementsByTagName("SUB_RADICAL") != null) {
			NodeList sub_elem_nodes = resultElement.getElementsByTagName("SUB_RADICAL");
			for (int j=0; j< sub_elem_nodes.getLength(); j++) {
				Element sub_elem = (Element) sub_elem_nodes.item(j);				
				String sub_tag = sub_elem.getAttribute("TAG");
				subRadicals.add(sub_tag);
			}
		}
					
		// Generate the chinese character objects
		chObj = new ChineseCharacter(id, tag, type, structure, keyRadical, strokeCount, strokeOrder, subRadicals);
		
		return chObj;
	}
	
	public static List<Set<String>> queyTotalInformation() throws SAXException, IOException, ParserConfigurationException{
		List<Set<String>> result = new ArrayList<>();
		
		File xmlFile = new File("/Users/liupeng/Documents/Data/Characters/radical_add_stroke_position_similar_structure_add_stroke_order.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("RADICAL");
		
		// Search total strokes, key_radicals, structures
		Set<String> s1_strokes = new HashSet<>();
		s1_strokes.add("横"); s1_strokes.add("竖"); s1_strokes.add("撇");
		s1_strokes.add("捺"); s1_strokes.add("点");
		
		Set<String> s2_strokes = new HashSet<>();
		s2_strokes.add("卧钩"); s2_strokes.add("横折横");
		s2_strokes.add("竖弯钩"); s2_strokes.add("弯钩");
		s2_strokes.add("横折横折"); s2_strokes.add("横勾");
		s2_strokes.add("竖折"); s2_strokes.add("横折钩");
		s2_strokes.add("横折"); s2_strokes.add("竖折折钩");
		s2_strokes.add("提"); s2_strokes.add("横撇");
		s2_strokes.add("横折弯钩"); s2_strokes.add("竖折撇");
		s2_strokes.add("横撇弯钩"); s2_strokes.add("横折折折钩");
		s2_strokes.add("竖折竖"); s2_strokes.add("撇折");
		s2_strokes.add("横折折撇"); s2_strokes.add("竖提");
		s2_strokes.add("撇点"); s2_strokes.add("横折提");
		s2_strokes.add("竖钩"); s2_strokes.add("斜钩");
		s2_strokes.add("竖弯");
		
		// key radical
		Set<String> s3_all_key_radicals = new HashSet<>();
		Set<String> s4_all_basic_sub_radical = new HashSet<>();
		Set<String> s5_all_single_chars = new HashSet<>();
		Set<String> s6_all_top_down_chars = new HashSet<>();
		Set<String> s7_all_top_middle_down_chars = new HashSet<>();
		Set<String> s8_all_left_right_chars = new HashSet<>();
		Set<String> s9_all_left_middle_down_chars = new HashSet<>();
		Set<String> s10_all_left_top_right_surround_chars = new HashSet<>();
		Set<String> s11_all_left_top_surround_chars = new HashSet<>();
		Set<String> s12_all_left_down_surround_chars = new HashSet<>();
		Set<String> s13_all_right_top_surround_chars = new HashSet<>();
		Set<String> s14_all_4_sides_surround_chars = new HashSet<>();
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String tag = element.getAttribute("TAG");
			
			if (tag.length() > 1) {
				continue;
			}
			
			String keyRadical = "";
			if(element.getElementsByTagName("KEY_RADICAL") != null && element.getElementsByTagName("KEY_RADICAL").item(0) != null) {
				keyRadical = element.getElementsByTagName("KEY_RADICAL").item(0).getTextContent();
				if (keyRadical != "") {
					s3_all_key_radicals.add(keyRadical);
				}
			}
			
			// basic sub radical
			if (element.getElementsByTagName("SUB_RADICAL") != null) {
				NodeList sub_elem_nodes = element.getElementsByTagName("SUB_RADICAL");
				for (int j=0; j< sub_elem_nodes.getLength(); j++) {
					Element sub_elem = (Element) sub_elem_nodes.item(j);
					String sub_tag = sub_elem.getAttribute("TAG");
					s4_all_basic_sub_radical.add(sub_tag);	
				}
			} 
			
			// structure info
			String structure = "";
			if(element.getElementsByTagName("STRUCTURE") != null && element.getElementsByTagName("STRUCTURE").item(0) != null) {
				structure = element.getElementsByTagName("STRUCTURE").item(0).getTextContent();
				
				switch (structure) {
				case "Single Character":
					s5_all_single_chars.add(tag);		
					break;
				case "Up and Down":
					s6_all_top_down_chars.add(tag);
					break;
				case "Up, Middle and Down":
					s7_all_top_middle_down_chars.add(tag);
					break;
				case "Left and Right":
					s8_all_left_right_chars.add(tag);
					break;
				case "Left, Middle and Right":
					s9_all_left_middle_down_chars.add(tag);
					break;
				case "Left Top Right surrounded":
					s10_all_left_top_right_surround_chars.add(tag);
					break;
				case "Left Top surrounded":
					s11_all_left_top_surround_chars.add(tag);
					break;
				case "Left Bottom surrounded":
					s12_all_left_down_surround_chars.add(tag);
					break;
				case "Right Top surrounded":
					s13_all_right_top_surround_chars.add(tag);
					break;
				case "4 sides surrounded":
					s14_all_4_sides_surround_chars.add(tag);
					break;

				default:
					break;
				}
			}
		}

		result.add(s1_strokes);
		result.add(s2_strokes);
		result.add(s3_all_key_radicals);
		result.add(s4_all_basic_sub_radical);
		
		result.add(s5_all_single_chars);
		result.add(s6_all_top_down_chars);
		result.add(s7_all_top_middle_down_chars);
		result.add(s8_all_left_right_chars);
		result.add(s9_all_left_middle_down_chars);
		result.add(s10_all_left_top_right_surround_chars);
		result.add(s11_all_left_top_surround_chars);
		result.add(s12_all_left_down_surround_chars);
		result.add(s13_all_right_top_surround_chars);
		result.add(s14_all_4_sides_surround_chars);
		
		return result;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
//		ChineseCharacter result = QueryCharFromXML.query("网");
//		System.out.println(result.getStrokeOrder());
//		
//		List<Map<String, String[]>> similarRadicalList = result.getSubRadicals();
//		System.out.println("List len: " + similarRadicalList.size());
//		
//		for (int i = 0; i < similarRadicalList.size(); i++) {
//			Map<String, String[]> similarRadicalMap = similarRadicalList.get(i);
//			System.out.println(similarRadicalMap.keySet().toArray()[0]);
//		}
		
//		String result = queryImagePath("网");
//		if (result == "") {
//			System.out.println("not find image");
//		}
//		System.out.println(result);
//		
//		File file = new File(result);
//		System.out.println(file);
		
//		List<Set<String>> result = QueryCharFromXML.queyTotalInformation();
//		System.out.println(result);
		
		String string = "竖|横折|撇|点|横";
		String[] strings = string.split("\\|");
		System.out.println(strings);
		
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}


	}

}
