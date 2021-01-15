package net.alex.app.parser.aspect;

import net.alex.app.parser.entity.DickWorld;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;
import java.util.List;

@Aspect
public class LoggingAspect {

    @Around("execution(* net.alex.app.parser.service.pdf.impl.PdfProcessService_impl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());

        System.out.println("Around before is running!");

        Object result = joinPoint.proceed(); //continue on the intercepted method

        System.out.println("Around after is running!");
        System.out.println("******");

//        return new ArrayList<>();
        return result;
    }

}
