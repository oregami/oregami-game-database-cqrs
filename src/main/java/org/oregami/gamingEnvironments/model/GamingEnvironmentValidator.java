package org.oregami.gamingEnvironments.model;

import org.oregami.common.CommonError;
import org.oregami.common.CommonErrorContext;
import org.oregami.common.CommonResult;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.readmodel.withTitles.GamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.withTitles.Title;
import org.oregami.gamingEnvironments.readmodel.withTitles.TitleUsage;
import org.oregami.transliteratedString.command.CreateTransliteratedStringCommand;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class GamingEnvironmentValidator {

    @Autowired
    GamingEnvironmentRepository gamingEnvironmentRepository;

    public CommonResult<Object> validate(AddTitleUsageCommand c) {
        List<CommonError> errors = new ArrayList<>();
        //search for identical text
        if (errors.isEmpty()) {
            GamingEnvironment gamingEnvironment = gamingEnvironmentRepository.findOne(c.getGamingEnvironmentId());
            Set<Title> gametitles = gamingEnvironment.getGametitles();

            for(Title t: gametitles) {
                if (t.getId().equals(c.getTitleId())) {
                    for (TitleUsage tu : t.getTitleUsages()) {
                        if (tu.getRegion().equals(c.getRegion())) {
                            errors.add(new CommonError(new CommonErrorContext("region"), "DUPLICATE_TITLE_USAGE_FOR_REGION"));
                        }
                    }
                }
            }

        }
        CommonResult<Object> result = new CommonResult<Object>(errors);
        return result;
    }
}
