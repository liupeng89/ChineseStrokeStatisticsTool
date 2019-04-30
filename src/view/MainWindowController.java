package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import model.ChineseCharacter;
import model.QueryCharFromXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainWindowController implements Initializable {
	
	private Main mainApp;
	
	private String inputText;
	
	private List<Set<String>> totalInfo;
	
	private NodeList nodeList;
	
	private Set<String> input_s1_set;
	private Set<String> input_s2_set;
	private Set<String> input_s3_set;
	private Set<String> input_s4_set;
	
	public Set<String> input_s5_set = new HashSet<>();
	public Set<String> input_s6_set = new HashSet<>();
	public Set<String> input_s7_set = new HashSet<>();
	public Set<String> input_s8_set = new HashSet<>();
	public Set<String> input_s9_set = new HashSet<>();
	public Set<String> input_s10_set = new HashSet<>();
	public Set<String> input_s11_set = new HashSet<>();
	public Set<String> input_s12_set = new HashSet<>();
	public Set<String> input_s13_set = new HashSet<>();
	public Set<String> input_s14_set = new HashSet<>();
	
	
	
	@FXML
	private TextArea inputTextArea;
	
	@FXML
	private Button statisticsBtn;
	
	@FXML
	private ProgressBar s1_Prograssbar;
	
	@FXML
	private ProgressBar s2_Prograssbar;
	
	@FXML
	private ProgressBar s3_Prograssbar;
	
	@FXML
	private ProgressBar s4_Prograssbar;
	
	@FXML
	private ProgressBar s5_Prograssbar;
	
	@FXML
	private ProgressBar s6_Prograssbar;
	
	@FXML
	private ProgressBar s7_Prograssbar;
	
	@FXML
	private ProgressBar s8_Prograssbar;
	
	@FXML
	private ProgressBar s9_Prograssbar;
	
	@FXML
	private ProgressBar s10_Prograssbar;
	
	@FXML
	private ProgressBar s11_Prograssbar;
	
	@FXML
	private ProgressBar s12_Prograssbar;
	
	@FXML
	private ProgressBar s13_Prograssbar;
	
	@FXML
	private ProgressBar s14_Prograssbar;
	
	@FXML
	private Label s1_label;
	@FXML
	private Label s2_label;
	@FXML
	private Label s3_label;
	@FXML
	private Label s4_label;
	@FXML
	private Label s5_label;
	@FXML
	private Label s6_label;
	@FXML
	private Label s7_label;
	@FXML
	private Label s8_label;
	@FXML
	private Label s9_label;
	@FXML
	private Label s10_label;
	@FXML
	private Label s11_label;
	@FXML
	private Label s12_label;
	@FXML
	private Label s13_label;
	@FXML
	private Label s14_label;
	
	public Tooltip s1_tooltip = new Tooltip();
	public Tooltip s2_tooltip = new Tooltip();
	public Tooltip s3_tooltip = new Tooltip();
	public Tooltip s4_tooltip = new Tooltip();
	public Tooltip s5_tooltip = new Tooltip();
	public Tooltip s6_tooltip = new Tooltip();
	public Tooltip s7_tooltip = new Tooltip();
	public Tooltip s8_tooltip = new Tooltip();
	public Tooltip s9_tooltip = new Tooltip();
	public Tooltip s10_tooltip = new Tooltip();
	public Tooltip s11_tooltip = new Tooltip();
	public Tooltip s12_tooltip = new Tooltip();
	public Tooltip s13_tooltip = new Tooltip();
	public Tooltip s14_tooltip = new Tooltip();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		try {
			List<Set<String>> result = QueryCharFromXML.queyTotalInformation();
			this.setTotalInfo(result);
			
			// Load XML data
			File xmlFile = new File("/Users/liupeng/Documents/Data/Characters/radical_add_stroke_position_similar_structure_add_stroke_order.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("RADICAL");
			this.setNodeList(nodeList);
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	protected void handleStatisticsButtonAction(ActionEvent event) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("Statistics Button Clicked!");
		
		if (this.nodeList  == null || this.nodeList.getLength() == 0) {
			System.out.println("Nodelist of XML is null!");
			return;
		}
		
		// Parse the input content.
		String text = this.inputTextArea.getText();
		
		// Remove invalid characters from text
		//[a1] by bianqn 2019-04-30
		StringBuilder sb = new StringBuilder(text);
		for(int i=0;i<sb.length();i++) {
			String regex = "[\\u4e00-\\u9fa5]+";
			if (!String.valueOf(sb.charAt(i)).matches(regex)) {
				System.out.println(sb.charAt(i));
				sb.replace(i,i+1, "");
				if (1!=0) {
					i--;
				}
			}
		}
		text = sb.toString();
		//[/a1]
		text = text.replace(" ", "").replace("\t", "").replace("\n", "");
	
		
		// search stroke order for input characters
		Set<String> inputStrokes = new HashSet<>();
		Set<String> inputKeyRadicals = new HashSet<>();
		Set<String> inputStructures = new HashSet<>();
		Set<String> inputSubRadicals = new HashSet<>();
		
		// Structure information
		int s5Num = 0; int s6Num = 0; int s7Num = 0; int s8Num = 0;
		int s9Num = 0; int s10Num = 0; int s11Num = 0; int s12Num = 0;
		int s13Num = 0; int s14Num = 0;
		
		for(int i = 0; i < text.length(); i++) {
			String tag = Character.toString(text.charAt(i));
			ChineseCharacter charObj = QueryCharFromXML.queryNodeList(this.nodeList, tag);
			
			// process stroke order 
			String strokeOrder = charObj.getStrokeOrder();
			String[] strokes = strokeOrder.split("\\|");
			for (String s: strokes) {
				inputStrokes.add(s);
			}
			
			// process key radical
			inputKeyRadicals.add(charObj.getKeyRadical());
			
			//Structure information
			String structure = charObj.getStructure();
			System.out.println(structure);
			
			switch (structure) {
			case "Single Character":
				s5Num += 1;
				input_s5_set.add(tag);
				break;
			case "Up and Down":
				s6Num += 1;
				input_s6_set.add(tag);
				break;
			case "Up, Middle and Down":
				s7Num += 1;
				input_s7_set.add(tag);
				break;
			case "Left and Right":
				s8Num += 1;
				input_s8_set.add(tag);
				break;
			case "Left, Middle and Right":
				s9Num += 1;
				input_s9_set.add(tag);
				break;
			case "Left Top Right surrounded":
				s10Num += 1;
				input_s10_set.add(tag);
				break;
			case "Left Top surrounded":
				s11Num += 1;
				input_s11_set.add(tag);
				break;
			case "Left Bottom surrounded":
				s12Num += 1;
				input_s12_set.add(tag);
				break;
			case "Right Top surrounded":
				s13Num += 1;
				input_s13_set.add(tag);
				break;
			case "4 sides surrounded":
				s14Num += 1;
				input_s14_set.add(tag);
				break;

			default:
				break;
			}
			
			// sub radicals
			if (charObj.getSubRadicals() != null) {
				for (String s: charObj.getSubRadicals()) {
					inputSubRadicals.add(s);
				}
			}
		}
		
		// s1
		Set<String> s1_strokes = this.getTotalInfo().get(0);
		List<String> inputS1Strokes = new ArrayList<>();
		int s1_num = 0;
		for(String s: s1_strokes) {
			if (inputStrokes.contains(s)) {
				s1_num += 1;
				inputS1Strokes.add(s);
			}
		}
		s1_Prograssbar.setProgress(1.0 * s1_num / s1_strokes.size());
		s1_label.setText("( " + s1_num + " \\ " + s1_strokes.size() + " )");
		s1_tooltip.setText(inputS1Strokes.toString());
		s1_Prograssbar.setTooltip(s1_tooltip);
		
		// s2
		Set<String> s2_strokes = this.getTotalInfo().get(1);
		List<String> inputS2Strokes = new ArrayList<>();
		int s2_num = 0;
		for (String s: s2_strokes) {
			if (inputStrokes.contains(s)) {
				s2_num += 1;
				inputS2Strokes.add(s);
			}
		}
		s2_Prograssbar.setProgress(1.0 * s2_num / s2_strokes.size());
		s2_label.setText("( " + s2_num + " \\ " + s2_strokes.size() + " )");
		s2_tooltip.setText(inputS2Strokes.toString());
		s2_Prograssbar.setTooltip(s2_tooltip);
		
		// s3
		Set<String> s3KeyRadicals = this.getTotalInfo().get(2);
		int s3Num = 0;
		for(String s: s3KeyRadicals) {
			if(inputKeyRadicals.contains(s)) {
				s3Num += 1;
			}
		}
		s3_Prograssbar.setProgress(1.0 * s3Num / s3KeyRadicals.size());
		s3_label.setText("( " + s3Num + " \\ " + s3KeyRadicals.size() + " )");
		s3_tooltip.setText(s3KeyRadicals.toString());
		s3_Prograssbar.setTooltip(s3_tooltip);
		
		// s4 sub radicals
		s4_Prograssbar.setProgress(1.0 * inputSubRadicals.size() / this.getTotalInfo().get(3).size());
		s4_label.setText("( " + inputSubRadicals.size() + " \\ " + this.getTotalInfo().get(3).size() + " )");
		s4_tooltip.setText(inputSubRadicals.toString());
		s4_Prograssbar.setTooltip(s4_tooltip);
		
		// set prograss bar values
		s5_Prograssbar.setProgress(1.0 * s5Num / this.getTotalInfo().get(4).size());
		s5_label.setText("( " + s5Num + " \\ " + this.getTotalInfo().get(4).size() + " )");
		s5_tooltip.setText(input_s5_set.toString());
		s5_Prograssbar.setTooltip(s5_tooltip);
		
		s6_Prograssbar.setProgress(1.0 * s6Num / this.getTotalInfo().get(5).size());
		s6_label.setText("( " + s6Num + " \\ " + this.getTotalInfo().get(5).size() + " )");
		s6_tooltip.setText(input_s6_set.toString());
		s6_Prograssbar.setTooltip(s6_tooltip);
		
		s7_Prograssbar.setProgress(1.0 * s7Num / this.getTotalInfo().get(6).size());
		s7_label.setText("( " + s7Num + " \\ " + this.getTotalInfo().get(6).size() + " )");
		s7_tooltip.setText(input_s7_set.toString());
		s7_Prograssbar.setTooltip(s7_tooltip);
		
		s8_Prograssbar.setProgress(1.0 * s8Num / this.getTotalInfo().get(7).size());
		s8_label.setText("( " + s8Num + " \\ " + this.getTotalInfo().get(7).size() + " )");
		s8_tooltip.setText(input_s8_set.toString());
		s8_Prograssbar.setTooltip(s8_tooltip);
		
		s9_Prograssbar.setProgress(1.0 * s9Num / this.getTotalInfo().get(8).size());
		s9_label.setText("( " + s9Num + " \\ " + this.getTotalInfo().get(8).size() + " )");
		s9_tooltip.setText(input_s9_set.toString());
		s9_Prograssbar.setTooltip(s9_tooltip);
		
		s10_Prograssbar.setProgress(1.0 * s10Num / this.getTotalInfo().get(9).size());
		s10_label.setText("( " + s10Num + " \\ " + this.getTotalInfo().get(9).size() + " )");
		s10_tooltip.setText(input_s10_set.toString());
		s10_Prograssbar.setTooltip(s10_tooltip);
		
		s11_Prograssbar.setProgress(1.0 * s11Num / this.getTotalInfo().get(10).size());
		s11_label.setText("( " + s11Num + " \\ " + this.getTotalInfo().get(10).size() + " )");
		s11_tooltip.setText(input_s11_set.toString());
		s11_Prograssbar.setTooltip(s11_tooltip);
		
		s12_Prograssbar.setProgress(1.0 * s12Num / this.getTotalInfo().get(11).size());
		s12_label.setText("( " + s12Num + " \\ " + this.getTotalInfo().get(11).size() + " )");
		s12_tooltip.setText(input_s12_set.toString());
		s12_Prograssbar.setTooltip(s12_tooltip);
		
		s13_Prograssbar.setProgress(1.0 * s13Num / this.getTotalInfo().get(12).size());
		s13_label.setText("( " + s13Num + " \\ " + this.getTotalInfo().get(12).size() + " )");
		s13_tooltip.setText(input_s13_set.toString());
		s13_Prograssbar.setTooltip(s13_tooltip);
		
		s14_Prograssbar.setProgress(1.0 * s14Num / this.getTotalInfo().get(13).size());
		s14_label.setText("( " + s14Num + " \\ " + this.getTotalInfo().get(13).size() + " )");
		s14_tooltip.setText(input_s14_set.toString());
		s14_Prograssbar.setTooltip(s14_tooltip);
	}

	public List<Set<String>> getTotalInfo() {
		return totalInfo;
	}

	public void setTotalInfo(List<Set<String>> totalInfo) {
		this.totalInfo = totalInfo;
	}

	public NodeList getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeList nodeList) {
		this.nodeList = nodeList;
	}

}
