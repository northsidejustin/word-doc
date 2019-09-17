import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DocXToZipConverter{

    String docFileName;
    String zipFileName;
    String folderName;

    public DocXToZipConverter(String docFileName){

        this.docFileName = docFileName;

    }

    public void changeExtensionToZip(){

        File docxFile = new File(this.docFileName);
        int index = docxFile.getName().lastIndexOf(".");
        zipFileName = docxFile.getName().substring(0, index) + ".zip";

        File zipFile = new File(zipFileName);
        boolean success = docxFile.renameTo(zipFile);
        
        if (success){

            System.out.println("Conversion successful");

        }
        else{

            System.out.println("Conversion failed");

        }
    }

    private boolean createDirectory(){

        int index = zipFileName.lastIndexOf(".");

        folderName = zipFileName.substring(0, index);

        File folder = new File(folderName);

        if (!folder.exists()){
            folder.mkdir();
            System.out.println("Created an empty directory "+folderName);
            return true;
        }
        else{
            System.out.println("Failed to create output directory.");
            return false;
        }
    }

    public void unzipFile(){

        if (createDirectory()){

            try{
                ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
                byte [] buffer = new byte[1024];
                ZipEntry ze = zis.getNextEntry();
    
                while (ze != null){
    
                    String fileName = ze.getName();
                    
                    File newFile = new File(folderName + File.separator + fileName);
    
                    new File(newFile.getParent()).mkdirs();
    
    
                    FileOutputStream fos = new FileOutputStream(newFile);
    
                    int len;
    
                    while ((len = zis.read(buffer)) > 0){
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    ze = zis.getNextEntry();
                }
    
                System.out.println("Unzip completed");
                zis.close();
    
            }
            catch(IOException e){
                System.out.println("Something went wrong");
            }

        }
        else{

            System.out.println("Unzip unsuccessful");

        }




    }


}