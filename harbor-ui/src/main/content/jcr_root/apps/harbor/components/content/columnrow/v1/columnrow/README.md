# Harbor Column Row (v1)

A homogeneous row of Columns.

## Usage

Intended to support the creation and rendering of a Row of Columns.  Offers 
Javascript authoring APIs to add columns as child Resources of the Column Row 
resource.

## General Information

* `group`: Harbor Scaffolding
* `resourceType`: `harbor/components/content/columnrow/v1/columnrow`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.columnrow.ColumnRow` 
Model interface.

Each column uses the `com.icfolson.aem.harbor.api.components.content.columnrow.Cloumn` 
Model interface.

## Authoring API

`Harbor.Components.ColumnRow.v1.ColumnRow.addColumn( editable, columnType )`

Produces a new child resource of the type specified as the `columnType`.  
The editable is intended to be the editable representing the Column Row.

## HTL

* `columnrow.html` - The primary renderer for the Column Row.  Responsible for
  instantiating a `ColumnRow` instance, classifying the row itself, and 
  calling upon the `columns.html` HTL template
* `authorhelp.html` - A helper message presented above the Column Row to
  provide a clear handle to click on to bring up the instance's toolbar.
* `columns.html` - HTL Template which iterates over all `Column` instances 
  in the passed in `ColumnRow` and renders them based on the `Column`s type. 
  Takes a single `columRow` parameter intended to be the instance of 
  `ColumnRow` being rendered.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.columnrow.v1.DefaultColumnRow`

Treats all direct children of the Column Row resource as the Columns of the row 
and adapts them to `Column`s when providing a response to `List<Column> getColumns()`.

Uses the `TagBasedClassification` implementation to expose Classifiability.

