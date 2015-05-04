package com.aaronbell.slingshot;

import org.apache.mesos.Protos.*;
import org.apache.mesos.SchedulerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Scheduler is a scheduler.
 *
 * @author aaron
 */
public class Scheduler implements org.apache.mesos.Scheduler {
    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Override
    public void registered(SchedulerDriver driver, FrameworkID frameworkId, MasterInfo masterInfo) {
        log.info("registered");
    }

    @Override
    public void reregistered(SchedulerDriver driver, MasterInfo masterInfo) {
        log.info("re-registered");
    }

    @Override
    public void resourceOffers(SchedulerDriver driver, List<Offer> offers) {
        log.info("resource offers");
    }

    @Override
    public void offerRescinded(SchedulerDriver driver, OfferID offerId) {
        log.info("offer rescinded");
    }

    @Override
    public void statusUpdate(SchedulerDriver driver, TaskStatus status) {
        log.info("status update");
    }

    @Override
    public void frameworkMessage(SchedulerDriver driver, ExecutorID executorId, SlaveID slaveId, byte[] data) {
        log.info("framework message");
    }

    @Override
    public void disconnected(SchedulerDriver driver) {
        log.info("disconnected");
    }

    @Override
    public void slaveLost(SchedulerDriver driver, SlaveID slaveId) {
        log.info("slave lost");
    }

    @Override
    public void executorLost(SchedulerDriver driver, ExecutorID executorId, SlaveID slaveId, int status) {
        log.info("executor lost");
    }

    @Override
    public void error(SchedulerDriver driver, String message) {
        log.info("error");
    }
}
