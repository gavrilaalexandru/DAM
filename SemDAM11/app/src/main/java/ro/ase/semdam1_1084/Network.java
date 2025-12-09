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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Network extends AsyncTask<URL, Void, InputStream> {
    InputStream inputStream = null;
//    static String rezultat = "";
    CursValutar cv = null;
    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            inputStream = connection.getInputStream();

            parsareXML(inputStream);

            /*InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linie = null;

            while ((linie = bufferedReader.readLine()) != null)  {
                rezultat += linie;
            }*/
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

    public void parsareXML(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(inputStream);
            domDoc.getDocumentElement().normalize();

            cv = new CursValutar();

            Node cube = getNodeByName("Cube", domDoc.getDocumentElement());
            if (cube != null) {
                String dataCurs = getAttributeValue(cube, "date");
                cv.setDataCurs(dataCurs);

                NodeList listaCopii = cube.getChildNodes();
                for (int i = 0; i < listaCopii.getLength(); i++) {
                    Node rate = listaCopii.item(i);
                    String atribut = getAttributeValue(rate, "currency");
                    if (atribut.equals("EUR"))
                        cv.setCursEUR(rate.getTextContent());
                    if (atribut.equals("USD"))
                        cv.setCursUSD(rate.getTextContent());
                    if (atribut.equals("GBP"))
                        cv.setCursGBP(rate.getTextContent());
                    if (atribut.equals("XAU"))
                        cv.setCursXAU(rate.getTextContent());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
