package org.oregami.gamingEnvironments.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.oregami.common.CommonError;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.readmodel.withTitles.RGamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.withTitles.RTitle;
import org.oregami.gamingEnvironments.readmodel.withTitles.RTitleUsage;

public class GamingEnvironmentValidatorTest {


    @Test
    public void checkSubRegions() {
        RGamingEnvironment g = new RGamingEnvironment("ge1", "text");
        RTitle t1 = new RTitle("t1", "ts1", "text1");
        RTitleUsage tu1 = new RTitleUsage("id", Region.EUROPE);
        t1.getTitleUsages().add(tu1);
        g.getGametitles().add(t1);

        GamingEnvironmentValidator v = new GamingEnvironmentValidator();

        AddTitleUsageCommand c1 = new AddTitleUsageCommand("tu1", "ge1", "t1", Region.USA);
        CommonError error1 = v.checkSubRegions(g, c1);
        Assert.assertThat(error1, Matchers.nullValue());

        AddTitleUsageCommand c2 = new AddTitleUsageCommand("tu1", "ge1", "t1", Region.GERMANY);
        CommonError error2 = v.checkSubRegions(g, c2);
        Assert.assertThat(error2, Matchers.notNullValue());
        Assert.assertThat(error2.getMessageName(), Matchers.is(GamingEnvironmentMessages.REGIONS_NOT_CONSISTENT.name()));


    }

    @Test
    public void checkForDuplicates() {
        RGamingEnvironment g = new RGamingEnvironment("ge1", "text");
        RTitle t1 = new RTitle("t1", "ts1", "text1");
        RTitleUsage tu1 = new RTitleUsage("id", Region.EUROPE);
        t1.getTitleUsages().add(tu1);
        g.getGametitles().add(t1);

        GamingEnvironmentValidator v = new GamingEnvironmentValidator();

        AddTitleUsageCommand c1 = new AddTitleUsageCommand("tu1", "ge1", "t1", Region.EUROPE);
        CommonError error1 = v.checkForDuplicates(g, c1);
        Assert.assertThat(error1, Matchers.notNullValue());
        Assert.assertThat(error1.getMessageName(), Matchers.is(GamingEnvironmentMessages.DUPLICATE_TITLE_USAGE_FOR_REGION.name()));


        AddTitleUsageCommand c2 = new AddTitleUsageCommand("tu2", "ge1", "t1", Region.USA);
        CommonError error2 = v.checkForDuplicates(g, c2);
        Assert.assertThat(error2, Matchers.nullValue());

    }
}