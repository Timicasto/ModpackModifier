package timicasto.modpackmodifier.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import timicasto.modpackmodifier.tweak.arobj.Astronomical;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class XMLGenerator {
    public static ByteArrayOutputStream getXML(Map<Integer, Astronomical.Star> stars) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(true);

            Element root = document.createElement("galaxy");
            document.appendChild(root);

            for (int i : stars.keySet()) {
                Element star = document.createElement("star");
                star.setAttribute("name", stars.get(i).name);
                star.setAttribute("temp", String.valueOf(stars.get(i).temp));
                star.setAttribute("x", String.valueOf(stars.get(i).x));
                star.setAttribute("y", String.valueOf(stars.get(i).y));
                star.setAttribute("numPlanets", String.valueOf(stars.get(i).numPlanets));
                for (int j : stars.get(i).planets.keySet()) {
                    Element planet = document.createElement("planet");
                    planet.setAttribute("name", stars.get(i).planets.get(j).name);
                    planet.setAttribute("DIMID", String.valueOf(stars.get(i).planets.get(j).dimID));
                    Element isKnown = document.createElement("isKnown");
                    isKnown.setTextContent(stars.get(i).planets.get(j).isKnown ? "true" : "false");
                    planet.appendChild(isKnown);
                    Element fogColor = document.createElement("fogColor");
                    fogColor.setTextContent("#" + Integer.toHexString(stars.get(i).planets.get(j).fogColor.getRGB()));
                    planet.appendChild(fogColor);
                    Element skyColor = document.createElement("skyColor");
                    skyColor.setTextContent("#" + Integer.toHexString(stars.get(i).planets.get(j).skyColor.getRGB()));
                    planet.appendChild(skyColor);
                    Element gravitationalMultiplier = document.createElement("gravitationalMultiplier");
                    gravitationalMultiplier.setTextContent(String.valueOf(stars.get(i).planets.get(j).gravitationalMultiplier));
                    planet.appendChild(gravitationalMultiplier);
                    Element orbitalDistance = document.createElement("orbitalDistance");
                    orbitalDistance.setTextContent(String.valueOf(stars.get(i).planets.get(j).orbitalDistance));
                    planet.appendChild(orbitalDistance);
                    Element orbitalTheta = document.createElement("orbitalTheta");
                    orbitalTheta.setTextContent(String.valueOf(stars.get(i).planets.get(j).orbitalTheta));
                    planet.appendChild(orbitalTheta);
                    Element orbitalPhi = document.createElement("orbitalPhi");
                    orbitalPhi.setTextContent(String.valueOf(stars.get(i).planets.get(j).orbitalPhi));
                    planet.appendChild(orbitalPhi);
                    Element rotationalPeriod = document.createElement("rotationalPeriod");
                    rotationalPeriod.setTextContent(String.valueOf(stars.get(i).planets.get(j).rotationalPeriod));
                    planet.appendChild(rotationalPeriod);
                    Element atmosphereDensity = document.createElement("atmosphereDensity");
                    atmosphereDensity.setTextContent(String.valueOf(stars.get(i).planets.get(j).atmosphereDensity));
                    planet.appendChild(atmosphereDensity);
                    Element seaLevel = document.createElement("seaLevel");
                    seaLevel.setTextContent(String.valueOf(stars.get(i).planets.get(j).seaLevel));
                    planet.appendChild(seaLevel);
                    Element oceanBlock = document.createElement("oceanBlock");
                    oceanBlock.setTextContent(stars.get(i).planets.get(j).oceanBlock);
                    planet.appendChild(oceanBlock);
                    Element fillerBlock = document.createElement("fillerBlock");
                    fillerBlock.setTextContent(stars.get(i).planets.get(j).filterBlock);
                    planet.appendChild(fillerBlock);
                    star.appendChild(planet);
                    root.appendChild(star);
                }
                TransformerFactory factory1 = TransformerFactory.newInstance();
                Transformer transformer = factory1.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(document);
                transformer.transform(source, new StreamResult(bos));
            }
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(bos.toString());
        return bos;
    }
}
