package timicasto.modpackmodifier.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import timicasto.modpackmodifier.tweak.arobj.Astronomical;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Properties;

public class XMLGenerator {
    public static ByteArrayOutputStream getXML(Map<Integer, Astronomical.Star> stars) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(true);

            Element root = document.createElement("galaxy");

            for (int i : stars.keySet()) {
                Element star = document.createElement("star");
                star.setAttribute("name", stars.get(i).name);
                star.setAttribute("temp", String.valueOf(stars.get(i).temp));
                star.setAttribute("x", String.valueOf(stars.get(i).x));
                star.setAttribute("y", String.valueOf(stars.get(i).y));
                star.setAttribute("numPlanets", String.valueOf(stars.get(i).planets.size()));
                for (int j : stars.get(i).planets.keySet()) {
                    Element planet = document.createElement("planet");

                }
                root.appendChild(star);
                TransformerFactory factory1 = TransformerFactory.newInstance();
                Transformer transformer = factory1.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(document);
                transformer.transform(source, new StreamResult(bos));
            }
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return bos;
    }
}
