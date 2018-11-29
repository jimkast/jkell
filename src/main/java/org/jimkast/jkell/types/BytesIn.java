package org.jimkast.jkell.types;

public interface BytesIn {
    int accept(byte[] b, int offset, int length) throws Exception;
}
