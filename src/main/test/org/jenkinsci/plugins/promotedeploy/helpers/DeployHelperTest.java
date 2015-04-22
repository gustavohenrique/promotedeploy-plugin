package org.jenkinsci.plugins.promotedeploy.helpers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.jenkinsci.plugins.promotedeploy.models.Deploy;
import org.junit.Test;

public class DeployHelperTest {

    @Test
    public void testOrderListByDate() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2015, Calendar.JANUARY, 1);
        Deploy d1 = new Deploy();
        d1.setTimeInMillis(cal1.getTimeInMillis());
        d1.setId("Deploy in January");
        
        Deploy d2 = new Deploy();
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2015, Calendar.APRIL, 10);
        d2.setTimeInMillis(cal2.getTimeInMillis());
        d2.setId("Deploy in April");
        
        Deploy d3 = new Deploy();
        Calendar cal3 = Calendar.getInstance();
        cal3.set(2015, Calendar.MARCH, 5);
        d3.setTimeInMillis(cal3.getTimeInMillis());
        d3.setId("Deploy in March");
        
        List<Deploy> deploys = Arrays.asList(d1, d2, d3);

        List<Deploy> ordered = DeployHelper.orderByDateAsc(deploys);
        assertEquals(ordered.get(0).getId(), d1.getId());
        assertEquals(ordered.get(1).getId(), d3.getId());
        assertEquals(ordered.get(2).getId(), d2.getId());
        
        ordered = DeployHelper.orderByDateDesc(deploys);
        assertEquals(ordered.get(0).getId(), d2.getId());
        assertEquals(ordered.get(1).getId(), d3.getId());
        assertEquals(ordered.get(2).getId(), d1.getId());
    }

}
