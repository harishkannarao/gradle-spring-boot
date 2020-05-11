package com.harishkannarao.springboot.gradledemo.logback;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;

public class LogbackNewLineConverter extends ThrowableProxyConverter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String NEW_LINE_REPLACEMENT = "";

    @Override
    protected String throwableProxyToString(IThrowableProxy tp) {
        return super.throwableProxyToString(tp).replaceAll(NEW_LINE, NEW_LINE_REPLACEMENT);
    }
}