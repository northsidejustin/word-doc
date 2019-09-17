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
        StringBuilder sb = new StringBuilder();

        try{
            String line = br.readLine();

            while (line != null){

                sb.append(line);
                line = br.readLine();
            }
        }   

        catch (IOException e){
            e.printStackTrace();
        }

        finally{
            br.close();
        }

        // System.out.println(sb.toString());
        return sb.toString();

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

    // public void beautifyXMLString() throws IOException, TransformerConfigurationException{

        
    //     try{

    //         Transformer transformer = TransformerFactory.newInstance().newTransformer();
    //         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    //         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

    //         StreamResult result = new StreamResult(new StringWriter());
    //         DOMSource source = new DOMSource(this.xmlFileName);

    //     }

    //     catch (TransformerConfigurationException e){

    //         e.printStackTrace();

    //     }

    //     finally{


    //     }
       
    // }

    public String getXMLFilePath(){
        return this.xmlFileName;
    }





}