package com.zheng.spider.health;

import com.codahale.metrics.health.HealthCheck;
import com.zheng.spider.core.Template;

import java.util.Optional;

public class TemplateHealthCheck extends HealthCheck {
	
    private final Template template;

    public TemplateHealthCheck(Template template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"));
        template.render(Optional.empty());
        
        return Result.healthy();
    }
}
