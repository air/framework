package com.aaronbell.slingshot;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.Status;
import org.apache.mesos.Protos.FrameworkInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slingshot main.
 *
 * @author aaron
 */
public class SlingshotMain {
    private static final Logger log = LoggerFactory.getLogger(SlingshotMain.class);

    private static final double FRAMEWORK_FAILOVER_TIMEOUT_S = 60 * 60 * 24; // 1 day
    private static final String FRAMEWORK_NAME = "Slingshot";
    private static final String FRAMEWORK_USER = ""; // FrameworkInfo.user is a required protobuf field
    // TODO no Principal yet

    private static String mesosUrl = null; // populated from command line

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(1);
        } else {
            mesosUrl = args[0];
        }

        // 1. Define a Scheduler
        Scheduler scheduler = new Scheduler();
        log.info("scheduler: {}", scheduler);

        // 2. Build a FrameworkInfo
        FrameworkInfo frameworkInfo = FrameworkInfo.newBuilder()
                .setFailoverTimeout(FRAMEWORK_FAILOVER_TIMEOUT_S)
                .setName(FRAMEWORK_NAME)
                .setUser(FRAMEWORK_USER)
                .build();
        log.info("framework: {}", frameworkInfo);

        // 3. Define a driver
        MesosSchedulerDriver driver = new MesosSchedulerDriver(scheduler, frameworkInfo, mesosUrl);

        // 4. Run the driver
        log.info("running driver: {}", driver);
        Status status = driver.run();
        log.info("status after driver.run: {}", status);

        // 5. And stop it
        log.info("stopping driver");
        status = driver.stop(); // TODO remember failover flag
        log.info("status after driver.stop: {}", status);

        System.exit(0);
    }

    private static void usage() {
        String name = SlingshotMain.class.getName();
        System.err.println(String.format("Usage: %s <master URL>", name));
    }
}
