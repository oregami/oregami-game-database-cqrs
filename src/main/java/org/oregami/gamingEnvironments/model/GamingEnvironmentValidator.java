package org.oregami.gamingEnvironments.model;

import org.oregami.common.CommonError;
import org.oregami.common.CommonErrorContext;
import org.oregami.common.CommonResult;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.readmodel.withTitles.RGamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.withTitles.RTitle;
import org.oregami.gamingEnvironments.readmodel.withTitles.RTitleUsage;
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
            RGamingEnvironment gamingEnvironment = gamingEnvironmentRepository.findOne(c.getGamingEnvironmentId());
            Set<RTitle> gametitles = gamingEnvironment.getGametitles();

            for(RTitle t: gametitles) {
                if (t.getId().equals(c.getTitleId())) {
                    for (RTitleUsage tu : t.getTitleUsages()) {
                        if (tu.getRegion().equals(c.getRegion())) {
                            errors.add(new CommonError(new CommonErrorContext("region", c.getTitleId()), "DUPLICATE_TITLE_USAGE_FOR_REGION"));
                        }
                    }
                }
            }

        }
        CommonResult<Object> result = new CommonResult<Object>(errors);
        return result;
    }
}
