package com.icfolson.aem.harbor.api.components.mixins.inheritable;

public interface Inheritable {

    /**
     * Indicates whether the inheritable component should inherit its properties from a parent
     *
     * @return true if properties of the component instance are to be inherited from parent pages, false otherwise
     */
    boolean isInherits();

}
