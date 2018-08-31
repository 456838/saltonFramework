package com.salton123.log;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.DiskLogStrategy;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.salton123.util.AppUtils;
import com.salton123.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.orhanobut.logger.Logger.ASSERT;
import static com.orhanobut.logger.Logger.DEBUG;
import static com.orhanobut.logger.Logger.ERROR;
import static com.orhanobut.logger.Logger.INFO;
import static com.orhanobut.logger.Logger.VERBOSE;
import static com.orhanobut.logger.Logger.WARN;


/**
 * User: newSalton@outlook.com
 * Date: 2018/6/15 下午4:14
 * ModifyTime: 下午4:14
 * Description:
 */
public class SaltonFormatStrategy implements FormatStrategy {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String NEW_LINE_REPLACEMENT = " <br> ";
    private static final String SEPARATOR = ",";

    @NonNull
    private final Date date;
    @NonNull
    private final SimpleDateFormat dateFormat;
    @NonNull
    private final LogStrategy logStrategy;
    @Nullable
    private final String tag;

    private SaltonFormatStrategy(@NonNull SaltonFormatStrategy.Builder builder) {
        AppUtils.checkNotNull(builder);
        date = builder.date;
        dateFormat = builder.dateFormat;
        logStrategy = builder.logStrategy;
        tag = builder.tag;
    }

    @NonNull
    public static SaltonFormatStrategy.Builder newBuilder() {
        return new SaltonFormatStrategy.Builder();
    }

    @Override
    public void log(int priority, @Nullable String onceOnlyTag, @NonNull String message) {
        AppUtils.checkNotNull(message);

        String tag = formatTag(onceOnlyTag);

        date.setTime(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();

        // machine-readable date/time
        builder.append(Long.toString(date.getTime()));

        // human-readable date/time
        builder.append(SEPARATOR);
        builder.append(dateFormat.format(date));

        // level
        builder.append(SEPARATOR);
        builder.append(logLevel(priority));

        // tag
        builder.append(SEPARATOR);
        builder.append(tag);

        // message
        if (message.contains(NEW_LINE)) {
            // a new line would break the CSV format, so we replace it here
            message = message.replaceAll(NEW_LINE, NEW_LINE_REPLACEMENT);
        }
        builder.append(SEPARATOR);
        builder.append(message);

        // new line
        builder.append(NEW_LINE);

        logStrategy.log(priority, tag, builder.toString());
    }

    @Nullable
    private String formatTag(@Nullable String tag) {
        if (!StringUtils.isEmpty(tag) && !StringUtils.isEquals(this.tag, tag)) {
            return this.tag + "-" + tag;
        }
        return this.tag;
    }

    public static final class Builder {
        private static final int MAX_BYTES = 500 * 1024; // 500K averages to a 4000 lines per file

        Date date;
        SimpleDateFormat dateFormat;
        LogStrategy logStrategy;
        String tag = "PRETTY_LOGGER";

        private Builder() {
        }

        @NonNull
        public SaltonFormatStrategy.Builder date(@Nullable Date val) {
            date = val;
            return this;
        }

        @NonNull
        public SaltonFormatStrategy.Builder dateFormat(@Nullable SimpleDateFormat val) {
            dateFormat = val;
            return this;
        }

        @NonNull
        public SaltonFormatStrategy.Builder logStrategy(@Nullable LogStrategy val) {
            logStrategy = val;
            return this;
        }

        @NonNull
        public SaltonFormatStrategy.Builder tag(@Nullable String tag) {
            this.tag = tag;
            return this;
        }

        @NonNull
        public SaltonFormatStrategy build() {
            if (logStrategy == null) {
                logStrategy = new LogcatLogStrategy();
            }
            return new SaltonFormatStrategy(this);
        }
    }

    static String logLevel(int value) {
        switch (value) {
            case VERBOSE:
                return "VERBOSE";
            case DEBUG:
                return "DEBUG";
            case INFO:
                return "INFO";
            case WARN:
                return "WARN";
            case ERROR:
                return "ERROR";
            case ASSERT:
                return "ASSERT";
            default:
                return "UNKNOWN";
        }
    }
}
