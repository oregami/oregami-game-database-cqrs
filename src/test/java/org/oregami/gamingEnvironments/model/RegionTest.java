package org.oregami.gamingEnvironments.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegionTest {

    @Test
    public void isSubRegionOf() {

        Assert.assertTrue(Region.isSubRegionOf(Region.WORLDWIDE, Region.EUROPE));
        Assert.assertTrue(Region.isSubRegionOf(Region.WORLDWIDE, Region.ASIA));
        Assert.assertTrue(Region.isSubRegionOf(Region.WORLDWIDE, Region.GERMANY));
        Assert.assertTrue(Region.isSubRegionOf(Region.WORLDWIDE, Region.USA));
        Assert.assertTrue(Region.isSubRegionOf(Region.EUROPE, Region.GERMANY));
        Assert.assertTrue(Region.isSubRegionOf(Region.EUROPE, Region.GREAT_BRITAIN));
        Assert.assertTrue(Region.isSubRegionOf(Region.ASIA, Region.JAPAN));

        Assert.assertFalse(Region.isSubRegionOf(Region.WORLDWIDE, Region.WORLDWIDE));
        Assert.assertFalse(Region.isSubRegionOf(Region.GERMANY, Region.EUROPE));
        Assert.assertFalse(Region.isSubRegionOf(Region.EUROPE, Region.WORLDWIDE));
        Assert.assertFalse(Region.isSubRegionOf(Region.ASIA, Region.GERMANY));
        Assert.assertFalse(Region.isSubRegionOf(Region.JAPAN, Region.USA));
    }
}