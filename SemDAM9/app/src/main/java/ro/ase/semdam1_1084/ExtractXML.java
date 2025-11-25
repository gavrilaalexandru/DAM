package ro.ase.semdam1_1084;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ExtractXML extends AsyncTask<URL, Void, InputStream> {
    InputStream inputStream = null;

    List<BiletAvion> biletAvionList = new ArrayList<>();
    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            inputStream = connection.getInputStream();

            biletAvionList = parsareXML(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputStream;
    }

    public static Node getNodeByName(String nodeName, Node parentNode) {

        if (parentNode.getNodeName().equals(nodeName)) {
            return parentNode;
        }

        NodeList list = parentNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            Node node = getNodeByName(nodeName, list.item(i));
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public static String getAttributeValue(Node node, String attrName) {
        try {
            return ((Element)node).getAttribute(attrName);
        }
        catch (Exception e) {
            return "";
        }
    }

    public List<BiletAvion> parsareXML(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(inputStream);
            domDoc.getDocumentElement().normalize();

            Node parinte = getNodeByName("BileteAvion", domDoc.getDocumentElement());
            if (parinte != null) {
                NodeList listaCopii = parinte.getChildNodes();
                for (int i = 0; i < listaCopii.getLength(); i++) {
                    Node copil = listaCopii.item(i);
                    if (copil != null && copil.getNodeName().equals("BiletAvion")) {
                        BiletAvion biletAvion = new BiletAvion();

                        NodeList taguri = copil.getChildNodes();
                        for (int j = 0; j < taguri.getLength(); j++) {
                            Node tag = taguri.item(j);
                            String atribut = getAttributeValue(tag, "atribut");
                            if (atribut.equals("Destinatie"))
                                biletAvion.setDestinatie(tag.getTextContent());
                            if (atribut.equals("DataZbor"))
                                biletAvion.setDataZbor(new Date(tag.getTextContent()));
                            if (atribut.equals("Pret"))
                                biletAvion.setPret(Float.parseFloat(tag.getTextContent()));
                            if (atribut.equals("Companie"))
                                biletAvion.setCompanie(tag.getTextContent());
                            if (atribut.equals("Categorie"))
                                biletAvion.setCategorie_Bilet(tag.getTextContent());
                        }
                        biletAvionList.add(biletAvion);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return biletAvionList;
    }
}
