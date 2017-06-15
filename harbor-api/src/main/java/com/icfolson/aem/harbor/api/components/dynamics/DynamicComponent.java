package com.icfolson.aem.harbor.api.components.dynamics;

public interface DynamicComponent {

    public static final String ALLOWED_LIST_ITEMS = "icf:allowedDynamicTypes";

    public String getItemResourceType();

}
