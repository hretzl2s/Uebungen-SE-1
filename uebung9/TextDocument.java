package org.hbrs.se1.ws22.uebung9;

import java.io.UnsupportedEncodingException;

public class TextDocument extends CoreDocument{
    public enum Encoding {
        UTF8 {
            public String toString() {
                return "UTF-8";
            }
        },
        UTF16 {
            public String toString() {
                return "UTF-16";
            }
        },
        UTF32 {
            public String toString() {
                return "UTF-32";
            }
        }
    }
    private String inhalt;
    private Encoding encoding;

    public TextDocument(String inhalt, Encoding encoding) {
        this.inhalt = inhalt;
        this.encoding = encoding;
    }

    public int calculateBytes() {
        int ergebnis = 0;
        try {
            switch (encoding) {
                case UTF8:
                    ergebnis = inhalt.getBytes(encoding.toString()).length;
                case UTF16:
                    ergebnis = inhalt.getBytes(encoding.toString()).length;
                case UTF32:
                    ergebnis = inhalt.getBytes(encoding.toString()).length;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return ergebnis;
    }
}
