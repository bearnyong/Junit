package com.study.section01.params;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;

public class SlashyDateConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        //2014/09/18 얘가 스트링 타입이 아닐 경우, 오류...?
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source
            );
        }

        try {
            //2014
            //09
            //18
            String[] parts = ((String) source).split("/");
            int year = Integer.parseInt(parts[0]); //2014
            int month = Integer.parseInt(parts[1]); //09
            int day = Integer.parseInt(parts[2]); //18

            return LocalDate.of(year, month, day); //로컬 데이터 타입으로 반환
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert ", e);
        }

    }
}
