import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

public class Main{
 
    public static void main(String [] args) throws IOException, TransformerConfigurationException, ParserConfigurationException, SAXException{

        DocXToZipConverter docFile = new DocXToZipConverter("ohscourse.docx");
        docFile.changeExtensionToZip();
        docFile.unzipFile();
        XMLToTextConverter xmlFile = new XMLToTextConverter("ohscourse/word/document.xml");
        xmlFile.checkIfXMLFileExist();
        xmlFile.convertStringToDocument();
    }
}