package com.softserveinc.ita.pageobjects;

import Elements.AbstractBaseElement;

public abstract class AbstractBasePage {

    private AbstractBaseElement uniqPageElement;

    public AbstractBasePage(AbstractBaseElement uniqPageElement) {
        this.uniqPageElement = uniqPageElement;
    }

    public boolean isDisplaidPage() {
        if (uniqPageElement.isDisplayedElements()) {

            return true;
        } else {

            return false;
        }
    }
}
