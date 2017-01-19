# Harbor 

Harbor is a collection of content and page components, services, and patterns constituting a common starting point for projects embodying a consistent philosophy of development.  From both an author and developer enablement perspective Harbor follows these tenets: 

* Cohesion
* Semantics
* Extensibility

## Internal Promotional Videos

The following videos were developed (poorly) to promote Harbor internally.

* [General Information](https://drive.google.com/open?id=0B2FwTcEm5yJ6dWh4LUZJS2pYcnM)
* [Enabling Content Reusability](https://drive.google.com/open?id=0B2FwTcEm5yJ6SzRTTUhUdlhkUXM)
* [Enabling Experience Reusability](https://drive.google.com/open?id=0B2FwTcEm5yJ6VjdZOHJrMU5zcUk)

## Components

### Harbor Title Component

* Group: Harbor

Presents the Page Title of the current page in an H1 DOM element.  The output H1 element will have an id attribute set to a sanitized version of the title text allowing for fragment linking.  Both the title text and element id may be overridden by authors.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Sets the rendered title text.  When left empty, the title text will default to the page's Page Title. |
| ID           | Sets the string ID to be attributed to the H1 element rendered for the title.  When left empty, the ID will be produced by sanitizing the title text. Indended to allow anchor linking to title elements. |

### Harbor Subtitle Component

* Group: Harbor

Presents the Page Subtitle of the current page in an H2 DOM element.  The output H2 element will have an id attribute set to a sanitized version of the subtitle text allowing for fragment linking.  Both the subtitle text and element id may be overridden by authors.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Sets the rendered subtitle text.  When left empty, the subtitle text will default to the page's Page Subtitle. |
| ID           | Sets the string ID to be attributed to the H2 element rendered for the subtitle.  When left empty, the ID will be produced by sanitizing the subtitle text. |

### Harbor Heading Component

* Group: Harbor

Presents a heading element appropriate for titling sections of content.  This component is NOT meant to be used as the principal title for content and as such only exposes heading options H2-H6.  If an H1 element is desired, the Title component should be used.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Size         | Establishes the size or level of the Heading.  Available sizes include H2 - H6.  If an H1 element is desired the Title component should be used instead. |
| Text         | Sets the rendered heading text. |
| ID           | Sets the string ID to be attributed to the rendered heading element.  When left empty, the ID will be produced by sanitizing the heading text. |

### Text Component

* Group: Harbor
* Classifiable

The Harbor Text component will allow the author to compose text within a Rich Text Editor configured to enable commonly used plugins.

#### Authorability

Text is edited via the text in-place editor.

| Dialog Field | Description |
| ------------ | ----------- |
| Classification | Input support for classifiability of the text component instance. |

### Call to Action Component

* Group: Harbor

Presents a Call to Action button which, when pressed, directs the user to a specified page path or external URL.

#### Authorability 

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Text to present as the button label |
| Link Target  | The page path or external URL the user should be directed to upon clicking the button |
| Size         | The button size |
| Style        | The button style - see [Button Options](http://getbootstrap.com/css/#buttons-options) for more information | 
| Action       | Selection of whether to present the new page in the same window or a new window then the button is clicked |

### Modal Call to Action Component

* Group: Harbor

### Accordion Group Component

* Group: Harbor

Presents a group of Accordion Items allowing for the ad-hoc addition of new items.  Each Accordion Item exposes a Paragraph System into which any number of components may be placed.  When in edit mode an author help message will appear indicating this and making it easy to select the Accordion Group in Touch UI.

#### Authorability

Accordion items are added using the "Add Experience" button in the toolbar of the Accordion Group Component.
  
When viewed in a non Preview / Publish mode, all Accordion Items will be expanded to expose their content to authors.

##### Accordion Group Authoring

| Dialog Field | Description | 
| ------------ | ----------- |
| Open First Item | Indicates whether the first Accordion Item in the Accordion Group whould be opened by default. |

##### Accordion Item Authoring

Each Accordion Item in an Accordion Group exposes its own Paragraph System to which any number of components may be added using normal authoring practices.  While authoring all Accordion Items will be opened to allow for modification.  When in Preview or Publish mode the Accordion Group will operate as a collection of collapsible elements.

The order of established Accordion Items may be modified using the up and down arrows presented in the Toolbar of a given Accordion Item.  Pressing the up button for the first Accordion Item in an Accordion Group or the down button for the last Accordion Item in an Accordion Group will have no effect.

Accordion Items may be removed completely by deleting them using the Delete option presented in the Toolbar for the individual Accordion Item.

| Dialog Field | Description | 
| ------------ | ----------- |
| Title | Text title to present for the Accordion Item. |

### Container Component

* Group: Harbor Scaffolding
* Classifiable
* Container



### Harbor Column Row Component

* Group: Harbor Scaffolding
* Classifiable

The Harbor Column Row component lets authors flexibly create and layout any number of columns using the Bootstrap grid system.

Columns MUST be placed in a Container component.  See Containers under Core Concepts for more information.

#### Authorability

New columns are added using the "Add Experience" button in the toolbar of the Column Row component.  When in edit mode an author help message will appear indicating this and making it easy to select the Column Row in Touch UI.

Responsiveness of the columns as defined by the Bootstrap grid system can be tested either using the AEM Device emulation mode, the AEM Layout mode, or by resizing your browser window.

##### Row Authoring

| Dialog Field | Description | 
| ------------ | ----------- |
| ID           | A unique identifier for the Row.  If no ID is set no id attribute will be rendered |
| Classification | Input support for classifiability of the row component instance.  Classifies the entire row |

##### Column Authoring

Each Column in a Row is authored independently.  

Many of the inputs control the width and positioning of  the column.  See the Bootstrap documentation concerning the [Grid System](http://getbootstrap.com/css/#grid) for more information on these inputs.

The rendered content of a column is a Paragraph System into which arbitrary components may be placed.  

While it is not recommended due to the various complexities it introduces in authorability, Column Row components may be nested inside Columns allowing extremely complex layouts.

| Dialog Field | Description | 
| ------------ | ----------- |
| Extra Small Device Width | Width of column when presented on extra small devices.  Defaults to none. |
| Small Device Width | Width of column when presented on a small device.  Defaults to none. |
| Medium Device Width | Width of column when presented on a medium device. Defaults to none. |
| Large Device Width | Width of column when presented on a large device.  Defaults to none. |
| Ordering | Indicates whether to push or pull the column to force a certain ordering.  Defaults to none. |
| Classification | Input support for classifiability of the column component instance.  Classifies the single column within the row. |
| Inherit Content | When set to Yes the Paragraph System for the column will be rendered as an Inheriting Paragraph System | 
| ID | A unique identifier for the Column.  If no ID is set no id attribute will be rendered |


### Harbor Link List Component

* Group: Harbor Lists
* Classifiable

Presents an author composed lists of links.  Links may be references to internal AEM pages or to external URLs.

#### Authorability

The addition of new list items is enabled by the "+" button in the toolbar of the Link List component.

When adding new items to an already populated list you will commonly need to tap on a list item, choose the "select parent" option from the toolbar, and then choose the Link List component from the dropdown list in order to be presented with the edit bar for the list itself.

##### List

| Dialog Field | Description |
| ------------ | ----------- |
| Classification | Input support for classifiability of the list component instance.  Classifies the entire list |

##### List Item

Each item in the list is rendered and authored as its own component.

Items in the list can be moved up or down in the list via the up and down buttons in the List Item's Toolbar.

| Dialog Field | Description |
| ------------ | ----------- |
| Title        | Presentable title to associated with the item in the list. |
| Path         | The path to an internal AEM page or the URL to an external page which the item in the list should link to. |

#### Developer Guide

The Link List component maintains a collection of List Items as direct child Resources.  Author-only Javascript under the namespace `Harbor.Components.LinkList` facilitates the addition and movement of List Items.

| Aspect | Value |
| ------------ | ----------- |
| Resource Type | harbor/components/content/lists/linklist |
| Component Group | Harbor Lists |
| Backing Class | com.icfolson.aem.harbor.core.components.content.list.link.LinkList |
| List Item Resource Type | harbor/components/content/lists/linklist/listablelink |
| List Item Backing Class | com.icfolson.aem.harbor.core.components.content.list.link.ListableLink |


## Core Concepts

### End User Core Concepts

#### Containers

Many of the elements of Bootstrap require placement within a Container.  In Harbor there are numerous Container Components which produce such a Container suitable for placing other components within.  These are identified by the `Container` demarcation in the documentation.  

For example, the Content Container Component is a basic Container.

General information about Containers in Bootstrap can be found [in the Bootstrap Container documentation](http://getbootstrap.com/css/#overview-container).

#### Classification

Many components in Harbor are "Classifiable".  This allows for the application of semantics to an 
otherwise general component.  For example, if you are producing a recipe, you might classify your 
Container component using a `Recipe` tag.  Systematically, for most components, this classification 
simply applies an additional class attribute to the rendered HTML for the component which allows 
for extended usage such as targeting of styles or unique indexability.

Classifications are always authored as Tags.

### Developer Core Concepts

#### Lists

The functionality of many of the Components created for a particular project can be distilled down to the presentation of a List of items.  Lists therefore are central to Harbor and enabled by Interfaces and Abstract Classes suggesting a pattern to follow in List Component development.

List Components are a composition of a List Construction Strategy and a List Rendering Strategy.  The List Construction Strategy is responsible for the creation of a list of items based on the logic of the strategy.  These items are passed to the List Rendering Strategy which has an opportunity to add additional properties germane to the rendering of a particular component.  Construction and Rendering Strategies may be mixed and matched within the context of concrete List Component implementations allowing reuse of both across multiple component types.

TODO: Insert example of reuse from the various page listing components

TODO: Link to Javadocs where appropriate

#### Trees

Similar to Lists, Trees pair a Construction Strategy with a Rendering Strategy allowing for reusability and extention via composition.

A Tree has a single TreeNode instance as its root.  A TreeNode may in turn have any number of child TreeNodes of the same type.

Tree Components are in turn components which intend to manage a Tree data structure's construction and presentation.

TODO: Link to Javadocs where appropriate

#### Classification

Components are Classifiable if they contain an `icf:classification` input which is of type Tag Field. 
What a component does with the classification is up to the needs and requirements of the component. 
For all current Harbor components, classification causes additional class attributes to be written 
to the rendered HTML of the component.  This allows for the application of style and functionality 
to sections of a site without the need to built numerous custom components.  For example, a Recipe 
component could be made up of a Content Container with an appropriate classification.  Anything 
under that container then is semantically considered to be part of the recipe.  It may be appropriate 
to further classify child elements such as Recipe Step or Ingredient depending on the granular 
needs of the site and system.








#OLD

## Lists

Many of the components which are built for a particular project are, at their core, a list of things.  The concern
of such components then is three-fold.

1. Construct the list of things germane to the instance of the component
2. Transform the list of things found in step one into a list of things ready for rendering (in practice this step is often combined with step 1)
3. Produce a rendering or visualization of the transformed list of things

The List API proposed by Harbor encapsulates each step allowing for the development of new components based on a composition
of implementations of the three steps listed above.

### Step 1: List Construction and the ListConstructionStrategy

Implementations of the ListConstructionStrategy cover Step 1 of the list workflow laid-out above.

```java
public interface ListConstructionStrategy <T> {

    Iterable<T> construct();

}
```

Such an implementation exposes a single `construct` method which produces a generic `Iterable`.  An implementation would
generally encapsulate the logic necessary to build up the list of things of interest to the component based on any number
of component-specific mechanisms.  The generic type is intended to be a domain object unencumbered with properties specific
to its presentation.

### Step 2: List Transformation and the ListRenderingStrategy

Once a List of domain objects has been constructed by a ListConstructionStrategy, this list can be fed into a
ListRenderingStrategy.

```java
public interface ListRenderingStrategy <T, R extends Iterable<?>> {

    public R toRenderableList(Iterable<T> itemIterable);

}
```

Implementations of the ListRenderingStrategy expose a single `toRenderableList` method which takes as input the output
from a ListConstructionStrategy's `construct` method and produces an Iterable of some type.  In the generic signature,
implementations must provide the type produced by the ListConstructionStrategy and a type of Iterable to be returned.
As such, developers of implementations are free to define their own Iterable implementations which is useful if the
transformed list needs to contain information germane to the entire list and not just the individual elements (for example,
if the list's ability to render as an ordered or unordered list is authorable, this information is relevant to the entire
list and not the individual items).  The elements of the transformed list should expose methods proper to the rendering of the element.

### Step 3: List Visualization

Visualization of a list is left to the rendering mechanism employed, be it a .jsp, a servlet returning json, or any other
mechanic.  At a high level, rendering involves iterating over the Iterable produced by the `ListRenderingStrategy` and
rendering each item as necessary.

#### The RenderableItem Interface

Implementations of the `RenderableItem` interface represent list items which are able to produce their own rendering.

```java
public interface RenderableItem {

    public String render();

}
```

An implementation of `RenderableItem` exposes a single `render` method which is intended to return a String representation
of the item.  If appropriate, an implementation of this interface may be returned as the members of the Iterable produced
by the `ListRenderingStrategy`'s `toRenderableList` method after which rendering mechanisms may simply call the `render` method
on each item to produce a rendering.

### List APIs and the Component Plugin

Using these APIs, new List components are made of a composition of a `ListConstructionStrategy` and a `ListRenderingStrategy`.  This
composition approach also allows for the creation of an authoring dialog via composition.  By attributing a `@DialogFieldSet` annotation
to both the `ListConstructionStrategy` and the `ListRenderingStrategy` and further annotating the members of these strategies with
appropriate `@DialogField` annotations, both your component and your authoring can be composed.  The
`com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.mainnavigation.BootstrapMainAutoNavigation`
component is a reasonable exemplar of this type of implementation.

### The ListComponent and AbstractListComponent

The `ListComponent` Interface and `AbstractListComponent` abstract class establish a general pattern for list component
composition.

```java
public interface ListComponent <T extends Iterable> {

    public T getItems();

    public Iterator<?> getIterator();

}
```

A `ListComponent` is known to produce some manner of Iterable.  This mirrors what the `ListRenderingStrategy` is able
to produce.  As a convenience, a `ListComponent` implementation will expose a `getIterator` method which is useful if
rendering using .jsp since the jstl `forEach` tag can iterate over an Iterator but not over an Iterable.

The 'AbstractListComponent` abstract class represents the super class of a standard List component.  Among other things it
exposes two abstract methods.

```java
protected abstract ListConstructionStrategy<T> getListConstructionStrategy();

protected abstract ListRenderingStrategy<T, R> getListRenderingStrategy();
```

These methods respecively return the `ListConstructionStrategy` and `ListRenderingStrategy` which the component employ.
The `AbstractListComponent` implements the `ListComponent` interface.  The object returned by the `AbstractListComponent`s
`getItems` method is the result of calling the rendering strategy's `toRenderableList` method on the results of the construction
 strategy's `construct` method.

*More Documentation Coming*