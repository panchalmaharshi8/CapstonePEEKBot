
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

public class XMLWriter {
    public static void main(String[] args) {
        try {
            // User input for number of rays, thickness, and material ID
            int numberOfRays = 10; // Change this to the desired number of rays
            int thickness = 15; // Change this to the desired thickness
            int materialId = 1; // Change this to the desired material ID

            // Create XML document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("thickness_metafile");
            rootElement.setAttribute("version_number", "1.0");
            doc.appendChild(rootElement);

            // Create job bundle element
            Element jobBundle = doc.createElement("job_bundle");
            jobBundle.setAttribute("job_id", "1");
            jobBundle.setAttribute("job_label", "42-ray, Zero-thickness sphere");
            rootElement.appendChild(jobBundle);

            // Create rays with specified number, thickness, and material ID
            for (int i = 1; i <= numberOfRays; i++) {
                Element ray = doc.createElement("ray");
                ray.setAttribute("number", String.valueOf(i));
                ray.setAttribute("thk_count", "1");
                ray.setAttribute("xdir", "0.0");
                ray.setAttribute("ydir", "0.0");
                ray.setAttribute("zdir", "1.0");
                jobBundle.appendChild(ray);

                Element thk = doc.createElement("thk");
                thk.setAttribute("material_id", String.valueOf(materialId));
                thk.setAttribute("thickness", String.valueOf(thickness));
                ray.appendChild(thk);
            }

            // Write the content into an XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("output.xml")); // Specify the desired output file name
            transformer.transform(source, result);

            System.out.println("XML file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> e72359a3ddb9fbabb922dc12a306a2e079f9dce3
