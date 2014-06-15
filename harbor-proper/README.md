# Harbor Proper

The API, Components, Templates, and Services which constitute the core of Harbor. 

_More Documentation Coming_

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

    public Iterable<T> construct();

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

#### The harbor:includeListItems JSP Tag

The `harbor:includeListItems` tag is provided as a rendering convenience for those components using .jsp as their
rendering mechanism.

Attribute Name | Attribute Type | Required | Description
-------------- | -------------- | -------- | -----------
items          | Iterable<?>    | true     | The Iterable of items generally produced by a `ListRenderingStrategy`
itemVar        | String         | false    | If using a script for rendering, this is the variable name by which the current item may be referred to
script         | String         | false    | A relative reference to a .jsp script which should be used to render each item.  This is required if your items do not implement `RenderableItem`.

The tag will iterate over all items in the Iterable provided rendering each in turn.  If a `script` attribute is specified,
the tag will use the specified .jsp script to render the individual items.  Within the context of said script the item's name
is the name provided in the `itemVar` attribute of the `harbor:includeListItems` tag.  If your list items themselves implement
the `RenderableItem` interface then you need not include a `script` or `itemVar` attribute, the tag will simply use the
`render` method exposed by `RenderableItem` to produce a rendering of the item.

### List APIs and the Component Plugin

Using these APIs, new List components are made of a composition of a `ListConstructionStrategy` and a `ListRenderingStrategy`.  This
composition approach also allows for the creation of an authoring dialog via composition.  By attributing a `@DialogFieldSet` annotation
to both the `ListConstructionStrategy` and the `ListRenderingStrategy` and further annotating the members of these strategies with
appropriate `@DialogField` annotations, both your component and your authoring can be composed.  The
`com.citytechinc.cq.harbor.proper.core.components.content.navigation.bootstrapnavigation.mainnavigation.BootstrapMainAutoNavigation`
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