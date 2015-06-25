package sparql;

/*
 * @author Harsh H Bhavsar
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.tdb.TDBFactory;

public class SparqlMethod {
	
	//Constructor
	public SparqlMethod(){
		super();
	}

	//This is the inference method for getting data from OWL file
	public String sparqlInferenceMethod(String qry){

		OntModel ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, null); 
		try {
			ont.read(
					new FileInputStream(
						//	"D:/AI Project/harsh/myNew.owl"),
							"SmartHospital.owl"),
					null, "RDF");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Reasoner reasoner = ReasonerRegistry.getOWLMicroReasoner(); 
		reasoner = reasoner.bindSchema(ont); 

		Dataset dataset = TDBFactory.createDataset(); 
		Model model = dataset.getDefaultModel(); 

		InfModel infModel = ModelFactory.createInfModel(reasoner, model);
		String disease = null;
		String hospital = null;
		String hospitalCode = null;
		StringBuilder res=new StringBuilder();
		Query query = QueryFactory.create(qry);
		QueryExecution exec = QueryExecutionFactory.create(query, infModel);
		try {
			System.out.println("here");
			ResultSet rs = exec.execSelect();
			while (rs.hasNext()) {
				QuerySolution soln = rs.nextSolution();
				disease = soln.get("dn").toString();
				hospital = soln.get("hn").toString();
				hospitalCode = soln.get("hc").toString();
			}
		} finally {
			exec.close();
		}
		
		res.append(disease);
		res.append("::");
		res.append(hospital);
		res.append("::");
		res.append(hospitalCode);
		return res.toString();
	}
	
	public String sparql(String qry) {
		Model model = ModelFactory.createDefaultModel();
		try {
			model.read(
					new FileInputStream(
							"D:/AI Project/harsh/myNew.owl"),
					null, "RDF");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String res = null;
		String res1 = null;
		Query query = QueryFactory.create(qry);
		QueryExecution exec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet rs = exec.execSelect();
			while (rs.hasNext()) {
				QuerySolution soln = rs.nextSolution();
				//res = soln.get("dn").toString();
				res = soln.get("dn").toString();
				System.out.println(res);
				System.out.println("HAS");
				res1 = soln.get("rn").toString();
				System.out.println(res1);
			}
		} finally {
			exec.close();
		}
		return res;
	}
}
