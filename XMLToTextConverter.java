import java.io.*;
import java.io.StringReader;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class XMLToTextConverter{

    String xmlFileName;

    public XMLToTextConverter(String xmlFileName){
        this.xmlFileName = xmlFileName;
    }

    public void checkIfXMLFileExist(){

        File xmlFile = new File(xmlFileName);

        boolean exists = xmlFile.exists();

        System.out.println("Does this file exist? "+exists);
    }

    private String storeXMLtoString() throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(this.xmlFileName));
        String xml = "";

        try{
            String line = br.readLine();
            while (line != null){

                xml += line;
                line = br.readLine();
            }
        }   

        catch (IOException e){
            e.printStackTrace();
        }

        finally{
            br.close();
        }
        xml = xml.trim().replaceFirst("^([\\W]+)<","<");
        return xml;

    }

    public Document convertStringToDocument() throws ParserConfigurationException, SAXException, IOException{

        Document doc = null;

        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(storeXMLtoString()));
            doc = db.parse(is);

        }

        catch (ParserConfigurationException | SAXException | IOException e){

            e.printStackTrace();

        }

        return doc;
    }


    private String beautifyXMLDoc() throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException {

        String beautifiedXMLString = "";

        
        try{
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(convertStringToDocument());
            transformer.transform(source, result);
            beautifiedXMLString = result.getWriter().toString();
        }

        catch (ParserConfigurationException | SAXException | IOException | TransformerException e){

            e.printStackTrace();
        }

        return beautifiedXMLString;

    }

    public void writeBeautifiedXMLToAFile() throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException{

        System.out.println("end of beautified");
        
        System.out.println(beautifyXMLDoc());

    }




}