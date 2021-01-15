package net.alex.app.parser.service.pdf.impl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import net.alex.app.parser.entity.DickWorld;
import net.alex.app.parser.service.pdf.PdfProcessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
@PropertySource(value="classpath:property.properties")
public class PdfProcessService_impl implements PdfProcessService {

    @Value("${pdf.max.deap}")
    private Integer pdfMaxDeap;

    @Override
    public List<File> getAllPdfFiles(File file) {

        List<File> resultList = new ArrayList<>();

        if (file == null) return resultList;

        if (file.isFile() && isItPdfFile(file)) {
            resultList.add(file);
            return resultList;
        }

        File[] internalFiles = file.listFiles();

        if (internalFiles == null) return resultList;

        for (File f : internalFiles) {
            resultList.addAll(getAllPdfFiles(f));
        }

        return resultList;
    }


    @Override
    public List<String> extractAllWords(File file) {

        PdfReader reader;
        StringBuilder resultString = new StringBuilder();

        try {

            reader = new PdfReader(new FileInputStream(file.getAbsolutePath())); // todo

            String textFromPage;

            for (int i = 1; i < pdfMaxDeap; i++) {
                try {
                    textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                } catch (Exception e) {
                    break;
                }
                resultString.append(" ").append(textFromPage.replaceAll("[\\s'’.]", " ")
//                        .replaceAll("’"," ")
                        .replaceAll("/", "")
                        .replaceAll("[^a-zA-Z\\s]", "")
                        .replaceAll("\\s{2,}", " "));
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(resultString.toString().split(" "));

}

    @Override
    public List<DickWorld> groupWords(List<String> strings){

        Map<String, DickWorld> map = new HashMap<>();

        for(String s: strings){

            if(map.containsKey(s)){
                map.get(s).addAmount();
            } else {
                map.put(s, new DickWorld(s));
            }
        }

        return new ArrayList(map.values());



    }

    private boolean isItPdfFile(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return false;
        }

        return name.substring(lastIndexOf).equals(".pdf");
    }


}
