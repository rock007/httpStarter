package com.zheng.spider.health;

import org.jdbi.v3.core.Jdbi;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
	
    private final Jdbi jdbi;

    public DatabaseHealthCheck(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    protected Result check() throws Exception {
        //if (database.isConnected()) {
            return Result.healthy();
        //} else {
        //    return Result.unhealthy("Cannot connect to " + jdbi..g.getUrl());
        //}
        
    }
}