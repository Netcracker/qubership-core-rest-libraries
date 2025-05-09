package org.qubership.cloud.log.manager.common.logback;

import org.qubership.cloud.log.manager.common.LogUtils;
import org.qubership.cloud.log.manager.common.LoggingSystem;
import org.qubership.cloud.log.manager.common.LoggingSystemProvider;

public class LogbackLoggingSystemProvider implements LoggingSystemProvider {

    private final boolean PRESENT;

    public LogbackLoggingSystemProvider() {
        PRESENT = LogUtils
                .isPresent("ch.qos.logback.classic.LoggerContext", LogbackLoggingSystemProvider.class.getClassLoader());
    }

    // only for testing purpose
    LogbackLoggingSystemProvider(boolean present) {
        PRESENT = present;
    }

    @Override
    public LoggingSystem provide() {
        if (PRESENT) {
            return new LogbackLoggingSystem();
        }
        return null;
    }
}
