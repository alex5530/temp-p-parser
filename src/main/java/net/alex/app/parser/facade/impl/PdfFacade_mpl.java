package net.alex.app.parser.facade.impl;

import net.alex.app.parser.entity.DickWorld;
import net.alex.app.parser.facade.PdfFacade;
import net.alex.app.parser.service.pdf.PdfProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PdfFacade_mpl implements PdfFacade {

    private String defaultPath;

    private int cutOfLevel;

    private PdfProcessService processService;

    @Override
    public List<DickWorld> countWord() {
        return countWord(new File(defaultPath));
    }

    @Override
    public List<DickWorld> countWord(File file) {

        List<File> pdfFiles = processService.getAllPdfFiles(file);

        List<String> resultList = new ArrayList<>();

        for (File fl: pdfFiles){
            resultList.addAll(processService.extractAllWords(fl));
        }

        return processService.groupWords(resultList)
                .stream().sorted().filter(dw -> dw.getAmount() > cutOfLevel).
                        collect(Collectors.toList());

    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public void setCutOfLevel(int cutOfLevel) {
        this.cutOfLevel = cutOfLevel;
    }

    @Autowired
    public void setProcessService(PdfProcessService processService) {
        this.processService = processService;
    }
}
