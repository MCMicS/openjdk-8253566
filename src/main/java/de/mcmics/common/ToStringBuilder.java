package de.mcmics.common;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ToStringBuilder {

    private static final String CONNECTOR = "=";
    private static final String NULL = "<null>";
    private static final String SEP = ", ";
    private static final String NEWLINE_SEP = ",\n";

    private final StringBuilder mBuilder = new StringBuilder();

    private boolean sep = false;
    private final String sepToUse;

    public ToStringBuilder(Object object) {
        this(object, false);
    }

    public ToStringBuilder(Object object, boolean useLineBreak) {
        sepToUse = useLineBreak ? NEWLINE_SEP : SEP;
        if (object == null) {
            mBuilder.append(NULL);
        } else {
            mBuilder.append(object.getClass().getSimpleName());
        }
        mBuilder.append(" [");
    }

    public ToStringBuilder append(String name, long value) {
        sep();
        append(name).append(CONNECTOR).append(value);
        return this;
    }

    public ToStringBuilder append(String name, Object value) {
        sep();
        append(name).append(CONNECTOR).append(value);
        return this;
    }

    public ToStringBuilder append(String name, String value) {
        sep();
        append(name).append(CONNECTOR).append(value);
        return this;
    }

    @Override
    public String toString() {
        mBuilder.append("]");
        return mBuilder.toString();
    }

    private ToStringBuilder append(Object value) {
        if (value == null) {
            mBuilder.append(NULL);
        } else {
            if (value.getClass().isArray()) {
                mBuilder.append("[");
                mBuilder.append(Arrays.stream((Object[]) value).map(Object::toString)
                    .collect(Collectors.joining(SEP)));
                mBuilder.append("]");
            } else {
                mBuilder.append(value.toString());
            }
        }
        return this;
    }

    private ToStringBuilder append(String value) {
        if (value == null) {
            mBuilder.append(NULL);
        } else {
            mBuilder.append(value);
        }
        return this;
    }

    private void sep() {
        if (sep) {
            append(sepToUse);
        } else {
            sep = true;
        }
    }

}
