package net.alex.app.parser.application;

import net.alex.app.parser.entity.DickWorld;
import net.alex.app.parser.facade.PdfFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");

        logger.debug("DEBUG MODE {}", "Try Debug Mode");
        logger.info("INFO in {}", Application.class.getSimpleName());
        logger.info("INFO in {} test value = {}", Application.class.getSimpleName(), "-------test------");

        PdfFacade pdfFacade = context.getBean(PdfFacade.class);

        List<DickWorld> dickWorlds = pdfFacade.countWord();

        final int[] countFilteredWords = {0};

        dickWorlds
                .stream()
                .sorted()
                .peek(x-> countFilteredWords[0]++)
                .forEach((x)-> System.out.println(x.getAmount()+"  "+x.getWorld()));

        System.out.println("\n\n"+countFilteredWords[0]);

    }

//    private void garbage_Change_String() throws NoSuchFieldException, IllegalAccessException {
//
//        String a="1111111";
//
//        System.out.println(a);
//        Field field = String.class.getDeclaredField("value");
//        field.setAccessible(true);
//
//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//
//        byte[] nv ={120,111,122,98,105,88,98,113,111};
//        Object newValue = nv;
//
//        Boolean d;
//
//        field.set(a, newValue);
//        System.out.println(a);
//    }

}
