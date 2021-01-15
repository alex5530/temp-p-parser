package net.alex.app.parser.service.pdf;

import net.alex.app.parser.entity.DickWorld;

import java.io.File;
import java.util.List;

public interface PdfProcessService {

    List<File> getAllPdfFiles(File file);

    List<String> extractAllWords(File file);

    List<DickWorld> groupWords(List<String> list);
}
