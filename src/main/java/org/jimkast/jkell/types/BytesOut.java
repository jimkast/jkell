package org.jimkast.jkell.types;

public interface BytesOut {
    void accept(byte[] b, int offset, int length) throws Exception;
}
