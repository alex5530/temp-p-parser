package net.alex.app.parser.facade;

import net.alex.app.parser.entity.DickWorld;

import java.io.File;
import java.util.List;

public interface PdfFacade {

    List<DickWorld> countWord();

    List<DickWorld> countWord(File file);

}
