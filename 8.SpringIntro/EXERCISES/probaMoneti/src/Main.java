import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] chars = "Аu 900/1000".toCharArray();
        char[] chars2 = "Au 900/1000".toCharArray();


        //Loading an existing document
        File file = new File("D:\\5. Java DB Advanced\\8. Spring intro\\EXERCISES\\SeedData\\pub_np_catalogues_coins_bg.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        System.out.println(text);

        //Closing the document
        document.close();

        Pattern divideByEntities=Pattern.compile("Емисия.[^$]*?Year");
        Matcher matcher=divideByEntities.matcher(text);

        Pattern fine=Pattern.compile("Емисия (?<year>\\d+) г.\\s*(„(?<event>.[^$]*)(?=”))?[^$]*?(?=Номинална)Номинална стойност – (?<nominal>\\d+)\\s+(?<fraction>.+)[^$]*(?=Метал)Метал(.*)– (?<metal>.+)\\s*(Качество – (?<quality>.[^$]+))?(?=Тегло)Тегло – (?<weight>[0-9\\.]+).+[^$]*(?=Диаметър)Диаметър – (?<diameter>[0-9\\.]+)[^$]*(?=Гурт)Гурт – (?<edge>.+[^$]*?)(?=Отсечена|Тираж)(Тираж – (?<mintage>.+))?\\s*(?=Отсечена)Отсечена в (?<place>.+[^$]*)(?=Художествен|Автор)(.+) – (?<author>.+[^$]*)(?=Year)");

        while (matcher.find()){
            String currentEntity=matcher.group();
            System.out.println(currentEntity);
            Matcher extractData=fine.matcher(currentEntity);
            if(extractData.find()){
                String yearStr=extractData.group("year");
                int year =Integer.parseInt(yearStr);
                String event=extractData.group("event");

                String nominalStr=extractData.group("nominal");
                String fraction=extractData.group("fraction");
                double nominal=Double.parseDouble(nominalStr);
                if(fraction.startsWith("сто")){
                    nominal/=100;
                }

                String metal=extractData.group("metal");
                String quality=extractData.group("quality");

                String weightStr=extractData.group("weight");
                double weight=Double.parseDouble(weightStr);

                String diameterStr=extractData.group("diameter");
                double diameter=Double.parseDouble(diameterStr);

                String edge=extractData.group("edge").trim();
                String mintageStr=extractData.group("mintage");
                int mintage=0;
                if(mintageStr!=null){
                    mintageStr=mintageStr.replace(" ","");
                    mintage=Integer.parseInt(mintageStr);
                }
                String place=extractData.group("place").trim();
                String author=extractData.group("author").trim();
                System.out.println(author);
            }
        }




    }

}
