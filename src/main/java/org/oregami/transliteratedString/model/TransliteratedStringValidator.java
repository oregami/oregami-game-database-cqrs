package org.oregami.transliteratedString.model;

import org.oregami.common.CommonError;
import org.oregami.common.CommonErrorContext;
import org.oregami.common.CommonResult;
import org.oregami.transliteratedString.command.CreateTransliteratedStringCommand;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransliteratedStringValidator {

    public static final String DUPLICATE_ENTRY = "DUPLICATE_ENTRY";

    @Autowired
    TransliteratedStringRepository transliteratedStringRepository;

    public CommonResult<Object> validate(CreateTransliteratedStringCommand c) {
        List<CommonError> errors = new ArrayList<>();
        if (c.getText()==null || c.getText().length()==0) {
            errors.add(new CommonError(new CommonErrorContext("text"), "MISSING_VALUE"));
        }
        /*
        else if (c.getText().length()<5) {
            errors.add(new CommonError(new CommonErrorContext("text"), "TOO_SHORT"));
        }
        */
        //search for identical text
        if (errors.isEmpty()) {
            List<TransliteratedString> transliteratedStringsWithIdenticalText = transliteratedStringRepository.findByTextIgnoreCaseContaining(c.getText());
            if (!transliteratedStringsWithIdenticalText.isEmpty()) {
                errors.add(new CommonError(new CommonErrorContext("text"), "DUPLICATE_ENTRY"));
            }
        }
        CommonResult<Object> result = new CommonResult<Object>(errors);
        return result;
    }
}
