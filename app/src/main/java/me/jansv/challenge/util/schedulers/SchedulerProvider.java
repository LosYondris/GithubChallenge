package me.jansv.challenge.util.schedulers;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler io();

    Scheduler ui();

    Scheduler computation();
}
