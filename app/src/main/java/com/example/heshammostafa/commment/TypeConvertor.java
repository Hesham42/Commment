package com.example.heshammostafa.commment;

/**
 * Created by HeshamMostafa on 8/7/2017.
 */

public class TypeConvertor {
    private final Object whatNeedToCast;

    public static TypeConvertor cast(Object whatNeedToCast) {
        return new TypeConvertor(whatNeedToCast);
    }

    private TypeConvertor(Object whatNeedToCast) {
        this.whatNeedToCast = whatNeedToCast;
    }

    public <OtherType> OtherType to(Class<OtherType> toWhichToCast) {
        try {
            return (OtherType) whatNeedToCast;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    whatNeedToCast.toString() + " must implement " + toWhichToCast.getName()
            );
        }
    }
}
