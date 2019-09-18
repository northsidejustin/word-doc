import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main{
 
    public static void main(String [] args) throws IOException, TransformerConfigurationException, ParserConfigurationException, SAXException, TransformerException{

        DocXToZipConverter docFile = new DocXToZipConverter("storyboard.docx");
        docFile.changeExtensionToZip();
        docFile.unzipFile();
        XMLToTextConverter xmlFile = new XMLToTextConverter("storyboard/word/document.xml");
        xmlFile.checkIfXMLFileExist();
        xmlFile.writeBeautifiedXMLToAFile();
    }
}