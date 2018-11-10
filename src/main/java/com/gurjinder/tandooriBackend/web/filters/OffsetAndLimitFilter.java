package com.gurjinder.tandooriBackend.web.filters;

public class OffsetAndLimitFilter {

    private int offset;
    private int limit;

    public OffsetAndLimitFilter() {
    }

    public OffsetAndLimitFilter(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
