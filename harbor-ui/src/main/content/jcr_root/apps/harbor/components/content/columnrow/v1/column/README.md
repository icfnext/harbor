# Harbor Column (v1)

A column exposing a single Paragraph System

## Usage

Intended to be used in the context of a Column Row.

## General Information

* `resourceType`: `harbor/components/content/columnrow/v1/column`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.columnrow.ParagraphSystemColumn` 
Model interface.

## HTL

* `column.html` - The primary renderer for the Column.  Includes `authorhelp.html` 
  when in edit mode.  Renders a single paragraph system with a type based on 
  the result of the `ParagraphSystemColumn` implementation's `paragraphSystemType()`
  method.
* `authorhelp.html` - A helper message presented above the Column to
  provide a clear handle to click on to bring up the instance's toolbar.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.columnrow.v1.DefaultColumn`

Uses the `TagBasedClassification` implementation to expose Classifiability.

### Dialog Fields

Dialog fields are annotated for the following methods.

* `getXsSize()` - A dropdown selection of Bootstrap column sizes
* `getSmSize()` - A dropdown selection of Bootstrap column sizes
* `getMdSize()` - A dropdown selection of Bootstrap column sizes
* `getLgSize()` - A dropdown selection of Bootstrap column sizes
* `getMdOrdering()` - A dropdown selection of Bootstrap column offsets
