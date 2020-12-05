package com.mycompany.iach7.api.auditing;

import java.io.Serializable;
import java.util.Objects;

/**
 * Message object containing from/to date values
 */
public class DtIntervalMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.from);
        hash = 23 * hash + Objects.hashCode(this.to);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DtIntervalMessage other = (DtIntervalMessage) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DtIntervalMessage{" + "from=" + from + ", to=" + to + '}';
    }
}
