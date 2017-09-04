package com.icfolson.aem.harbor.core.components.content.list.assets

import com.day.cq.dam.api.Asset
import com.day.cq.dam.api.Rendition
import com.day.cq.dam.api.RenditionPicker
import com.day.cq.dam.api.Revision
import org.apache.sling.api.resource.Resource

import javax.annotation.Nonnull
import javax.jcr.RepositoryException

class MockAsset implements Asset {

    private Resource resource

    MockAsset(Resource resource) {
        this.resource = resource
    }

    @Override
    String getPath() {
        resource.path
    }

    @Override
    String getName() {
        resource.name
    }

    @Override
    String getMetadataValue(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    Object getMetadata(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    long getLastModified() {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition getRendition(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition getImagePreviewRendition() {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition getOriginal() {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition getCurrentOriginal() {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean isSubAsset() {
        throw new UnsupportedOperationException()
    }

    @Override
    Map<String, Object> getMetadata() {
        throw new UnsupportedOperationException()
    }

    @Override
    Resource setRendition(String s, InputStream inputStream, String s1) {
        throw new UnsupportedOperationException()
    }

    @Override
    void setCurrentOriginal(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    Revision createRevision(String s, String s1) throws Exception {
        throw new UnsupportedOperationException()
    }

    @Override
    List<Rendition> getRenditions() {
        throw new UnsupportedOperationException()
    }

    @Override
    Iterator<Rendition> listRenditions() {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition getRendition(RenditionPicker renditionPicker) {
        throw new UnsupportedOperationException()
    }

    @Override
    String getModifier() {
        throw new UnsupportedOperationException()
    }

    @Override
    Asset restore(String s) throws Exception {
        throw new UnsupportedOperationException()
    }

    @Override
    Collection<Revision> getRevisions(Calendar calendar) throws Exception {
        throw new UnsupportedOperationException()
    }

    @Override
    String getMimeType() {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition addRendition(String s, InputStream inputStream, String s1) {
        throw new UnsupportedOperationException()
    }

    @Override
    Rendition addRendition(String s, InputStream inputStream, Map<String, Object> map) {
        throw new UnsupportedOperationException()
    }

    @Override
    Asset addSubAsset(String s, String s1, InputStream inputStream) {
        throw new UnsupportedOperationException()
    }

    @Override
    Collection<Asset> getSubAssets() {
        throw new UnsupportedOperationException()
    }

    @Override
    void removeRendition(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    void setBatchMode(boolean b) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean isBatchMode() {
        throw new UnsupportedOperationException()
    }

    @Override
    String getMetadataValueFromJcr(String s) {
        throw new UnsupportedOperationException()
    }

    @Override
    String getID() {
        throw new UnsupportedOperationException()
    }

    @Override
    void initAssetState() throws RepositoryException {
        throw new UnsupportedOperationException()
    }

    @Override
    <AdapterType> AdapterType adaptTo(@Nonnull Class<AdapterType> clazz) {
        def adapted = null

        if (clazz == Resource) {
            adapted = resource
        }

        adapted as AdapterType
    }
}
