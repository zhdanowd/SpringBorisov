package com.zhdanov.bpp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyDeprecated {
    Class newClass();
}
