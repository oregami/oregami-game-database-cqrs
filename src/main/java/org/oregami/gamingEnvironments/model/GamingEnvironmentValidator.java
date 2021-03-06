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

import static org.oregami.gamingEnvironments.model.GamingEnvironmentMessages.DUPLICATE_TITLE_USAGE_FOR_REGION;
import static org.oregami.gamingEnvironments.model.GamingEnvironmentMessages.REGIONS_NOT_CONSISTENT;

@Component
public class GamingEnvironmentValidator {

    @Autowired
    GamingEnvironmentRepository gamingEnvironmentRepository;

    public CommonResult<Object> validate(AddTitleUsageCommand c) {
        List<CommonError> errors = new ArrayList<>();
        RGamingEnvironment gamingEnvironment = gamingEnvironmentRepository.findOne(c.getGamingEnvironmentId());

        //search for identical text
        if (errors.isEmpty()) {
            CommonError checkForDuplicates = checkForDuplicates(gamingEnvironment, c);
            if (checkForDuplicates!=null) {
                errors.add(checkForDuplicates);
            }
        }

        //check if region-subregion is ok
        CommonError checkSubRegions = checkSubRegions(gamingEnvironment, c);
        if (checkSubRegions!=null) {
            errors.add(checkSubRegions);
        }

        CommonResult<Object> result = new CommonResult<Object>(errors);
        return result;
    }

    protected CommonError checkSubRegions(RGamingEnvironment gamingEnvironment, AddTitleUsageCommand c) {
        for (RTitle t: gamingEnvironment.getGametitles()) {
            if (t.getId().equals(c.getTitleId())) {
                for (RTitleUsage tu: t.getTitleUsages()) {
                    if (Region.isSubRegionOf(tu.getRegion(), c.getRegion()) || Region.isSubRegionOf(c.getRegion(), tu.getRegion())) {
                        return (new CommonError(new CommonErrorContext("region", c.getTitleId()), REGIONS_NOT_CONSISTENT.name()));
                    }
                }
            }
        }
        return null;
    }

    protected CommonError checkForDuplicates(RGamingEnvironment gamingEnvironment, AddTitleUsageCommand c) {
        Set<RTitle> gametitles = gamingEnvironment.getGametitles();

        for(RTitle t: gametitles) {
            if (t.getId().equals(c.getTitleId())) {
                for (RTitleUsage tu : t.getTitleUsages()) {
                    if (tu.getRegion().equals(c.getRegion())) {
                        return (new CommonError(new CommonErrorContext("region", c.getTitleId()), DUPLICATE_TITLE_USAGE_FOR_REGION.name()));
                    }
                }
            }
        }
        return null;
    }


}
