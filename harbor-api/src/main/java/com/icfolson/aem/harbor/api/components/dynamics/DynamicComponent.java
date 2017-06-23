package com.icfolson.aem.harbor.api.components.dynamics;

public interface DynamicComponent {

    String ALLOWED_LIST_ITEMS = "icf:allowedDynamicTypes";

    String getItemResourceType();

}
