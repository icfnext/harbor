package com.citytechinc.cq.harbor.components.content.list;

import com.google.common.base.Optional;

import java.util.List;

public interface ListComponent <RootType, ContentType> {

    public Optional<RootType> getRootOptional();

    public Boolean isHasRoot();

    public List<ContentType> getContent();

}
