package NameTool.src.test.java.de.hse.swb.swt;

import NameTool.src.main.java.de.hse.swb.swt.nametool.NameTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameToolTest {

    String firstParam;
    String secondParam;
    String actualResult;
    int limitValue;

    @BeforeEach
    void Setup() throws NoSuchFieldException, SecurityException, IllegalAccessException  {
        limitValue = getLimitFieldValue();
        firstParam = "first";
        secondParam = "second";
    }

    @Test
    void newName_firstParamOverLimit_NotOverLimit(){
        firstParam = "Menschenskindermaenner";
        String expectedResult = firstParam.substring(0, limitValue);

        actualResult = NameTool.newName(firstParam, secondParam);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void newName_secondParamOverLimit_NotOverLimit(){
        secondParam = "Menschenskindermaenner";
        String expectedResult = (firstParam + " " + secondParam).substring(0, limitValue);

        actualResult = NameTool.newName(firstParam, secondParam);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void newName_bothParamsOverLimit_NotOverLimit(){
        firstParam = "Musterfraeuleinswesen";
        secondParam = "Menschenskindermaenner";
        String expectedResult = firstParam.substring(0, limitValue);

        actualResult = NameTool.newName(firstParam, secondParam);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void newName_bothParamsUnderLessThanLimit_ResultUnderLimit(){
        String expectedResult = firstParam + " " + secondParam;

        actualResult = NameTool.newName(firstParam, secondParam);

        assertEquals(expectedResult, actualResult);
    }

    private int getLimitFieldValue() throws NoSuchFieldException, IllegalAccessException {
        Field limitField = NameTool.class.getDeclaredField("LIMIT");
        limitField.setAccessible(true);
        return (int) limitField.get(null);
    }
}
