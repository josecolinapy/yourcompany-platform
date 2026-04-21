package com.yourcompany.platform.common.observability.support;

import com.yourcompany.platform.common.observability.model.MetricTag;

import java.util.Collection;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class MetricTagSupport {
    private MetricTagSupport() {
    }

    public static Tags toTags(final Collection<MetricTag> metricTags) {
        if (metricTags == null || metricTags.isEmpty()) {
            return Tags.empty();
        }
        return Tags.of(metricTags.stream().map(tag -> Tag.of(tag.key(), tag.value())).toList());
    }
}
