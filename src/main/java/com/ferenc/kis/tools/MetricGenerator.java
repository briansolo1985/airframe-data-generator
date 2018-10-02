package com.ferenc.kis.tools;

import java.util.*;
import java.util.stream.Collectors;

public class MetricGenerator implements ClockSubscriber {

    private static final List<String> TEMPLATES = Arrays.asList(
            "cortina.pageview.platform.{PLATFORM}.marketingchannel.{MARKETINGCHANNEL}.posa.{POSA}.resolution.{RESOLUTION}",
            "cortina.reservation.platform.{PLATFORM}.marketingchannel.{MARKETINGCHANNEL}.posa.{POSA}.resolution.{RESOLUTION}",
            "cortina.session.platform.{PLATFORM}.marketingchannel.{MARKETINGCHANNEL}.posa.{POSA}.resolution.{RESOLUTION}",
            "cortina.errorcode.platform.{PLATFORM}.marketingchannel.{MARKETINGCHANNEL}.posa.{POSA}.errorcode.{ERRORCODE}.resolution.{RESOLUTION}",
            "pda.{DATACENTER}.{NODE}.{METRIC}.{MEASURE}",
            "styx.{DATACENTER}.{NODE}.{METRIC}.{MEASURE}"
    );

    private static final Map<String, Set<String>> DIMENSIONS = new HashMap<String, Set<String>>() {{
        this.put("PLATFORM", Arrays.asList("Desktop", "Mobile").stream().collect(Collectors.toSet()));
        this.put("MARKETINGCHANNEL", Arrays.asList("Default", "TMS").stream().collect(Collectors.toSet()));
        this.put("POSA", Arrays.asList("HU", "UK", "CN").stream().collect(Collectors.toSet()));
        this.put("RESOLUTION", Arrays.asList("1m").stream().collect(Collectors.toSet()));
        this.put("ERRORCODE", Arrays.asList("Desktop", "Mobile").stream().collect(Collectors.toSet()));
    }};

    // TODO consider resolution for creating timestamp

    @Override
    public void tickCallback(long nowEpochMillis) {

    }
}
