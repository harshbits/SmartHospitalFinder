package view;

/*
 * @author Harsh H Bhavsar
 * 
 */


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import sparql.SparqlMethod;
import algorithm.AStarSearchAlgorithm;
import algorithm.Edge;
import algorithm.Node;

public class MainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JComboBox<String> symptomCombo;
	private JTextPane symptomDisplay;
	private JTextPane diseaseDisplay;
	private JTextPane hospitalDisplay;
	private JTextPane pathDisplay;

	private JButton clearButton;
	private JButton submitButton;

	List<String> symptoms = new ArrayList<String>();
	private JComboBox<Node> currentLocation;

	String curLoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
					frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MainScreen() throws ClassNotFoundException, SQLException {

		List<Node> allNodes = new ArrayList<Node>();

		Node BFC = new Node("BFC", "Baylor Medical Center at Frisco");
		allNodes.add(BFC);
		Node CHATHM = new Node("CHATHM", "Chatham Courts");
		allNodes.add(CHATHM);
		Node CMPCRD = new Node("CMPCRD", "W Campbell Road + Coit Road");
		allNodes.add(CMPCRD);
		Node CRDJBH = new Node("CRDJBH", "Coit Road + J Bush Highway");
		allNodes.add(CRDJBH);
		Node CRDWSP = new Node("CRDWSP", "Coit Road + W Spring Creek Pkwy");
		allNodes.add(CRDWSP);
		Node DIKFRRD = new Node("DIKFRRD", "Dikerson + Frankford Road");
		allNodes.add(DIKFRRD);
		Node DNTWRP = new Node("DNTWRP",
				"Dallas Northway Tollway + Warren Pkwy");
		allNodes.add(DNTWRP);
		Node FRRDCRD = new Node("FRRDCRD", "Frankford Road + Coit Road");
		allNodes.add(FRRDCRD);
		Node HHB = new Node("HHB", "The Heart Hospital Baylor Plano");
		allNodes.add(HHB);
		Node HILFRD = new Node("HILFRD", "Hilcrest Road + Frankford Road");
		allNodes.add(HILFRD);
		Node HILOHIO = new Node("HILOHIO", "Hilcrest Road + Ohio Dr");
		allNodes.add(HILOHIO);
		Node JBHDNT = new Node("JBHDNT",
				"J Bush Highway + Dallas Northway Tollway");
		allNodes.add(JBHDNT);
		Node MCCCRD = new Node("MCCCRD", "McCallum Blvd + Coit Road");
		allNodes.add(MCCCRD);
		Node MCCDIK = new Node("MCCDIK", "McCallum Blvd + Dikerson");
		allNodes.add(MCCDIK);
		Node MCCHIL = new Node("MCCHIL", "McCallum blvd + Hilcrest Road");
		allNodes.add(MCCHIL);
		Node MCCMND = new Node("MCCMND", "McCallum Blvd + Meandering Way");
		allNodes.add(MCCMND);
		Node MCP = new Node("MCP", "The Medical Center of Plano");
		allNodes.add(MCP);
		Node MNDFRD = new Node("MNDFRD", "Meandering Way + Frankford Road");
		allNodes.add(MNDFRD);
		Node OHIOAID = new Node("OHIOAID", "Ohio Dr + Allied Dr");
		allNodes.add(OHIOAID);
		Node UTD = new Node("UTD", "The University of Texas at Dallas");
		allNodes.add(UTD);
		Node UTDWW = new Node("UTDWW", "UTD + Waterview Parkway");
		allNodes.add(UTDWW);
		Node WPKCRD = new Node("WPKCRD", "W Plano pkwy + Coit Road");
		allNodes.add(WPKCRD);
		Node WSPDNT = new Node("WSPDNT",
				"W Spring Creek Pkwy + Dallas Northway Tollway");
		allNodes.add(WSPDNT);
		Node WWCMP = new Node("WWCMP", "Waterview Parkway + W Campbell Road");
		allNodes.add(WWCMP);
		Node WWFRRD = new Node("WWFRRD", "Waterview Parkway + Frankford Road");
		allNodes.add(WWFRRD);
		Node WWWPK = new Node("WWWPK", "Waterview Parkway + W Plano pkwy");
		allNodes.add(WWWPK);

		UTD.adjacencies = new Edge[] {

		new Edge(UTDWW, 0.5) };

		UTDWW.adjacencies = new Edge[] { new Edge(UTD, 0.5),
				new Edge(WWCMP, 0.5), new Edge(WWFRRD, 0.8) };

		WWFRRD.adjacencies = new Edge[] { new Edge(UTDWW, 0.8),
				new Edge(WWWPK, 0.8), new Edge(FRRDCRD, 0.8) };

		FRRDCRD.adjacencies = new Edge[] { new Edge(WWFRRD, 0.8),
				new Edge(MCCCRD, 0.6), new Edge(DIKFRRD, 0.4),
				new Edge(CRDJBH, 0.5) };

		CRDJBH.adjacencies = new Edge[] { new Edge(FRRDCRD, 0.5),
				// new Edge(CRDWSP, 3.6),
				new Edge(WPKCRD, 0.4), new Edge(JBHDNT, 3.1)

		};

		JBHDNT.adjacencies = new Edge[] { new Edge(CRDJBH, 3.1),
				new Edge(WSPDNT, 3.5) };

		WSPDNT.adjacencies = new Edge[] {

		new Edge(DNTWRP, 2.8), new Edge(CRDWSP, 3.4), new Edge(JBHDNT, 3.5) };

		DNTWRP.adjacencies = new Edge[] {

		new Edge(BFC, 1.1), new Edge(WSPDNT, 2.8) };

		CRDWSP.adjacencies = new Edge[] {

		new Edge(WSPDNT, 3.4), new Edge(MCP, 2.5)
		// new Edge(CRDJBH,3.6)
		};

		BFC.adjacencies = new Edge[] {

		new Edge(DNTWRP, 1.1) };

		CHATHM.adjacencies = new Edge[] {

		new Edge(MCCCRD, 0.2), new Edge(MCCDIK, 0.2)

		};

		MCCDIK.adjacencies = new Edge[] {

		new Edge(CHATHM, 0.2), new Edge(MCCMND, 0.3), new Edge(DIKFRRD, 0.7)

		};

		DIKFRRD.adjacencies = new Edge[] {

		new Edge(FRRDCRD, 0.4), new Edge(MNDFRD, 0.4), new Edge(MCCDIK, 0.7)

		};

		MCCCRD.adjacencies = new Edge[] {

		new Edge(CHATHM, 0.2), new Edge(CMPCRD, 0.7), new Edge(FRRDCRD, 0.6)

		};

		CMPCRD.adjacencies = new Edge[] { new Edge(MCCCRD, 0.7),
				new Edge(WWCMP, 0.7) };

		WWCMP.adjacencies = new Edge[] { new Edge(UTDWW, 0.5),
				new Edge(CMPCRD, 0.7)

		};

		WWWPK.adjacencies = new Edge[] {

		new Edge(WPKCRD, 1.3), new Edge(WWFRRD, 0.8) };

		WPKCRD.adjacencies = new Edge[] {

		new Edge(MCP, 0.7),
				// new Edge(FRRDCRD, 0.9),
				new Edge(CRDJBH, 0.4), new Edge(WWWPK, 1.3),
				new Edge(OHIOAID, 1.3) };

		MCP.adjacencies = new Edge[] {
				// new Edge(FRRDCRD, 1.6),
				// new Edge(CMPCRD, 2.9),
				new Edge(CRDWSP, 2.5), new Edge(WPKCRD, 0.7) };

		HHB.adjacencies = new Edge[] {

		new Edge(OHIOAID, 0.3) };

		OHIOAID.adjacencies = new Edge[] {

		new Edge(HILOHIO, 0.5), new Edge(HHB, 0.3), new Edge(WPKCRD, 1.3) };

		HILFRD.adjacencies = new Edge[] {

		new Edge(HILOHIO, 0.8), new Edge(MCCHIL, 0.7), new Edge(MNDFRD, 0.2) };

		HILOHIO.adjacencies = new Edge[] {

		new Edge(HILFRD, 0.8), new Edge(OHIOAID, 0.5) };

		MNDFRD.adjacencies = new Edge[] {

		new Edge(HILFRD, 0.2), new Edge(DIKFRRD, 0.4), new Edge(MCCMND, 0.7) };

		MCCMND.adjacencies = new Edge[] {

		new Edge(MCCHIL, 0.3), new Edge(MCCDIK, 0.3), new Edge(MNDFRD, 0.7) };

		MCCHIL.adjacencies = new Edge[] {

		new Edge(HILFRD, 0.7), new Edge(MCCMND, 0.3) };

		setTitle("Smart Hospital Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		symptomCombo = new JComboBox<>();
		symptomCombo.setBounds(10, 82, 204, 20);
		contentPane.add(symptomCombo);

		symptomCombo.addItem("Fast Heart Beats");
		symptomCombo.addItem("Slow Heart Beats");
		symptomCombo.addItem("Normal Heart Beats");
		symptomCombo.addItem("High Body Temperature");
		symptomCombo.addItem("Low Body Temperature");
		symptomCombo.addItem("Normal Body Temperature");
		symptomCombo.addItem("Chest Pain");
		symptomCombo.addItem("Forehead Pain");
		symptomCombo.addItem("Headache Pain");
		symptomCombo.addItem("Muscles  Pain");
		symptomCombo.addItem("Running Nose");
		symptomCombo.addItem("Shortness of Breath");
		symptomCombo.addItem("Fever");
		symptomCombo.addItem("Vomiting and Nausea");
		symptomCombo.addItem("Watery Eyes");
				
			
		JLabel lblSmartHospitalFinder = new JLabel("SMART HOSPITAL FINDER");
		lblSmartHospitalFinder.setBounds(174, 11, 187, 17);
		lblSmartHospitalFinder.setForeground(Color.RED);
		lblSmartHospitalFinder.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblSmartHospitalFinder);

		JLabel lblNewLabel = new JLabel("Select Symptoms");
		lblNewLabel.setBounds(10, 56, 106, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel);

		JLabel lblSelectedSymptoms = new JLabel("Selected Symptoms");
		lblSelectedSymptoms.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectedSymptoms.setBounds(325, 58, 160, 15);
		contentPane.add(lblSelectedSymptoms);

		symptomDisplay = new JTextPane();
		symptomDisplay.setBounds(323, 82, 291, 153);
		symptomDisplay
				.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		contentPane.add(symptomDisplay);

		diseaseDisplay = new JTextPane();
		diseaseDisplay.setBounds(10, 152, 303, 20);
		diseaseDisplay
				.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		diseaseDisplay.setEditable(false);
		contentPane.add(diseaseDisplay);

		JLabel lblDiseaseRecognized = new JLabel("Disease Recognized");
		lblDiseaseRecognized.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiseaseRecognized.setBounds(10, 126, 160, 15);
		contentPane.add(lblDiseaseRecognized);

		hospitalDisplay = new JTextPane();
		hospitalDisplay.setBounds(10, 215, 303, 20);
		hospitalDisplay.setBorder(BorderFactory
				.createLineBorder(Color.black, 1));
		hospitalDisplay.setEditable(false);
		contentPane.add(hospitalDisplay);

		JLabel lblHospitalFound = new JLabel("Hospital Found");
		lblHospitalFound.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHospitalFound.setBounds(10, 188, 180, 15);
		contentPane.add(lblHospitalFound);

		pathDisplay = new JTextPane();
		pathDisplay.setBounds(10, 280, 604, 312);
		pathDisplay.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pathDisplay.setEditable(false);
		contentPane.add(pathDisplay);

		JLabel lblShortestPathTo = new JLabel(
				"Shortest Path to The above Hospital");
		lblShortestPathTo.setForeground(Color.BLUE);
		lblShortestPathTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShortestPathTo.setBounds(10, 254, 316, 15);
		contentPane.add(lblShortestPathTo);

		submitButton = new JButton("SUBMIT");
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		submitButton.setForeground(Color.RED);
		submitButton.setBounds(224, 81, 89, 23);
		contentPane.add(submitButton);

		JLabel lblNewLabel_1 = new JLabel("(Double click to add multiple)");
		lblNewLabel_1.setBounds(124, 57, 168, 14);
		contentPane.add(lblNewLabel_1);

		clearButton = new JButton("CLEAR");
		clearButton.setForeground(Color.RED);
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		clearButton.setBounds(224, 118, 89, 23);
		contentPane.add(clearButton);

		JLabel lblCurrentLocation = new JLabel("Current Location");
		lblCurrentLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentLocation.setBounds(269, 254, 131, 15);
		contentPane.add(lblCurrentLocation);

		currentLocation = new JComboBox<Node>();
		currentLocation.setBounds(410, 249, 204, 20);
		contentPane.add(currentLocation);

		currentLocation.addItem(new Node("UTD",
				"UTD:The University of Texas at Dallas"));
		currentLocation.addItem(new Node("CHATHM", "CHATHM:Chatham Courts"));
		currentLocation.addItem(new Node("WSPDNT", "WSPDNT:W Spring Creek Pkwy Dallas Northway Tollway"));

		curLoc = String.valueOf(currentLocation.getSelectedItem());

		StringBuilder sb = new StringBuilder();
		// symptoms =

		symptomCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				symptoms.add(symptomCombo.getSelectedItem().toString());
				sb.append(">" + symptomCombo.getSelectedItem().toString()
						+ "\r");
				symptomDisplay.setText(sb.toString());

			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				symptoms.clear();
				sb.setLength(0);
				symptomDisplay.setText("");
				diseaseDisplay.setText("");
				hospitalDisplay.setText("");
				pathDisplay.setText("");

			}
		});
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (symptoms.size() < 2) {

					JOptionPane.showMessageDialog(new JFrame(),
							"You have to enter atleast 3 Symptoms",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} else {

					String c[] = currentLocation.getSelectedItem().toString()
							.split(":");
					String currentLoc = c[1];

					StringBuilder sb = new StringBuilder();
					StringBuilder sb1 = new StringBuilder();

					for (int i = 0; i < symptoms.size(); i++) {
						sb.append("?s" + i + " smf:symptomName " + "?o" + i
								+ " FILTER (regex(?o" + i + ", '"
								+ symptoms.get(i) + "'))");
						sb.append("\n");
					}

					for (int i = 0; i < symptoms.size(); i++) {
						sb1.append("?d smf:hasSymptom ?s" + i + ".");
						sb.append("\n");
					}

					String qry = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
							+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
							+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
							+ "PREFIX smf: <http://www.semanticweb.org/harsh/ontologies/2015/3/projectOnt_AI#>"
							+ " SELECT *" + " WHERE { "
							+ sb
							+ sb1
							+ " ?d smf:diseaseName ?dn."
							+ " ?d smf:diseaseType ?dt."
							+ " ?d smf:hasRisk ?r. "
							+ " ?r smf:riskName  ?rn."
							+ " ?l rdf:type smf:Location."
							+ " ?l smf:locationName ?lo FILTER (regex(?lo, '"
							+ currentLoc
							+ "')) "
							+ " ?l smf:locationArea ?ln."
							+ " ?lnm smf:areaName ?lno FILTER(regex(?lno, ?ln)) "
							+ " ?d smf:hasSpecialityHospital ?hs."
							+ " ?lnm smf:ContainsHospital ?lh."
							+ " ?lh smf:hasSpeciality ?lhs."
							+ " ?d smf:hasHospitalSpeciality ?dhs."
							+ " ?lhs1 smf:hospitalType ?lho FILTER(regex(?lho, 'General Hospital'))"
							+ " ?lhs1 smf:containsSpeciality ?fho FILTER(?fho IN(?lh))"
							+ " BIND(IF (?lhs != ?dhs, ?fho,?hs) AS ?h1)"
							+ " BIND(IF (?rn != 'High Risk', ?hs, ?h1) AS ?h)"
							+ " ?h smf:hospitalName ?hn."
							+ " ?h smf:hospitalCode ?hc " + " }";

					for (int i = 0; i < symptoms.size(); i++) {
						System.out.println(symptoms.get(i));
					}

					SparqlMethod m = new SparqlMethod();
					String output = m.sparqlInferenceMethod(qry);
					System.out.println("output is : "+ output);
					String[] temp;
					String delimiter = "::";
					temp = output.split(delimiter);
					diseaseDisplay.setText(temp[0]);
					hospitalDisplay.setText(temp[1]);

					Connection connect = null;
					Statement statement = null;
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;

					try {
						Class.forName("com.mysql.jdbc.Driver");

						connect = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/practice?"
										+ "user=root&password=");

						statement = (Statement) connect.createStatement();

						// resultSet =
						// statement.executeQuery("select * from node ");

						/*
						 * preparedStatement = (PreparedStatement) connect
						 * .prepareStatement("SELECT * FROM node_data" +
						 * "JOIN node ON node_data.destination_node_id = node_data.destination_node_id"
						 * + "WHERE node.node_name = ? )");
						 */
						
						System.out.println(qry);

						preparedStatement = (PreparedStatement) connect
								.prepareStatement("SELECT nd.data_id, nd.current_node_id, nd.destination_node_id,"
										+ " nd.straight_distance FROM   node n , node_data nd "
										+ "WHERE n.node_id = nd.destination_node_id "
										+ "AND n.node_name = ? ");

						System.out.println(temp[1]);
				
						preparedStatement.setString(1, temp[1]);
						resultSet = preparedStatement.executeQuery();

						Map<String, Double> nodeMapping = new HashMap<String, Double>();

						while (resultSet.next()) {
							nodeMapping.put(resultSet.getString(2),
									resultSet.getDouble(4));
							System.out.println(resultSet.getDouble(4));
						}

						try{
							
						
						for (Node node : allNodes) {
							node.setH_value(nodeMapping.get(node.getNodeName()));

						}}catch(NullPointerException ex){
							
							JOptionPane.showMessageDialog(new JFrame(),
									"Sorry!! No Disease Found. \n Plase Try with new reduced combination.",
									"No Disease Found", JOptionPane.ERROR_MESSAGE);
							
						}

						AStarSearchAlgorithm algorithm = new AStarSearchAlgorithm();

						Node source = null;
						Node destination = null;
						String currentLocCode = c[0];
						System.out.println("current loc code :" + c[0]);
						for (Node node : allNodes) {

							if (node.getNodeName().equals(currentLocCode)) {
								source = node;
							} else if (node.getNodeName().equals(temp[2])) {
								destination = node;

							}
						}

						algorithm.AstarSearchAlgo(source, destination);
						List<Node> path = algorithm.printPath(destination);

						System.out.println("Path: " + path);
						StringBuilder displayPath = new StringBuilder();
						for (int i = 0; i < path.size(); i++) {

							displayPath.append(":> " + path.get(i).toString());
							displayPath.append("\n");

						}

						pathDisplay.setText(displayPath.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
	}

}
